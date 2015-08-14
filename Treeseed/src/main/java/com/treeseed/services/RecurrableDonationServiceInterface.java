package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import com.treeseed.ejb.Donation;
import com.treeseed.ejb.RecurrableDonation;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface RecurrableDonationServiceInterface.
 */
public interface RecurrableDonationServiceInterface {
	
	/**
	 * Save recurrable donation.
	 *
	 * @param recurrableDonation the recurrable donation
	 * @return the recurrable donation wrapper
	 */
	RecurrableDonationWrapper saveRecurrableDonation(RecurrableDonationWrapper recurrableDonation);
	
	
	
	/**
	 * Gets the recurrable donation.
	 *
	 * @param donorId the donor id
	 * @param nonprofitId the nonprofit id
	 * @param campaignId the campaign id
	 * @return the recurrable donation
	 */
	List<RecurrableDonationWrapper> getRecurrableDonation(int donorId, int nonprofitId, int campaignId);

	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public RecurrableDonationWrapper getById(int id);
	
	/**
	 * Edits the recurrable donation.
	 *
	 * @param donation the donation
	 */
	public void editRecurrableDonation(RecurrableDonationWrapper donation);
	
	
	/**
	 * Gets the recurrable donation from donor.
	 *
	 * @param donorId the donor id
	 * @return the recurrable donation from donor
	 */
	public List<RecurrableDonationWrapper> getRecurrableDonationFromDonor(int donorId);
	
	/**
	 * Gets the recurrable donations by nonprofit.
	 *
	 * @param nonProfitId the non profit id
	 * @param cant the cant
	 * @return the recurrable donations by nonprofit
	 */
	public List<RecurrableDonationWrapper> getRecurrableDonationsByNonprofit(int nonProfitId);
}
