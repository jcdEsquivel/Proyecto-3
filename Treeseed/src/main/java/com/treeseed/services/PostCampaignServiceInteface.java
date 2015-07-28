package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.ejb.PostCampaign;

public interface PostCampaignServiceInteface {
	
	/**
	 * Gets the post of campaigns.
	 *
	 * @param PostCampaignRequest postRequest
	 * @return Page<PostCampaign>
	 */
	public  Page<PostCampaign> getPosts(PostCampaignRequest postRequest);

}
