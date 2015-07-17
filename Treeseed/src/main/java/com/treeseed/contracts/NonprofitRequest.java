package com.treeseed.contracts;

import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;

public class NonprofitRequest extends BasePagingRequest {
	
	private NonprofitPOJO nonprofit;
	private String name;
	private String country;
	private int id;
	private int idUser;
	private String description;
	private String reason;
	private String mision;
	private String email;
	private String coverImage;
	private String profileImage;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getPrfofileImage() {
		return profileImage;
	}

	public void setPrfofileImage(String prfofileImage) {
		this.profileImage = prfofileImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMision() {
		return mision;
	}

	public void setMision(String mission) {
		this.mision = mission;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	private String cause;
	
	public NonprofitRequest() {
		super();
	}
	
	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}
	
	public void setNonprofit(NonprofitPOJO user) {
		this.nonprofit = user;
	}

	@Override
	public String toString() {
		return "NonprofitRequest [nonprofit=" + nonprofit + "]";
	}
}
