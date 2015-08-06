package com.treeseed.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;
import com.treeseed.repositories.DonationRepository;
import com.treeseed.repositories.RecurrableDonationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationService.
 */
@Service
public class DonationService implements DonationServiceInterface{
	
	/** The donation repository. */
	@Autowired
	DonationRepository donationRepository;

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#findAmountPerMonthOfNonProfit(int, java.sql.Date, java.sql.Date)
	 */
	@Override
	@Transactional
	public double findAmountPerMonthOfNonProfit(int nonProfitId,
			Date startDate, Date endDate) {
		return donationRepository.findAmountPerMonthOfNonProfit(nonProfitId, startDate, endDate);
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#findDonorsPerCampaign(int)
	 */
	@Override
	public int findDonorsPerCampaign(int campaignId) {

		return donationRepository.countDistincDonorIdByCampaingId(campaignId);
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#saveDonation(com.treeseed.ejbWrapper.DonationWrapper)
	 */
	@Override
	public DonationWrapper saveDonation(DonationWrapper donation) {
		DonationWrapper donationSaved = new DonationWrapper(donationRepository.save(donation.getWrapperObject()));
		return donationSaved;
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#updateDonation(com.treeseed.ejbWrapper.DonationWrapper)
	 */
	@Override
	public void updateDonation(DonationWrapper donation) {
		donationRepository.update(donation.getId(),donation.getStripeId());	
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#getDonationByStripeId(java.lang.String)
	 */
	@Override
	public DonationWrapper getDonationByStripeId(String stripeId) {
		return new DonationWrapper(donationRepository.findByStripeId(stripeId));	
	}
}