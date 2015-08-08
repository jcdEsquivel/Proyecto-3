package com.treeseed.pojo;

public class RecurrableDonationPOJO {

	
	private int id;	
	private double amount;
	private String stripeId;
	private int donorFatherId;
	private int donorId;
	private int nonProfitId;
	private int campaingId;
	private String date;
	private int planId;
	
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStripeId() {
		return stripeId;
	}
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}
	public int getDonorFatherId() {
		return donorFatherId;
	}
	public void setDonorFatherId(int donorFatherId) {
		this.donorFatherId = donorFatherId;
	}
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	public int getNonProfitId() {
		return nonProfitId;
	}
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	public int getCampaingId() {
		return campaingId;
	}
	public void setCampaingId(int campaingId) {
		this.campaingId = campaingId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

	
}
