package com.treeseed.pojo;

import java.sql.Date;

public class DonationPOJO {
	
	private int id;
	private int campaignId;
	private int donorId;
	private int donorFatherId;
	private int nonProfitId;
	private double amount;
	private Date donationDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public int getNonProfitId() {
		return nonProfitId;
	}
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDonationDate() {
		return donationDate;
	}
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
	
	
}
