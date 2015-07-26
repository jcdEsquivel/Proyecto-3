package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.PostNonprofitPOJO;

public class PostCampaignResponse extends BaseResponse {

	private List<PostNonprofitPOJO> posts;
	
	public List<PostNonprofitPOJO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostNonprofitPOJO> posts) {
		this.posts = posts;
	}

	
}
