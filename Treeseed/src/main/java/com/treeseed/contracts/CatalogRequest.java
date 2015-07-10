package com.treeseed.contracts;

import com.treeseed.pojo.CatalogPOJO;

public class CatalogRequest {
	
	private CatalogPOJO catalog;
	
	private String lenguage;
	private String type;

	public CatalogRequest() {
		super();
	}
	
	public CatalogPOJO getDonor() {
		return catalog;
	}
	
	public void setCatalog(CatalogPOJO catalog) {
		this.catalog = catalog;
	}

	@Override
	public String toString() {
		return "CatalogRequest [catalog=" + catalog + "]";
	}

	public String getLenguage() {
		return lenguage;
	}

	public void setLenguage(String lenguage) {
		this.lenguage = lenguage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
