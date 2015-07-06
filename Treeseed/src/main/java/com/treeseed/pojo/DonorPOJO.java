package com.treeseed.pojo;

import java.sql.Date;

public class DonorPOJO {
	public DonorPOJO() {
		super();
	}
	private int id;

	private String name;
	
	private String lastName;
	
	private Date dateTime;
	
	private boolean isActive;
	
	private String profilePicture;
	
	private String webPage;

	private String country;

	private String description;
	
	private UserGeneralPOJO userGeneral;
	
	private String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserGeneralPOJO getUserGeneral() {
		return userGeneral;
	}

	public void setUserGeneral(UserGeneralPOJO userGeneral) {
		this.userGeneral = userGeneral;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}