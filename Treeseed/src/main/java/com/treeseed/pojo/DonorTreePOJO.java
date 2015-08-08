package com.treeseed.pojo;

import java.util.List;

public class DonorTreePOJO {
	
	private int identity;

	private String name;

	private String profilePicture;
	
	private List<DonorTreePOJO> children;
	
	
	

	public DonorTreePOJO() {
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public List<DonorTreePOJO> getChildren() {
		return children;
	}

	public void setChildren(List<DonorTreePOJO> children) {
		this.children = children;
	}



	public int getIdentity() {
		return identity;
	}



	public void setIdentity(int identity) {
		this.identity = identity;
	}

}