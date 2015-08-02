package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the recurrabledonation database table.
 * 
 */
@Entity
@NamedQuery(name="RecurrableDonation.findAll", query="SELECT r FROM RecurrableDonation r")
public class RecurrableDonation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double amount;
	
	private String stripeId;
	
	private int donorFatherId;
	
	private int donorId;

	private int nonProfitId;
	
	@Column(nullable = true)
	private int campaingId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	

	private boolean isActive;


	public RecurrableDonation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getStripeId() {
		return stripeId;
	}

	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

	public int getDonorFatherId() {
		return donorFatherId;
	}

	public void setDonorFatherId(int donorFatherId) {
		this.donorFatherId = donorFatherId;
	}
	
	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public int getNonProfitId() {
		return nonProfitId;
	}

	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}

	public int getCampaingId() {
		return campaingId;
	}

	public void setCampaingId(int campaingId) {
		this.campaingId = campaingId;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}