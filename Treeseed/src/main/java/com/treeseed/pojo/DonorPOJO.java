package com.treeseed.pojo;

// TODO: Auto-generated Javadoc
/**
 * The Class DonorPOJO.
 */
public class DonorPOJO {
	
	/** The id. */
	private int id;

	/** The description. */
	private String description;

	/** The last name. */
	private String lastName;

	/** The name. */
	private String name;

	/** The profile picture. */
	private String profilePicture;

	/** The web page. */
	private String webPage;
	
	/** The country. */
	private int country;
	
	/** The country s. */
	private String countryS;
	
	/** The type s spanish. */
	private String typeSSpanish;

	/** The type s english. */
	private String typeSEnglish;
	
	/** The father. */
	private DonorPOJO father;
	
	/** The type. */
	private int type;
	
	/** The user general. */
	private UserGeneralPOJO userGeneral;
	
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets the catalog.
	 *
	 * @return the catalog
	 */
	public int getCatalog() {
		return country;
	}

	/**
	 * Gets the father donor.
	 *
	 * @return the father donor
	 */
	public DonorPOJO getFatherDonor() {
		return father;
	}

	/**
	 * Sets the father donor.
	 *
	 * @param fatherDonor the new father donor
	 */
	public void setFatherDonor(DonorPOJO fatherDonor) {
		this.father = fatherDonor;
	}

	/**
	 * Sets the catalog.
	 *
	 * @param catalog the new catalog
	 */
	public void setCatalog(int catalog) {
		this.country = catalog;
	}

	/**
	 * Instantiates a new donor pojo.
	 */
	public DonorPOJO() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
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
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
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
	 * Gets the web page.
	 *
	 * @return the web page
	 */
	public String getWebPage() {
		return this.webPage;
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
	 * Gets the country s.
	 *
	 * @return the country s
	 */
	public String getCountryS() {
		return countryS;
	}

	/**
	 * Sets the country s.
	 *
	 * @param countryS the new country s
	 */
	public void setCountryS(String countryS) {
		this.countryS = countryS;
	}
	
	/**
	 * Gets the type s spanish.
	 *
	 * @return the type s spanish
	 */
	public String getTypeSSpanish() {
		return typeSSpanish;
	}

	/**
	 * Sets the type s spanish.
	 *
	 * @param typeSSpanish the new type s spanish
	 */
	public void setTypeSSpanish(String typeSSpanish) {
		this.typeSSpanish = typeSSpanish;
	}

	/**
	 * Gets the type s english.
	 *
	 * @return the type s english
	 */
	public String getTypeSEnglish() {
		return typeSEnglish;
	}

	/**
	 * Sets the type s english.
	 *
	 * @param typeSEnglish the new type s english
	 */
	public void setTypeSEnglish(String typeSEnglish) {
		this.typeSEnglish = typeSEnglish;
	}

}