package com.treeseed.contracts;

import com.treeseed.pojo.DonorPOJO;

public class DonorRequest extends BasePagingRequest {
	
	private DonorPOJO donor;
	private String name;
	private String lastName;
	private String country;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DonorRequest() {
		super();
	}
	
	public DonorPOJO getDonor() {
		return donor;
	}

	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setUser(DonorPOJO user) {
		this.donor = user;
	}

	@Override
	public String toString() {
		return "DonorRequest [nonprofit=" + donor + "]";
	}
}
