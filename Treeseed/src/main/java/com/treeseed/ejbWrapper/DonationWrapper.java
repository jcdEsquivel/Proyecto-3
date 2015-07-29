package com.treeseed.ejbWrapper;


import java.util.Date;

import com.treeseed.ejb.Donation;


public class DonationWrapper {
	
	private Donation wrapperObject;


	public DonationWrapper(Donation donation) {
		super();
		setWrapperObject(donation);
	}
	
	public DonationWrapper() {
		super();
		setWrapperObject(new Donation());
	}
	
	public int getDonorFatherId() {
		return wrapperObject.getDonorFatherId();
	}

	public void setDonorFatherId(int donorFatherId) {
		wrapperObject.setDonorFatherId(donorFatherId);
	}

	public Donation getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(Donation wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public int getId() {
		return wrapperObject.getId();
	}

	public void setId(int id) {
		wrapperObject.setId(id);
	}

	public double getAmount() {
		return wrapperObject.getAmount();
	}

	public void setAmount(double amount) {
		wrapperObject.setAmount(amount);
	}

	public int getCampaingId() {
		return wrapperObject.getCampaingId();
	}

	public void setCampaingId(int campaingId) {
		wrapperObject.setCampaingId(campaingId);
	}

	public Date getDateTime() {
		return wrapperObject.getDateTime();
	}

	public int getDonorId() {
		return wrapperObject.getDonorId();
	}

	public int getNonProfitId() {
		return wrapperObject.getNonProfitId();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public void setDateTime(Date dateTime) {
		wrapperObject.setDateTime(dateTime);
	}

	public void setDonorId(int donorId) {
		wrapperObject.setDonorId(donorId);
	}

	public void setNonProfitId(int nonProfitId) {
		wrapperObject.setNonProfitId(nonProfitId);
	}

	public boolean isActive() {
		return wrapperObject.isActive();
	}

	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	public String toString() {
		return wrapperObject.toString();
	}

	
}
