package com.treeseed.ejbWrapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.treeseed.ejb.RecurrableDonation;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.DonorServiceInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class RecurrableDonationWrapper.
 */
public class RecurrableDonationWrapper {

	/** The campaign service. */
	@Autowired
	private CampaignServiceInterface campaignService;
	/** The wrapper object. */
	RecurrableDonation wrapperObject;

	/** The campaign. */
	CampaignWrapper campaign;
	
	/** The campaign service. */
	@Autowired
	private DonorServiceInterface donorService;


	/** The campaign. */
	private DonorWrapper donor;

	/**
	 * Instantiates a new recurrable donation wrapper.
	 *
	 * @param recurrableDonation the recurrable donation
	 */
	public RecurrableDonationWrapper(RecurrableDonation recurrableDonation) {
		super();
		setWrapperObject(recurrableDonation);
	}
	
	/**
	 * Instantiates a new recurrable donation wrapper.
	 */
	public RecurrableDonationWrapper() {
		super();
		setWrapperObject(new RecurrableDonation());
	}

	/**
	 * Gets the wrapper object.
	 *
	 * @return the wrapper object
	 */
	public RecurrableDonation getWrapperObject() {
		return wrapperObject;
	}

	/**
	 * Sets the wrapper object.
	 *
	 * @param wrapperObject the new wrapper object
	 */
	public void setWrapperObject(RecurrableDonation wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	/* (non-Javadoc)
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
	 * @param id the new id
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
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		wrapperObject.setAmount(amount);
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
	 * Sets the date time.
	 *
	 * @param dateTime the new date time
	 */
	public void setDateTime(Date dateTime) {
		wrapperObject.setDateTime(dateTime);
	}

	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public boolean getIsActive() {
		return wrapperObject.getIsActive();
	}

	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(boolean isActive) {
		wrapperObject.setIsActive(isActive);
	}

	/**
	 * Gets the stripe id.
	 *
	 * @return the stripe id
	 */
	public String getStripeId() {
		return wrapperObject.getStripeId();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return wrapperObject.hashCode();
	}


	/**
	 * Sets the stripe id.
	 *
	 * @param stripeId the new stripe id
	 */
	public void setStripeId(String stripeId) {
		wrapperObject.setStripeId(stripeId);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return wrapperObject.toString();
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
	 * Sets the donor id.
	 *
	 * @param donorId the new donor id
	 */
	public void setDonorId(int donorId) {
		wrapperObject.setDonorId(donorId);
	}

	/**
	 * Gets the non profit id.
	 *
	 * @return the non profit id
	 */
	public int getNonProfitId() {
		return wrapperObject.getNonProfitId();
	}

	/**
	 * Sets the non profit id.
	 *
	 * @param nonProfitId the new non profit id
	 */
	public void setNonProfitId(int nonProfitId) {
		wrapperObject.setNonProfitId(nonProfitId);
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
	 * @param campaingId the new campaing id
	 */
	public void setCampaingId(int campaingId) {
		wrapperObject.setCampaingId(campaingId);
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
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
	 * @param donorFatherId the new donor father id
	 */
	public void setDonorFatherId(int donorFatherId) {
		wrapperObject.setDonorFatherId(donorFatherId);
	}
	
	/**
	 * Gets the plan id.
	 *
	 * @return the plan id
	 */
	public int getPlanId(){
		switch((int)getAmount()){
		case 10:
			return 1;
		case 18:
			return 2;
		case 36:
			return 3;
		case 50:
			return 4;
		case 100:
			return 5;
		case 250:
			return 6;
		}
		
		return 0;
	}
	
	/**
	 * Gets the campaign.
	 *
	 * @return the campaign
	 */
	public CampaignWrapper getCampaign() {
		if(campaign.getWrapperObject()==null){
			setCampaign(campaignService.getCampaignById(getCampaingId()));
		}
		return campaign;
	}

	/**
	 * Sets the campaign.
	 *
	 * @param campaign the new campaign
	 */
	private void setCampaign(CampaignWrapper campaign) {
		this.campaign = campaign;
	}
	
	public DonorWrapper getDonor() {
		if(donor.getWrapperObject()==null){
			setDonor(donorService.getDonorById(getDonorId()));
		}
		
		return donor;
	}

	private void setDonor(DonorWrapper donor) {
		this.donor = donor;
	}

	
	
}
