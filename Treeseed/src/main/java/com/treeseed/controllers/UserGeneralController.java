package com.treeseed.controllers;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
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

import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.EmailUniqueRequest;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.contracts.UserGeneralResponse;
import com.treeseed.utils.Utils;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.repositories.UserGeneralRepository;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.UserGeneralService;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.utils.PojoUtils;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.ParentUserWrapper;

// TODO: Auto-generated Javadoc
/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/users")
public class UserGeneralController {

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
	 * User general create.
	 *
	 * @param ur the ur
	 * @param user the user
	 * @return the user general response
	 */
	protected UserGeneralResponse userGeneralCreate(@RequestBody UserGeneralRequest ur, ParentUserWrapper user){	
		
		UserGeneralResponse us = new UserGeneralResponse();
		
		try{
		
			UserGeneralWrapper userGeneral = new UserGeneralWrapper();
	
			Date fechaActual = new Date();
			
			userGeneral.setEmail(ur.getUserGeneral().getEmail());
			
			byte[] hash = Utils.encryption(ur.getUserGeneral().getPassword());
			  String file_string="";
			  
			  for(int i = 0; i < hash.length; i++)
			     {
			         file_string += (char)hash[i];
			     }  
			  
			userGeneral.setPassword(file_string);
			userGeneral.setDateTime(fechaActual);
			userGeneral.setIsActive(true);
			
			if(user instanceof NonprofitWrapper){
				NonprofitWrapper userNonprofit = (NonprofitWrapper)user;
				userGeneral.setNonprofit(userNonprofit.getWrapperObject());
			}else{
				DonorWrapper userDonor = (DonorWrapper)user;
				userGeneral.setDonor(userDonor.getWrapperObject());
				if(ur.getUserGeneral().getFacebookId() == null)
				{
					userGeneral.setFacebookID("");
				}
				else
				{
					userGeneral.setFacebookID(ur.getUserGeneral().getFacebookId());
				}
				
				if(ur.getUserGeneral().getFacebookToken() == null)
				{
					userGeneral.setFacebookToken("");
				}
				else
				{
					userGeneral.setFacebookToken(ur.getUserGeneral().getFacebookToken());
				}
			}
			
			Boolean state = userGeneralService.saveUserGeneral(userGeneral);
			if(state){
				us.setCode(200);
				us.setCodeMessage("user created succesfully");
			}else{
				us.setCode(400);
				us.setCodeMessage("general User does not create");
			}
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				us.setCode(10);
				us.setErrorMessage("Data Base error");
			}else{
				us.setCode(500);
			}
		}	
	
		return us;
}
	
		
	/**
	 * Creates the.
	 *
	 * @param email the email
	 * @return the base response
	 */
	@RequestMapping(value ="/isEmailUnique", method = RequestMethod.POST)
		public BaseResponse create(@RequestBody EmailUniqueRequest request){	
	
			BaseResponse response = new BaseResponse();
			
			try{
			
				Boolean isEmailUnique = userGeneralService.isEmailUnique(request.getEmail());
				
				response.setCode(200);
				
				if(isEmailUnique){
					response.setCodeMessage("UNIQUE");
				}else{
					response.setCodeMessage("NOT-UNIQUE");
				}
			
			}catch(Exception e){
				if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
					response.setCode(10);
					response.setErrorMessage("Data Base error");
				}else{
					response.setCode(500);
				}
			}
			return response;
	}
}
