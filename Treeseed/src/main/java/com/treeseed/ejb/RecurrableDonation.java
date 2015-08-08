package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the recurrabledonation database table.
 * 
 */
@Entity
@NamedQuery(name="RecurrableDonation.findAll", query="SELECT r FROM RecurrableDonation r")
public class RecurrableDonation implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/** The amount. */
	private double amount;
	
	/** The stripe id. */
	private String stripeId;
	
	/** The donor father id. */
	private int donorFatherId;
	
	/** The donor id. */
	private int donorId;

	/** The non profit id. */
	private int nonProfitId;
	
	/** The campaing id. */
	@Column(nullable = true)
	private int campaingId;

	/** The date time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	

	/** The is active. */
	private boolean isActive;


	/**
	 * Instantiates a new recurrable donation.
	 */
	public RecurrableDonation() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
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
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the date time.
	 *
	 * @return the date time
	 */
	public Date getDateTime() {
		return this.dateTime;
	}

	/**
	 * Sets the date time.
	 *
	 * @param dateTime the new date time
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public boolean getIsActive() {
		return this.isActive;
	}

	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the stripe id.
	 *
	 * @return the stripe id
	 */
	public String getStripeId() {
		return stripeId;
	}

	/**
	 * Sets the stripe id.
	 *
	 * @param stripeId the new stripe id
	 */
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

	/**
	 * Gets the donor father id.
	 *
	 * @return the donor father id
	 */
	public int getDonorFatherId() {
		return donorFatherId;
	}

	/**
	 * Sets the donor father id.
	 *
	 * @param donorFatherId the new donor father id
	 */
	public void setDonorFatherId(int donorFatherId) {
		this.donorFatherId = donorFatherId;
	}
	
	/**
	 * Gets the donor id.
	 *
	 * @return the donor id
	 */
	public int getDonorId() {
		return donorId;
	}

	/**
	 * Sets the donor id.
	 *
	 * @param donorId the new donor id
	 */
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	/**
	 * Gets the non profit id.
	 *
	 * @return the non profit id
	 */
	public int getNonProfitId() {
		return nonProfitId;
	}

	/**
	 * Sets the non profit id.
	 *
	 * @param nonProfitId the new non profit id
	 */
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}

	/**
	 * Gets the campaing id.
	 *
	 * @return the campaing id
	 */
	public int getCampaingId() {
		return campaingId;
	}

	/**
	 * Sets the campaing id.
	 *
	 * @param campaingId the new campaing id
	 */
	public void setCampaingId(int campaingId) {
		this.campaingId = campaingId;
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}