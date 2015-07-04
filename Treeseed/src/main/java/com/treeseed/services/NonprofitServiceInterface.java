package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;

public interface NonprofitServiceInterface {

	Page<Nonprofit> getAll(NonprofitRequest ur);

	Boolean saveNonprofit(Nonprofit nonprofit);

	Nonprofit getSessionNonprofit(int idNonprofit);

	Page<Nonprofit> getByName(NonprofitRequest ur);
	
	Page<Nonprofit> getByCountry(NonprofitRequest ur);
	
	Page<Nonprofit> getByCause(NonprofitRequest ur);
}
