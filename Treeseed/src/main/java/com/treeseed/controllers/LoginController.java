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
import com.treeseed.ejb.Usuario;
import com.treeseed.services.LoginServiceInterface;


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
		
		Usuario loggedUser = loginService.checkUser(lr);
		
		LoginResponse response = new LoginResponse();
		HttpSession currentSession = request.getSession();
		
		if(loggedUser == null){
			response.setCode(401);
			response.setErrorMessage("Unauthorized User");
		}else{
			
			
			response.setCode(200);
			response.setCodeMessage("User authorized");
			
			//CREATE AND SET THE VALUES FOR THE CONTRACT OBJECT
			response.setIdUsuario(loggedUser.getIdUsuario());
			response.setFirstName(loggedUser.getFirstname());
			response.setLastName(loggedUser.getLastname());
			//
			
			currentSession.setAttribute("idUser", loggedUser.getIdUsuario());
		}
		
		return response;
		
	}
}
