package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import com.treeseed.ejb.Donation;
import com.treeseed.ejbWrapper.DonationWrapper;

public interface DonationServiceInterface {
	
	double findAmountPerMonthOfNonProfit(int nonProfitId, Date startDate, Date endDate);
	int findDonorsPerCampaign(int campaignId);
	DonationWrapper saveDonation(DonationWrapper donation);
	void updateDonation(DonationWrapper donation);
	
}
