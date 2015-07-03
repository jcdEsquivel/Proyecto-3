package com.treeseed.contracts;

import com.treeseed.pojo.CatalogPOJO;

public class CatalogRequest {
	
private CatalogPOJO catalog;
	
	public CatalogRequest() {
		super();
	}
	
	public CatalogPOJO getDonor() {
		return catalog;
	}
	
	public void setUser(CatalogPOJO user) {
		this.catalog = user;
	}

	@Override
	public String toString() {
		return "UsersRequest [user=" + catalog + "]";
	}

}
