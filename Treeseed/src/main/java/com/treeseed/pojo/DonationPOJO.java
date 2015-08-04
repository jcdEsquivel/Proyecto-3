package com.treeseed.pojo;

import java.sql.Date;

public class DonationPOJO {
	
	private int id;
	private int campaignId;
	private int nonProfitId;
	private double amount;
	private String nonprofitName;
	private Date donationDate;
	private String dateS;
	private NonprofitPOJO nonprofit;
	
	
	public String getNonprofitName() {
		return nonprofitName;
	}
	public void setNonprofitName(String nonprofitName) {
		this.nonprofitName = nonprofitName;
	}
	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}
	public void setNonprofit(NonprofitPOJO nonprofit) {
		this.nonprofit = nonprofit;
	}
	public String getDateS() {
		return dateS;
	}
	public void setDateS(String dateS) {
		this.dateS = dateS;
	}
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
	
	
}
