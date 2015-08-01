package com.treeseed.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.CardServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.DonorService;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
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
@RequestMapping(value = "rest/protected/donation")
public class DonationController {

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

	@Autowired
	NonprofitServiceInterface nonprofitService;
	
	@Autowired
	CardServiceInterface cardService;
	
	@Autowired
	DonorServiceInterface donorService;

	/**
	 * Gets the nonprofits.
	 *
	 * @param dr
	 *            the Donation Request
	 * @return the nonprofits
	 */
	@RequestMapping(value = "/getDonationOfNonProfitPerMonth", method = RequestMethod.POST)
	public DonationResponse getNonprofits(@RequestBody DonationRequest dr) {

		double totalDonations = donationService.findAmountPerMonthOfNonProfit(dr.getNonProfitId(),
				dr.getStartPeriodDate(), dr.getEndPeriodDate());

		DonationResponse ds = new DonationResponse();

		DonationPOJO donation = new DonationPOJO();
		donation.setAmount(totalDonations);
		donation.setNonProfitId(dr.getNonProfitId());

		ds.setCode(200);
		ds.setCodeMessage("Donations fetch success");
		ds.setDonation(donation);
		return ds;
	}
	

	@RequestMapping(value = "/donate", method = RequestMethod.POST)
	@Transactional(rollbackFor = { AuthenticationException.class, InvalidRequestException.class,
			APIConnectionException.class, CardException.class, APIException.class, StripeException.class })
	public DonationResponse donate(@RequestBody DonationRequest dr) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException, StripeException {

		DonationResponse ds = new DonationResponse();
		DonationWrapper donation = new DonationWrapper();
		String campaignName = "";
		String cardIdStripe="";
		ArrayList<Object> resultCharge = new ArrayList<Object>();
		

		if (dr.getDonation().getDonorId() > 0) {
			DonorWrapper donor = donorService.getDonorProfileByID(dr.getDonation().getDonorId());
			if (dr.getDonation().getNonProfitId() > 0) {
				NonprofitWrapper nonProfit = nonprofitService.getNonProfitById(dr.getDonation().getNonProfitId());

				donation.setAmount(dr.getDonation().getAmount());
				donation.setDateTime(new Date());
				donation.setDonorFatherId(dr.getDonation().getDonorFatherId());
				donation.setDonorId(dr.getDonation().getDonorId());
				donation.setNonProfitId(nonProfit.getId());
				donation.setActive(true);

				if (dr.getDonation().getCampaignId() != 0) {
					CampaignWrapper campaign = campaignService.getCampaignById(dr.getDonation().getCampaignId());
					campaignName = campaign.getName();
					donation.setCampaingId(campaign.getId());
					campaign.setAmountCollected(campaign.getAmountCollected() + donation.getAmount());
					campaignService.updateCampaign(campaign);
				}
				donationService.saveDonation(donation);
				
				if(donor.getStripeId().equals("")){
					resultCharge=StripeUtils.createDonationNewCustomer(donor.getCompleteName(), donor.getUsergenerals().get(0).getEmail(), dr.getDonation().getAmount(), dr.getToken(), donation.getId(),nonProfit, dr.getDonation().getCampaignId(), campaignName);
					
					CardWrapper card = new CardWrapper();
					
					card.setStripeId((String)resultCharge.get(1));
					card.setDonor(donor.getWrapperObject());
					
					donation.setStripeId((String)resultCharge.get(2));
					donor.setStripeId((String)resultCharge.get(0));
					
					donorService.update(donor);
					cardService.saveCard(card);
					
				}else{
					
					if(dr.getToken().equals("")){
						CardWrapper card = cardService.getCardByID(dr.getDonation().getCardId());
						cardIdStripe = card.getStripeId();
					}
					
					resultCharge=StripeUtils.createDonation(donor.getStripeId(),dr.getDonation().getAmount(), dr.getToken(), cardIdStripe, donation.getId(),nonProfit, dr.getDonation().getCampaignId(), campaignName);
					donation.setStripeId(((Charge)resultCharge.get(1)).getId());
					
					if(!((String)resultCharge.get(0)).equals("")){
						CardWrapper card = new CardWrapper();
						card.setStripeId((String)resultCharge.get(0));
						card.setDonor(donor.getWrapperObject());
				
						cardService.saveCard(card);
					}
				}
				
				donationService.updateDonation(donation);
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

		return ds;
	}

	/*@RequestMapping(value = "/donateSubscription", method = RequestMethod.POST)
	@Transactional(rollbackFor = { AuthenticationException.class, InvalidRequestException.class,
			APIConnectionException.class, CardException.class, APIException.class, StripeException.class })
	public DonationResponse donateSubscription(@RequestBody DonationRequest dr) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException, StripeException {

		DonationResponse ds = new DonationResponse();
		Charge stripeResponse;
		DonationWrapper donation = new DonationWrapper();
		String campaignName = "";
		DonorRequest donorRequest = new DonorRequest();
		donorRequest.setId(dr.getDonation().getDonorId());

		if (dr.getDonation().getDonorId() > 0) {
			DonorWrapper donor = new donorService.getDonorProfileByID(dr.getDonation().getDonorId());
			if (dr.getDonation().getNonProfitId() > 0) {
				if (dr.getPlan() > 0) {
					NonprofitWrapper nonProfit = nonprofitService.getNonProfitById(dr.getDonation().getNonProfitId());

					donation.setAmount(dr.getDonation().getAmount());
					donation.setDateTime(new Date());
					donation.setDonorFatherId(dr.getDonation().getDonorFatherId());
					donation.setDonorId(dr.getDonation().getDonorId());
					donation.setNonProfitId(nonProfit.getId());
					donation.setActive(true);

					if (dr.getDonation().getCampaignId() != 0) {
						CampaignWrapper campaign = campaignService.getCampaignById(dr.getDonation().getCampaignId());
						campaignName = campaign.getName();
						donation.setCampaingId(campaign.getId());
						campaign.setAmountCollected(campaign.getAmountCollected() + donation.getAmount());
						campaignService.updateCampaign(campaign);
					}
					donationService.saveDonation(donation);
					
					if(donor)
					
					stripeResponse = StripeUtils.createCharge(dr.getDonation().getAmount(), dr.getToken(),
							donation.getId(), dr.getDonation().getNonProfitId(), dr.getDonation().getCampaignId(),
							nonProfit.getName(), campaignName);
					ds.setCodeMessage("Donation Subscription Complete");
					ds.setCode(200);
				} else {
					ds.setErrorMessage("No Plan specified");
					ds.setCode(400);
				}

			} else {
				ds.setErrorMessage("No Nonprofit specified");
				ds.setCode(400);
			}
		} else {
			ds.setErrorMessage("No donor specified");
			ds.setCode(400);
		}

		return ds;
	}*/
}
