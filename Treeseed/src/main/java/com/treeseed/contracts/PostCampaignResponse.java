package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.PostCampaignPOJO;;

public class PostCampaignResponse  extends BaseResponse {
	
private List<PostCampaignPOJO> posts;
	
	public List<PostCampaignPOJO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostCampaignPOJO> posts) {
		this.posts = posts;
	}

}
