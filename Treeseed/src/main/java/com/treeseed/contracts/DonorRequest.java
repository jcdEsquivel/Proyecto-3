package com.treeseed.contracts;

import com.treeseed.pojo.DonorPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class DonorRequest.
 */
public class DonorRequest extends BasePagingRequest {

	/** The donor. */
	private DonorPOJO donor;
	
	/** The name. */
	private String name;
	
	/** The last name. */
	private String lastName;
	
	/** The description. */
	private String description;
	
	/** The email. */
	private String email;
	
	/** The country. */
	private String country;
	
	/** The web page. */
	private String webPage;
	
	/** The id. */
	private int id;
	
	/** The id user. */
	private int idUser;
	
	/** The profile picture. */
	private String profilePicture;
	
	/** The is active. */
	private int isActive;
	
	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public int getIsActive() {
		return isActive;
	}

	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the web page.
	 *
	 * @return the web page
	 */
	public String getWebPage() {
		return webPage;
	}

	/**
	 * Gets the profile picture.
	 *
	 * @return the profile picture
	 */
	public String getProfilePicture() {
		return profilePicture;
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
	 * Sets the web page.
	 *
	 * @param webPage the new web page
	 */
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Instantiates a new donor request.
	 */
	public DonorRequest() {
		super();
	}

	/**
	 * Gets the donor.
	 *
	 * @return the donor
	 */
	public DonorPOJO getDonor() {
		return donor;
	}

	/**
	 * Sets the donor.
	 *
	 * @param donor the new donor
	 */
	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(DonorPOJO user) {
		this.donor = user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DonorRequest [nonprofit=" + donor + "]";
	}
}
