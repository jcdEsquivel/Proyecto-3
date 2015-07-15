package com.treeseed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.GeneralSearchRequest;
import com.treeseed.contracts.GeneralSearchResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.pojo.GeneralSearchResultPOJO;
import com.treeseed.services.GeneralSearchServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/generalSearch")
public class GeneralSearchController {

	@Autowired GeneralSearchServiceInterface searchService;
	
	
	@RequestMapping(value ="/search", method = RequestMethod.POST)
	public GeneralSearchResponse search(@RequestBody GeneralSearchRequest request){
		
		GeneralSearchResponse response = new GeneralSearchResponse();
		
		try{
			
			response.setResults(searchService.search(request.getFilter(), request.getCountry()));
		
			response.setCode(200);
			response.setCodeMessage("Genereal Search");
			
		}catch(Exception ex){
			response.setCode(500);
			response.setErrorMessage("Server error");
		}
		
		
		return response;
	}
	
}
