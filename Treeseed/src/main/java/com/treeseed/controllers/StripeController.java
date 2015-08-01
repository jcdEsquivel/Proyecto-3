package com.treeseed.controllers;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.stripe.model.Event;



@RestController
@RequestMapping(value = "rest/protected/stripe")
public class StripeController {
	
	@RequestMapping(consumes="application/json",
            produces="application/json",
            method=RequestMethod.POST,
            value="webhook/invocespaysucceeded")
	public String stripeWebhookEndpoint(@RequestBody String request){
	
		Event eventRequest = Event.GSON.fromJson(request, Event.class);
		System.out.println(request);
		
		return null;
	}
}
