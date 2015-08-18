package com.treeseed.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.contracts.TransparencyReportRequest;
import com.treeseed.contracts.TransparencyReportResponse;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.TransparencyReportPOJO;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.TransparencyReportServiceInterface;
import com.treeseed.utils.PageWrapper;

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
	
	/** The donation report service. */
	@Autowired
	DonationServiceInterface donationService;
	
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
		
		TransparencyReportResponse ts = new TransparencyReportResponse();
		
		try{
		
			NonprofitWrapper nonprofit = nonprofitService.getSessionNonprofit(tr.getTransparencyReport().getNonProfitId());
			TransparencyReport transparencyReport = new TransparencyReport();
			TransparencyReportPOJO transparencyReportPOJO = new TransparencyReportPOJO();
			TransparencyReportWrapper transparancyReportWrapper = new TransparencyReportWrapper();
			
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
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				ts.setCode(10);
				ts.setErrorMessage("Data Base error");
			}else{
				ts.setCode(500);
			}
		}	
		return ts;	
	}
	
	/**
	 * Gets the transparency reports.
	 *
	 * @param tr the transparect report request
	 * @return the transparency report response
	 */
	@RequestMapping(value ="/getTransparencyReports", method = RequestMethod.POST)
	@Transactional
	public TransparencyReportResponse getTransparencyReports(@RequestBody TransparencyReportRequest tr){	
		
		TransparencyReportResponse ts = new TransparencyReportResponse();
		
		try{
			TransparencyReportPOJO transparencyReportPOJO = null;	
			PageWrapper<TransparencyReportWrapper> pageResults = null;
			List<TransparencyReportPOJO> transparencyReportsPOJO = new ArrayList<TransparencyReportPOJO>();
			
			tr.setPageNumber(tr.getPageNumber()-1);
			pageResults = transparencyReportService.findTransparencyReport(tr);		
			
			ts.setTotalElements(pageResults.getTotalItems());
			
			for(TransparencyReportWrapper objeto: pageResults.getResults())
			{
				transparencyReportPOJO = new TransparencyReportPOJO();
				transparencyReportPOJO.setId(objeto.getId());
				transparencyReportPOJO.setDate(objeto.getDateTime());
				transparencyReportPOJO.setDateS(new SimpleDateFormat("dd MMMMM yyyy").format(objeto.getDateTime()));
				transparencyReportPOJO.setDescription(objeto.getDescription());
				transparencyReportPOJO.setAmountIn(objeto.getAmountIn());
				transparencyReportPOJO.setAmountOut(objeto.getAmountOut());
				
				transparencyReportsPOJO.add(transparencyReportPOJO);
			};
	
			ts.setTransparencyReports(transparencyReportsPOJO);
			
			if(transparencyReportsPOJO.size()>0){
				ts.setCodeMessage("Transparency reports fetch successfully");
				ts.setCode(200);
			}else{
				ts.setErrorMessage("Transparency reports fetch unsuccessfully");
				ts.setCode(400);
			}
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				ts.setCode(10);
				ts.setErrorMessage("Data Base error");
			}else{
				ts.setCode(500);
			}
		}	
		
		return ts;
	}
}
