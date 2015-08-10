package com.treeseed.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.ExceptionEvent;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Card;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;
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
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.ejb.Donation;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.DonorTreePOJO;
import com.treeseed.pojo.NonprofitPOJO;
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

	
	
	
	/** The donation service. */
	@Autowired
	DonorServiceInterface donorService;
	
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
	
	

	/**
	 * Gets the donations per month.
	 * @param dr the donation request
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
	

	/**
	 * Donate.
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
			try {
				donor.getId();
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
				if(dr.getDonation().getDonorFatherId()>0){
					donation.setDonorFatherId(dr.getDonation().getDonorFatherId());
				}

				if (dr.getDonation().getCampaignId() != 0) {
					try {
						CampaignWrapper campaign = campaignService.getCampaignById(dr.getDonation().getCampaignId());
						campaignName = campaign.getName();
						donation.setCampaingId(campaign.getId());
						campaign.setAmountCollected(campaign.getAmountCollected() + donation.getAmount());
						campaignService.updateCampaign(campaign);
					} catch (Exception e) {
						throw e;
					}
					
				}
				donationService.saveDonation(donation);
				
				if(donor.getStripeId() == null){
					resultCharge=StripeUtils.createDonationNewCustomer(donor.getId(),donor.getCompleteName(), donor.getUsergenerals().get(0).getEmail(), dr.getDonation().getAmount(), dr.getToken(), donation.getId(),nonProfit, dr.getDonation().getCampaignId(), campaignName);
					
					CardWrapper card = new CardWrapper();
					
					card.setStripeId((String)resultCharge.get(1));
					card.setActive(true);
					
					donation.setStripeId((String)resultCharge.get(2));
					donor.setStripeId((String)resultCharge.get(0));
					donor.setCards(new ArrayList<Card>());
					donor.addCard(card.getWrapperObject());
					donor.setSubscriptionCard(card.getWrapperObject());
					
					cardService.saveCard(card);
					donorService.updateStripeIdAndSubscriptionCard(donor);
					
				}else{
					
					if(dr.getToken().equals("")){
						CardWrapper card = cardService.getCardByID(dr.getDonation().getCardId());
						cardIdStripe = card.getStripeId();
					}
					
					resultCharge=StripeUtils.createDonation(donor.getStripeId(),dr.getDonation().getAmount(), dr.getToken(), cardIdStripe, donation.getId(),nonProfit, dr.getDonation().getCampaignId(), campaignName);
					donation.setStripeId( (String)resultCharge.get(1));
					
					if(!((String)resultCharge.get(0)).equals("")){
						CardWrapper card = new CardWrapper();
						card.setStripeId((String)resultCharge.get(0));
						card.setDonor(donor.getWrapperObject());
						card.setActive(true);
						donor.addCard(card.getWrapperObject());
						
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
	
	/**
	 * Gets the donations of a donor.
	 *
	 * @param dr the donation request
	 * @return the donation response
	 */
	@RequestMapping(value ="/gettreedonation", method = RequestMethod.POST)
	public DonationResponse getTreeDonation(@RequestBody DonationRequest dr){	
	
		DonationResponse response = new DonationResponse();
	
		try {
			if(dr.getDonorId()>0){
				DonorWrapper donor = donorService.getDonorById(dr.getDonorId());
				response.setTreeDonation(donationService.getSumDonationsByDonor(donor.getId()));
				response.setTreeDonation(response.getTreeDonation()+getTreeDonationSons(donor, dr.getTreeLevelX(), dr.getTreeLevelY()));

				response.setCodeMessage("Correct donation");
				response.setCode(200);
			}else{
				response.setErrorMessage("Donor do not receive");
				response.setCode(400);
			}
		} catch (Exception e) {
			response.setErrorMessage(e.getMessage());
			response.setCode(500);
		}
		
		
		return response;
	}
	
	/**
	 * Gets the tree donation sons.
	 *
	 * @param donor the donor
	 * @param levelX the level x
	 * @param levelY the level y
	 * @return the tree donation sons
	 */
	public double getTreeDonationSons(DonorWrapper donor, int levelX, int levelY){
		double total=0;
		int levelXDo = levelX;
		List<Donor> sonslist = donor.getSons();
		if(sonslist.size()>0){
			int number = 0;
			while(number<sonslist.size()&&levelXDo>0){
				DonorWrapper donorWrapper = new DonorWrapper(sonslist.get(number));
				total=donationService.getSumDonationsByDonor(donorWrapper.getId());
				if(donorWrapper.getSons().size()>0){
					if(levelY>1){
						total+=getTreeDonationSons(donorWrapper,levelX,levelY-1);
					}
				}
				number++;
				levelXDo--;
			}
		}
		return total;
	}
}

