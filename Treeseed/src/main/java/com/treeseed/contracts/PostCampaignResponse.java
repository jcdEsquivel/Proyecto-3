package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.PostNonprofitPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignResponse.
 */
public class PostCampaignResponse extends BaseResponse {

	/** The posts. */
	private List<PostNonprofitPOJO> posts;
	
	/**
	 * Gets the posts.
	 *
	 * @return the posts
	 */
	public List<PostNonprofitPOJO> getPosts() {
		return posts;
	}

	/**
	 * Sets the posts.
	 *
	 * @param posts the new posts
	 */
	public void setPosts(List<PostNonprofitPOJO> posts) {
		this.posts = posts;
	}

	
}
