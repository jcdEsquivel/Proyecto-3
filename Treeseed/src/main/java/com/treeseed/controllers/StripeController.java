package com.treeseed.controllers;


import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.treeseed.contracts.CardRequest;
import com.treeseed.contracts.CardResponse;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.pojo.CardPOJO;
import com.treeseed.utils.StripeUtils;





@RestController
@RequestMapping(value = "rest")
public class StripeController {
	
	@ResponseBody
	@RequestMapping( value="/stripewebhookendpoint",  method=RequestMethod.POST)
	public String stripeWebhookEndpoint(@RequestBody String request){
	
		//Event eventRequest = Event.GSON.fromJson(request, Event.class);
		System.out.println("Hola");
		System.out.println(request.toString());
		//logger.info();
		
		return null;
	}
	
	@RequestMapping(value ="/stripesubscription", method = RequestMethod.POST)
	public String stripeSubscriptionPaid(String request) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		System.out.println(request);
		return null;
	}	
}
