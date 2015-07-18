package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;

public class PostNonprofitWrapper {

	private PostNonprofit wrapperObject;

	public PostNonprofitWrapper( ) {
	}
	
	public PostNonprofitWrapper(PostNonprofit wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	public PostNonprofit getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(PostNonprofit wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	public boolean equals(Object arg0) {
		return wrapperObject.equals(arg0);
	}

	public int getId() {
		return wrapperObject.getId();
	}

	public void setId(int id) {
		wrapperObject.setId(id);
	}

	public Date getCreationDate() {
		return wrapperObject.getCreationDate();
	}

	public void setCreationDate(Date creationDate) {
		wrapperObject.setCreationDate(creationDate);
	}

	public String getDescription() {
		return wrapperObject.getDescription();
	}

	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	public boolean getIsActive() {
		return wrapperObject.getIsActive();
	}

	public void setIsActive(boolean isActive) {
		wrapperObject.setIsActive(isActive);
	}

	public String getPicture() {
		return wrapperObject.getPicture();
	}

	public void setPicture(String picture) {
		wrapperObject.setPicture(picture);
	}

	public String getTittle() {
		return wrapperObject.getTittle();
	}

	public Nonprofit getNonprofit() {
		return wrapperObject.getNonprofit();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public void setTittle(String tittle) {
		wrapperObject.setTittle(tittle);
	}

	public void setNonprofit(Nonprofit nonprofit) {
		wrapperObject.setNonprofit(nonprofit);
	}

	public String toString() {
		return wrapperObject.toString();
	}
}
