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

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.UserGeneralService;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.services.UsersServiceInterface;
import com.treeseed.utils.PojoUtils;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.ParentUserWrapper;

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
	@Autowired
	ServletContext servletContext;
	@Autowired
	CatalogServiceInterface catalogService;
	
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
	@RequestMapping(value ="/registerNonProfit", method = RequestMethod.POST)
	public NonprofitResponse nonProfitCreate(@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("country") String country,
			@RequestParam("cause") String cause,
			@RequestParam("file") MultipartFile file){	
		
		CatalogWrapper countryW = catalogService.findCatalogById(Integer.parseInt(country));
		CatalogWrapper causeW = catalogService.findCatalogById(Integer.parseInt(cause));
		
		NonprofitResponse us = new NonprofitResponse();
		String resultFileName = Utils.writeToFile(file,servletContext);
		
		
		UserGeneralWrapper userGeneral = new UserGeneralWrapper();
		NonprofitWrapper user = new NonprofitWrapper();
		Date fechaActual = new Date();
		
		if(!resultFileName.equals("")){
			user.setProfilePicture(resultFileName);
		}else{
			user.setProfilePicture("");
		}
		
		user.setName(name);
		user.setDateTime(fechaActual);
		user.setActive(true);
		user.setCause(causeW.getWrapperObject());
		user.setConutry(countryW.getWrapperObject());
		
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
		us.setCode(409);
		us.setErrorMessage("No imagen de perfil");
		
		return us;
		
	}
	
	private UserGeneralResponse userGeneralCreate(@RequestBody UserGeneralRequest ur, ParentUserWrapper user){	
		
		UserGeneralResponse us = new UserGeneralResponse();
		
		UserGeneralWrapper userGeneral = new UserGeneralWrapper();
		//List<UserGeneral> generals= new ArrayList<UserGeneral>();
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
			//DonorWrapper userDonor = (DonorWrapper)user;
			//userGeneral.setNonprofit(userDonor.getWrapperObject());
		}
		
		
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
