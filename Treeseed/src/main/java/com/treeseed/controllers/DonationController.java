package com.treeseed.controllers;

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
import com.treeseed.ejb.Nonprofit;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.NonprofitPOJO;
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
	
	
	/**
	 * Gets the nonprofits.
	 *
	 * @param npr the nonProfitrequest
	 * @return the nonprofits
	 */
	@RequestMapping(value ="/getSingleDonations", method = RequestMethod.POST)
	@Transactional
	public NonprofitResponse getNonprofits(@RequestBody NonprofitRequest npr){	
		
		npr.setPageNumber(npr.getPageNumber() - 1);
		
		Page<Nonprofit> viewNonprofits = nonProfitService.getNonProfit(npr);
		
		NonprofitResponse nps = new NonprofitResponse();
		
		
		nps.setCodeMessage("nonprofits fetch success");
		
		
		nps.setTotalElements(viewNonprofits.getTotalElements());
		nps.setTotalPages(viewNonprofits.getTotalPages());
		
		List<NonprofitPOJO> viewNonprofitsPOJO = new ArrayList<NonprofitPOJO>();
		
		for(Nonprofit objeto:viewNonprofits.getContent())
		{
			NonprofitPOJO nnonprofit = new NonprofitPOJO();
			nnonprofit.setId(objeto.getId());
			nnonprofit.setName(objeto.getName());
			nnonprofit.setDescription(objeto.getDescription());
			nnonprofit.setWebPage(objeto.getWebPage());
			nnonprofit.setProfilePicture(objeto.getProfilePicture());
			viewNonprofitsPOJO.add(nnonprofit);
		};
		
		
		nps.setNonprofits(viewNonprofitsPOJO);
		nps.setCode(200);
		return nps;
			
	}
}
