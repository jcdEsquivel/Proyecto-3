package com.treeseed.pojo;

import com.treeseed.ejb.Donor;

public class DonorPOJO {
	
	private int id;

	private String description;

	private String lastName;

	private String name;

	private String profilePicture;

	private String webPage;
	
	private int country;
	
	private Donor father;
	
	private int type;
	

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCatalog() {
		return country;
	}

	public Donor getFatherDonor() {
		return father;
	}

	public void setFatherDonor(Donor fatherDonor) {
		this.father = fatherDonor;
	}

	public void setCatalog(int catalog) {
		this.country = catalog;
	}

	public DonorPOJO() {
		System.out.println("test");
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getWebPage() {
		return this.webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

}
