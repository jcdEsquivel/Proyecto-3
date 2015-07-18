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
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
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

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/nonprofit")
public class NonprofitController extends UserGeneralController{

	@Autowired
	CatalogServiceInterface catalogService;
	EmailValidator validator = EmailValidator.getInstance();
	@Autowired
	 NonprofitServiceInterface nonProfitService;
	 @Autowired
	 ServletContext servletContext;
	@Autowired
	 UserGeneralServiceInterface userGeneralService;
	@Autowired
	HttpServletRequest request;	
		
	
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public NonprofitResponse nonProfitCreate(@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("country") String country,
			@RequestParam("cause") String cause,
			@RequestParam(value ="file", required=false) MultipartFile file){
		String resultFileName = null;
		NonprofitResponse us = new NonprofitResponse();
		Boolean alreadyUser=userGeneralService.userExist(email);
		email = email.toLowerCase();
		
		if(validator.isValid(email)){
			if(!alreadyUser){
		
				CatalogWrapper countryW = catalogService.findCatalogById(Integer.parseInt(country));
				CatalogWrapper causeW = catalogService.findCatalogById(Integer.parseInt(cause));
				
				if(file!=null){
					resultFileName = Utils.writeToFile(file,servletContext);
				}else{
					resultFileName = "resources/file-storage/1436319975812.jpg";
				}
				
				UserGeneralWrapper userGeneral = new UserGeneralWrapper();
				NonprofitWrapper user = new NonprofitWrapper();
				
				if(!resultFileName.equals("")){
					user.setProfilePicture(resultFileName);
				}else{
					user.setProfilePicture("");
				}
				
				user.setName(name);
				
				user.setActive(true);
				user.setCause(causeW.getWrapperObject());
				user.setConutry(countryW.getWrapperObject());
				
				int nonProfitId = nonProfitService.saveNonprofit(user);
			
				if(nonProfitId>0){
					UserGeneralRequest ug = new UserGeneralRequest();
					UserGeneralResponse ugr = new UserGeneralResponse();
					UserGeneralPOJO userG=new UserGeneralPOJO();
					userG.setEmail(email);
					userG.setPassword(password);
					ug.setUserGeneral(userG);
					ugr= userGeneralCreate(ug,user);
					
					if(ugr.getCode()==200){
						us.setNonProfitId(nonProfitId);
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
	public NonprofitResponse getNonprofits(@RequestBody NonprofitRequest npr){	
		
		npr.setPageNumber(npr.getPageNumber() - 1);
		
		Page<Nonprofit> viewNonprofits = nonProfitService.getNonProfit(npr);
		
		NonprofitResponse nps = new NonprofitResponse();
		
		nps.setCode(200);
		nps.setCodeMessage("nonprofits fetch success");
		
		
		nps.setTotalElements(viewNonprofits.getTotalElements());
		nps.setTotalPages(viewNonprofits.getTotalPages());
		
		List<NonprofitPOJO> viewNonprofitsPOJO = new ArrayList<NonprofitPOJO>();
		
		for(Nonprofit objeto:viewNonprofits.getContent())
		{
			NonprofitPOJO nnonprofit = new NonprofitPOJO();
			nnonprofit.setId(objeto.getId());
			nnonprofit.setName(objeto.getName());
			nnonprofit.setDescription(objeto.getDescription());
			nnonprofit.setWebPage(objeto.getWebPage());
			nnonprofit.setProfilePicture(objeto.getProfilePicture());
			viewNonprofitsPOJO.add(nnonprofit);
		};
		
		
		nps.setNonprofits(viewNonprofitsPOJO);
		nps.setCode(200);
		return nps;
			
	}
	
	@RequestMapping(value ="/getNonProfitProfile", method = RequestMethod.POST)
	@Transactional
	public NonprofitResponse getNonProfitProfile(@RequestBody NonprofitRequest npr){	
		
		
		HttpSession currentSession = request.getSession();
		int tempId= 0;
		
		if(npr.getIdUser()!=0){
			tempId= (int) currentSession.getAttribute("idUser");
		}

		Nonprofit nonprofit = nonProfitService.getNonProfitByID(npr);
		
		NonprofitResponse nps = new NonprofitResponse();
		
		if(tempId==nonprofit.getUsergenerals().get(0).getId()){
			nps.setOwner(true);
		}else{
			nps.setOwner(false);
		}
		
		nps.setCode(200);
		nps.setCodeMessage("nonprofit search success");
			
		NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();

		nonprofitPOJO.setId(nonprofit.getId());
		nonprofitPOJO.setName(nonprofit.getName());
		nonprofitPOJO.setDescription(nonprofit.getDescription());
		nonprofitPOJO.setWebPage(nonprofit.getWebPage());
		nonprofitPOJO.setProfilePicture(nonprofit.getProfilePicture());
		nonprofitPOJO.setMainPicture(nonprofit.getMainPicture());
		nonprofitPOJO.setMision(nonprofit.getMision());
		nonprofitPOJO.setReason(nonprofit.getReason());
		
		UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
		UserGeneral userGeneral;
		userGeneral= nonprofit.getUsergenerals().get(0);
		
		userGeneralPOJO.setEmail(userGeneral.getEmail());
		
		nonprofitPOJO.setUserGeneral(userGeneralPOJO);	
		
		nps.setNonprofit(nonprofitPOJO);
		nps.setCode(200);
		return nps;
			
	}
	
	
	@RequestMapping(value ="/editNonProfit", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public NonprofitResponse editNonProfit(@RequestPart(value="data") NonprofitRequest npr, @RequestPart(value="fileCover", required=false) MultipartFile fileCover,
			@RequestPart(value="fileProfile", required=false) MultipartFile fileProfile){
	
		String coverImageName = "";
		String profileImageName = "";
		
		NonprofitResponse us = new NonprofitResponse();
		
		UserGeneral ug = new UserGeneral();
		ug = userGeneralService.getUGByID(npr.getIdUser());
		
		
		if(ug.getEmail().equals(npr.getEmail())){
			
				NonprofitWrapper nonprofit = new NonprofitWrapper();
					
					if(fileCover!=null){
						coverImageName = Utils.writeToFile(fileCover,servletContext);
					}
		
					if(fileProfile!=null){
						profileImageName = Utils.writeToFile(fileProfile,servletContext);
					}
					
					

					if(!coverImageName.equals("")){
						nonprofit.setMainPicture(coverImageName);
					}else{
						nonprofit.setMainPicture(npr.getMainPicture());
					}
					
					if(!profileImageName.equals("")){
						nonprofit.setProfilePicture(profileImageName);
					}else{
						nonprofit.setProfilePicture(npr.getProfilePicture());
					}
					
					nonprofit.setId(npr.getId());
					nonprofit.setName(npr.getName());
					nonprofit.setDescription(npr.getDescription());
					nonprofit.setMision(npr.getMision());
					nonprofit.setReason(npr.getReason());
					nonprofit.setWebPage(npr.getWebPage());
					
					NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();
					
					nonProfitService.updateNonProfit(nonprofit);
					
					Nonprofit nonprofitobject = nonProfitService.getNonProfitById(npr.getId());
					
					
					nonprofitPOJO.setName(nonprofitobject.getName());
					nonprofitPOJO.setDescription(nonprofitobject.getDescription());
					nonprofitPOJO.setMision(nonprofitobject.getMision());
					nonprofitPOJO.setReason(nonprofitobject.getReason());
					nonprofitPOJO.setWebPage(nonprofitobject.getWebPage());
					nonprofitPOJO.setMainPicture(nonprofitobject.getMainPicture());
					nonprofitPOJO.setProfilePicture(nonprofitobject.getProfilePicture());
					
					us.setNonprofit(nonprofitPOJO);
					us.setCode(200);
					us.setCodeMessage("Nonprofit updated sucessfully");
		}else{
			
			Boolean alreadyUser=userGeneralService.userExist(npr.getEmail());
			npr.setEmail(npr.getEmail().toLowerCase());

			if(validator.isValid(npr.getEmail())){
				if(!alreadyUser){
			
					UserGeneralWrapper userGeneral = new UserGeneralWrapper();
					
					userGeneral.setEmail(npr.getEmail());
					userGeneral.setId(npr.getIdUser());
					
					UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
					
					userGeneralService.updateUserGeneral(userGeneral);
					
					userGeneralPOJO.setEmail(userGeneral.getEmail());
					
					
					NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();
					
					nonprofitPOJO.setName(npr.getName());
					nonprofitPOJO.setDescription(npr.getDescription());
					nonprofitPOJO.setMision(npr.getMision());
					nonprofitPOJO.setReason(npr.getReason());
					nonprofitPOJO.setWebPage(npr.getWebPage());
					nonprofitPOJO.setId(npr.getId());
					nonprofitPOJO.setMainPicture(npr.getMainPicture());
					nonprofitPOJO.setProfilePicture(npr.getProfilePicture());
					
					
					us.setNonprofit(nonprofitPOJO);
					
					us.setCode(200);
					us.setCodeMessage("Nonprofit updated sucessfully");	
			
					
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
