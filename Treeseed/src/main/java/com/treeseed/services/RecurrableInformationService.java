package com.treeseed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treeseed.pojo.RecurrableInformationResultPOJO;
import com.treeseed.repositories.RecurrableInformationRepositoryInterface;

@Service
public class RecurrableInformationService implements RecurrableInformationServiceInterface{
	
	@Autowired 
	RecurrableInformationRepositoryInterface repository;
	
	/**
	 * Returns the list with the recurrable information results.
	 *
	 * @return the RecurrableInformationResultPOJO
	 * @param the donorId
	 */
	@Override
	public List<RecurrableInformationResultPOJO> getRecurrableInformation(
			int donorId) {
		return  repository.getRecurrableInformation(donorId);
	}

}
