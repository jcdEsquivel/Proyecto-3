package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.DonorTreePOJO;

public class DonorRequest extends BasePagingRequest {

	private DonorPOJO donor;
	private String name;
	private String lastName;
	private String description;
	private String email;
	private String country;
	private String webPage;
	private int id;
	private int idUser;
	private String profilePicture;
	private int isActive;
	
	/** The tree level x. */
	private int treeLevelX;
	
	/** The tree level y. */
	private int treeLevelY;
	
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getWebPage() {
		return webPage;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	
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
	/**
	 * Gets the tree level x.
	 *
	 * @return the tree level x
	 */
	public int getTreeLevelX() {
		return treeLevelX;
	}

	/**
	 * Sets the tree level x.
	 *
	 * @param treeLevelX the new tree level x
	 */
	public void setTreeLevelX(int treeLevelX) {
		this.treeLevelX = treeLevelX;
	}

	/**
	 * Gets the tree level y.
	 *
	 * @return the tree level y
	 */
	public int getTreeLevelY() {
		return treeLevelY;
	}

	/**
	 * Sets the tree level y.
	 *
	 * @param treeLevelY the new tree level y
	 */
	public void setTreeLevelY(int treeLevelY) {
		this.treeLevelY = treeLevelY;
	}
}
