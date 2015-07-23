package com.treeseed.services;

import org.springframework.data.domain.Page;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;

public interface PostNonprofitServiceInterface {
	
	public  int savePostNonprofit(PostNonprofitWrapper wrapper);
	
	public  Page<PostNonprofit> getPosts(PostNonprofitRequest postRequest);

	public PostNonprofit updatePostNonprofit(PostNonprofitWrapper wrapper);
}
