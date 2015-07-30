package com.treeseed.contracts;

import java.util.Date;


import com.treeseed.pojo.PostNonprofitPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostNonprofitRequest.
 */
public class PostNonprofitRequest  extends BasePagingRequest{


	/** The post nonprofit. */
	private PostNonprofitPOJO postNonprofit;
	
	/**
	 * Gets the post nonprofit.
	 *
	 * @return the post nonprofit
	 */
	public PostNonprofitPOJO getPostNonprofit() {
		return postNonprofit;
	}

	/**
	 * Sets the post nonprofit.
	 *
	 * @param postNonprofit the new post nonprofit
	 */
	public void setPostNonprofit(PostNonprofitPOJO postNonprofit) {
		this.postNonprofit = postNonprofit;
	}
	
	
	
}
