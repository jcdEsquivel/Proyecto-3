package com.treeseed.ejbWrapper;

import java.util.Date;
import java.util.List;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;

public class CampaignWrapper {
	
	private Campaign wrapperObject;

	private Campaign wrapperObject;

	public CampaignWrapper() {
		setWrapperObject(new Campaign());
	}

	public CampaignWrapper(Campaign campaign) {
		this.wrapperObject = campaign;
	}

	public Date getStartDate() {
		return wrapperObject.getStartDate();
	}

	public void setStartDate(Date startDate) {
		wrapperObject.setStartDate(startDate);
	}

	public Campaign getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(Campaign wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	public PostCampaign addPostcampaign(PostCampaign postcampaign) {
		return wrapperObject.addPostcampaign(postcampaign);
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

	public double getAmountCollected() {
		return wrapperObject.getAmountCollected();
	}

	public void setAmountCollected(double amountCollected) {
		wrapperObject.setAmountCollected(amountCollected);
	}

	public double getAmountGoal() {
		return wrapperObject.getAmountGoal();
	}

	public void setAmountGoal(double amountGoal) {
		wrapperObject.setAmountGoal(amountGoal);
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

	public Date getDueDate() {
		return wrapperObject.getDueDate();
	}

	public void setDueDate(Date dueDate) {
		wrapperObject.setDueDate(dueDate);
	}

	public String getName() {
		return wrapperObject.getName();
	}

	public void setName(String name) {
		wrapperObject.setName(name);
	}

	public String getPicture() {
		return wrapperObject.getPicture();
	}

	public void setPicture(String picture) {
		wrapperObject.setPicture(picture);
	}

	public Nonprofit getNonprofit() {
		return wrapperObject.getNonprofit();
	}

	public List<PostCampaign> getPostcampaigns() {
		return wrapperObject.getPostcampaigns();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public void setNonprofit(Nonprofit nonprofit) {
		wrapperObject.setNonprofit(nonprofit);
	}

	public void setPostcampaigns(List<PostCampaign> postcampaigns) {
		wrapperObject.setPostcampaigns(postcampaigns);
	}

	public boolean isActive() {
		return wrapperObject.isActive();
	}

	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	public PostCampaign removePostcampaign(PostCampaign postcampaign) {
		return wrapperObject.removePostcampaign(postcampaign);
	}

	public String toString() {
		return wrapperObject.toString();
	}
}
