package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejbWrapper.CampaignWrapper;


public interface CampaignServiceInterface {
	
	/**
	 * Gets the all campaigns.
	 *
	 * @param ur the Campaign Request
	 * @return the all campaigns
	 */
	Page<Campaign> getAllCampaigns(CampaignRequest ur);

	/**
	 * Save campaign.
	 *
	 * @param nonProfit the non profit
	 * @return the int
	 */
	int saveCampaign(CampaignWrapper campaign);
	
	/**
	 * Gets the campaigns by nonprofit.
	 *
	 * @param ur the Campaign Request
	 * @return the campaigns by nonprofit
	 */
	Page<Campaign> getCampaignsByNonprofit(CampaignRequest ur);
	
	/**
	 * Gets the campaign by id.
	 *
	 * @param id the id
	 * @return the campaign by id
	 */
	CampaignWrapper getCampaignById(int id);
	
	void updateCampaign(CampaignWrapper campaign);

}
