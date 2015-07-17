package com.treeseed.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.repositories.CampaignRepository;
import com.treeseed.repositories.NonprofitRepository;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/campaign")
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

}
