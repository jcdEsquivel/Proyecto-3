package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.utils.PageWrapper;


// TODO: Auto-generated Javadoc
/**
 * The Interface CampaignServiceInterface.
 */
public interface CampaignServiceInterface {
	
	/**
	 * Gets the all campaigns.
	 *
	 * @param ur the ur
	 * @return the all campaigns
	 */
	public Page<Campaign> getAllCampaigns(CampaignRequest ur);
	
	/**
	 * Find campaigns from nonprofit.
	 *
	 * @param ur as CampaignRequest
	 * @return the page wrapper
	 */
	public PageWrapper<CampaignWrapper> findCampaignsFromNonprofit(CampaignRequest ur);


	/**
	 * Save campaign.
	 *
	 * @param nonProfit the non profit
	 * @return the int
	 */
	public int saveCampaign(CampaignWrapper nonProfit);

	
	/**
	 * Gets the campaigns by nonprofit.
	 *
	 * @param ur as  CampaignRequest
	 * @return the campaigns by nonprofit
	 */
	public PageWrapper<CampaignWrapper> getCampaignsByNonprofit(CampaignRequest ur);

	
	/**
	 * Gets the campaign by id.
	 *
	 * @param id the id
	 * @return the campaign by id
	 */
	CampaignWrapper getCampaignById(int id);
	
	/**
	 * Updates campaign.
	 *
	 * @param campaign wrapper
	 */
	void updateCampaign(CampaignWrapper campaign);

	/**
	 * Close campaign.
	 *
	 * @param id the id
	 */
	void closeCampaign(int id);
	
	/**
	 * Donor recomendation.
	 *
	 * @param idDonor the id donor
	 * @return the list
	 */
	List<CampaignWrapper> donorRecomendation(int idDonor);
	
	/**
	 * Donor recomendation random.
	 *
	 * @return the list
	 */
	List<CampaignWrapper> donorRecomendationRandom(List<Integer> list);
}
