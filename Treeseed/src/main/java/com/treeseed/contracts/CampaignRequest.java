package com.treeseed.contracts;

import java.util.Date;
import java.util.List;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.pojo.CampaignPOJO;

public class CampaignRequest extends BasePagingRequest {
	
	private CampaignPOJO campaign;

	private int id;

	private double amountCollected;

	private double amountGoal;

	private Date creationDate;

	private String description;
	
	private Date startDate;

	private Date dueDate;

	private String name;

	private String picture;
	
	private boolean isActive;

	private Nonprofit nonprofit;

	private List<PostCampaign> postCampaigns;

	public CampaignPOJO getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignPOJO campaign) {
		this.campaign = campaign;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmountCollected() {
		return amountCollected;
	}

	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}

	public double getAmountGoal() {
		return amountGoal;
	}

	public void setAmountGoal(double amountGoal) {
		this.amountGoal = amountGoal;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Nonprofit getNonprofit() {
		return nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

	public List<PostCampaign> getPostCampaigns() {
		return postCampaigns;
	}

	public void setPostCampaigns(List<PostCampaign> postCampaigns) {
		this.postCampaigns = postCampaigns;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
