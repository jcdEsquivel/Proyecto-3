package com.treeseed.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.repositories.DonationRepository;

@Service
public class DonationService implements DonationServiceInterface{
	
	@Autowired
	DonationRepository donationRepository;

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

	@Override
	public DonationWrapper saveDonation(DonationWrapper donation) {
		DonationWrapper donationSaved = new DonationWrapper(donationRepository.save(donation.getWrapperObject()));
		return donationSaved;
	}

	@Override
	public void updateDonation(DonationWrapper donation) {
		//donationRepository.update(donation.getWrapperObject());
	}
}
