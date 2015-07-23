package com.treeseed.contracts;

import java.util.Date;


import com.treeseed.pojo.PostNonprofitPOJO;

public class PostNonprofitRequest  extends BasePagingRequest{


	private PostNonprofitPOJO postNonprofit;
	private int id;
	private String picture;
	private String tittle;
	private String description;
	

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PostNonprofitPOJO getPostNonprofit() {
		return postNonprofit;
	}

	public void setPostNonprofit(PostNonprofitPOJO postNonprofit) {
		this.postNonprofit = postNonprofit;
	}
	
	
	
}
