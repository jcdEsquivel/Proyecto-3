package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.NonprofitPOJO;

public class NonprofitResponse extends BaseResponse{
	
	private List<NonprofitPOJO> nonprofits;

	public NonprofitResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<NonprofitPOJO> getNonprofits() {
		return nonprofits;
	}

	public void setNonprofits(List<NonprofitPOJO> usuarios) {
		this.nonprofits = usuarios;
	}

}
