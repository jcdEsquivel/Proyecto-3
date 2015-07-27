package com.treeseed.services;

import org.springframework.data.domain.Page;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostNonprofitServiceInterface.
 */
public interface PostNonprofitServiceInterface {
	
	/**
	 * Save post nonprofit.
	 *
	 * @param wrapper the wrapper
	 * @return the int
	 */
	public  int savePostNonprofit(PostNonprofitWrapper wrapper);
	
	/**
	 * Gets the posts.
	 *
	 * @param postRequest the post request
	 * @return the posts
	 */
	public  Page<PostNonprofit> getPosts(PostNonprofitRequest postRequest);

	/**
	 * Update post nonprofit.
	 *
	 * @param wrapper the wrapper
	 * @return the post nonprofit wrapper
	 */
	public PostNonprofitWrapper updatePostNonprofit(PostNonprofitWrapper wrapper);
	
	/**
	 * Delete post nonprofit.
	 *
	 * @param request the request
	 */
	public void deletePostNonprofit(PostNonprofitRequest request);
}
