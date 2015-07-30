package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import com.treeseed.ejb.Donation;

public interface DonationServiceInterface {
	
	double findAmountPerMonthOfNonProfit(int nonProfitId, Date startDate, Date endDate);
	int findDonorsPerCampaign(int campaignId);
	
}
