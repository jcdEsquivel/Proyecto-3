package com.treeseed.contracts;

import java.util.Date;



import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignRequest.
 */
public class PostCampaignRequest  extends BasePagingRequest{


	/** The post campaign. */
	private PostCampaignPOJO postCampaign;
	
	/** The nonprofit id. */
	private int nonprofitId;

	/**
	 * Gets the nonprofit id.
	 *
	 * @return the nonprofit id
	 */
	public int getNonprofitId() {
		return nonprofitId;
	}

	/**
	 * Sets the nonprofit id.
	 *
	 * @param nonprofitId the new nonprofit id
	 */
	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}

	/**
	 * Gets the post campaign.
	 *
	 * @return the post campaign
	 */
	public PostCampaignPOJO getPostCampaign() {
		return postCampaign;
	}

	/**
	 * Sets the post campaign.
	 *
	 * @param postNonprofit the new post campaign
	 */
	public void setPostCampaign(PostCampaignPOJO postNonprofit) {
		this.postCampaign = postNonprofit;
	}
	
	
	
}
