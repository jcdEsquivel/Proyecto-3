package com.treeseed.contracts;

import java.sql.Date;
import com.stripe.net.StripeResponse;
import com.treeseed.pojo.DonationPOJO;

public class DonationRequest {
	
	private DonationPOJO donation;
	
	private int id;
	private int campaignId;
	private int nonProfitId;
	private double amount;
	private Date donationDate;
	private String token;
	private Date startPeriodDate;
	private Date endPeriodDate;
	private int plan;
	
	

	public DonationRequest(){
		super();
	}
	
	public Date getStartPeriodDate() {
		return startPeriodDate;
	}
	
	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public void setStartPeriodDate(Date startPeriodDate) {
		this.startPeriodDate = startPeriodDate;
	}

	public Date getEndPeriodDate() {
		return endPeriodDate;
	}

	public void setEndPeriodDate(Date endPeriodDate) {
		this.endPeriodDate = endPeriodDate;
	}

	public DonationPOJO getDonation() {
		return donation;
	}
	public void setDonation(DonationPOJO donation) {
		this.donation = donation;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
