package com.treeseed.contracts;

import java.util.Date;


import com.treeseed.pojo.PostNonprofitPOJO;

public class PostNonprofitRequest  extends BasePagingRequest{


	private PostNonprofitPOJO postNonprofit;
	
	
	public PostNonprofitPOJO getPostNonprofit() {
		return postNonprofit;
	}
	public void setPostNonprofit(PostNonprofitPOJO postNonprofit) {
		this.postNonprofit = postNonprofit;
	}
	
}
