package com.treeseed.services;

import java.sql.Date;

public interface DonationServiceInterface {
	
	double findAmountPerMonthOfNonProfit(int nonProfitId, Date startDate, Date endDate);
	
}
