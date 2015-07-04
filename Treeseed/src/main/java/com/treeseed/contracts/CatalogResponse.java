package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.CatalogPOJO;

public class CatalogResponse extends BaseResponse {
	
private List<CatalogPOJO> catalogs;
	
	public CatalogResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<CatalogPOJO> getCatalogs() {
		return catalogs;
	}

	public void setUsuarios(List<CatalogPOJO> catalogs) {
		this.catalogs = catalogs;
	}

}
