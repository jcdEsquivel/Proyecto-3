package com.treeseed.services;

import com.treeseed.pojo.RecurrableInformationResultPOJO;
import java.util.List;

public interface RecurrableInformationServiceInterface {
	
	public List<RecurrableInformationResultPOJO> getRecurrableInformation(int donorId);
	
}
