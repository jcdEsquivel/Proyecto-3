package com.treeseed.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.treeseed.contracts.CampaignResponse;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.contracts.UserGeneralResponse;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.utils.Utils;

@RestController
@RequestMapping(value = "rest/protected/campaing")
public class CampaignController {
	
	@Autowired
	CampaignServiceInterface campaignService;
	@Autowired
	ServletContext servletContext;
	@Autowired
	HttpServletRequest request;	
	@Autowired
	NonprofitServiceInterface nonprofitService;
	
	@RequestMapping(value ="/advanceGet", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse getNonprofits(@RequestBody CampaignRequest cr){	
		
		cr.setPageNumber(cr.getPageNumber() - 1);
	
		Page<Campaign> viewCampaign = campaignService.getAllCampaigns(cr);
		
		CampaignResponse cs = new CampaignResponse();
	
		cs.setCodeMessage("campaigns fetch success");
		
		cs.setTotalElements(viewCampaign.getTotalElements());
		cs.setTotalPages(viewCampaign.getTotalPages());
		
		List<CampaignPOJO> viewCampaignPOJO = new ArrayList<CampaignPOJO>();
		
		for(Campaign objeto:viewCampaign.getContent())
		{
			CampaignPOJO campaign = new CampaignPOJO();
			campaign.setId(objeto.getId());
			campaign.setName(objeto.getName());
			campaign.setDescription(objeto.getDescription());
			campaign.setAmountCollected(objeto.getAmountCollected());
			campaign.setAmountGoal(objeto.getAmountGoal());
			campaign.setPicture(objeto.getPicture());
			
			NonprofitPOJO cp = new NonprofitPOJO();
			cp.setName(objeto.getNonprofit().getName());
			cp.setId(objeto.getNonprofit().getId());
			
			campaign.setNonprofit(cp);
			viewCampaignPOJO.add(campaign);
		};
		
		cs.setCampaigns(viewCampaignPOJO);
		cs.setCode(200);
		return cs;
			
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public CampaignResponse campaingCreate(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("date1") String date1, @RequestParam("date2") String date2,
			@RequestParam("amount") String amount, @RequestParam("idNonprofit") String idNonprofit,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		String resultFileName = null;
		NonprofitWrapper nonprofit = nonProfitService.getSessionNonprofit(Integer.parseInt(idNonprofit));
		CampaignResponse response = new CampaignResponse();
		String[] dateTmp;
		String[] dateTmp1;
		GregorianCalendar dueDate=new GregorianCalendar();
		GregorianCalendar startDate=new GregorianCalendar();

		if (nonprofit.getWrapperObject() != null) {
			if (file != null) {

				CampaignWrapper campaign = new CampaignWrapper();

				resultFileName = Utils.writeToFile(file, servletContext);

				if (!resultFileName.equals("")) {
					campaign.setPicture(resultFileName);
				} else {
					campaign.setPicture("");
				}
				date2=date2.replace('"','0');
				dateTmp=date2.split("-");
				dateTmp[2]=dateTmp[2].split("T")[0];
				
				
				
				dueDate.set(Integer.parseInt(dateTmp[0]), Integer.parseInt(dateTmp[1])-1, Integer.parseInt(dateTmp[2]), 23, 59,0);
				
				date1=date1.replace('"','0');
				dateTmp1=date1.split("-");
				dateTmp1[2]=dateTmp1[2].split("T")[0];
				
				startDate.set(Integer.parseInt(dateTmp[0]), Integer.parseInt(dateTmp[1])-1, Integer.parseInt(dateTmp[2])-1, 23, 59,0);
			
				campaign.setName(name);
				
				campaign.setActive(true);
				campaign.setCreationDate(new Date());
				campaign.setDescription(description);
				campaign.setDueDate(dueDate.getTime());
				campaign.setStartDate(startDate.getTime());
				campaign.setAmountGoal(Double.parseDouble(amount));
				campaign.setNonprofit(nonprofit.getWrapperObject());

				int campaingId = campaignService.saveCampaign(campaign);

				if (campaingId > 0) {
					response.setCode(200);
					response.setCodeMessage("campaign created successfully");

				}else{
					response.setCode(400);
					response.setCodeMessage("campaign creation unsuccessful");
				}
			} else {
				response.setCode(400);
				response.setCodeMessage("NO IMAGE SPECIFIED");
			}

		} else

		{
			response.setCode(400);
			response.setCodeMessage("NONPROFIT DO NOT EXIST");
		}

		return response;

	}

}
