package com.treeseed.services;

import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;


public interface TransparencyReportServiceInterface {

	TransparencyReport saveTransparencyReport(TransparencyReportWrapper transparencyReport);
}
