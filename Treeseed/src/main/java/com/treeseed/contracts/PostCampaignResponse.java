package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.PostCampaignPOJO;;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignResponse.
 */
public class PostCampaignResponse  extends BaseResponse {
	
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
	 * @param posts the new posts
	 */
	public void setPosts(List<PostCampaignPOJO> posts) {
		this.posts = posts;
	}

}
