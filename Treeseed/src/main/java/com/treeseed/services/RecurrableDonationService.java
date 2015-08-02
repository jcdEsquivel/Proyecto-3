package com.treeseed.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;
import com.treeseed.repositories.DonationRepository;
import com.treeseed.repositories.RecurrableDonationRepository;

@Service
public class RecurrableDonationService implements RecurrableDonationServiceInterface{
	
	@Autowired
	RecurrableDonationRepository recurrableDonationRepository;


	@Override
	public RecurrableDonationWrapper saveRecurrableDonation(RecurrableDonationWrapper recurrableDonation) {
		RecurrableDonationWrapper recurrableDonationSaved = new RecurrableDonationWrapper(recurrableDonationRepository.save(recurrableDonation.getWrapperObject()));
		return recurrableDonationSaved;
	}

}
