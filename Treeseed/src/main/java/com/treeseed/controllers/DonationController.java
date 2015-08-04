package com.treeseed.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.utils.PageWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationController.
 */
@RestController
@RequestMapping(value = "rest/protected/donation")
public class DonationController {
	
	/** The donation service. */
	@Autowired
	NonprofitServiceInterface nonProfitService;
	
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
	
	/**
	 * Gets the donations of a donor.
	 *
	 * @param dr the donation request
	 * @return the donation response
	 */
	@RequestMapping(value ="/getDonationOfDonorPerMonth", method = RequestMethod.POST)
	public DonationResponse getDonationOfDonorPerMonth(@RequestBody DonationRequest dr){	

		DonationPOJO donationPOJO = null;		
		DonationResponse ds = new DonationResponse();
		PageWrapper<DonationWrapper> pageResults = null;
		List<DonationPOJO> donationsPOJO = new ArrayList<DonationPOJO>();

		dr.setPageNumber(dr.getPageNumber()-1);
		
		pageResults = donationService.findDonationsOfDonor(dr);		
		
		ds.setTotalElements(pageResults.getTotalItems());
		
		for(DonationWrapper objeto: pageResults.getResults())
		{
			donationPOJO = new DonationPOJO();
			donationPOJO.setAmount(objeto.getAmount());
			donationPOJO.setCampaignId(objeto.getCampaingId());
			donationPOJO.setNonProfitId(objeto.getNonProfitId());
			donationPOJO.setNonprofitName(nonProfitService.getNonProfitById(objeto.getNonProfitId()).getName());
			donationPOJO.setDateS(new SimpleDateFormat("dd MMMMM yyyy").format(objeto.getDateTime()));
			
			donationsPOJO.add(donationPOJO);
		};

		ds.setDonations(donationsPOJO);
		
		if(donationsPOJO.size()>0){
			ds.setCodeMessage("Transparency reports fetch successfully");
			ds.setCode(200);
		}else{
			ds.setErrorMessage("Transparency reports fetch unsuccessfully");
			ds.setCode(400);
		}
		
		return ds;
	}
}
