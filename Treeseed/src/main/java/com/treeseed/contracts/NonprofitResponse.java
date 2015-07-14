package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.NonprofitPOJO;

public class NonprofitResponse extends BaseResponse{
	
	private List<NonprofitPOJO> nonprofits;
	private NonprofitPOJO nonprofit;
	private int nonProfitId;
	private boolean isOwner;



	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public NonprofitResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<NonprofitPOJO> getNonprofits() {
		return nonprofits;
	}

	public void setNonprofits(List<NonprofitPOJO> nonprofits) {
		this.nonprofits = nonprofits;
	}
	
	public void setNonprofit(NonprofitPOJO nonprofit) {
		this.nonprofit = nonprofit;
	}
	

	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}

	public int getNonProfitId() {
		return nonProfitId;
	}

	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	
	

}
