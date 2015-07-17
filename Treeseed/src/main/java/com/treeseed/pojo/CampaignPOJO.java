package com.treeseed.pojo;

import java.util.Date;

import org.joda.time.DateTime;

public class CampaignPOJO {
	public CampaignPOJO() {
		super();
	}
	
	private int id;
	private Date creationDate;
	private String name;
	private String description;
	private Date dueDate;
	private double amountGoal;
	private double amountCollected;
	private boolean isActive;
	private String picture;
	
	private int idNonProfit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setDueDate(Date date) {
		this.dueDate = date;
	}

	public double getAmountGoal() {
		return amountGoal;
	}

	public void setAmountGoal(double amountGoal) {
		this.amountGoal = amountGoal;
	}

	public double getAmountCollected() {
		return amountCollected;
	}

	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getIdNonProfit() {
		return idNonProfit;
	}

	public void setIdNonProfit(int idNonProfit) {
		this.idNonProfit = idNonProfit;
	}
}
