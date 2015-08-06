package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import com.treeseed.ejb.Donation;
import com.treeseed.ejbWrapper.DonationWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface DonationServiceInterface.
 */
public interface DonationServiceInterface {
	
	/**
	 * Find amount per month of non profit.
	 *
	 * @param nonProfitId the non profit id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the double
	 */
	double findAmountPerMonthOfNonProfit(int nonProfitId, Date startDate, Date endDate);
	
	/**
	 * Find donors per campaign.
	 *
	 * @param campaignId the campaign id
	 * @return the int
	 */
	int findDonorsPerCampaign(int campaignId);
	
	/**
	 * Save donation.
	 *
	 * @param donation the donation
	 * @return the donation wrapper
	 */
	DonationWrapper saveDonation(DonationWrapper donation);
	
	/**
	 * Update donation.
	 *
	 * @param donation the donation
	 */
	void updateDonation(DonationWrapper donation);
	
	/**
	 * Gets the donation by stripe id.
	 *
	 * @param stripeId the stripe id
	 * @return the donation by stripe id
	 */
	DonationWrapper getDonationByStripeId(String stripeId);
	
}
