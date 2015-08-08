package com.treeseed.ejb;

import java.io.Serializable;

import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@NamedQuery(name="Card.findAll", query="SELECT c FROM Card c")
public class Card implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/** The stripe id. */
	private String stripeId;
	
	/** The is active. */
	private boolean isActive;
	
	/** The donor. */
	//bi-directional many-to-one association to Donor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDonor")
	private Donor donor;

	/**
	 * Instantiates a new card.
	 */
	public Card() {
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
	 * Gets the donor.
	 *
	 * @return the donor
	 */
	public Donor getDonor() {
		return donor;
	}


	/**
	 * Sets the donor.
	 *
	 * @param donor the new donor
	 */
	public void setDonor(Donor donor) {
		this.donor = donor;
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

}