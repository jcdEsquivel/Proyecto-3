package com.treeseed.contracts;

import com.treeseed.pojo.NonprofitPOJO;

public class NonprofitRequest extends BasePagingRequest {
	
	private NonprofitPOJO nonprofit;
	
	public NonprofitRequest() {
		super();
	}
	
	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}
	
	public void setNonprofit(NonprofitPOJO user) {
		this.nonprofit = user;
	}

	@Override
	public String toString() {
		return "NonprofitRequest [nonprofit=" + nonprofit + "]";
	}
}
