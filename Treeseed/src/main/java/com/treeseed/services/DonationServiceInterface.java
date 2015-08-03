package com.treeseed.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Donation;
import com.treeseed.ejb.Nonprofit;

public interface DonationServiceInterface {
	
	double findAmountPerMonthOfNonProfit(int nonProfitId, Date startDate, Date endDate);
	int findDonorsPerCampaign(int campaignId);
	
	Page<Donation> getDonations(DonationRequest ur);
}
