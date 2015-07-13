package com.treeseed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treeseed.pojo.GeneralSearchResultPOJO;
import com.treeseed.repositories.GeneralSearchRepositoryInterface;

@Service
public class GeneralSearchService implements GeneralSearchServiceInterface{

	@Autowired GeneralSearchRepositoryInterface repository;
	
	@Override
	public List<GeneralSearchResultPOJO> search(String filter, String country) {
		
		if(filter.isEmpty()){
			filter = null;
		}
		
		if(country.isEmpty()){
			country = null;
		}
		return  repository.search(filter, country);
	}

}
