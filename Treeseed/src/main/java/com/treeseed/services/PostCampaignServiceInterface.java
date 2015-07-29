package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostCampaignServiceInterface.
 */
public interface PostCampaignServiceInterface {
	
	/**
	 * Save post.
	 *
	 * @param wrapper the wrapper
	 * @return the int
	 */
	public  int savePost(PostCampaignWrapper wrapper);
	
	//public  Page<PostCampaignWrapper> getPosts(PostNonprofitRequest postRequest);
	
	
	public void deletePostCampaign(PostCampaignRequest request);
}
