package com.treeseed.pojo;

import java.util.List;

public class DonorTreePOJO {
	
	private int id;

	private String name;

	private String profilePicture;
	
	private List<DonorTreePOJO> children;
	
	
	

	public DonorTreePOJO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

}