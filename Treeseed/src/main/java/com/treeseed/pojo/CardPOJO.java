package com.treeseed.pojo;

import com.treeseed.ejb.Donor;

public class CardPOJO {

	private int id;

	private String stripeId;
	
	private String brand;
	
	private int expMonth;
	
	private int expYear;
	
	private String last4Numbers;
	
	private boolean isActive;
	
	private DonorPOJO donor;
	
	private boolean isDefault;
	
	public String getBrand() {
		return brand;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public String getLast4Numbers() {
		return last4Numbers;
	}

	public void setLast4Numbers(String last4Numbers) {
		this.last4Numbers = last4Numbers;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStripeId() {
		return stripeId;
	}

	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public DonorPOJO getDonor() {
		return donor;
	}

	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}
}
