package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.Donor;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.UserGeneral;

public class UserGeneralWrapper {
	private UserGeneral wrapperObject;
	
	public String getFacebookToken() {
		return wrapperObject.getFacebookToken();
	}

	public void setFacebookToken(String facebookToken) {
		wrapperObject.setFacebookToken(facebookToken);
	}

	public String getFacebookId() {
		return wrapperObject.getFacebookId();
	}

	public void setFacebookID(String facebookID) {
		wrapperObject.setFacebookId(facebookID);
	}

	public UserGeneralWrapper() {
		super();
		setWrapperObject(new UserGeneral());
	}
	
	public UserGeneralWrapper(UserGeneral userGeneral) {
		super();
		setWrapperObject(userGeneral);
	}

	public UserGeneral getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(UserGeneral wrapperObject) {
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

	public String getEmail() {
		return wrapperObject.getEmail();
	}

	public void setEmail(String email) {
		wrapperObject.setEmail(email);
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

	public String getPassword() {
		return wrapperObject.getPassword();
	}

	public void setPassword(String password) {
		wrapperObject.setPassword(password);
	}

	public Donor getDonor() {
		return wrapperObject.getDonor();
	}

	public void setDonor(Donor donor) {
		wrapperObject.setDonor(donor);
	}

	public Nonprofit getNonprofit() {
		return wrapperObject.getNonprofit();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public void setNonprofit(Nonprofit nonprofit) {
		wrapperObject.setNonprofit(nonprofit);
	}

	public String toString() {
		return wrapperObject.toString();
	}
}
