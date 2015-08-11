package com.treeseed.services;

import com.treeseed.pojo.RecurrableInformationResultPOJO;
import java.util.List;

public interface RecurrableInformationServiceInterface {
	
	/**
	 * Returns the list with the recurrable information results.
	 *
	 * @return the RecurrableInformationResultPOJO list
	 * @param the donorId
	 */
	public List<RecurrableInformationResultPOJO> getRecurrableInformation(int donorId);
	
}
