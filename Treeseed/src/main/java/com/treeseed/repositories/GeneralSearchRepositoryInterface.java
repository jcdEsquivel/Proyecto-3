package com.treeseed.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.treeseed.pojo.GeneralSearchResultPOJO;


public interface GeneralSearchRepositoryInterface {
	
	public List<GeneralSearchResultPOJO> search(String filter, String country);
	
}
