package com.treeseed.ejbWrapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.treeseed.ejb.Donation;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.DonorServiceInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationWrapper.
 */
public class DonationWrapper {

	/** The wrapper object. */
	private Donation wrapperObject;


	/**
	 * Gets the stripe id.
	 *
	 * @return the stripe id
	 */
	public String getStripeId() {
		return wrapperObject.getStripeId();
	}

	/**
	 * Sets the stripe id.
	 *
	 * @param stripeId
	 *            the new stripe id
	 */
	public void setStripeId(String stripeId) {
		wrapperObject.setStripeId(stripeId);
	}

	/**
	 * Instantiates a new donation wrapper.
	 *
	 * @param donation
	 *            the donation
	 */
	public DonationWrapper(Donation donation) {
		super();
		setWrapperObject(donation);
	}

	/**
	 * Instantiates a new donation wrapper.
	 */
	public DonationWrapper() {
		super();
		setWrapperObject(new Donation());
	}

	/**
	 * Gets the donor father id.
	 *
	 * @return the donor father id
	 */
	public int getDonorFatherId() {
		return wrapperObject.getDonorFatherId();
	}

	/**
	 * Sets the donor father id.
	 *
	 * @param donorFatherId
	 *            the new donor father id
	 */
	public void setDonorFatherId(int donorFatherId) {
		wrapperObject.setDonorFatherId(donorFatherId);
	}

	/**
	 * Gets the wrapper object.
	 *
	 * @return the wrapper object
	 */
	public Donation getWrapperObject() {
		return wrapperObject;
	}

	/**
	 * Sets the wrapper object.
	 *
	 * @param wrapperObject
	 *            the new wrapper object
	 */
	public void setWrapperObject(Donation wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return wrapperObject.getId();
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		wrapperObject.setId(id);
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return wrapperObject.getAmount();
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(double amount) {
		wrapperObject.setAmount(amount);
	}

	/**
	 * Gets the campaing id.
	 *
	 * @return the campaing id
	 */
	public int getCampaingId() {
		return wrapperObject.getCampaingId();
	}

	/**
	 * Sets the campaing id.
	 *
	 * @param campaingId
	 *            the new campaing id
	 */
	public void setCampaingId(int campaingId) {
		wrapperObject.setCampaingId(campaingId);
	}

	/**
	 * Gets the date time.
	 *
	 * @return the date time
	 */
	public Date getDateTime() {
		return wrapperObject.getDateTime();
	}

	/**
	 * Gets the donor id.
	 *
	 * @return the donor id
	 */
	public int getDonorId() {
		return wrapperObject.getDonorId();
	}

	/**
	 * Gets the non profit id.
	 *
	 * @return the non profit id
	 */
	public int getNonProfitId() {
		return wrapperObject.getNonProfitId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return wrapperObject.hashCode();
	}

	/**
	 * Sets the date time.
	 *
	 * @param dateTime
	 *            the new date time
	 */
	public void setDateTime(Date dateTime) {
		wrapperObject.setDateTime(dateTime);
	}

	/**
	 * Sets the donor id.
	 *
	 * @param donorId
	 *            the new donor id
	 */
	public void setDonorId(int donorId) {
		wrapperObject.setDonorId(donorId);
	}

	/**
	 * Sets the non profit id.
	 *
	 * @param nonProfitId
	 *            the new non profit id
	 */
	public void setNonProfitId(int nonProfitId) {
		wrapperObject.setNonProfitId(nonProfitId);
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return wrapperObject.isActive();
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive
	 *            the new active
	 */
	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return wrapperObject.toString();
	}





}
