package com.treeseed.pojo;

import java.util.Date;

import org.joda.time.DateTime;

import com.treeseed.ejb.Nonprofit;

public class CampaignPOJO {
	public CampaignPOJO() {
		super();
	}
	
	private int id;
	private Date creationDate;
	private String name;
	private String description;
	private Date startDate;
	private Date dueDate;
	private String startDateS;
	private String dueDateS;
	private boolean start;
	private boolean end;
	private double amountGoal;
	private double amountCollected;
	private boolean isActive;
	private String picture;
	private int idNonProfit;
	private int percent;

	private NonprofitPOJO nonProfit;
	
	public NonprofitPOJO getNonprofit()
	{
		return this.nonProfit;
	}
	
	public void setNonprofit(NonprofitPOJO nonProfit)
	{
		this.nonProfit = nonProfit;
	}
	
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

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartDateS() {
		return startDateS;
	}

	public void setStartDateS(String startDateS) {
		this.startDateS = startDateS;
	}

	public String getDueDateS() {
		return dueDateS;
	}

	public void setDueDateS(String dueDateS) {
		this.dueDateS = dueDateS;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
}
