package com.treeseed.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.UsersResponse;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorService;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.utils.PojoUtils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/donor")
public class DonorController {

	@Autowired
	DonorServiceInterface donorService;
	@Autowired
	CatalogServiceInterface catalogService;
	
	//Codigo comentado para usar como base
	//@Autowired
	//GeneralServiceInterface generalService;
	
	//@Autowired
	//RentServiceInterface rentService;
	
	@Autowired
	HttpServletRequest request;
		
	@RequestMapping(value ="/registerDonor", method = RequestMethod.POST)
	public DonorRequest create(@RequestBody DonorRequest ur){	
		
		DonorRequest us = new DonorRequest();
		
		Catalog cat = new Catalog();
		cat.setActive(true);
		cat.setDescription("test");
		cat.setEnglish("Country");
		cat.setSpanish("Pais");
		cat.setName("Costa Rica");
		cat.setType("Country");
		
		Catalog type = new Catalog();
		type.setActive(true);
		type.setDescription("test");
		type.setEnglish("Donante");
		type.setSpanish("Donante");
		type.setName("Costa Rica");
		type.setType("Person");
		
		catalogService.saveCatalog(cat);
		catalogService.saveCatalog(type);
		
		Donor user = new Donor();
		user.setName(ur.getDonor().getName());
		user.setLastName(ur.getDonor().getLastName());
		user.setProfilePicture(ur.getDonor().getProfilePicture());
		user.setWebPage(ur.getDonor().getWebPage());
		user.setDescription(ur.getDonor().getDescription());
		//user.setCountry(cat);
		//user.setType(type);
		//user.setFather(user);
		
		Boolean state = donorService.saveDonor(user);
		if(state){
			//us.setCode(200);
			//us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}
}
