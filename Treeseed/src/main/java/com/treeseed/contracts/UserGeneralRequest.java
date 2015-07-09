package com.treeseed.contracts;

import com.treeseed.pojo.UserGeneralPOJO;

public class UserGeneralRequest extends BasePagingRequest {
	
	private UserGeneralPOJO userGeneral;
	
	public UserGeneralRequest() {
		super();
	}
	
	public UserGeneralPOJO getUserGeneral() {
		return userGeneral;
	}
	
	public void setUserGeneral(UserGeneralPOJO user) {
		this.userGeneral = user;
	}

	@Override
	public String toString() {
		return "UserGeneralRequest [userGeneral=" + userGeneral + "]";
	}
}
