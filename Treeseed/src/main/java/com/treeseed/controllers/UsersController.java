package com.treeseed.controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

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
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.ParentUserWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.repositories.CatalogRepository;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorService;
import com.treeseed.services.DonorServiceInterface;
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
	 UserGeneralServiceInterface userGeneralService;
	
	@Autowired
	HttpServletRequest request;	
		
	@RequestMapping(value ="/registerDonor", method = RequestMethod.POST)
	public DonorResponse create(@RequestBody DonorRequest ur){	
		
		DonorResponse us = new DonorResponse();
	
		Catalog type = new Catalog();
		type.setId(2);
		type.setActive(true);
		type.setDescription("Person type donor");
		type.setEnglish("Person");
		type.setSpanish("Persona");
		type.setName("Person");
		type.setType("DonorType");
	
		DonorWrapper user = new DonorWrapper();
		user.setName(ur.getDonor().getName());
		user.setLastName(ur.getDonor().getLastName());
		user.setType(type);
	
		
		Boolean state = donorService.saveDonor(user);
		if(state){
			
			UserGeneralRequest ug = new UserGeneralRequest();
			ug.setUserGeneral(ur.getDonor().getUserGeneral());
			userGeneralCreate(ug,user);
			
			us.setCode(200);
			us.setCodeMessage("Donor registered succesfully");
		}
		return us;
		
	}
	
	
	@RequestMapping(value ="/nonProfit/create", method = RequestMethod.POST)
	public NonprofitResponse nonProfitCreate(@RequestBody NonprofitRequest ur){	
		
		NonprofitResponse us = new NonprofitResponse();

		UserGeneralWrapper userGeneral = new UserGeneralWrapper();
		NonprofitWrapper user = new NonprofitWrapper();
		Date fechaActual = new Date();
		
		user.setName(ur.getNonprofit().getName());
		user.setDescription(ur.getNonprofit().getDescription());
		user.setDateTime(fechaActual);
		user.setActive(true);
		user.setMainPicture(ur.getNonprofit().getMainPicture());
		user.setMision(ur.getNonprofit().getMision());
		user.setBanKAccount(ur.getNonprofit().getBanKAccount());
		user.setProfilePicture(ur.getNonprofit().getProfilePicture());
		user.setReason(ur.getNonprofit().getReason());
		user.setWebPage(ur.getNonprofit().getWebPage());
		

		/*Boolean state = nonProfitService.saveNonprofit(user);
		if(state){
			UserGeneralRequest ug = new UserGeneralRequest();
			ug.setUserGeneral(ur.getNonprofit().getUserGeneral());
			userGeneralCreate(ug, user);
			
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}*/
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
	public List<Catalog> getAllCountries(){	
	
		List<Catalog> list = jdbcTemplate.query(
                "SELECT id, name FROM catalog WHERE type = ?", new Object[] { "Country" },
                (rs, rowNum) -> new Catalog(rs.getInt("id"), rs.getString("name"))
        );

		return list;

	}
	
	
}
