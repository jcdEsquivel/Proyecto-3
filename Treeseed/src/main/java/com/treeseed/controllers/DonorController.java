package com.treeseed.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.contracts.UserGeneralResponse;
import com.treeseed.utils.Utils;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.DonorTreePOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;

// TODO: Auto-generated Javadoc
/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/donor")
public class DonorController extends UserGeneralController{
	
	/** The donor service. */
	@Autowired
	DonorServiceInterface donorService;
	
	/** The catalog service. */
	@Autowired
	CatalogServiceInterface catalogService;
	
	/** The validator. */
	EmailValidator validator = EmailValidator.getInstance();
	 
 	/** The servlet context. */
 	@Autowired
	 ServletContext servletContext;
	
	/** The user general service. */
	@Autowired
	 UserGeneralServiceInterface userGeneralService;
	
	/** The request. */
	@Autowired
	HttpServletRequest request;	
		
	/**
	 * Creates the.
	 *
	 * @param name the name
	 * @param lastName the last name
	 * @param email the email
	 * @param password the password
	 * @param country the country
	 * @param facebookId the facebook id
	 * @param facebookToken the facebook token
	 * @param file the file
	 * @return the donor response
	 */
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public DonorResponse create(@RequestParam("name") String name, 
								@RequestParam("lastName") String lastName,
							    @RequestParam("email") String email,
							    @RequestParam("password") String password,
							    @RequestParam("country") String country,
							    @RequestParam("facebookId") String facebookId,
							    @RequestParam("facebookToken") String facebookToken,
							    @RequestParam(value ="file", required=false) MultipartFile file)
																								{	
		
		
		String resultFileName = "";
		CatalogWrapper Countrytype = null;
		CatalogWrapper userType = null;
		
		DonorResponse us = new DonorResponse();
		
		Boolean alreadyUser=userGeneralService.userExist(email);
		  email = email.toLowerCase();
		  
	    Boolean result = userGeneralService.validateFacebookId(facebookId); 
		if (result == true)  
		{
			us.setCode(400);
		    us.setCodeMessage("ID FACEBOOK ALREADY REGISTER");
			return us;
		}
		
	   if(validator.isValid(email)){
		   if(!alreadyUser){
				   if(country.equals("") == false){
					   Countrytype = catalogService.findCatalogById(Integer.parseInt(country));
				   }else{
					   Countrytype = new CatalogWrapper(null);
				   }
				userType = catalogService.getAllByType("DonorType").get(0);
				
				
				if (file == null)
				{
					resultFileName = "resources/file-storage/1436319975812.jpg";
				}
				else
				{
					resultFileName = Utils.writeToFile(file,servletContext);
				}
		
				DonorWrapper user = new DonorWrapper();
				user.setName(name);
				user.setLastName(lastName);
				user.setActive(true);
				user.setProfilePicture(resultFileName);
				user.setCountry(Countrytype.getWrapperObject());
				user.setType(userType.getWrapperObject());
			
				int donorID = donorService.saveDonor(user);
				if(donorID>0){	
				    UserGeneralRequest ug = new UserGeneralRequest();
					UserGeneralResponse ugr = new UserGeneralResponse();
					UserGeneralPOJO userG=new UserGeneralPOJO();
					userG.setEmail(email);
					userG.setPassword(password);
					userG.setFacebookId(facebookId);
					userG.setFacebookToken(facebookToken);
					ug.setUserGeneral(userG);
					ugr= userGeneralCreate(ug,user);
					
					if(ugr.getCode()==200){
						us.setDonorId(donorID);
						us.setCode(200);
						us.setCodeMessage("user created succesfully");
					}else{
						us.setCode(ugr.getCode());
						us.setCodeMessage(ugr.getCodeMessage());
					}
				}	
		   	}else{
			    us.setCode(400);
			    us.setCodeMessage("EMAIL ALREADY IN USE");
			}	   
		}else{
		   us.setCode(400);
		   us.setCodeMessage("BAD EMAIL");
		}
		return us;
		
	}
	
	/**
	 * Gets the donors.
	 *
	 * @param dr the dr
	 * @return the donors
	 */
	@RequestMapping(value ="/advanceGet", method = RequestMethod.POST)
	@Transactional
	public DonorResponse getDonors(@RequestBody DonorRequest dr){	
		
		dr.setPageNumber(dr.getPageNumber() - 1);
		
		Page<Donor> viewDonors = donorService.getAll(dr);
		
		DonorResponse ds = new DonorResponse();
		
		ds.setCode(200);
		ds.setCodeMessage("donors fetch success");
				
		ds.setTotalElements(viewDonors.getTotalElements());
		ds.setTotalPages(viewDonors.getTotalPages());
		
		List<DonorPOJO> viewDonorsPOJO = new ArrayList<DonorPOJO>();
		
		for(Donor objeto:viewDonors.getContent())
		{
			DonorPOJO ndonor = new DonorPOJO();
			ndonor.setId(objeto.getId());
			ndonor.setName(objeto.getName());
			ndonor.setWebPage(objeto.getWebPage());
			ndonor.setLastName(objeto.getLastName());
			ndonor.setProfilePicture(objeto.getProfilePicture());
			ndonor.setDescription(objeto.getDescription());
			viewDonorsPOJO.add(ndonor);
		};
		
		
		ds.setDonors(viewDonorsPOJO);
		ds.setCode(200);
		return ds;	
	}
	
	/**
	 * Gets the donor profile.
	 *
	 * @param dr the dr
	 * @return the donor profile
	 */
	@RequestMapping(value ="/getDonorProfile", method = RequestMethod.POST)
	@Transactional
	public DonorResponse getDonorProfile(@RequestBody DonorRequest dr){	
		
		HttpSession currentSession = request.getSession();
		int tempId= 0;
		
		if(dr.getIdUser()!=0){
			tempId= (int) currentSession.getAttribute("idUser");
		}
		
		DonorWrapper donor = donorService.getDonorProfileByID(dr.getId());
		
		DonorResponse nps = new DonorResponse();
		
		if(tempId==donor.getUsergenerals().get(0).getId()){
			nps.setOwner(true);
		}else{
			nps.setOwner(false);
		}
		
		nps.setCode(200);
		nps.setCodeMessage("nonprofit fetch success");
			
		DonorPOJO donorPOJO = new DonorPOJO();

		donorPOJO.setId(donor.getId());
		donorPOJO.setName(donor.getName());
		donorPOJO.setLastName(donor.getLastName());
		donorPOJO.setDescription(donor.getDescription());
		donorPOJO.setWebPage(donor.getWebPage());
		donorPOJO.setProfilePicture(donor.getProfilePicture());
		
		UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
		UserGeneral userGeneral;
		userGeneral= donor.getUsergenerals().get(0);
		
		userGeneralPOJO.setEmail(userGeneral.getEmail());
		
		donorPOJO.setUserGeneral(userGeneralPOJO);
		
		nps.setDonor(donorPOJO);
		nps.setCode(200);
		return nps;	
	}
	

	@RequestMapping(value ="/getTree", consumes = {"application/json"}, method = RequestMethod.POST)
	public DonorResponse getDonorTree(@RequestBody DonorRequest dr) throws JsonProcessingException{
		
		ObjectMapper mapper = new ObjectMapper();
		DonorResponse response = new DonorResponse();
		DonorWrapper donor = donorService.getDonorById(dr.getId());
		DonorTreePOJO tree = new DonorTreePOJO();
		tree.setIdentity(donor.getId());
		tree.setName(donor.getName());
		tree.setProfilePicture(donor.getProfilePicture());
		tree.setChildren(getTree(donor, dr.getTreeLevelX(), dr.getTreeLevelY()));
		
		response.setTree(tree);
		
		return response;		
	}
	
	/**
	 * Gets the tree.
	 *
	 * @param donor the donor
	 * @param levelX the number of sons per donor
	 * @param levelY the number of level
	 * @return the tree
	 */
	public List<DonorTreePOJO> getTree(DonorWrapper donor, int levelX, int levelY){
		List<DonorTreePOJO> sons=new ArrayList<DonorTreePOJO>();
		int levelXDo = levelX;
		List<Donor> sonslist = donor.getSons();
		if(sonslist.size()>0){
			int number = 0;
			while(number<sonslist.size()&&levelXDo>0){
				DonorWrapper donorWrapper = new DonorWrapper(sonslist.get(number));
				DonorTreePOJO donorPojo = new DonorTreePOJO();
				donorPojo.setIdentity(donorWrapper.getId());
				donorPojo.setName(donorWrapper.getName());
				donorPojo.setProfilePicture(donorWrapper.getProfilePicture());
				if(donorWrapper.getSons().size()>0){
					if(levelY>1){
						donorPojo.setChildren(getTree(donorWrapper,levelX,levelY-1));
					}else{
						donorPojo.setChildren(null);
					}
				}else{
					donorPojo.setChildren(null);
				}
				number++;
				levelXDo--;
				sons.add(donorPojo);
			}
		}
		return sons;
	}
	
	@RequestMapping(value ="/editDonor", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public DonorResponse editDonor(@RequestPart(value="data") DonorRequest dr, @RequestPart(value="fileCover", required=false) MultipartFile fileCover,
			@RequestPart(value="fileProfile", required=false) MultipartFile fileProfile){
		
		String profileImageName = "";
		
		DonorResponse us = new DonorResponse();
		DonorPOJO donorPOJO = new DonorPOJO();
		
		UserGeneral ug = new UserGeneral();
		ug = userGeneralService.getUGByID(dr.getIdUser());
		
		if(ug.getEmail().equals(dr.getEmail())){
			
				DonorWrapper donor = new DonorWrapper();
	
				if(fileProfile!=null){
					profileImageName = Utils.writeToFile(fileProfile,servletContext);
				}

				if(!profileImageName.equals("")){
					donor.setProfilePicture(profileImageName);
				}else{
					donor.setProfilePicture(dr.getProfilePicture());
				}
					
				donor.setId(dr.getId());
				donor.setName(dr.getName());
				donor.setLastName(dr.getLastName());
				donor.setDescription(dr.getDescription());
				donor.setWebPage(dr.getWebPage());
				
				Donor donorobject = new Donor();
				donorPOJO = new DonorPOJO();
				
				donorService.updateDonor(donor);
				
				donorobject= donorService.getSessionDonor(dr.getId());
				
				donorPOJO.setName(donorobject.getName());
				donorPOJO.setLastName(donorobject.getLastName());
				donorPOJO.setDescription(donorobject.getDescription());
				donorPOJO.setProfilePicture(donorobject.getProfilePicture());
				donorPOJO.setWebPage(donorobject.getWebPage());
				donorPOJO.setId(donorobject.getId());
				
				UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
				
				userGeneralPOJO.setEmail(ug.getEmail());
				
				donorPOJO.setUserGeneral(userGeneralPOJO);
				
				us.setDonor(donorPOJO);
				us.setCode(200);
				us.setCodeMessage("Donor updated sucessfully");
		}else{
			
			Boolean alreadyUser=userGeneralService.userExist(dr.getEmail());
			dr.setEmail(dr.getEmail().toLowerCase());

			if(validator.isValid(dr.getEmail())){
				if(!alreadyUser){
			
					UserGeneralWrapper userGeneral = new UserGeneralWrapper();
					userGeneral.setEmail(dr.getEmail());
					userGeneral.setId(dr.getId());
					
					UserGeneral userGeneralobject = new UserGeneral();
					UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
					
					userGeneralService.updateUserGeneral(userGeneral);
					
					userGeneralPOJO.setEmail(userGeneral.getEmail());
					
					donorPOJO.setName(dr.getName());
					donorPOJO.setLastName(dr.getLastName());
					donorPOJO.setDescription(dr.getDescription());
					donorPOJO.setProfilePicture(dr.getProfilePicture());
					donorPOJO.setWebPage(dr.getWebPage());
					donorPOJO.setUserGeneral(userGeneralPOJO);
					donorPOJO.setId(dr.getId());
					
					us.setDonor(donorPOJO);
					us.setCode(200);
					us.setCodeMessage("Donor updated sucessfully");	
		
			}else{
					us.setCode(400);
					us.setCodeMessage("EMAIL ALREADY IN USE");
					UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
					userGeneralPOJO.setEmail(ug.getEmail());
					donorPOJO.setUserGeneral(userGeneralPOJO);
					us.setDonor(donorPOJO);
				}
			}else{
					us.setCode(400);
					us.setCodeMessage("BAD EMAIL");
					UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
					userGeneralPOJO.setEmail(ug.getEmail());
					donorPOJO.setUserGeneral(userGeneralPOJO);
					us.setDonor(donorPOJO);
				}
		}
		return us;		
	}
	
	
	/**
	 * Delete donor.
	 *
	 * @param dr the donor request
	 * @return the donor response
	 */
	@RequestMapping(value ="/deleteDonor", method = RequestMethod.POST)
	@Transactional
	public DonorResponse deleteDonor(@RequestBody DonorRequest dr){
		
		DonorResponse us = new DonorResponse();
		
		try{
			donorService.deleteDonor(dr);
						
		 	UserGeneralWrapper ug =  userGeneralService.getUserByDonorId(dr.getId());
		 	UserGeneralWrapper ugw = new UserGeneralWrapper();
		 	ugw.setId(ug.getId());
		 	userGeneralService.deleteUserGeneral(ugw);

		 	us.setCode(200);
			us.setCodeMessage("Donor deleted sucessfully");
			
		
		}catch(Exception e){
			us.setCode(400);
			us.setCodeMessage("Error Database");
			
		}
	
		return us;		
	}
	
}
