package com.treeseed.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;
import com.treeseed.repositories.DonationRepository;
import com.treeseed.repositories.RecurrableDonationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RecurrableDonationService.
 */
@Service
public class RecurrableDonationService implements RecurrableDonationServiceInterface{
	
	/** The recurrable donation repository. */
	@Autowired
	RecurrableDonationRepository recurrableDonationRepository;


	/* (non-Javadoc)
	 * @see com.treeseed.services.RecurrableDonationServiceInterface#saveRecurrableDonation(com.treeseed.ejbWrapper.RecurrableDonationWrapper)
	 */
	@Override
	public RecurrableDonationWrapper saveRecurrableDonation(RecurrableDonationWrapper recurrableDonation) {
		RecurrableDonationWrapper recurrableDonationSaved = new RecurrableDonationWrapper(recurrableDonationRepository.save(recurrableDonation.getWrapperObject()));
		return recurrableDonationSaved;
	}

}
