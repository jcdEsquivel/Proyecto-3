package com.treeseed.controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.treeseed.contracts.CatalogResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.contracts.UserGeneralResponse;
import com.treeseed.contracts.UsersResponse;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.ParentUserWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.repositories.CatalogRepository;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorService;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.utils.PojoUtils;
import com.treeseed.utils.Utils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/users")
public class UsersController {

	@Autowired
	DonorServiceInterface donorService;
	@Autowired
	CatalogServiceInterface catalogService;
	
	//@Autowired
	//NonprofitServiceInterface nonProfitService;
	//@Autowirede
	//UserGeneralServiceInterface userGeneralService;
	
	@Autowired
	 NonprofitServiceInterface nonProfitService;
	 @Autowired
	 ServletContext servletContext;
	
	@Autowired
	 UserGeneralServiceInterface userGeneralService;
	
	@Autowired
	HttpServletRequest request;	
		
	@RequestMapping(value ="/registerDonor", method = RequestMethod.POST)
	public DonorResponse create(@RequestParam("name") String name, 
								@RequestParam("lastName") String lastName,
							    @RequestParam("email") String email,
							    @RequestParam("password") String password,
							    @RequestParam("country") String country, 
							    @RequestParam("file") MultipartFile file){	
		
		DonorResponse us = new DonorResponse();
	
		CatalogWrapper Countrytype = catalogService.findCatalogById(Integer.parseInt(country));
		CatalogWrapper userType = catalogService.getAllCatalogByType("DonorType").get(0);
		
		String resultFileName = Utils.writeToFile(file,servletContext);
		
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
		    UserGeneralPOJO userG=new UserGeneralPOJO();
		    userG.setEmail(email);
		    userG.setPassword(password);
		    ug.setUserGeneral(userG);
		    userGeneralCreate(ug, user);
			
			us.setCode(200);
			us.setCodeMessage("Donor registered succesfully");
		}
		return us;
		
	}
	
	@RequestMapping(value ="/registerNonProfit", method = RequestMethod.POST)
	 public NonprofitResponse nonProfitCreate(@RequestParam("name") String name, 
	   @RequestParam("email") String email,
	   @RequestParam("password") String password,
	   @RequestParam("country") String country,
	   @RequestParam("cause") String cause,
	   @RequestParam("file") MultipartFile file){ 
	  
	  NonprofitResponse us = new NonprofitResponse();
	  String resultFileName = Utils.writeToFile(file,servletContext);
	  
	  UserGeneralWrapper userGeneral = new UserGeneralWrapper();
	  NonprofitWrapper user = new NonprofitWrapper();
	  Date fechaActual = new Date();
	  
	  if(!resultFileName.equals("")){
	   user.setName(name);
	   user.setDateTime(fechaActual);
	   user.setActive(true);
	   //user.setCause(cause);
	   //user.setConutry(country);
	   user.setProfilePicture(resultFileName);
	   
	   Boolean state = nonProfitService.saveNonprofit(user);

	   if(state){
	    UserGeneralRequest ug = new UserGeneralRequest();
	    UserGeneralPOJO userG=new UserGeneralPOJO();
	    userG.setEmail(email);
	    userG.setPassword(password);
	    ug.setUserGeneral(userG);
	    userGeneralCreate(ug, user);
	    
	    us.setCode(200);
	    us.setCodeMessage("user created succesfully");
	   }
	  }else{
	   us.setCode(409);
	   us.setErrorMessage("No imagen de perfil");
	  }
	  
	  return us;
	  
	 }
	
	private UserGeneralResponse userGeneralCreate(@RequestBody UserGeneralRequest ur, ParentUserWrapper user){	
		
		UserGeneralResponse us = new UserGeneralResponse();
		
		UserGeneralWrapper userGeneral = new UserGeneralWrapper();
		userGeneral.setEmail(ur.getUserGeneral().getEmail());
		
		byte[] hash = Utils.encryption(ur.getUserGeneral().getPassword());
		  String file_string="";
		  
		  for(int i = 0; i < hash.length; i++)
		     {
		         file_string += (char)hash[i];
		     }  
		  
		userGeneral.setPassword(file_string);
		userGeneral.setIsActive(true);
		
		if(user instanceof NonprofitWrapper){
			NonprofitWrapper userNonprofit = (NonprofitWrapper)user;
			userGeneral.setNonprofit(userNonprofit.getWrapperObject());
		}else{
			DonorWrapper userDonor = (DonorWrapper)user;
			userGeneral.setDonor(userDonor.getWrapperObject());
		}
		
		Boolean state = userGeneralService.saveUserGeneral(userGeneral);
		if(state){
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value ="/getAllCountries", method = RequestMethod.POST)
	public List<CatalogWrapper> getAllCountries(){	
	
		List<CatalogWrapper> list = catalogService.getAllCatalogByType("Country");
		return list;

	}

}
