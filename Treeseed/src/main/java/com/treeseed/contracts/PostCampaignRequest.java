package com.treeseed.contracts;

import java.util.Date;



import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;

public class PostCampaignRequest  extends BasePagingRequest{


	private PostCampaignPOJO postCampaign;

	public PostCampaignPOJO getPostCampaign() {
		return postCampaign;
	}

	public void setPostCampaign(PostCampaignPOJO postNonprofit) {
		this.postCampaign = postNonprofit;
	}
	
	
	
}
