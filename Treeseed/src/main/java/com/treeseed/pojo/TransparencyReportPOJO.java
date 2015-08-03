package com.treeseed.pojo;

import java.util.Date;


public class TransparencyReportPOJO {
	
	private int id;
	private double amountIn;
	private double amountOut;
	private String description;
	private Date date;
	private String dateS;
	private int nonProfitId;
	
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
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the amount In.
	 * 
	 * @return the Amount In
	 */
	public double getAmountIn() {
		return amountIn;
	}
	
	/**
	 * Sets the amount in.
	 * 
	 */
	public void setAmountIn(double amountIn) {
		this.amountIn = amountIn;
	}
	
	/**
	 * Gets the amount out.
	 * 
	 * @return the amount out
	 */
	public double getAmountOut() {
		return amountOut;
	}
	
	/**
	 * Sets the amount out.
	 * 
	 */
	public void setAmountOut(double amountOut) {
		this.amountOut = amountOut;
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
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the month.
	 * 
	 * @return the month
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the Date.
	 * 
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Gets the month string.
	 * 
	 * @return the month
	 */
	public String getDateS() {
		return dateS;
	}
	
	/**
	 * Sets the Date String.
	 * 
	 */
	public void setDateS(String dateS) {
		this.dateS = dateS;
	}
	
	/**
	 * Gets the month.
	 * 
	 * @return the month
	 */
	public int getNonProfitId() {
		return nonProfitId;
	}
	
	/**
	 * Sets the id.
	 * 
	 */
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
}
