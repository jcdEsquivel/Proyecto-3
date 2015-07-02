package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the recurrabledonation database table.
 * 
 */
@Entity
@NamedQuery(name="Recurrabledonation.findAll", query="SELECT r FROM Recurrabledonation r")
public class Recurrabledonation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	private Object isActive;

	//bi-directional many-to-one association to Donor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="donorId")
	private Donor donor;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nonProfitId")
	private Nonprofit nonprofit;

	public Recurrabledonation() {
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

	public Object getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Object isActive) {
		this.isActive = isActive;
	}

	public Donor getDonor() {
		return this.donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

}