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
	private int donorFatherId;
	
	/** The non profit id. */
	private int nonProfitId;
	
	/** The amount. */
	private double amount;
	private int cardId;
	
	
	/** The amount. */
	private String nonprofitName;
	
	/** The donation date. */
	private Date donationDate;
	
	/** The amount. */
	private String dateS;
	
	/** The amount. */
	private NonprofitPOJO nonprofit;
	
	/** The donor id. */
	private int donorId;
	
	/** The donation date s. */
	private String donationDateS;
	
	/** The campaign. */
	private CampaignPOJO campaign;
	
	/** The donor. */
	private DonorPOJO donor;
	
	/**
	 * Gets the donor name.
	 *
	 * @return the donor name
	 */
	public String getNonprofitName() {
		return nonprofitName;
	}

	/**
	 * Sets the non profit name.
	 *
	 * @param non profit name
	 */
	public void setNonprofitName(String nonprofitName) {
		this.nonprofitName = nonprofitName;
	}

	/**
	 * Gets the date string.
	 *
	 * @return the date string
	 */
	public String getDateS() {
		return dateS;
	}
	
	/**
	 * Sets the non profit name.
	 *
	 * @param non profit name
	 */
	public void setDateS(String dateS) {
		this.dateS = dateS;
	}

	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}
	
	/**
	 * Sets the non profit name.
	 *
	 * @param non profit name
	 */
	public void setNonprofit(NonprofitPOJO nonprofit) {
		this.nonprofit = nonprofit;
	}

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
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	public int getDonorFatherId() {
		return donorFatherId;
	}
	public void setDonorFatherId(int donorFatherId) {
		this.donorFatherId = donorFatherId;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	
}
