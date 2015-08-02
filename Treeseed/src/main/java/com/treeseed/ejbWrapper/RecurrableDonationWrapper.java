package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.RecurrableDonation;

public class RecurrableDonationWrapper {

	RecurrableDonation wrapperObject;

	

	public RecurrableDonationWrapper(RecurrableDonation recurrableDonation) {
		super();
		setWrapperObject(recurrableDonation);
	}
	
	public RecurrableDonationWrapper() {
		super();
		setWrapperObject(new RecurrableDonation());
	}

	public RecurrableDonation getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(RecurrableDonation wrapperObject) {
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

	public Date getDateTime() {
		return wrapperObject.getDateTime();
	}

	public void setDateTime(Date dateTime) {
		wrapperObject.setDateTime(dateTime);
	}

	public boolean getIsActive() {
		return wrapperObject.getIsActive();
	}

	public void setIsActive(boolean isActive) {
		wrapperObject.setIsActive(isActive);
	}

	public String getStripeId() {
		return wrapperObject.getStripeId();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}


	public void setStripeId(String stripeId) {
		wrapperObject.setStripeId(stripeId);
	}

	public String toString() {
		return wrapperObject.toString();
	}
	
	public int getDonorId() {
		return wrapperObject.getDonorId();
	}

	public void setDonorId(int donorId) {
		wrapperObject.setDonorId(donorId);
	}

	public int getNonProfitId() {
		return wrapperObject.getNonProfitId();
	}

	public void setNonProfitId(int nonProfitId) {
		wrapperObject.setNonProfitId(nonProfitId);
	}

	public int getCampaingId() {
		return wrapperObject.getCampaingId();
	}

	public void setCampaingId(int campaingId) {
		wrapperObject.setCampaingId(campaingId);
	}

	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	public int getDonorFatherId() {
		return wrapperObject.getDonorFatherId();
	}

	public void setDonorFatherId(int donorFatherId) {
		wrapperObject.setDonorFatherId(donorFatherId);
	}
}
