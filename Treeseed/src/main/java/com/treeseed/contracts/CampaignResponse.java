package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.CampaignPOJO;


// TODO: Auto-generated Javadoc
/**
 * The Class CampaignResponse.
 */
public class CampaignResponse extends BaseResponse {
	
	/** The campaigns. */
	private List<CampaignPOJO> campaigns;
	
	/** The campaign. */
	private CampaignPOJO campaign;
	
	/** The campaign id. */
	private int campaignId;
	
	/** The is owner. */
	private boolean isOwner;
	
	/**
	 * Gets the campaigns.
	 *
	 * @return the campaigns
	 */
	public List<CampaignPOJO> getCampaigns() {
		return campaigns;
	}
	
	/**
	 * Sets the campaigns.
	 *
	 * @param campaigns the new campaigns
	 */
	public void setCampaigns(List<CampaignPOJO> campaigns) {
		this.campaigns = campaigns;
	}
	
	/**
	 * Gets the campaign.
	 *
	 * @return the campaign
	 */
	public CampaignPOJO getCampaign() {
		return campaign;
	}
	
	/**
	 * Sets the campaign.
	 *
	 * @param campaign the new campaign
	 */
	public void setCampaign(CampaignPOJO campaign) {
		this.campaign = campaign;
	}
	
	/**
	 * Gets the campaign id.
	 *
	 * @return the campaign id
	 */
	public int getCampaignId() {
		return campaignId;
	}
	
	/**
	 * Sets the campaign id.
	 *
	 * @param campaignId the new campaign id
	 */
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	
	/**
	 * Checks if is owner.
	 *
	 * @return true, if is owner
	 */
	public boolean isOwner() {
		return isOwner;
	}
	
	/**
	 * Sets the owner.
	 *
	 * @param isOwner the new owner
	 */
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	

}
