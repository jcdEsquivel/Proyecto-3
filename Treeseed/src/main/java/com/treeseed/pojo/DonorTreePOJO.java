package com.treeseed.pojo;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DonorTreePOJO.
 */
public class DonorTreePOJO {
	
	/** The identity. */
	private int identity;

	/** The name. */
	private String name;

	/** The profile picture. */
	private String profilePicture;
	
	/** The children. */
	private List<DonorTreePOJO> children;
	
	
	

	/**
	 * Instantiates a new donor tree pojo.
	 */
	public DonorTreePOJO() {
	}



	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the profile picture.
	 *
	 * @return the profile picture
	 */
	public String getProfilePicture() {
		return this.profilePicture;
	}

	/**
	 * Sets the profile picture.
	 *
	 * @param profilePicture the new profile picture
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<DonorTreePOJO> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public void setChildren(List<DonorTreePOJO> children) {
		this.children = children;
	}



	/**
	 * Gets the identity.
	 *
	 * @return the identity
	 */
	public int getIdentity() {
		return identity;
	}



	/**
	 * Sets the identity.
	 *
	 * @param identity the new identity
	 */
	public void setIdentity(int identity) {
		this.identity = identity;
	}

}