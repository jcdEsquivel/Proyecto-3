package com.treeseed.contracts;

import java.util.Date;



import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;

public class PostCampaignRequest  extends BasePagingRequest{


	private PostCampaignPOJO postCampaign;
	private int nonprofitId;

	public int getNonprofitId() {
		return nonprofitId;
	}

	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}

	public PostCampaignPOJO getPostCampaign() {
		return postCampaign;
	}

	public void setPostCampaign(PostCampaignPOJO postNonprofit) {
		this.postCampaign = postNonprofit;
	}
	
	
	
}
