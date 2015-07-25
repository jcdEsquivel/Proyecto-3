package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;

public interface PostCampaignServiceInterface {
	
	public  int savePost(PostCampaignWrapper wrapper);
	
	//public  Page<PostCampaignWrapper> getPosts(PostNonprofitRequest postRequest);
	
}
