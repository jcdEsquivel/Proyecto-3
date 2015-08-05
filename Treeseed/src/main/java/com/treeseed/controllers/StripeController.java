package com.treeseed.controllers;




import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.*;
import org.codehaus.jackson.JsonParseException;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.net.StripeResponse;








@RestController
@RequestMapping(value="rest/stripe/")
public class StripeController {
	
	
	@RequestMapping(value ="stripe/get", method = RequestMethod.GET)
	public ResponseEntity<String> get(){
	
		ResponseEntity<String> response;

			response= new ResponseEntity<String>("Hola",HttpStatus.OK);
		
		return response;
	}
		
	@RequestMapping(value ="invoice/payment", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public StripeResponse stripeWebhookEndpoint(HttpServletRequest  request) throws JsonParseException, JsonMappingException, IOException{
	
		StripeResponse response;
		String jsonBody = IOUtils.toString( request.getInputStream());
		System.out.println("Hola");
		System.out.println(jsonBody);
		if(jsonBody!=null){
			response = new StripeResponse(200,"OK");
		}else{
			response = new StripeResponse(400,"BAD");
		}
		return response;
	}
		

}
