package com.treeseed.contracts;

import com.treeseed.pojo.DonorPOJO;

public class DonorRequest extends BasePagingRequest {

	private DonorPOJO donor;
	private String name;
	private String lastName;
	private String description;
	private String email;
	private String country;
	private int id;
	private int idUser;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

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
