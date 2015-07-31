package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.treeseed.ejb.TransparencyReport;

public interface TransparencyReportRepository extends CrudRepository <TransparencyReport,Integer>{
	
	/**
	 * Find by nonprofitId.
	 *
	 * @param nonProfit id
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<TransparencyReport> findByNonprofitId(int nonprofitId, Pageable pageable);
		
}
