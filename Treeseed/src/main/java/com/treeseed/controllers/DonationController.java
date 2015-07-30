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
import org.springframework.web.bind.annotation.RestController;
import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
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
		Charge stripeResponse;
		DonationWrapper donation = new DonationWrapper();

		if (dr.getDonation().getDonorId() > 0) {
			if (dr.getDonation().getNonProfitId() > 0) {
				NonprofitWrapper nonProfit = nonprofitService.getNonProfitById(dr.getDonation().getNonProfitId());
				DecimalFormat format = new DecimalFormat("0.00");

				Stripe.apiKey = Utils.stripeApiKey();

				String number = format.format(dr.getDonation().getAmount());
				number = number.replace(".", "");
				int numberI = Integer.parseInt(number);

				Map<String, Object> chargeParams = new HashMap<String, Object>();
				chargeParams.put("amount", numberI);
				chargeParams.put("currency", "usd");
				chargeParams.put("source", dr.getToken()); // obtained with
															// Stripe.js

				donation.setAmount(dr.getDonation().getAmount());
				donation.setDateTime(new Date());
				donation.setDonorFatherId(dr.getDonation().getDonorFatherId());
				donation.setDonorId(dr.getDonation().getDonorId());
				donation.setNonProfitId(nonProfit.getId());
				donation.setActive(true);

				if (dr.getDonation().getCampaignId() != 0) {
					CampaignWrapper campaign = campaignService.getCampaignById(dr.getDonation().getCampaignId());
					donation.setCampaingId(campaign.getId());
					campaign.setAmountCollected(campaign.getAmountCollected() + donation.getAmount());
					campaignService.updateCampaign(campaign);
					donationService.saveDonation(donation);
					chargeParams.put("description",
							"Donation #" + donation.getId() + "# Nonprofit #" + nonProfit.getId() + "# Nonprofit Name #"
									+ nonProfit.getName() + "# Campaign #" + campaign.getId() + "# Campaign Name #"
									+ campaign.getName());
				} else {
					donationService.saveDonation(donation);
					chargeParams.put("description", "Donation #" + donation.getId() + "# Nonprofit #"
							+ nonProfit.getId() + "# Nonprofit Name #" + nonProfit.getName());
				}

				stripeResponse = Charge.create(chargeParams);
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
}
