package com.treeseed.ejbWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.services.DonationServiceInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignWrapper.
 */
public class CampaignWrapper {
	/** The wrapper object. */
	private Campaign wrapperObject;
	
	/** The state. */
	private String state;
	
	/** The percent. */
	private double percent;
	
	/** The due date String. */
	//Due date in String format dd/MMM/yyyy
	private String dueDateS;
	
	/** The start date String. */
	//Start date in String format dd/MMM/yyyy
	private String startDateS;
	
	/** The start. */
	//Start date is after today?
	private boolean start;
	
	/** The end. */
	//Star due date is after today?
	private boolean end;


	/**
	 * Gets the percent.
	 *
	 * @return the percent
	 */
	public double getPercent() {
		setPercent((getAmountCollected()/getAmountGoal())*100);
		return percent;
	}

	/**
	 * Sets the percent.
	 *
	 * @param percent the new percent
	 */
	private void setPercent(double percent) {
		this.percent = percent;
	}

	/**
	 * Instantiates a new campaign wrapper.
	 */
	public CampaignWrapper() {
		setWrapperObject(new Campaign());
	}

	/**
	 * Instantiates a new campaign wrapper.
	 *
	 * @param campaign the campaign
	 */
	public CampaignWrapper(Campaign campaign) {
		this.wrapperObject = campaign;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return wrapperObject.getStartDate();
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		wrapperObject.setStartDate(startDate);
	}

	/**
	 * Gets the wrapper object.
	 *
	 * @return the wrapper object
	 */
	public Campaign getWrapperObject() {
		return wrapperObject;
	}

	/**
	 * Sets the wrapper object.
	 *
	 * @param wrapperObject the new wrapper object
	 */
	public void setWrapperObject(Campaign wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	/**
	 * Adds the postcampaign.
	 *
	 * @param postcampaign the postcampaign
	 * @return the post campaign
	 */
	public PostCampaign addPostcampaign(PostCampaign postcampaign) {
		return wrapperObject.addPostcampaign(postcampaign);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return wrapperObject.getId();
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		wrapperObject.setId(id);
	}

	/**
	 * Gets the amount collected.
	 *
	 * @return the amount collected
	 */
	public double getAmountCollected() {
		return wrapperObject.getAmountCollected();
	}

	/**
	 * Sets the amount collected.
	 *
	 * @param amountCollected the new amount collected
	 */
	public void setAmountCollected(double amountCollected) {
		wrapperObject.setAmountCollected(amountCollected);
	}

	/**
	 * Gets the amount goal.
	 *
	 * @return the amount goal
	 */
	public double getAmountGoal() {
		return wrapperObject.getAmountGoal();
	}

	/**
	 * Sets the amount goal.
	 *
	 * @param amountGoal the new amount goal
	 */
	public void setAmountGoal(double amountGoal) {
		wrapperObject.setAmountGoal(amountGoal);
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return wrapperObject.getCreationDate();
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		wrapperObject.setCreationDate(creationDate);
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return wrapperObject.getDescription();
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public Date getDueDate() {
		return wrapperObject.getDueDate();
	}

	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(Date dueDate) {
		wrapperObject.setDueDate(dueDate);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return wrapperObject.getName();
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		wrapperObject.setName(name);
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return wrapperObject.getPicture();
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(String picture) {
		wrapperObject.setPicture(picture);
	}

	/**
	 * Gets the nonprofit.
	 *
	 * @return the nonprofit
	 */
	public Nonprofit getNonprofit() {
		return wrapperObject.getNonprofit();
	}

	/**
	 * Gets the postcampaigns.
	 *
	 * @return the postcampaigns
	 */
	public List<PostCampaign> getPostcampaigns() {
		return wrapperObject.getPostcampaigns();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return wrapperObject.hashCode();
	}

	/**
	 * Sets the nonprofit.
	 *
	 * @param nonprofit the new nonprofit
	 */
	public void setNonprofit(Nonprofit nonprofit) {
		wrapperObject.setNonprofit(nonprofit);
	}

	/**
	 * Sets the postcampaigns.
	 *
	 * @param postcampaigns the new postcampaigns
	 */
	public void setPostcampaigns(List<PostCampaign> postcampaigns) {
		wrapperObject.setPostcampaigns(postcampaigns);
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return wrapperObject.isActive();
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	/**
	 * Removes the postcampaign.
	 *
	 * @param postcampaign the postcampaign
	 * @return the post campaign
	 */
	public PostCampaign removePostcampaign(PostCampaign postcampaign) {
		return wrapperObject.removePostcampaign(postcampaign);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return wrapperObject.toString();
	}

	/**
	 * Gets the due date s.
	 *
	 * @return the due date s
	 */
	public String getDueDateS() {
		setDueDateS(new SimpleDateFormat("dd/MMM/yyyy").format(getDueDate()));
		return dueDateS;
	}

	/**
	 * Sets the due date s.
	 *
	 * @param dueDateS the new due date s
	 */
	private void setDueDateS(String dueDateS) {
		this.dueDateS = dueDateS;
	}

	/**
	 * Gets the start date s.
	 *
	 * @return the start date s
	 */
	public String getStartDateS() {
		setStartDateS(new SimpleDateFormat("dd/MMM/yyyy").format(getStartDate()));
		return startDateS;
	}

	/**
	 * Sets the start date s.
	 *
	 * @param startDateS the new start date s
	 */
	private void setStartDateS(String startDateS) {
		this.startDateS = startDateS;
	}

	/**
	 * Checks if is start.
	 *
	 * @return true, if is start
	 */
	public boolean isStart() {
		if(getStartDate().after(new Date())){
			
			setStart(true);
		}else{
			setStart(false);
		}
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	private void setStart(boolean start) {
		this.start = start;
	}

	/**
	 * Checks if is end.
	 *
	 * @return true, if is end
	 */
	public boolean isEnd() {
		if(getDueDate().after(new Date())){
			setEnd(true);
		}else{
			setEnd(false);
		}
		return end;
	}

	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	private void setEnd(boolean end) {
		
		this.end = end;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		
		if(isStart()){
			if(isActive()){
				setState("soon");
			}else{
				setState("finished");
			}
		} else if(!isEnd() ){
			setState("finished");
		}else if(!isStart()&&isEnd()){
			
			if(isActive()){
				setState("active");
			}else{
				setState("finished");
			}
			 
		}
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	private void setState(String state) {
		this.state = state;
	}

	
	
}
