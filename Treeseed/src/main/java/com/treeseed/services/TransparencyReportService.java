package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.treeseed.contracts.TransparencyReportRequest;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;
import com.treeseed.repositories.TransparencyReportRepository;
import com.treeseed.utils.PageWrapper;

@Service
public class TransparencyReportService implements TransparencyReportServiceInterface{
	
	@Autowired
	TransparencyReportRepository transparencyReportRepository;

	@Override
	public TransparencyReport saveTransparencyReport(
			TransparencyReportWrapper transparencyReportWrapper) {
		TransparencyReport transparencyReport = 
				transparencyReportRepository.save(transparencyReportWrapper.getWrapperObject());
		return transparencyReport;
	}
	
	/**
	 * Gets the transparency reports.
	 *
	 * @param tr the tr
	 * @return Page  with the reports
	 */
	@Override
	public PageWrapper<TransparencyReportWrapper> findTransparencyReport(
			TransparencyReportRequest tr) {
		
		Sort.Direction direction = Sort.Direction.DESC;
		PageRequest pr = null;
		PageWrapper<TransparencyReportWrapper> pageWrapper = new PageWrapper<TransparencyReportWrapper>();
		int month = 0;
		int year = 0;
		
		if (tr.getSortBy().size() > 0) {
			Sort sort = new Sort(direction, tr.getSortBy());
			pr = new PageRequest(tr.getPageNumber(), tr.getPageSize(), sort);
		} else {
			pr = new PageRequest(tr.getPageNumber(), tr.getPageSize());
		}
		
		//If we have correct data for month and year we should retrieve it
		//Otherwise it should always be null in order to create the query
		if(tr.getMonth() != null && tr.getMonth() != ""){
			month = Integer.parseInt(tr.getMonth());
		}
		else{
			tr.setMonth(null);
		}
		
		if(tr.getYear() != null && tr.getYear() != ""){
			year = Integer.parseInt(tr.getYear());
		}
		else{
			tr.setYear(null);
		}
			
		
		Page<TransparencyReport> transparencyReports = transparencyReportRepository.findByNonprofitIdAndDate(tr.getNonProfitId(), 
																											tr.getMonth(),
																											month,
																											tr.getYear(), 
																											year,
																											pr);
		 
		for(TransparencyReport t : transparencyReports.getContent()){
			pageWrapper.getResults().add(new TransparencyReportWrapper(t));
		}
		
		pageWrapper.setTotalItems(transparencyReports.getTotalElements());
		
		return pageWrapper;
	}
}
