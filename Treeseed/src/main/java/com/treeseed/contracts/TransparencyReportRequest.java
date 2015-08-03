package com.treeseed.contracts;

import java.sql.Date;

import com.treeseed.pojo.TransparencyReportPOJO;

public class TransparencyReportRequest extends BasePagingRequest {

	private int id;
	private double amountIn;
	private double amountOut;
	private String description;
	private Date date;
	private int nonProfitId;
	private TransparencyReportPOJO transparencyReport;
	private String month;
	private String year;
	
	/**
	 * Gets the month.
	 * 
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	
	/**
	 * Sets the month.
	 * 
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	 * Gets the year.
	 * 
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * Sets the year.
	 * 
	 */
	public void setYear(String year) {
		this.year = year;
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
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the amount IN.
	 * 
	 * @return the Amount In
	 */
	public double getAmountIn() {
		return amountIn;
	}
	
	/**
	 * Sets the Amount In.
	 * 
	 */
	public void setAmountIn(double amountIn) {
		this.amountIn = amountIn;
	}
	
	/**
	 * Gets the Amount Out.
	 * 
	 * @return the Amount Out
	 */
	public double getAmountOut() {
		return amountOut;
	}
	
	/**
	 * Sets the Amount Out.
	 * 
	 */
	public void setAmountOut(double amountOut) {
		this.amountOut = amountOut;
	}
	
	/**
	 * Gets the Description.
	 * 
	 * @return the Description
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
	 * Gets the Date.
	 * 
	 * @return the Date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 * 
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Gets the NonprofitId.
	 * 
	 * @return the Nonprofit Id
	 */
	public int getNonProfitId() {
		return nonProfitId;
	}
	
	/**
	 * Sets the nonprofit Id.
	 * 
	 */
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	
	/**
	 * Gets the TransparencyReport.
	 * 
	 * @return the TransparencyReport
	 */
	public TransparencyReportPOJO getTransparencyReport() {
		return transparencyReport;
	}
	
	/**
	 * Sets the TransparencyReport.
	 * 
	 */
	public void setTransparencyReport(TransparencyReportPOJO transparencyReport) {
		this.transparencyReport = transparencyReport;
	}
	
	
}
