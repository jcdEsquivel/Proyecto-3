package com.treeseed.services;

import java.util.List;

import com.treeseed.pojo.GeneralSearchResultPOJO;


public interface GeneralSearchServiceInterface {

	public List<GeneralSearchResultPOJO> search(String filter, String country);
	
}
