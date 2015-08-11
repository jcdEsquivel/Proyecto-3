package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.RecurrableDonationPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class RecurrableDonationResponse.
 */
public class RecurrableDonationResponse extends BaseResponse{

	/** The donations. */
	private List<RecurrableDonationPOJO> donations;

	/**
	 * Gets the donations.
	 *
	 * @return the donations
	 */
	public List<RecurrableDonationPOJO> getDonations() {
		return donations;
	}

	/**
	 * Sets the donations.
	 *
	 * @param donations the new donations
	 */
	public void setDonations(List<RecurrableDonationPOJO> donations) {
		this.donations = donations;
	}
	
}
