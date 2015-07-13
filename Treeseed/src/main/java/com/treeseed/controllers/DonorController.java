package com.treeseed.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.contracts.UserGeneralResponse;
import com.treeseed.utils.Utils;
import com.treeseed.ejb.Donor;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;

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
							    @RequestParam(value ="file", required=false) MultipartFile file)
																								{	
		
		
		String resultFileName = "";
		
		DonorResponse us = new DonorResponse();
		
		Boolean alreadyUser=userGeneralService.userExist(email);
		  email = email.toLowerCase();
		  
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
			
				Boolean state = donorService.saveDonor(user);
				if(state){	
				    UserGeneralRequest ug = new UserGeneralRequest();
					UserGeneralResponse ugr = new UserGeneralResponse();
					UserGeneralPOJO userG=new UserGeneralPOJO();
					userG.setEmail(email);
					userG.setPassword(password);
					ug.setUserGeneral(userG);
					ugr= userGeneralCreate(ug,user);
					
					if(ugr.getCode()==200){
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
		
		Donor donor = donorService.getDonorProfileByID(dr);
		
		DonorResponse nps = new DonorResponse();
		
		nps.setCode(200);
		nps.setCodeMessage("nonprofit fetch success");
			
		DonorPOJO donorPOJO = new DonorPOJO();

		donorPOJO.setId(donor.getId());
		donorPOJO.setName(donor.getName());
		donorPOJO.setDescription(donor.getDescription());
		donorPOJO.setWebPage(donor.getWebPage());
		donorPOJO.setProfilePicture(donor.getProfilePicture());
		
		nps.setDonor(donorPOJO);
		nps.setCode(200);
		return nps;	
	}
	
}
