package com.treeseed.contracts;

import com.treeseed.pojo.NonprofitPOJO;

public class NonprofitRequest extends BasePagingRequest {
	
	private NonprofitPOJO nonprofit;
	private String name;
	private String country;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	private String cause;
	
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
