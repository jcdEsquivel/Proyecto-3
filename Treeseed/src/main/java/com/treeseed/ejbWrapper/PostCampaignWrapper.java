package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;

public class PostCampaignWrapper {

	private PostCampaign post;
	
	public PostCampaignWrapper(PostCampaign post) {
		super();
		
		setWrapperObject(post);
	}
	
	public PostCampaign getWrapperObject() {
		return post;
	}

	public void setWrapperObject(PostCampaign wrapperObject) {
		this.post = wrapperObject;
	}
	
	public boolean equals(Object obj) {
		return post.equals(obj);
	}

	public int getId() {
		return post.getId();
	}

	public void setId(int id) {
		post.setId(id);
	}

	public Date getCreationDate() {
		return post.getCreationDate();
	}

	public void setCreationDate(Date creationDate) {
		post.setCreationDate(creationDate);
	}

	public String getDescription() {
		return post.getDescription();
	}

	public void setDescription(String description) {
		post.setDescription(description);
	}

	public boolean isActive() {
		return post.isActive();
	}

	public void setActive(boolean isActive) {
		post.setActive(isActive);
	}

	public String getPicture() {
		return post.getPicture();
	}

	public void setPicture(String picture) {
		post.setPicture(picture);
	}

	public String getTittle() {
		return post.getTittle();
	}

	public Campaign getCampaign() {
		return post.getCampaign();
	}

	public int hashCode() {
		return post.hashCode();
	}

	public void setTittle(String tittle) {
		post.setTittle(tittle);
	}

	public void setCampaign(Campaign campaign) {
		post.setCampaign(campaign);
	}

	public String toString() {
		return post.toString();
	}

	
}
