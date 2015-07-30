package com.treeseed.controllers;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.TransparencyReportRequest;
import com.treeseed.contracts.TransparencyReportResponse;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;
import com.treeseed.pojo.TransparencyReportPOJO;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.TransparencyReportServiceInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class TransparencyReportController.
 */
@RestController
@RequestMapping(value = "rest/protected/transparencyReport")
public class TransparencyReportController {
	
	/** The transparency report service. */
	@Autowired
	TransparencyReportServiceInterface transparencyReportService;
	
	/** The servlet context. */
	@Autowired
	ServletContext servletContext;
	
	/** The request. */
	@Autowired
	HttpServletRequest request;
	
	/** The nonprofit service. */
	@Autowired
	NonprofitServiceInterface nonprofitService;
	
	/**
	 * Creates the transparency report.
	 *
	 * @param tr the tr
	 * @return the transparency report response
	 */
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public TransparencyReportResponse createTransparencyReport(@RequestBody TransparencyReportRequest tr){	
		
		NonprofitWrapper nonprofit = nonprofitService.getSessionNonprofit(tr.getTransparencyReport().getNonProfitId());
		TransparencyReport transparencyReport = new TransparencyReport();
		TransparencyReportPOJO transparencyReportPOJO = new TransparencyReportPOJO();
		TransparencyReportWrapper transparancyReportWrapper = new TransparencyReportWrapper();
		TransparencyReportResponse ts = new TransparencyReportResponse();
		
		if(nonprofit != null){
			
			transparancyReportWrapper.setAmountIn(tr.getTransparencyReport().getAmountIn());
			transparancyReportWrapper.setAmountOut(tr.getTransparencyReport().getAmountOut());
			transparancyReportWrapper.setDateTime(new Date());
			transparancyReportWrapper.setDescription(tr.getTransparencyReport().getDescription());
			transparancyReportWrapper.setNonprofit(nonprofit.getWrapperObject());
			
			transparencyReport = transparencyReportService.saveTransparencyReport(transparancyReportWrapper);
			
			if(transparencyReport!=null){
				
				transparencyReportPOJO.setAmountIn(transparencyReport.getAmountIn());
				transparencyReportPOJO.setAmountOut(transparencyReport.getAmountOut());
				//transparencyReportPOJO.setDate(transparencyReport.getDateTime());
				transparencyReportPOJO.setDescription(transparencyReport.getDescription());
				transparencyReportPOJO.setId(transparencyReport.getId());
				
			}
			
			ts.setTransparencyReport(transparencyReportPOJO);
			
			ts.setCode(200);
			ts.setCodeMessage("Transaparency Report created successfully");
		}
		return ts;	
	}
}
