package com.treeseed.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.LoginRequest;
import com.treeseed.contracts.LoginResponse;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.services.LoginServiceInterface;
import com.treeseed.utils.Utils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "rest/login")
public class LoginController {
	
	@Autowired
	LoginServiceInterface loginService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST)
	@Transactional
	public BaseResponse checkuser(@RequestBody LoginRequest lr){	
		
		byte[] hash = Utils.encryption(lr.getPassword());
		  String file_string="";
		  
		  for(int i = 0; i < hash.length; i++)
		     {
		         file_string += (char)hash[i];
		     }  
		
		UserGeneralWrapper loggedUser = loginService.checkUser(lr.getEmail(), file_string);
		
		LoginResponse response = new LoginResponse();
		HttpSession currentSession = request.getSession();
		
		if(loggedUser == null){
			response.setCode(401);
			response.setErrorMessage("Unauthorized User");
		}else{
			
			
			response.setCode(200);
			response.setCodeMessage("User authorized");
			if(loggedUser.getDonor()!=null){
				response.setIdUsuario(loggedUser.getDonor().getId());
				response.setFirstName(loggedUser.getDonor().getName());
				response.setLastName(loggedUser.getDonor().getLastName());
				response.setImg(loggedUser.getDonor().getProfilePicture());
				response.setType("donor");
			}else if(loggedUser.getNonprofit()!=null){
				response.setIdUsuario(loggedUser.getNonprofit().getId());
				response.setFirstName(loggedUser.getNonprofit().getName());
				response.setImg(loggedUser.getNonprofit().getProfilePicture());
				response.setType("nonprofit");
			}
			response.setIdSession(loggedUser.getId());
			//CREATE AND SET THE VALUES FOR THE CONTRACT OBJECT
			
			
			
			currentSession.setAttribute("idUser", loggedUser.getId());
		}
		
		return response;
		
	}
}
