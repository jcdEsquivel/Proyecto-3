package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;
import com.treeseed.repositories.TransparencyReportRepository;

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
}
