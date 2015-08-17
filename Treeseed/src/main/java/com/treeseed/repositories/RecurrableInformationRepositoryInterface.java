package com.treeseed.repositories;

import java.util.List;

import com.treeseed.pojo.RecurrableInformationResultPOJO;

public interface RecurrableInformationRepositoryInterface {
	
	/**
	 * Returns the list with the recurrable information results.
	 *
	 * @return the RecurrableInformationResultPOJO list
	 * @param the donorId
	 */
	public List<RecurrableInformationResultPOJO> getRecurrableInformation(int donorId);
}
