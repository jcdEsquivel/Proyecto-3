package com.treeseed.pojo;

import com.treeseed.ejb.Donor;

// TODO: Auto-generated Javadoc
/**
 * The Class CardPOJO.
 */
public class CardPOJO {

	/** The id. */
	private int id;

	/** The stripe id. */
	private String stripeId;
	
	/** The brand. */
	private String brand;
	
	/** The exp month. */
	private int expMonth;
	
	/** The exp year. */
	private int expYear;
	
	/** The last4 numbers. */
	private String last4Numbers;
	
	/** The is active. */
	private boolean isActive;
	
	/** The donor. */
	private DonorPOJO donor;
	
	/** The is default. */
	private boolean isDefault;
	
	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Checks if is default.
	 *
	 * @return true, if is default
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * Sets the default.
	 *
	 * @param isDefault the new default
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand the new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the exp month.
	 *
	 * @return the exp month
	 */
	public int getExpMonth() {
		return expMonth;
	}

	/**
	 * Sets the exp month.
	 *
	 * @param expMonth the new exp month
	 */
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	/**
	 * Gets the exp year.
	 *
	 * @return the exp year
	 */
	public int getExpYear() {
		return expYear;
	}

	/**
	 * Sets the exp year.
	 *
	 * @param expYear the new exp year
	 */
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	/**
	 * Gets the last4 numbers.
	 *
	 * @return the last4 numbers
	 */
	public String getLast4Numbers() {
		return last4Numbers;
	}

	/**
	 * Sets the last4 numbers.
	 *
	 * @param last4Numbers the new last4 numbers
	 */
	public void setLast4Numbers(String last4Numbers) {
		this.last4Numbers = last4Numbers;
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
	public DonorPOJO getDonor() {
		return donor;
	}

	/**
	 * Sets the donor.
	 *
	 * @param donor the new donor
	 */
	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}
}
