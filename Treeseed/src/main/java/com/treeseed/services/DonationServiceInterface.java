package com.treeseed.services;

import java.sql.Date;
import com.treeseed.contracts.DonationRequest;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.utils.PageWrapper;

public interface DonationServiceInterface {
	
	double findAmountPerMonthOfNonProfit(int nonProfitId, Date startDate, Date endDate);
	
	int findDonorsPerCampaign(int campaignId);
	
	PageWrapper<DonationWrapper> findDonationsOfDonor(DonationRequest dr);
}
