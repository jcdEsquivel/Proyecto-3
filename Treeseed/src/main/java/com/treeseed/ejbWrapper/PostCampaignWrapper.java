package com.treeseed.ejbWrapper;

import java.util.Date;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.PostCampaign;

public class PostCampaignWrapper {
	
	private PostCampaign wrapperObject;

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

	public boolean isActive() {
		return wrapperObject.isActive();
	}

	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	public String getPicture() {
		return wrapperObject.getPicture();
	}

	public void setPicture(String picture) {
		wrapperObject.setPicture(picture);
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public String getTittle() {
		return wrapperObject.getTittle();
	}

	public void setTittle(String tittle) {
		wrapperObject.setTittle(tittle);
	}

	public Campaign getCampaign() {
		return wrapperObject.getCampaign();
	}

	public void setCampaign(Campaign campaign) {
		wrapperObject.setCampaign(campaign);
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public String toString() {
		return wrapperObject.toString();
	}
	
}
