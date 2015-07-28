package com.treeseed.contracts;

import com.treeseed.pojo.PostCampaignPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignRequest.
 */
public class PostCampaignRequest extends BasePagingRequest {
	
	/** The post campaign. */
	private PostCampaignPOJO postCampaign;

	/**
	 * Gets the post campaign.
	 *
	 * @return the post campaign
	 */
	public PostCampaignPOJO getPostCampaign() {
		return postCampaign;
	}

	/**
	 * Sets the post nonprofit.
	 *
	 * @param postCampaign the new post nonprofit
	 */
	public void setPostNonprofit(PostCampaignPOJO postCampaign) {
		this.postCampaign = postCampaign;
	}

}
