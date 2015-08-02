package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import com.treeseed.ejb.Donation;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;

public interface RecurrableDonationServiceInterface {
	
	RecurrableDonationWrapper saveRecurrableDonation(RecurrableDonationWrapper recurrableDonation);
	
}
