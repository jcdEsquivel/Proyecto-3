package com.treeseed.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NonprofitPOJO {
	public NonprofitPOJO() {
		super();
	}

	private int id;

	private String banKAccount;

	private Date dateTime;

	private String description;

	private String mainPicture;

	private String mision;

	private String name;

	private String profilePicture;

	private String reason;

	private String webPage;
	
	private UserGeneralPOJO userGeneral;
	
	private boolean isActive;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBanKAccount() {
		return banKAccount;
	}

	public void setBanKAccount(String banKAccount) {
		this.banKAccount = banKAccount;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}

	public String getMision() {
		return mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public UserGeneralPOJO getUserGeneral() {
		return userGeneral;
	}

	public void setUserGeneral(UserGeneralPOJO userGeneral) {
		this.userGeneral = userGeneral;
	}

}
