package com.treeseed.contracts;

import com.treeseed.pojo.PostCampaignPOJO;;

public class PostCampaignRequest extends BasePagingRequest {
	
	private PostCampaignPOJO postCampaign;

	public PostCampaignPOJO getPostCampaign() {
		return postCampaign;
	}

	public void setPostNonprofit(PostCampaignPOJO postCampaign) {
		this.postCampaign = postCampaign;
	}

}
