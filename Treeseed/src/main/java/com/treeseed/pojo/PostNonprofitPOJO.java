package com.treeseed.pojo;

public class PostNonprofitPOJO {
	private int id;
	private String title;
	private String picture;
	private String description;
	private int nonprofitId;
	private String date;
	
	

	public PostNonprofitPOJO() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNonprofitId() {
		return nonprofitId;
	}
	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
