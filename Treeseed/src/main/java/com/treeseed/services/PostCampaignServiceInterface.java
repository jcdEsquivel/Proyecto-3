package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.utils.PageWrapper;

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
	
	
	
	/**
	 * Gets the posts.
	 *
	 * @param postRequest the post request
	 * @return the posts
	 */
	public PageWrapper<PostCampaignWrapper> getPostsFromCampaign(PostCampaignRequest postRequest);
}
