package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.ejb.PostCampaign;

public interface PostCampaignServiceInteface {
	
	public  Page<PostCampaign> getPosts(PostCampaignRequest postRequest);

}
