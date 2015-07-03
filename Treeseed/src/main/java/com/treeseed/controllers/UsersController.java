package com.treeseed.controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.treeseed.utils.Utils;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.contracts.UserGeneralResponse;
import com.treeseed.contracts.UsersRequest;
import com.treeseed.contracts.UsersResponse;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.UserGeneralService;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.services.UsersServiceInterface;
import com.treeseed.utils.PojoUtils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/users")
public class UsersController {
	
	@Autowired
	NonprofitServiceInterface nonProfitService;
	@Autowired
	UserGeneralServiceInterface userGeneralService;
	
	//Codigo comentado para usar como base
	/*
	@Autowired
	GeneralServiceInterface generalService;
	
	@Autowired
	RentServiceInterface rentService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	@Transactional
	public UsersResponse getAll(@RequestBody UsersRequest ur){	
		
		ur.setPageNumber(ur.getPageNumber() - 1);
		Page<Usuario> users = usersService.getAll(ur);
		
		UsersResponse us = new UsersResponse();
		
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setTotalElements(users.getTotalElements());
		us.setTotalPages(users.getTotalPages());
		
		
		List<UsuarioPOJO> viewUsers = new ArrayList<UsuarioPOJO>();
		
		users.getContent().forEach(u->{
			UsuarioPOJO nuser = new UsuarioPOJO();
			nuser.setEmail(u.getEmail());
			nuser.setFirstname(u.getFirstname());
			nuser.setIdTipoUsuario(u.getTipoUsuario().getIdTipoUsuario());
			nuser.setIdUsuario(u.getIdUsuario());
			nuser.setLastname(u.getLastname());
			viewUsers.add(nuser);
		});
		
		us.setUsuarios(viewUsers);
		return us;		
	}
	
*/
	@RequestMapping(value ="/nonProfit/create", method = RequestMethod.POST)
	public NonprofitResponse nonProfitCreate(@RequestBody NonprofitRequest ur){	
		
		NonprofitResponse us = new NonprofitResponse();

		UserGeneral userGeneral = new UserGeneral();
		Nonprofit user = new Nonprofit();
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
		

		Boolean state = nonProfitService.saveNonprofit(user);

		if(state){
			UserGeneralRequest ug = new UserGeneralRequest();
			ug.setUserGeneral(ur.getNonprofit().getUserGeneral());
			userGeneralCreate(ug, user);
			
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}
	
	private UserGeneralResponse userGeneralCreate(@RequestBody UserGeneralRequest ur, Nonprofit user){	
		
		UserGeneralResponse us = new UserGeneralResponse();
		
		UserGeneral userGeneral = new UserGeneral();
		List<UserGeneral> generals= new ArrayList<UserGeneral>();
		

		userGeneral.setEmail(ur.getUserGeneral().getEmail());
		userGeneral.setPassword(ur.getUserGeneral().getPassword());
		userGeneral.setIsActive(true);
		
		user.setUsergenerals(generals);
		userGeneral.setNonprofit(user);
		
		Boolean state = userGeneralService.saveUserGeneral(userGeneral);
		if(state){
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}
	/*
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public UsersResponse create(@RequestBody UsersRequest ur){	
		
		UsersResponse us = new UsersResponse();
		TipoUsuario tp = generalService.getTipoUsuarioById(ur.getUser().getIdTipoUsuario());
		
		Usuario user = new Usuario();
		user.setFirstname(ur.getUser().getFirstname());
		user.setLastname(ur.getUser().getLastname());
		user.setEmail(ur.getUser().getEmail());
		user.setPassword("resetPasswordTodo");
		user.setTipoUsuario(tp);
		
		Boolean state = usersService.saveUser(user);
		if(state){
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}*/
}
