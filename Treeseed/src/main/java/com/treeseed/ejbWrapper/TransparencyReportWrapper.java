package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.TransparencyReport;

public class TransparencyReportWrapper {
	
	private TransparencyReport wrapperObject;

	public TransparencyReportWrapper(TransparencyReport transparencyReport) {
		super();
		setWrapperObject(transparencyReport);
	}
	
	public TransparencyReportWrapper() {
		super();
		setWrapperObject(new TransparencyReport());
	}

	public TransparencyReport getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(TransparencyReport wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public int getId() {
		return wrapperObject.getId();
	}

	public void setId(int id) {
		wrapperObject.setId(id);
	}

	public double getAmountIn() {
		return wrapperObject.getAmountIn();
	}

	public void setAmountIn(double amountIn) {
		wrapperObject.setAmountIn(amountIn);
	}

	public double getAmountOut() {
		return wrapperObject.getAmountOut();
	}

	public void setAmountOut(double amountOut) {
		wrapperObject.setAmountOut(amountOut);
	}

	public Date getDateTime() {
		return wrapperObject.getDateTime();
	}

	public void setDateTime(Date dateTime) {
		wrapperObject.setDateTime(dateTime);
	}

	public String getDescription() {
		return wrapperObject.getDescription();
	}

	public Nonprofit getNonprofit() {
		return wrapperObject.getNonprofit();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	public void setNonprofit(Nonprofit nonprofit) {
		wrapperObject.setNonprofit(nonprofit);
	}

	public String toString() {
		return wrapperObject.toString();
	}

	
}
