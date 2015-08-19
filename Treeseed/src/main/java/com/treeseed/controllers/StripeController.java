package com.treeseed.controllers;




import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.*;
import org.codehaus.jackson.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.Invoice;
import com.stripe.net.StripeResponse;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.utils.StripeUtils;








// TODO: Auto-generated Javadoc
/**
 * The Class StripeController.
 */
@RestController
@RequestMapping(value="rest/stripe/")
public class StripeController {
	
	/** The donation service. */
	@Autowired
	DonationServiceInterface donationService;
	
	/** The campaign service. */
	@Autowired
	CampaignServiceInterface campaignService;
	
	/**
	 * Stripe webhook endpoint.
	 *
	 * @param request the stripe request
	 * @return the stripe response
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	@Transactional(rollbackFor = { AuthenticationException.class, InvalidRequestException.class,
			APIConnectionException.class, CardException.class, APIException.class, StripeException.class,JsonParseException.class , JsonMappingException.class, IOException.class})	
	@RequestMapping(value ="invoice/payment", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public StripeResponse stripeWebhookEndpoint(HttpServletRequest  request) throws JsonParseException, JsonMappingException, IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
	
		StripeResponse response;

		String jsonBody = IOUtils.toString( request.getInputStream());
		Event eventRequest = Event.GSON.fromJson(jsonBody, Event.class);
		Invoice invoice = (Invoice)eventRequest.getData().getObject();
		Charge charge = StripeUtils.getCharge(invoice.getCharge());
		
		if(donationService.getDonationByStripeId(charge.getId()).getWrapperObject()==null){
			DonationWrapper donation = new DonationWrapper();
			Customer customer = StripeUtils.getCustomer(invoice.getCustomer());
			
			String planId = invoice.getLines().getData().get(0).getPlan().getId();
			String[] planIds = planId.split("#");
			
			NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
			String paymentText = n.format(charge.getAmount() / 100.0);
			paymentText = paymentText.replace("$", "");
			
			donation.setAmount(Double.parseDouble(paymentText));
			donation.setDateTime(new Date());
			donation.setDonorId(Integer.parseInt(customer.getDescription()));
			donation.setActive(true);
			donation.setStripeId(charge.getId());
			if(planIds.length>2){
				try {
					CampaignWrapper campaign = campaignService.getCampaignById(Integer.parseInt(planIds[1]));
					donation.setCampaingId(campaign.getId());
					campaign.setAmountCollected(campaign.getAmountCollected() + donation.getAmount());
					campaignService.updateCampaign(campaign);
				} catch (Exception e) {
					throw e;
				}
			}			
			
			try {
				donation.setNonProfitId(Integer.parseInt(planIds[0]));
			} catch (Exception e) {
				throw e;
			}
			
			donationService.saveDonation(donation);
		}
		
		if(jsonBody!=null){
			response = new StripeResponse(200,"OK");
		}else{
			response = new StripeResponse(400,"BAD");
		}
		return response;
	}
		

}
