package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the donation database table.
 * 
 */
@Entity
@NamedQuery(name="Donation.findAll", query="SELECT d FROM Donation d")
public class Donation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double amount;

	@Column(nullable = true)
	private int campaingId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	private int donorId;

	private int nonProfitId;
	
	private int donorFatherId;
	
	private boolean isActive;

	public Donation() {
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

	public int getCampaingId() {
		return this.campaingId;
	}

	public void setCampaingId(int campaingId) {
		this.campaingId = campaingId;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getDonorId() {
		return this.donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public int getNonProfitId() {
		return this.nonProfitId;
	}

	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getDonorFatherId() {
		return donorFatherId;
	}

	public void setDonorFatherId(int donorFatherId) {
		this.donorFatherId = donorFatherId;
	}
}