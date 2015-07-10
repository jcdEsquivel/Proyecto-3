package com.treeseed.contracts;

import com.treeseed.pojo.DonorPOJO;

public class DonorRequest extends BasePagingRequest {
	
	private DonorPOJO donor;
	
	public DonorRequest() {
		super();
	}
	
	public DonorPOJO getDonor() {
		return donor;
	}
	
	public void setUser(DonorPOJO user) {
		this.donor = user;
	}

	@Override
	public String toString() {
		return "UsersRequest [user=" + donor + "]";
	}
}
