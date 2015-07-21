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
import com.treeseed.contracts.SessionRequest;
import com.treeseed.contracts.SessionResponse;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.services.LoginServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
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
	UserGeneralServiceInterface userGeneralService;
	
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
		
		UserGeneralWrapper loggedUser = loginService.checkUserActive(lr.getEmail(), file_string);
		
		LoginResponse response = new LoginResponse();
		HttpSession currentSession = request.getSession();
		
		if(loggedUser.getWrapperObject() == null){
			response.setCode(401);
			response.setErrorMessage("Unauthorized User");
		}else{
			
			
			response.setCode(200);
			response.setCodeMessage("User authorized");
			if(loggedUser.getDonor()!=null){
				response.setIdUser(loggedUser.getDonor().getId());
				response.setFirstName(loggedUser.getDonor().getName());
				response.setLastName(loggedUser.getDonor().getLastName());
				response.setImg(loggedUser.getDonor().getProfilePicture());
				response.setType("donor");
			}else if(loggedUser.getNonprofit()!=null){
				response.setIdUser(loggedUser.getNonprofit().getId());
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
	
	
	@RequestMapping(value = "/getSession", method = RequestMethod.POST)
	@Transactional
	public SessionResponse getSession(@RequestBody SessionRequest sr){
		
		UserGeneral user = userGeneralService.getSessionUserGeneral(sr.getId());
		SessionResponse response = new SessionResponse();
		
		HttpSession currentSession = request.getSession();
		int tempId= (int) currentSession.getAttribute("idUser");
		
		if(tempId==user.getId()){
			response.setIdSession(user.getId());
			if(user.getNonprofit()!=null){
				response.setType("nonprofit");
				response.setIdUserType(user.getNonprofit().getId());
			}else{
				response.setType("donor");
				response.setIdUserType(user.getDonor().getId());
			}
			
			response.setCode(200);
			response.setCodeMessage("Authorized");
			
		}else{
			response.setType("guest");
			response.setIdUserType(0);
			response.setIdSession(0);
			response.setCode(401);
			response.setErrorMessage("Unauthorized Request");
		}
		
		return response;
		
	}
}
