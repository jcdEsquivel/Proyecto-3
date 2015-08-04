package com.treeseed.pojo;

import java.sql.Date;

import com.treeseed.ejbWrapper.CampaignWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationPOJO.
 */
public class DonationPOJO {
	
	/** The id. */
	private int id;
	
	/** The campaign id. */
	private int campaignId;
	
	/** The non profit id. */
	private int nonProfitId;
	
	/** The amount. */
	private double amount;
	
	/** The donation date. */
	private Date donationDate;
	
	/** The donor id. */
	private int donorId;
	
	/** The donation date s. */
	private String donationDateS;
	
	/** The campaign. */
	private CampaignPOJO campaign;
	
	/** The donor. */
	private DonorPOJO donor;
	
	/**
	 * Gets the donor.
	 *
	 * @return the donor
	 */
	public DonorPOJO getDonor() {
		return donor;
	}
	
	/**
	 * Sets the donor.
	 *
	 * @param donor the new donor
	 */
	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}
	
	/**
	 * Gets the campaign.
	 *
	 * @return the campaign
	 */
	public CampaignPOJO getCampaign() {
		return campaign;
	}
	
	/**
	 * Sets the campaign.
	 *
	 * @param campaign the new campaign
	 */
	public void setCampaign(CampaignPOJO campaign) {
		this.campaign = campaign;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the donation date s.
	 *
	 * @return the donation date s
	 */
	public String getDonationDateS() {
		return donationDateS;
	}
	
	/**
	 * Sets the donation date s.
	 *
	 * @param donationDateS the new donation date s
	 */
	public void setDonationDateS(String donationDateS) {
		this.donationDateS = donationDateS;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Gets the non profit id.
	 *
	 * @return the non profit id
	 */
	public int getNonProfitId() {
		return nonProfitId;
	}
	
	/**
	 * Sets the non profit id.
	 *
	 * @param nonProfitId the new non profit id
	 */
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Gets the donation date.
	 *
	 * @return the donation date
	 */
	public Date getDonationDate() {
		return donationDate;
	}
	
	/**
	 * Sets the donation date.
	 *
	 * @param donationDate the new donation date
	 */
	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}
	
	
}
