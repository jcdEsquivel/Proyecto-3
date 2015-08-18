package com.treeseed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.RecurrableInformationRequest;
import com.treeseed.contracts.RecurrableInformationResponse;
import com.treeseed.services.RecurrableInformationServiceInterface;

/**
 * The Class RecurrableInformationController.
 */
@RestController
@RequestMapping(value ="rest/protected/recurrableInformation")
public class RecurrableInformationController {
	
	/** The recurrableInformation service. */
	@Autowired 
	RecurrableInformationServiceInterface recurrableInformationService;
	
	
	/**
	 * Gets the recurrable Information of a donor.
	 *
	 * @param request the request
	 * @return the Recurrable Information response
	 */
	@RequestMapping(value ="/getRecurrableInformation", method = RequestMethod.POST)
	public RecurrableInformationResponse getRecurrableInformation(@RequestBody RecurrableInformationRequest request){
		
		RecurrableInformationResponse response = new RecurrableInformationResponse();
		
		try{
			
			response.setResults(recurrableInformationService.getRecurrableInformation(request.getDonorId()));
		
			response.setCode(200);
			response.setCodeMessage("Recurrable Information Fetched correctly");
			
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
