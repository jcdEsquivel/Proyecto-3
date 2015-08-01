package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.PostNonprofitPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostNonprofitResponse.
 */
public class PostNonprofitResponse extends BaseResponse {

	/** The posts. */
	private List<PostNonprofitPOJO> posts;
	
	/** The post. */
	private PostNonprofitPOJO post;
	
	/**
	 * Gets the post.
	 *
	 * @return the post
	 */
	public PostNonprofitPOJO getPost() {
		return post;
	}

	/**
	 * Sets the post.
	 *
	 * @param post the new post
	 */
	public void setPost(PostNonprofitPOJO post) {
		this.post = post;
	}

	/**
	 * Gets the posts.
	 *
	 * @return the posts
	 */
	public List<PostNonprofitPOJO> getPosts() {
		return posts;
	}

	/**
	 * Sets the posts.
	 *
	 * @param posts the new posts
	 */
	public void setPosts(List<PostNonprofitPOJO> posts) {
		this.posts = posts;
	}

	
}
