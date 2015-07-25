package com.treeseed.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.services.DonationServiceInterface;

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
	
	/**
	 * Gets the nonprofits.
	 *
	 * @param dr the dr
	 * @return the nonprofits
	 */
	@RequestMapping(value ="/getDonationOfNonProfitPerMonth", method = RequestMethod.POST)
	public DonationResponse getNonprofits(@RequestBody DonationRequest dr){	
	
		double totalDonations = donationService.findAmountPerMonthOfNonProfit(dr.getNonProfitId(), dr.getStartPeriodDate(), dr.getEndPeriodDate());
		
		DonationResponse ds = new DonationResponse();
		
		DonationPOJO donation = new DonationPOJO();
		donation.setAmount(totalDonations);
		donation.setNonProfitId(dr.getNonProfitId());
		
		ds.setCode(200);
		ds.setCodeMessage("Donations fetch success");
		ds.setDonation(donation);
		return ds;	
	}
}
