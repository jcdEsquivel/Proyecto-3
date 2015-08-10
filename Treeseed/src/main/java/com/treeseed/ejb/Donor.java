package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the donor database table.
 * 
 */
@Entity
@NamedQuery(name="Donor.findAll", query="SELECT d FROM Donor d")
public class Donor implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	/** The stripe id. */
	private String stripeId;
	
	/** The is active. */
	private boolean isActive;
	
	/** The subscription card. */
	//uni-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subscriptionCard")
	private Card subscriptionCard;



	/** The country. */
	//uni-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country")
	private Catalog country;

	/** The type. */
	//uni-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type")
	private Catalog type;

	/** The father. */
	//bi-directional many-to-one association to Donor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="father")
	private Donor father;


	/** The sons. */
	//bi-directional many-to-one association to Donor
	@OneToMany(mappedBy="father")
	private List<Donor> sons;

	
	/** The donor settings. */
	//bi-directional many-to-one association to Donorsetting
	@OneToMany(mappedBy="donor")
	private List<DonorSetting> donorSettings;

	/** The notification donors. */
	//bi-directional many-to-one association to Notificationdonor
	@OneToMany(mappedBy="donor")
	private List<NotificationDonor> notificationDonors;


	/** The user generals. */
	//bi-directional many-to-one association to Usergeneral
	@OneToMany(mappedBy="donor")
	private List<UserGeneral> userGenerals;
	
	/** The cards. */
	//bi-directional many-to-one association to Card
	@OneToMany(mappedBy="donor")
	private List<Card> cards;

	/**
	 * Instantiates a new donor.
	 */
	public Donor() {
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Catalog getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Catalog country) {
		this.country = country;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Catalog getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(Catalog type) {
		this.type = type;
	}

	/**
	 * Gets the father.
	 *
	 * @return the father
	 */
	public Donor getFather() {
		return this.father;
	}

	/**
	 * Sets the father.
	 *
	 * @param father the new father
	 */
	public void setFather(Donor father) {
		this.father = father;
	}

	/**
	 * Gets the sons.
	 *
	 * @return the sons
	 */
	public List<Donor> getSons() {
		return this.sons;
	}

	/**
	 * Sets the sons.
	 *
	 * @param sons the new sons
	 */
	public void setSons(List<Donor> sons) {
		this.sons = sons;
	}

	/**
	 * Adds the son.
	 *
	 * @param son the son
	 * @return the donor
	 */
	public Donor addSon(Donor son) {
		getSons().add(son);
		son.setFather(this);

		return son;
	}

	/**
	 * Removes the son.
	 *
	 * @param son the son
	 * @return the donor
	 */
	public Donor removeSon(Donor son) {
		getSons().remove(son);
		son.setFather(null);

		return son;
	}

	/**
	 * Gets the donorsettings.
	 *
	 * @return the donorsettings
	 */
	public List<DonorSetting> getDonorsettings() {
		return this.donorSettings;
	}

	/**
	 * Sets the donorsettings.
	 *
	 * @param donorsettings the new donorsettings
	 */
	public void setDonorsettings(List<DonorSetting> donorsettings) {
		this.donorSettings = donorsettings;
	}

	/**
	 * Adds the donorsetting.
	 *
	 * @param donorsetting the donorsetting
	 * @return the donor setting
	 */
	public DonorSetting addDonorsetting(DonorSetting donorsetting) {
		getDonorsettings().add(donorsetting);
		donorsetting.setDonor(this);

		return donorsetting;
	}

	/**
	 * Removes the donorsetting.
	 *
	 * @param donorsetting the donorsetting
	 * @return the donor setting
	 */
	public DonorSetting removeDonorsetting(DonorSetting donorsetting) {
		getDonorsettings().remove(donorsetting);
		donorsetting.setDonor(null);

		return donorsetting;
	}

	/**
	 * Gets the notificationdonors.
	 *
	 * @return the notificationdonors
	 */
	public List<NotificationDonor> getNotificationdonors() {
		return this.notificationDonors;
	}

	/**
	 * Sets the notificationdonors.
	 *
	 * @param notificationdonors the new notificationdonors
	 */
	public void setNotificationdonors(List<NotificationDonor> notificationdonors) {
		this.notificationDonors = notificationdonors;
	}

	/**
	 * Adds the notificationdonor.
	 *
	 * @param notificationdonor the notificationdonor
	 * @return the notification donor
	 */
	public NotificationDonor addNotificationdonor(NotificationDonor notificationdonor) {
		getNotificationdonors().add(notificationdonor);
		notificationdonor.setDonor(this);

		return notificationdonor;
	}

	/**
	 * Removes the notificationdonor.
	 *
	 * @param notificationdonor the notificationdonor
	 * @return the notification donor
	 */
	public NotificationDonor removeNotificationdonor(NotificationDonor notificationdonor) {
		getNotificationdonors().remove(notificationdonor);
		notificationdonor.setDonor(null);

		return notificationdonor;
	}
	
	
	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public List<Card> getCards() {
		return this.cards;
	}

	/**
	 * Sets the cards.
	 *
	 * @param cards the new cards
	 */
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * Adds the card.
	 *
	 * @param card the card
	 * @return the card
	 */
	public Card addCard(Card card) {
		getCards().add(card);
		card.setDonor(this);

		return card;
	}

	/**
	 * Removes the card.
	 *
	 * @param card the card
	 * @return the card
	 */
	public Card removeCard(Card card) {
		getCards().remove(card);
		card.setDonor(null);

		return card;
	}



	/**
	 * Gets the usergenerals.
	 *
	 * @return the usergenerals
	 */
	public List<UserGeneral> getUsergenerals() {
		return this.userGenerals;
	}

	/**
	 * Sets the usergenerals.
	 *
	 * @param usergenerals the new usergenerals
	 */
	public void setUsergenerals(List<UserGeneral> usergenerals) {
		this.userGenerals = usergenerals;
	}

	/**
	 * Adds the usergeneral.
	 *
	 * @param usergeneral the usergeneral
	 * @return the user general
	 */
	public UserGeneral addUsergeneral(UserGeneral usergeneral) {
		getUsergenerals().add(usergeneral);
		usergeneral.setDonor(this);

		return usergeneral;
	}

	/**
	 * Removes the usergeneral.
	 *
	 * @param usergeneral the usergeneral
	 * @return the user general
	 */
	public UserGeneral removeUsergeneral(UserGeneral usergeneral) {
		getUsergenerals().remove(usergeneral);
		usergeneral.setDonor(null);

		return usergeneral;
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
	 * Gets the stripe id.
	 *
	 * @return the stripe id
	 */
	public String getStripeId() {
		return stripeId;
	}

	/**
	 * Sets the stripe id.
	 *
	 * @param stripeId the new stripe id
	 */
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}
	
	/**
	 * Gets the subscription card.
	 *
	 * @return the subscription card
	 */
	public Card getSubscriptionCard() {
		return subscriptionCard;
	}

	/**
	 * Sets the subscription card.
	 *
	 * @param subscriptionCard the new subscription card
	 */
	public void setSubscriptionCard(Card subscriptionCard) {
		this.subscriptionCard = subscriptionCard;
	}



}