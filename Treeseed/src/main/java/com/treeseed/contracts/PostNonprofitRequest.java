package com.treeseed.contracts;

import java.util.Date;

public class PostNonprofitRequest {

	public int getNonprofitId() {
		return nonprofitId;
	}
	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private int nonprofitId;
	private Date creationDate;
	private String descripcion;
	private boolean isActive;
	private String picture;
	private String title;
	
}
