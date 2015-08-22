package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.utils.PageWrapper;

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
	 * @param month the month
	 * @return the double
	 */
	double findAmountPerMonthOfNonProfit(String month, int nonProfitId);
	
	/**
	 * Find donors per campaign.
	 *
	 * @param campaignId the campaign id
	 * @return the int
	 */
	int findDonorsPerCampaign(int campaignId);
	
	
	/**
	 * Find donors per nonprofit.
	 *
	 * @param id the nonprofit id
	 * @return the int
	 */
	int findDonorsPerNonprofit(int id);
	
	/**
	 * Find money for nonprofit.
	 *
	 * @param id the nonprofit id
	 * @return the double
	 */
	double findNonprofitMoney(int id);
	
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

	/**
	 * Gets the donations of Donor.
	 *
	 * @param dr the donation request
	 * @return the donations
	 */
	PageWrapper<DonationWrapper> findDonationsOfDonor(DonationRequest dr);
	
	/**
	 * Gets the donations.
	 *
	 * @param ur the donation request
	 * @return the donations
	 */
	Page<Donation> getDonations(DonationRequest ur);
	
	/**
	 * Gets the sum donations by donor.
	 *
	 * @param idDonor the id donor
	 * @return the sum donations by donor
	 */
	double getSumDonationsByDonor(int idDonor);

	Page<Donation> getDonationsDonor(DonationRequest ur);

	Page<Donation> getReportDonations(DonationRequest ur);

	/**
	 * Gets the donations by nonprofit.
	 *
	 * @param nonProfitId the non profit id
	 * @return the donations by nonprofit
	 */
	public List<DonationWrapper> getDonationsByNonprofit(int nonProfitId);
}
