package com.treeseed.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;

// TODO: Auto-generated Javadoc
/**
 * The Class NonprofitPOJO.
 */
public class NonprofitPOJO {
	
	/**
	 * Instantiates a new nonprofit pojo.
	 */
	public NonprofitPOJO() {
		super();
	}

	/** The id. */
	private int id;

	/** The ban k account. */
	private String banKAccount;

	/** The cause name. */
	private String causeNameSpanish;
	
	/** The cause name english. */
	private String causeNameEnglish;
	
	/** The cause name to show. */
	private String causeShow;

	/** The description. */
	private String description;

	/** The main picture. */
	private String mainPicture;

	/** The mision. */
	private String mision;

	/** The name. */
	private String name;

	/** The profile picture. */
	private String profilePicture;

	/** The reason. */
	private String reason;

	/** The web page. */
	private String webPage;
	
	/** The user general. */
	private UserGeneralPOJO userGeneral;
	
	
	/** The is active. */
	private boolean isActive;
	
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
	 * Gets the ban k account.
	 *
	 * @return the ban k account
	 */
	public String getBanKAccount() {
		return banKAccount;
	}

	/**
	 * Sets the ban k account.
	 *
	 * @param banKAccount the new ban k account
	 */
	public void setBanKAccount(String banKAccount) {
		this.banKAccount = banKAccount;
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
	 * @param mision the new mision
	 */
	public void setMision(String mision) {
		this.mision = mision;
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
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the user general.
	 *
	 * @return the user general
	 */
	public UserGeneralPOJO getUserGeneral() {
		return userGeneral;
	}

	/**
	 * Sets the user general.
	 *
	 * @param userGeneral the new user general
	 */
	public void setUserGeneral(UserGeneralPOJO userGeneral) {
		this.userGeneral = userGeneral;
	}



	/**
	 * Gets the cause name spanish.
	 *
	 * @return the cause name spanish
	 */
	public String getCauseNameSpanish() {
		return causeNameSpanish;
	}

	/**
	 * Sets the cause name spanish.
	 *
	 * @param causeNameSpanish the new cause name spanish
	 */
	public void setCauseNameSpanish(String causeNameSpanish) {
		this.causeNameSpanish = causeNameSpanish;
	}

	/**
	 * Gets the cause name english.
	 *
	 * @return the cause name english
	 */
	public String getCauseNameEnglish() {
		return causeNameEnglish;
	}

	/**
	 * Sets the cause name english.
	 *
	 * @param causeNameEnglish the new cause name english
	 */
	public void setCauseNameEnglish(String causeNameEnglish) {
		this.causeNameEnglish = causeNameEnglish;
	}

	/**
	 * Gets the cause show.
	 *
	 * @return the cause show
	 */
	public String getCauseShow() {
		return causeShow;
	}

	/**
	 * Sets the cause show.
	 *
	 * @param causeShow the new cause show
	 */
	public void setCauseShow(String causeShow) {
		this.causeShow = causeShow;
	}




}
