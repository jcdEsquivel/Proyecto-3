package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.CampaignPOJO;

public interface CampaignServiceInterface {
	
	Page<Campaign> getAllCampaigns(CampaignRequest ur);
}
