package com.treeseed.pojo;

import java.util.Date;

public class UserGeneralPOJO {
	
	public UserGeneralPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int id;
	private String email;
	private String password;
	private boolean isActive;
	private NonprofitPOJO nonProfit;
	private Date dateTime;
	private String facebookToken;
	private String facebookId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public NonprofitPOJO getNonProfit() {
		return nonProfit;
	}
	public void setNonProfit(NonprofitPOJO nonProfit) {
		this.nonProfit = nonProfit;
	}
	public String getFacebookToken() {
		return facebookToken;
	}
	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookID) {
		this.facebookId = facebookID;
	}
	
	
}
