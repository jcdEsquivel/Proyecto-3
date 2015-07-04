package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.UserGeneralPOJO;

public class UserGeneralResponse extends BaseResponse{
	
	private List<UserGeneralPOJO> usersGeneral;

	public UserGeneralResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<UserGeneralPOJO> getUsersGeneral() {
		return usersGeneral;
	}

	public void setUsersGeneral(List<UserGeneralPOJO> usuarios) {
		this.usersGeneral = usuarios;
	}

}
