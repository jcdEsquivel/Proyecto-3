package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.TransparencyReportRequest;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;
import com.treeseed.utils.PageWrapper;


public interface TransparencyReportServiceInterface {

	TransparencyReport saveTransparencyReport(TransparencyReportWrapper transparencyReport);
	
	/**
	 * Gets the transparency reports.
	 *
	 * @param tr the tr
	 * @return Page  with the reports
	 */
	 PageWrapper<TransparencyReportWrapper> findTransparencyReport(TransparencyReportRequest tr);
}
