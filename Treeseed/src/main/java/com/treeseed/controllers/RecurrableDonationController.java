package com.treeseed.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.ExceptionEvent;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.contracts.RecurrableDonationRequest;
import com.treeseed.contracts.RecurrableDonationResponse;
import com.treeseed.ejb.Card;
import com.treeseed.ejb.RecurrableDonation;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.RecurrableDonationPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.CardServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.DonorService;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.RecurrableDonationServiceInterface;
import com.treeseed.utils.StripeUtils;
import com.treeseed.utils.Utils;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.net.StripeResponse;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationController.
 */
@RestController
@RequestMapping(value = "rest/protected/recurrableDonation")
public class RecurrableDonationController {

	/** The donation service. */
	@Autowired
	DonationServiceInterface donationService;

	/** The servlet context. */
	@Autowired
	ServletContext servletContext;

	/** The request. */
	@Autowired
	HttpServletRequest request;

	/** The campaign service. */
	@Autowired
	CampaignServiceInterface campaignService;

	/** The nonprofit service. */
	@Autowired
	NonprofitServiceInterface nonprofitService;

	/** The card service. */
	@Autowired
	CardServiceInterface cardService;

	/** The donor service. */
	@Autowired
	DonorServiceInterface donorService;

	/** The recurrable donation service. */
	@Autowired
	RecurrableDonationServiceInterface recurrableDonationService;

	/**
	 * Donate subscription.
	 *
	 * @param dr the Donation Request
	 * @return the donation response
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 * @throws StripeException the stripe exception
	 */
	@RequestMapping(value = "/subscription", method = RequestMethod.POST)
	@Transactional(rollbackFor = { AuthenticationException.class, InvalidRequestException.class,
			APIConnectionException.class, CardException.class, APIException.class, StripeException.class })
	public DonationResponse donateSubscription(@RequestBody DonationRequest dr) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException, StripeException {

		DonationResponse ds = new DonationResponse();
		
		try{
		
			RecurrableDonationWrapper donation = new RecurrableDonationWrapper();
			String cardIdStripe = "";
			ArrayList<Object> resultCharge = new ArrayList<Object>();
			boolean sameCard=false;
	
			if (dr.getDonation().getDonorId() > 0) {
				DonorWrapper donor = donorService.getDonorProfileByID(dr.getDonation().getDonorId());
				try {
					donation.setDonorId(donor.getId());
				} catch (Exception e) {
					throw e;
				}
				
				if (dr.getDonation().getNonProfitId() > 0) {
					NonprofitWrapper nonProfit = nonprofitService.getNonProfitById(dr.getDonation().getNonProfitId());
	
					try {
						donation.setNonProfitId(nonProfit.getId());
					} catch (Exception e) {
						throw e;
					}
					donation.setAmount(dr.getDonation().getAmount());
					donation.setDateTime(new Date());
					donation.setDonorFatherId(dr.getDonation().getDonorFatherId());
					donation.setDonorId(dr.getDonation().getDonorId());
					donation.setActive(true);
					donation.setAmount(getPlanAmount(dr.getPlan()));
	
					if (dr.getDonation().getCampaignId() >0) {
						CampaignWrapper campaign = campaignService.getCampaignById(dr.getDonation().getCampaignId());
						try {
							donation.setCampaingId(campaign.getId());
							campaignService.updateCampaign(campaign);
						} catch (Exception e) {
							throw e;
						}
					}
	
					if (donor.getStripeId()==null) {
						resultCharge = StripeUtils.createRecurrableDonationNewCustomer(donor.getId(),donor.getCompleteName(),
								donor.getUsergenerals().get(0).getEmail(), dr.getPlan(), dr.getToken(),
								nonProfit, dr.getDonation().getCampaignId());
	
						CardWrapper card = new CardWrapper();
	
						card.setStripeId((String) resultCharge.get(1));
	
						donation.setStripeId((String) resultCharge.get(2));
						donor.setStripeId((String) resultCharge.get(0));
						donor.setSubscriptionCard(card.getWrapperObject());
						donor.addCard(card.getWrapperObject());
						donor.setSubscriptionCard(card.getWrapperObject());
	
						cardService.saveCard(card);
						donorService.updateStripeIdAndSubscriptionCard(donor);
					} else {
	
						if (dr.getToken().equals("")) {
							CardWrapper card = cardService.getCardByID(dr.getDonation().getCardId());
							cardIdStripe = card.getStripeId();
							if(donor.getSubscriptionCard().getStripeId().equals(cardIdStripe)){
								sameCard=true;
							}
						}
	
						resultCharge = StripeUtils.createRecurrableDonation(donor.getStripeId(), dr.getPlan(),
								dr.getToken(), cardIdStripe, nonProfit, dr.getDonation().getCampaignId(), sameCard);
						donation.setStripeId( (String)resultCharge.get(1));
	
						if (!((String) resultCharge.get(0)).equals("")) {
							CardWrapper card = new CardWrapper();
							card.setStripeId((String) resultCharge.get(0));
							card.setDonor(donor.getWrapperObject());
							card.setActive(true);
							donor.addCard(card.getWrapperObject());
							donor.setSubscriptionCard(card.getWrapperObject());
							
							cardService.saveCard(card);
							
						}
						if(!sameCard){
							donorService.updateSubscriptionCard(donor);
						}
					}
	
					recurrableDonationService.saveRecurrableDonation(donation);
					
					ds.setCodeMessage("Donation Complete");
					ds.setCode(200);
	
				} else {
					ds.setErrorMessage("No Nonprofit specified");
					ds.setCode(400);
				}
			} else {
				ds.setErrorMessage("No donor specified");
				ds.setCode(400);
			}
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				ds.setCode(10);
				ds.setErrorMessage("Data Base error");
			}else{
				ds.setCode(500);
			}
		}
		return ds;
	}
	

	/**
	 * Gets the plan amount.
	 *
	 * @param plan the plan
	 * @return the plan amount
	 */
	private double getPlanAmount(int plan){

		  double amount=0;
		  
		  switch (plan) {
		  case 1:
		   amount=10;
		   break;
		  case 2:
		   amount=18;
		   break;
		  case 3:
		   amount=36;
		   break;
		  case 4:
		   amount=50;
		   break;
		  case 5:
		   amount=100;
		   break;
		  case 6:
		   amount=250;
		   break;
		  
		  default:
		   break;
		  }
		  
		  return amount;
}
	
	
	/**
	 * Gets the recurrable donations.
	 *
	 * @param request the request
	 * @return the recurrable donations
	 */
	@RequestMapping(value = "/getRecurrableDonations", method = RequestMethod.POST)
	public  RecurrableDonationResponse getRecurrableDonations(@RequestBody RecurrableDonationRequest request){
		
		RecurrableDonationResponse response = new RecurrableDonationResponse();
		try{
		
			List<RecurrableDonationPOJO> donationList = new ArrayList<RecurrableDonationPOJO>();
			RecurrableDonationPOJO pojoTemp = null;
			List<RecurrableDonationWrapper> wrappers = recurrableDonationService
												.getRecurrableDonation(request.getDonorId(),
																		request.getNonprofitId(),
																		request.getCampaignId());
			
			
			for (RecurrableDonationWrapper r : wrappers) {
				pojoTemp = new RecurrableDonationPOJO();
				
				pojoTemp.setId(r.getId());
				pojoTemp.setAmount(r.getAmount());
				pojoTemp.setCampaingId(r.getCampaingId());
				pojoTemp.setDate(r.getDateTime().toString());
				pojoTemp.setId(r.getId());
				pojoTemp.setDonorId(r.getDonorId());
				pojoTemp.setNonProfitId(r.getNonProfitId());
				pojoTemp.setPlanId(r.getPlanId());
				pojoTemp.setStripeId(r.getStripeId());
				
				donationList.add(pojoTemp);
			}
			
			response.setDonations(donationList);
			response.setCode(200);
			response.setCodeMessage("Donations fetch");
		
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
	
	
	/**
	 * Edits the recurrable donation.
	 *
	 * @param request the request
	 * @return the recurrable donation response
	 */
	@RequestMapping(value = "/editRecurrableDonation", method = RequestMethod.POST)
	public  RecurrableDonationResponse editRecurrableDonation(@RequestBody RecurrableDonationRequest request){
	
		RecurrableDonationResponse response = new RecurrableDonationResponse();
		
		try{
			String plan = "";
			NonprofitWrapper nonprofit = nonprofitService.getNonProfitById(request.getNonprofitId());
			DonorWrapper donor = donorService.getDonorById(request.getDonorId());
			RecurrableDonationWrapper donation = recurrableDonationService.getById(request.getDonationId());
			CampaignWrapper campaign = null;
		
			
			if(request.getCampaignId() != 0){//donation is for campaing
				campaign = campaignService.getCampaignById(request.getCampaignId());
				plan = nonprofit.getId()+"#"+request.getCampaignId()+"#"+request.getPlanId();
			}else{
				plan = nonprofit.getId()+"#"+request.getPlanId();
			}

			StripeUtils.editPlan(donor.getStripeId(), donation.getStripeId(), plan);
			
			//update recurrable donation
			donation.setAmount(getPlanAmount(request.getPlanId()));
			recurrableDonationService.editRecurrableDonation(donation);
			
			response.setCode(200);
			response.setCodeMessage("Donation was updated");
			
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
	
	

	/**
	 * Gets the recurrable donations from portfolio.
	 *
	 * @param request the request
	 * @return the recurrable donations from portfolio
	 */
	@RequestMapping(value = "/getRecurrableDonationsPortfolio", method = RequestMethod.POST)
	public  RecurrableDonationResponse getRecurrableDonationsFromPortfolio(@RequestBody RecurrableDonationRequest request){
		
		CampaignWrapper campaign = null;
		NonprofitWrapper nonprofit = null;
		RecurrableDonationResponse response = new RecurrableDonationResponse();
		
		try{
			
			List<RecurrableDonationPOJO> donationList = new ArrayList<RecurrableDonationPOJO>();
			RecurrableDonationPOJO pojoTemp = null;
			List<RecurrableDonationWrapper> wrappers = recurrableDonationService
									.getRecurrableDonationFromDonor(request.getDonorId());
			
			
			for (RecurrableDonationWrapper r : wrappers) {
				pojoTemp = new RecurrableDonationPOJO();
				
				pojoTemp.setId(r.getId());
				pojoTemp.setAmount(r.getAmount());
				pojoTemp.setCampaingId(r.getCampaingId());
				pojoTemp.setDate(r.getDateTime().toString());
				pojoTemp.setId(r.getId());
				pojoTemp.setDonorId(r.getDonorId());
				pojoTemp.setNonProfitId(r.getNonProfitId());
				pojoTemp.setPlanId(r.getPlanId());
				pojoTemp.setStripeId(r.getStripeId());
				pojoTemp.setChanged(false);
				if(r.getCampaingId() > 0){
					//gets campaign and sets name
					campaign = campaignService.getCampaignById(r.getCampaingId());
					pojoTemp.setCampaignName("-"+campaign.getName());
					pojoTemp.setNonprofitName(campaign.getNonprofit().getName());
				}else{
					//gets nonprofit
					nonprofit = nonprofitService.getNonProfitById(r.getNonProfitId());
					pojoTemp.setCampaignName(" ");
					pojoTemp.setNonprofitName(nonprofit.getName());
				}
				
				donationList.add(pojoTemp);
			}
			
			
			
			response.setDonations(donationList);
			response.setCode(200);
			response.setCodeMessage("Donations fetch");
		
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
	
	
	

	/**
	 * Edits the multiple recurrable donation.
	 *
	 * @param requestObj the request obj
	 * @return the recurrable donation response
	 */
	@RequestMapping(value = "/editMultipleRecurrableDonation", method = RequestMethod.POST)
	public  RecurrableDonationResponse editMultipleRecurrableDonation(@RequestBody RecurrableDonationRequest requestObj){
	
		RecurrableDonationResponse response = new RecurrableDonationResponse();
		
		try{
			HttpSession currentSession = request.getSession();
			int sessionId = (int) currentSession.getAttribute("idUser");
			String plan = "";
			
			DonorWrapper donor = donorService.getDonorById(requestObj.getDonorId());
			CampaignWrapper campaign = null;
			RecurrableDonationWrapper donationWrapper = null;
		
			if(sessionId == donor.getUsergenerals().get(0).getId()){
				
				for (RecurrableDonationPOJO pojo : requestObj.getDonations()) {

					if(pojo.getChanged()){//if plan was changed, we update everything from the donation
						
						donationWrapper = recurrableDonationService.getById(pojo.getId());
						
						if(pojo.getCampaingId() != 0){//donation is for campaing
							campaign = campaignService.getCampaignById(requestObj.getCampaignId());
							plan = pojo.getNonProfitId()+"#"+pojo.getCampaingId()+"#"+pojo.getPlanId();
						}else{
							plan = pojo.getNonProfitId()+"#"+pojo.getPlanId();
						}

						StripeUtils.editPlan(donor.getStripeId(), donationWrapper.getStripeId(), plan);
						
						//update recurrable donation
						donationWrapper.setAmount(getPlanAmount(pojo.getPlanId()));
						recurrableDonationService.editRecurrableDonation(donationWrapper);
					}
	
				}
				
				response.setCode(200);
				response.setCodeMessage("Donations were updated");
				
				
			}else{//security problem
				response.setCode(500);
				response.setErrorMessage("Invalid request");
				
				return response;
			}
			
			
			
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



