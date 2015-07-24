package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.PostNonprofitPOJO;

public class PostNonprofitResponse extends BaseResponse {

	private List<PostNonprofitPOJO> posts;
	private PostNonprofitPOJO post;
	
	public PostNonprofitPOJO getPost() {
		return post;
	}

	public void setPost(PostNonprofitPOJO post) {
		this.post = post;
	}

	public List<PostNonprofitPOJO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostNonprofitPOJO> posts) {
		this.posts = posts;
	}

	
}
