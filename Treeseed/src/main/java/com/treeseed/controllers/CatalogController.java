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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.treeseed.utils.Utils;
import com.treeseed.contracts.CatalogRequest;
import com.treeseed.contracts.CatalogResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.pojo.CatalogPOJO;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.utils.PojoUtils;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/catalog")
public class CatalogController {
	
	
	@Autowired
	CatalogServiceInterface catalogService;
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value ="/getAllCatalog", method = RequestMethod.POST)
	public CatalogResponse getCatalogByType(@RequestBody String type){
		
		CatalogResponse us = new CatalogResponse();
		
		List<CatalogWrapper> list = catalogService.getAllByType(type);
		
		List<CatalogPOJO> viewCatalogPOJO = new ArrayList<CatalogPOJO>();
		  
		for(CatalogWrapper objeto:list)
		  {
			  CatalogPOJO catalog = new CatalogPOJO();
			  catalog.setId(objeto.getId());
			  catalog.setName(objeto.getName());
			  viewCatalogPOJO.add(catalog);
		  };
		
		us.setCatalogs(viewCatalogPOJO);
		return us;
	}
	
	public CatalogWrapper getCatalogById(int id){	
		CatalogWrapper us = catalogService.findCatalogById(id);
		return us;
	}
	
}



