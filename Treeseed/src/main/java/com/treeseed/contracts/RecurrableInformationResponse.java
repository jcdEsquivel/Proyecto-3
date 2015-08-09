package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.RecurrableInformationResultPOJO;

public class RecurrableInformationResponse extends BaseResponse{

	private  List<RecurrableInformationResultPOJO> results;

	/**
	 * Gets the recurrable information results.
	 *
	 * @return the RecurrableInformationResultPOJO list
	 */
	public List<RecurrableInformationResultPOJO> getResults() {
		return results;
	}
	/**
	 * Sets the recurrable information results to be returned.
	 *
	 */
	public void setResults(List<RecurrableInformationResultPOJO> results) {
		this.results = results;
	}
}
