package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Donor;
import com.treeseed.ejb.TransparencyReport;

public interface TransparencyReportRepository extends CrudRepository <TransparencyReport,Integer>{
	
	/**
	 * Find by nonprofitId.
	 *
	 * @param nonProfit id
	 * @Param String monthNull,
	 * @Param String month,
	 * @Param String yearNull,
	 * @Param String year,
     * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT t FROM TransparencyReport t inner join t.nonprofit n WHERE( n.id = :nonprofitId) and "
			+ "( :monthNull is null or MONTH(t.dateTime)=:month) and "
			+ "( :yearNull is null or YEAR(t.dateTime)=:year)")
	public Page<TransparencyReport> findByNonprofitIdAndDate(@Param("nonprofitId") int nonprofitId,
															@Param("monthNull") String monthNull,
															@Param("month") int month,
															@Param("yearNull") String yearNull,
															@Param("year") int year,
															Pageable pageable);
		
}
