package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignResponse.
 */
public class PostCampaignResponse extends BaseResponse {

	/** The posts. */
	private List<PostCampaignPOJO> posts;
	
	/**
	 * Gets the posts.
	 *
	 * @return the posts
	 */
	public List<PostCampaignPOJO> getPosts() {
		return posts;
	}

	/**
	 * Sets the posts.
	 *
	 * @param pojos the new posts
	 */
	public void setPosts(List<PostCampaignPOJO> pojos) {
		this.posts = pojos;
	}

	
}
