package com.treeseed.ejbWrapper;

import com.treeseed.ejb.Card;
import com.treeseed.ejb.Donor;

public class CardWrapper {
	
	Card wrapperObject;

	

	public CardWrapper() {
		super();
		setWrapperObject(new Card());
	}
	
	public CardWrapper(Card card) {
		super();
		setWrapperObject(card);
	}
	
	public Donor getDonor() {
		return wrapperObject.getDonor();
	}

	public void setDonor(Donor donor) {
		wrapperObject.setDonor(donor);
	}
	
	public Card getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(Card wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public int getId() {
		return wrapperObject.getId();
	}

	public String getStripeId() {
		return wrapperObject.getStripeId();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public void setId(int id) {
		wrapperObject.setId(id);
	}

	public boolean isActive() {
		return wrapperObject.isActive();
	}

	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	public void setStripeId(String stripeId) {
		wrapperObject.setStripeId(stripeId);
	}

	public String toString() {
		return wrapperObject.toString();
	}
	
	

}
