package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;

// TODO: Auto-generated Javadoc
/**
 * The Interface DonorServiceInterface.
 */
public interface DonorServiceInterface {

	/**
	 * Gets the all.
	 *
	 * @param ur the ur
	 * @return the all
	 */
	Page<Donor> getAll(DonorRequest ur);

	/**
	 * Save donor.
	 *
	 * @param user the user
	 * @return the int
	 */
	int saveDonor(DonorWrapper user);

	/**
	 * Gets the session donor.
	 *
	 * @param idUser the id user
	 * @return the session donor
	 */
	Donor getSessionDonor(int idUser);
	
	/**
	 * Gets the donor profile by id.
	 *
	 * @param id the id
	 * @return the donor profile by id
	 */
	DonorWrapper getDonorProfileByID(int id);
	
	/**
	 * Delete donor.
	 *
	 * @param dr the dr
	 */
	void deleteDonor(DonorRequest dr);
	
	/**
	 * Update donor.
	 *
	 * @param donor the donor
	 */
	void updateDonor(DonorWrapper donor);
	
	/**
	 * Update stripe id.
	 *
	 * @param donor the donor
	 */
	void updateStripeId(DonorWrapper donor);
	
	/**
	 * Update subscription card.
	 *
	 * @param donor the donor
	 */
	void updateSubscriptionCard(DonorWrapper donor);
	
	/**
	 * Update stripe id and subscription card.
	 *
	 * @param donor the donor
	 */
	void updateStripeIdAndSubscriptionCard(DonorWrapper donor);
}
