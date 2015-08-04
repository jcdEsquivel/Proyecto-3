package com.treeseed.pojo;

import java.sql.Date;

import com.treeseed.ejbWrapper.CampaignWrapper;

public class DonationPOJO {
	
	private int id;
	private int campaignId;
	private int nonProfitId;
	private double amount;
	private Date donationDate;
	private int donorId;
	private String donationDateS;
	private CampaignPOJO campaign;
	private DonorPOJO donor;
	
	public DonorPOJO getDonor() {
		return donor;
	}
	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}
	public CampaignPOJO getCampaign() {
		return campaign;
	}
	public void setCampaign(CampaignPOJO campaign) {
		this.campaign = campaign;
	}
	public int getId() {
		return id;
	}
	public String getDonationDateS() {
		return donationDateS;
	}
	public void setDonationDateS(String donationDateS) {
		this.donationDateS = donationDateS;
	}
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
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
