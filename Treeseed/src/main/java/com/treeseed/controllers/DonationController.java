package com.treeseed.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.ejb.Donation;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.DonorServiceInterface;

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
	
	/** The donation service. */
	@Autowired
	CampaignServiceInterface campaignService;
	
	/** The donation service. */
	@Autowired
	DonorServiceInterface donorService;
	
	/** The servlet context. */
	@Autowired
	ServletContext servletContext;
	
	/** The request. */
	@Autowired
	HttpServletRequest request;
	
	/**
	 * Gets the donations per month.
	 *
	 * @param dr the donation request
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
	 * Gets the donations reports.
	 *
	 * @param drt the Donation Request
	 * @return the nonprofits
	 */
	@RequestMapping(value ="/getDonationReport", method = RequestMethod.POST)
	@Transactional
	public DonationResponse getDonationReport(@RequestBody DonationRequest drt){	
		
		drt.setPageNumber(drt.getPageNumber() - 1);
		
		Page<Donation> viewDonations = donationService.getDonations(drt);
		
		DonationResponse dr = new DonationResponse();
		
		dr.setCodeMessage("Donations fetch success");
		
		
		dr.setTotalElements(viewDonations.getTotalElements());
		dr.setTotalPages(viewDonations.getTotalPages());
		
		List<DonationPOJO> viewDonationsPOJO = new ArrayList<DonationPOJO>();
		CampaignPOJO campaignPOJO = null;
		DonorPOJO donorPOJO = null;
		
		
		for(Donation objeto:viewDonations.getContent())
		{
			DonationPOJO donation = new DonationPOJO();
			
			donation.setId(objeto.getId());
			donation.setAmount(objeto.getAmount());;
			donation.setCampaignId(objeto.getCampaingId());
			donation.setNonProfitId(objeto.getNonProfitId());
			donation.setDonorId(objeto.getDonorId());
			donation.setDonationDateS(new SimpleDateFormat("dd MMMMM yyyy").format(objeto.getDateTime()));
			if(objeto.getCampaingId() != 0){
				campaignPOJO = new CampaignPOJO();
				CampaignWrapper campaignWrapper = new CampaignWrapper();
				campaignWrapper = campaignService.getCampaignById(objeto.getCampaingId());
				campaignPOJO.setName(campaignWrapper.getWrapperObject().getName());
				donation.setCampaign(campaignPOJO);
			}
			
			if(objeto.getDonorId() != 0){
				donorPOJO = new DonorPOJO();
				DonorWrapper donorWrapper = new DonorWrapper();
				donorWrapper = donorService.getDonorById(objeto.getDonorId());
				donorPOJO.setName(donorWrapper.getWrapperObject().getName());
				donation.setDonor(donorPOJO);
			}

			viewDonationsPOJO.add(donation);
		};
		
		
		dr.setDonations(viewDonationsPOJO);
		dr.setCode(200);
		return dr;
			
	}
}
