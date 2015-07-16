package com.treeseed.contracts;

import java.util.Date;

public class PostNonprofitRequest {

	private int nonprofitId;
	private String descripcion;
	private String picture;
	private String title;
	
	public int getNonprofitId() {
		return nonprofitId;
	}
	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}


	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	
}
