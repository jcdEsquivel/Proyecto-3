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
	 * @param id
	 * @param type as campaign or NGO
	 * @return the recurrable donation
	 */
	List<RecurrableDonationWrapper> getRecurrableDonation(int id, String type);
	
}
