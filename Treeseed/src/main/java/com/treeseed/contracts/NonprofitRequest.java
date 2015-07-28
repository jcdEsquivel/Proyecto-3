package com.treeseed.contracts;

import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class NonprofitRequest.
 */
public class NonprofitRequest extends BasePagingRequest {
	
	/** The nonprofit. */
	private NonprofitPOJO nonprofit;
	
	/** The name. */
	private String name;
	
	/** The country. */
	private String country;
	
	/** The id. */
	private int id;
	
	/** The id user. */
	private int idUser;
	
	/** The description. */
	private String description;
	
	/** The reason. */
	private String reason;
	
	/** The mision. */
	private String mision;
	
	/** The email. */
	private String email;
	
	/** The main picture. */
	private String mainPicture;
	
	/** The profile picture. */
	private String profilePicture;
	
	/** The web page. */
	private String webPage;
	

	/**
	 * Gets the web page.
	 *
	 * @return the web page
	 */
	public String getWebPage() {
		return webPage;
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
	 * Gets the main picture.
	 *
	 * @return the main picture
	 */
	public String getMainPicture() {
		return mainPicture;
	}

	/**
	 * Sets the main picture.
	 *
	 * @param mainPicture the new main picture
	 */
	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
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
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Sets the reason.
	 *
	 * @param reason the new reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * Gets the mision.
	 *
	 * @return the mision
	 */
	public String getMision() {
		return mision;
	}

	/**
	 * Sets the mision.
	 *
	 * @param mission the new mision
	 */
	public void setMision(String mission) {
		this.mision = mission;
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
	 * Gets the cause.
	 *
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * Sets the cause.
	 *
	 * @param cause the new cause
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/** The cause. */
	private String cause;
	
	/**
	 * Instantiates a new nonprofit request.
	 */
	public NonprofitRequest() {
		super();
	}
	
	/**
	 * Gets the nonprofit.
	 *
	 * @return the nonprofit
	 */
	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}
	
	/**
	 * Sets the nonprofit.
	 *
	 * @param user the new nonprofit
	 */
	public void setNonprofit(NonprofitPOJO user) {
		this.nonprofit = user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NonprofitRequest [nonprofit=" + nonprofit + "]";
	}
}
