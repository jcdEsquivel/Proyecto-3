package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Donation;
import com.treeseed.ejb.Nonprofit;

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
	 * Gets the donations.
	 *
	 * @param ur the donation request
	 * @return the donations
	 */
	Page<Donation> getDonations(DonationRequest ur);
}
