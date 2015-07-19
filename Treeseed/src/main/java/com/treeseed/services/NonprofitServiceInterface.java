package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;

public interface NonprofitServiceInterface {

	Page<Nonprofit> getNonProfit(NonprofitRequest ur);

	int saveNonprofit(NonprofitWrapper nonprofit);

	NonprofitWrapper getSessionNonprofit(int idNonprofit);

	public Page<Nonprofit> getByName(String name);
	
	Page<Nonprofit> getByCountry(NonprofitRequest ur);
	
	Page<Nonprofit> getByCause(NonprofitRequest ur);
	
	Nonprofit getNonProfitByID(NonprofitRequest ur);
	
	Nonprofit getNonProfitById(int id);
	
	Nonprofit updateNonProfit(NonprofitWrapper nonprofit);
}
