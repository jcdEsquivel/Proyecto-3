package com.treeseed.pojo;

/**
 * The Class RecurrableInformationResultPOJO, which has the 
 * information needed in the donor portfolio.
 */
public class RecurrableInformationResultPOJO {
	
	/** The recurrable donation amount. */
	private double amount;
	/** The nonprofit id. */
	private int nonprofitId;
	/** The nonprofit name. */
	private String nonprofitName;
	/** The nonprofit cause in english. */
	private String englishCause;
	/** The nonprofit cause in spanish. */
	private String spanishCause;
	
	/**
	 * Gets the recurrable donation amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * Sets the recurrable donation amount.
	 *
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * Gets the nonprofit id.
	 *
	 * @return the id
	 */
	public int getNonprofitId() {
		return nonprofitId;
	}
	/**
	 * Sets the nonprofit id.
	 *
	 * @param nonprofitId
	 */
	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}
	/**
	 * Gets the nonprofit name.
	 *
	 * @return the nonprofit name
	 */
	public String getNonprofitName() {
		return nonprofitName;
	}
	/**
	 * Sets the nonprofit name.
	 *
	 * @param nonprofit name
	 */
	public void setNonprofitName(String nonprofitName) {
		this.nonprofitName = nonprofitName;
	}
	/**
	 * Gets the cause in english.
	 *
	 * @return the cause in english
	 */
	public String getEnglishCause() {
		return englishCause;
	}
	/**
	 * Sets the english cause.
	 *
	 * @param english cause
	 */
	public void setEnglishCause(String englishCause) {
		this.englishCause = englishCause;
	}
	/**
	 * Gets the cause in spanish.
	 *
	 * @return the cause in spanish
	 */
	public String getSpanishCause() {
		return spanishCause;
	}
	/**
	 * Sets the spanish cause.
	 *
	 * @param spanish cause
	 */
	public void setSpanishCause(String spanishCause) {
		this.spanishCause = spanishCause;
	}
	
	
}
