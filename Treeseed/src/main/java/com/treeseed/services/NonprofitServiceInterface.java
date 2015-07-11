package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;

public interface NonprofitServiceInterface {

	Page<Nonprofit> getNonProfit(NonprofitRequest ur);

	Boolean saveNonprofit(NonprofitWrapper nonprofit);

	Nonprofit getSessionNonprofit(int idNonprofit);

	Page<Nonprofit> getByName(NonprofitRequest ur);
	
	Page<Nonprofit> getByCountry(NonprofitRequest ur);
	
	Page<Nonprofit> getByCause(NonprofitRequest ur);
}
