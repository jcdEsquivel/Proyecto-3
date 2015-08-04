package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.DonationPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationResponse.
 */
public class DonationResponse extends BaseResponse {
	
/** The donations. */
private List<DonationPOJO> donations;

/** The donation. */
private DonationPOJO donation;
	
	/**
	 * Instantiates a new donation response.
	 */
	public DonationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the donations.
	 *
	 * @return the donations
	 */
	public List<DonationPOJO> getDonations() {
		return donations;
	}

	/**
	 * Sets the donations.
	 *
	 * @param donations the new donations
	 */
	public void setDonations(List<DonationPOJO> donations) {
		this.donations = donations;
	}

	/**
	 * Gets the donation.
	 *
	 * @return the donation
	 */
	public DonationPOJO getDonation() {
		return donation;
	}

	/**
	 * Sets the donation.
	 *
	 * @param donation the new donation
	 */
	public void setDonation(DonationPOJO donation) {
		this.donation = donation;
	}	
	
}
