package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.GeneralSearchResultPOJO;

public class GeneralSearchResponse extends BaseResponse{

	private  List<GeneralSearchResultPOJO> results;

	public List<GeneralSearchResultPOJO> getResults() {
		return results;
	}

	public void setResults(List<GeneralSearchResultPOJO> results) {
		this.results = results;
	}
	
}
