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
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/donor")
public class DonorController extends UserGeneralController{
	
	@Autowired
	DonorServiceInterface donorService;
	@Autowired
	CatalogServiceInterface catalogService;
	EmailValidator validator = EmailValidator.getInstance();
	 @Autowired
	 ServletContext servletContext;
	@Autowired
	 UserGeneralServiceInterface userGeneralService;
	@Autowired
	HttpServletRequest request;	
		
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
				   
				CatalogWrapper Countrytype = catalogService.findCatalogById(Integer.parseInt(country));
				CatalogWrapper userType = catalogService.getAllByType("DonorType").get(0);
				
				
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
		
		
		ds.setDonor(viewDonorsPOJO);
		ds.setCode(200);
		return ds;	
	}
	
	@RequestMapping(value ="/getDonorProfile", method = RequestMethod.POST)
	@Transactional
	public DonorResponse getDonorProfile(@RequestBody DonorRequest dr){	
		
		HttpSession currentSession = request.getSession();
		int tempId= 0;
		
		if(dr.getIdUser()!=0){
			tempId= (int) currentSession.getAttribute("idUser");
		}
		
		Donor donor = donorService.getDonorProfileByID(dr);
		
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
	
	@RequestMapping(value ="/editDonor", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public DonorResponse editDonor(@RequestPart(value="data") DonorRequest dr, @RequestPart(value="fileCover", required=false) MultipartFile fileCover,
			@RequestPart(value="fileProfile", required=false) MultipartFile fileProfile){
		
		String profileImageName = null;
		
		DonorResponse us = new DonorResponse();
		DonorPOJO donorPOJO = new DonorPOJO();
		
		UserGeneral ug = new UserGeneral();
		ug = userGeneralService.getUGByID(dr.getIdUser());
		
		if(ug.getEmail().equals(dr.getEmail())){
			
				DonorWrapper donor = new DonorWrapper();
					
				donor.setId(dr.getId());
				donor.setName(dr.getName());
				donor.setLastName(dr.getLastName());
				donor.setDescription(dr.getDescription());
				donor.setWebPage(dr.getWebPage());
				donor.setProfilePicture(dr.getProfileImage());
				
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
					donorPOJO.setProfilePicture(dr.getProfileImage());
					donorPOJO.setWebPage(dr.getWebPage());
					donorPOJO.setUserGeneral(userGeneralPOJO);
					donorPOJO.setId(dr.getId());
					
					us.setDonor(donorPOJO);
					us.setCode(200);
					us.setCodeMessage("Donor updated sucessfully");	
		
			}else{
					us.setCode(400);
					us.setCodeMessage("EMAIL ALREADY IN USE");
				}
			}else{
					us.setCode(400);
					us.setCodeMessage("BAD EMAIL");
				}
		}
		return us;		
	}
	
}
