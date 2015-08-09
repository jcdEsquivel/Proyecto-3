package com.treeseed.contracts;


// TODO: Auto-generated Javadoc
/**
 * The Class RecurrableDonationRequest.
 */
public class RecurrableDonationRequest extends BasePagingRequest{
	
	/** The nonprofit id. */
	private int nonprofitId;
	
	/** The campaign id. */
	private int campaignId;
	
	/** The donor id. */
	private int donorId;
	
	/** The stripe id. */
	private String stripeId;
	
	/** The donation id. */
	private int donationId;
	
	/** The plan id. */
	private int planId;
	
	
	
	
	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	/**
	 * Gets the donation id.
	 *
	 * @return the donation id
	 */
	public int getDonationId() {
		return donationId;
	}

	/**
	 * Sets the donation id.
	 *
	 * @param donationId the new donation id
	 */
	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}

	/**
	 * Gets the nonprofit id.
	 *
	 * @return the nonprofit id
	 */
	public int getNonprofitId() {
		return nonprofitId;
	}
	
	/**
	 * Sets the nonprofit id.
	 *
	 * @param nonprofitId the new nonprofit id
	 */
	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}
	
	/**
	 * Gets the campaign id.
	 *
	 * @return the campaign id
	 */
	public int getCampaignId() {
		return campaignId;
	}
	
	/**
	 * Sets the campaign id.
	 *
	 * @param campaignId the new campaign id
	 */
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	
	/**
	 * Gets the donor id.
	 *
	 * @return the donor id
	 */
	public int getDonorId() {
		return donorId;
	}
	
	/**
	 * Sets the donor id.
	 *
	 * @param donorId the new donor id
	 */
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	
	/**
	 * Gets the stripe id.
	 *
	 * @return the stripe id
	 */
	public String getStripeId() {
		return stripeId;
	}
	
	/**
	 * Sets the stripe id.
	 *
	 * @param stripeId the new stripe id
	 */
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}
	
	
}
