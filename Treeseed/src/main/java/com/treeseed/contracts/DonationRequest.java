package com.treeseed.contracts;

import java.sql.Date;

import com.treeseed.pojo.DonationPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationRequest.
 */
public class DonationRequest extends BasePagingRequest{
	
	/** The donation. */
	private DonationPOJO donation;
	
	/** The id. */
	private int id;
	
	/** The campaign id. */
	private int campaignId;
	
	/** The non profit id. */
	private int nonProfitId;
	
	/**  The donor id. */
	private int donorId;
	
	/** The amount. */
	private double amount;
	
	/** The donation date. */
	private Date donationDate;	
	
	/** The month. */
	private String month;
	
	/** The year. */
	private String year;
	
	/** The start period date. */
	private Date startPeriodDate;
	
	/** The end period date. */
	private Date endPeriodDate;
	
	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month the new month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * Gets the donor id.
	 *
	 *@return the donor id
	 */
	public int getDonorId() {
		return donorId;
	}
	
	/**
	 * Sets the donor id.
	 *
	 * @param the donor id
	 */
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	/**
	 * Instantiates a new donation request.
	 */
	public DonationRequest(){
		super();
	}
	
	/**
	 * Gets the start period date.
	 *
	 * @return the start period date
	 */
	public Date getStartPeriodDate() {
		return startPeriodDate;
	}

	/**
	 * Sets the start period date.
	 *
	 * @param startPeriodDate the new start period date
	 */
	public void setStartPeriodDate(Date startPeriodDate) {
		this.startPeriodDate = startPeriodDate;
	}

	/**
	 * Gets the end period date.
	 *
	 * @return the end period date
	 */
	public Date getEndPeriodDate() {
		return endPeriodDate;
	}

	/**
	 * Sets the end period date.
	 *
	 * @param endPeriodDate the new end period date
	 */
	public void setEndPeriodDate(Date endPeriodDate) {
		this.endPeriodDate = endPeriodDate;
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
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
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
