package com.treeseed.controllers;




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
import com.stripe.model.Event;






@RestController
@RequestMapping(value = "rest/protected/stripe")
public class StripeController {
	
	
	/*@RequestMapping( value="/stripewebhookendpoint",  method=RequestMethod.POST)
	public String stripeWebhookEndpoint(@RequestBody String request){
	
		//Event eventRequest = Event.GSON.fromJson(request, Event.class);
		System.out.println("Hola");
		System.out.println(request.toString());
		//logger.info();
		
		return null;
	}*/
	
	@RequestMapping(value ="stripesubscription", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public @ResponseBody String stripeSubscriptionPaid(Event request) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{

		System.out.println(request.toString());
		return null;
	}	
}
