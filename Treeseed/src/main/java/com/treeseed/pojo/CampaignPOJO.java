package com.treeseed.pojo;

import java.util.Date;

import org.joda.time.DateTime;

import com.treeseed.ejb.Nonprofit;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignPOJO.
 */
public class CampaignPOJO {
	
	/**
	 * Instantiates a new campaign pojo.
	 */
	public CampaignPOJO() {
		super();
	}
	
	/** The id. */
	private int id;
	
	/** The creation date. */
	private Date creationDate;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The start date. */
	private Date startDate;
	
	/** The due date. */
	private Date dueDate;
	
	/** The start date s. */
	private String startDateS;
	
	/** The due date s. */
	private String dueDateS;
	
	/** The start. */
	private boolean start;
	
	/** The end. */
	private boolean end;
	
	/** The amount goal. */
	private double amountGoal;
	
	/** The amount collected. */
	private double amountCollected;
	
	/** The is active. */
	private boolean isActive;
	
	/** The picture. */
	private String picture;
	
	/** The id non profit. */
	private int idNonProfit;
	
	/** The percent. */
	private int percent;
	
	/** The state. */
	private String state;
	
	/** The cant donors. */
	private int cantDonors;
	

	

	/** The non profit. */
	private NonprofitPOJO nonProfit;
	
	/**
	 * Gets the nonprofit.
	 *
	 * @return the nonprofit
	 */
	public NonprofitPOJO getNonprofit()
	{
		return this.nonProfit;
	}
	
	/**
	 * Sets the nonprofit.
	 *
	 * @param nonProfit the new nonprofit
	 */
	public void setNonprofit(NonprofitPOJO nonProfit)
	{
		this.nonProfit = nonProfit;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Gets the amount goal.
	 *
	 * @return the amount goal
	 */
	public double getAmountGoal() {
		return amountGoal;
	}

	/**
	 * Sets the amount goal.
	 *
	 * @param amountGoal the new amount goal
	 */
	public void setAmountGoal(double amountGoal) {
		this.amountGoal = amountGoal;
	}

	/**
	 * Gets the amount collected.
	 *
	 * @return the amount collected
	 */
	public double getAmountCollected() {
		return amountCollected;
	}

	/**
	 * Sets the amount collected.
	 *
	 * @param amountCollected the new amount collected
	 */
	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Gets the id non profit.
	 *
	 * @return the id non profit
	 */
	public int getIdNonProfit() {
		return idNonProfit;
	}

	/**
	 * Sets the id non profit.
	 *
	 * @param idNonProfit the new id non profit
	 */
	public void setIdNonProfit(int idNonProfit) {
		this.idNonProfit = idNonProfit;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the start date s.
	 *
	 * @return the start date s
	 */
	public String getStartDateS() {
		return startDateS;
	}

	/**
	 * Sets the start date s.
	 *
	 * @param startDateS the new start date s
	 */
	public void setStartDateS(String startDateS) {
		this.startDateS = startDateS;
	}

	/**
	 * Gets the due date s.
	 *
	 * @return the due date s
	 */
	public String getDueDateS() {
		return dueDateS;
	}

	/**
	 * Sets the due date s.
	 *
	 * @param dueDateS the new due date s
	 */
	public void setDueDateS(String dueDateS) {
		this.dueDateS = dueDateS;
	}

	/**
	 * Checks if is start.
	 *
	 * @return true, if is start
	 */
	public boolean isStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(boolean start) {
		this.start = start;
	}

	/**
	 * Checks if is end.
	 *
	 * @return true, if is end
	 */
	public boolean isEnd() {
		return end;
	}

	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}

	/**
	 * Gets the percent.
	 *
	 * @return the percent
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * Sets the percent.
	 *
	 * @param percent the new percent
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the cant donors.
	 *
	 * @return the cant donors
	 */
	public int getCantDonors() {
		return cantDonors;
	}

	/**
	 * Sets the cant donors.
	 *
	 * @param cantDonors the new cant donors
	 */
	public void setCantDonors(int cantDonors) {
		this.cantDonors = cantDonors;
	}
}
