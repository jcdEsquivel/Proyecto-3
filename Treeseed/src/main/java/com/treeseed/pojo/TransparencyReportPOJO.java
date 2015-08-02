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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmountIn() {
		return amountIn;
	}
	public void setAmountIn(double amountIn) {
		this.amountIn = amountIn;
	}
	public double getAmountOut() {
		return amountOut;
	}
	public void setAmountOut(double amountOut) {
		this.amountOut = amountOut;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDateS() {
		return dateS;
	}
	public void setDateS(String dateS) {
		this.dateS = dateS;
	}
	public int getNonProfitId() {
		return nonProfitId;
	}
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
}
