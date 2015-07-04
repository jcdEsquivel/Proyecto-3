package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;

public interface NonprofitServiceInterface {

	Page<Nonprofit> getAll(NonprofitRequest ur);

	Boolean saveNonprofit(NonprofitWrapper nonprofit);

	Nonprofit getSessionNonprofit(int idNonprofit);

}
