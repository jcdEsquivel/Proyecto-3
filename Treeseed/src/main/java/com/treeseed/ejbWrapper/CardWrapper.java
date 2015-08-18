package com.treeseed.ejbWrapper;

import com.treeseed.ejb.Card;
import com.treeseed.ejb.Donor;

// TODO: Auto-generated Javadoc
/**
 * The Class CardWrapper.
 */
public class CardWrapper {
	
	/** The wrapper object. */
	Card wrapperObject;

	

	/**
	 * Instantiates a new card wrapper.
	 */
	public CardWrapper() {
		super();
		setWrapperObject(new Card());
	}
	
	/**
	 * Instantiates a new card wrapper.
	 *
	 * @param card the card
	 */
	public CardWrapper(Card card) {
		super();
		setWrapperObject(card);
	}
	
	/**
	 * Gets the donor.
	 *
	 * @return the donor
	 */
	public Donor getDonor() {
		return wrapperObject.getDonor();
	}

	/**
	 * Sets the donor.
	 *
	 * @param donor the new donor
	 */
	public void setDonor(Donor donor) {
		wrapperObject.setDonor(donor);
	}
	
	/**
	 * Gets the wrapper object.
	 *
	 * @return the wrapper object
	 */
	public Card getWrapperObject() {
		return wrapperObject;
	}

	/**
	 * Sets the wrapper object.
	 *
	 * @param wrapperObject the new wrapper object
	 */
	public void setWrapperObject(Card wrapperObject) {
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		wrapperObject.setId(id);
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
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
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
	
	

}
