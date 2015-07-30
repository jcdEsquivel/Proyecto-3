package com.treeseed.pojo;

import java.sql.Date;

public class TransparencyReportPOJO {
	
	private int id;
	private double amountIn;
	private double amountOut;
	private String description;
	private Date date;
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
	public int getNonProfitId() {
		return nonProfitId;
	}
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
}
