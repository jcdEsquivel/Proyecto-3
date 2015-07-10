package com.treeseed.contracts;

import com.treeseed.pojo.DonorPOJO;

public class DonorRequest extends BasePagingRequest {
	
	private DonorPOJO donor;
<<<<<<< HEAD
	private String name;
	private String lastName;
	private String country;
=======
	
	public DonorRequest() {
		super();
	}
>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
	
	public DonorPOJO getDonor() {
		return donor;
	}
<<<<<<< HEAD

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
=======
	
	public void setUser(DonorPOJO user) {
		this.donor = user;
>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "DonorRequest [nonprofit=" + donor + "]";
=======
		return "UsersRequest [user=" + donor + "]";
>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
	}
}
