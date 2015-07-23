package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.utils.PageWrapper;


public interface CampaignServiceInterface {
	
	public Page<Campaign> getAllCampaigns(CampaignRequest ur);
	
	public PageWrapper<CampaignWrapper> findCampaignsFromNonprofit(CampaignRequest ur);

	public int saveCampaign(CampaignWrapper nonProfit);
	public PageWrapper<CampaignWrapper> getCampaignsByNonprofit(CampaignRequest ur);
}
