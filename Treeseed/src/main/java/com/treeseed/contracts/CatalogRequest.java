package com.treeseed.contracts;

import com.treeseed.pojo.CatalogPOJO;

public class CatalogRequest {
	
private CatalogPOJO catalog;
	
	public CatalogRequest() {
		super();
	}
	
	public CatalogPOJO getCatalog() {
		return catalog;
	}
	
	public void setCatalog(CatalogPOJO user) {
		this.catalog = user;
	}

	@Override
	public String toString() {
		return "CatalogRequest [Catalog=" + catalog + "]";
	}

}
