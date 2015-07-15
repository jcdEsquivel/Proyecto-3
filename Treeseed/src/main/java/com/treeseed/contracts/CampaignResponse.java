package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.CampaignPOJO;


public class CampaignResponse extends BaseResponse {
	
	private List<CampaignPOJO> campaigns;
	private CampaignPOJO campaign;
	private int campaignId;
	private boolean isOwner;
	public List<CampaignPOJO> getCampaigns() {
		return campaigns;
	}
	
	public void setCampaigns(List<CampaignPOJO> campaigns) {
		this.campaigns = campaigns;
	}
	public CampaignPOJO getCampaign() {
		return campaign;
	}
	public void setCampaign(CampaignPOJO campaign) {
		this.campaign = campaign;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	

}
