package com.treeseed.contracts;

import java.util.Date;


import com.treeseed.pojo.PostNonprofitPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostNonprofitRequest.
 */
public class PostNonprofitRequest  extends BasePagingRequest{


	/** The post nonprofit. */
	private PostNonprofitPOJO postNonprofit;
	
	/** The id. */
	private int id;
	
	/** The picture. */
	private String picture;
	
	/** The tittle. */
	private String tittle;
	
	/** The description. */
	private String description;
	

	/**
	 * Gets the tittle.
	 *
	 * @return the tittle
	 */
	public String getTittle() {
		return tittle;
	}

	/**
	 * Sets the tittle.
	 *
	 * @param tittle the new tittle
	 */
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the post nonprofit.
	 *
	 * @return the post nonprofit
	 */
	public PostNonprofitPOJO getPostNonprofit() {
		return postNonprofit;
	}

	/**
	 * Sets the post nonprofit.
	 *
	 * @param postNonprofit the new post nonprofit
	 */
	public void setPostNonprofit(PostNonprofitPOJO postNonprofit) {
		this.postNonprofit = postNonprofit;
	}
	
	
	
}
