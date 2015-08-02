package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the donor database table.
 * 
 */
@Entity
@NamedQuery(name="Donor.findAll", query="SELECT d FROM Donor d")
public class Donor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String lastName;

	private String name;

	private String profilePicture;

	private String webPage;
	
	private String stripeId;
	
	private boolean isActive;

	//uni-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country")
	private Catalog country;

	//uni-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type")
	private Catalog type;

	//bi-directional many-to-one association to Donor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="father")
	private Donor father;

	//bi-directional many-to-one association to Donor
	@OneToMany(mappedBy="father")
	private List<Donor> sons;

	//bi-directional many-to-one association to Donorsetting
	@OneToMany(mappedBy="donor")
	private List<DonorSetting> donorSettings;

	//bi-directional many-to-one association to Notificationdonor
	@OneToMany(mappedBy="donor")
	private List<NotificationDonor> notificationDonors;


	//bi-directional many-to-one association to Usergeneral
	@OneToMany(mappedBy="donor")
	private List<UserGeneral> userGenerals;
	
	//bi-directional many-to-one association to Card
	@OneToMany(mappedBy="donor")
	private List<Card> cards;

	public Donor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getWebPage() {
		return this.webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public Catalog getCountry() {
		return this.country;
	}

	public void setCountry(Catalog country) {
		this.country = country;
	}

	public Catalog getType() {
		return this.type;
	}

	public void setType(Catalog type) {
		this.type = type;
	}

	public Donor getFather() {
		return this.father;
	}

	public void setFather(Donor father) {
		this.father = father;
	}

	public List<Donor> getSons() {
		return this.sons;
	}

	public void setSons(List<Donor> sons) {
		this.sons = sons;
	}

	public Donor addSon(Donor son) {
		getSons().add(son);
		son.setFather(this);

		return son;
	}

	public Donor removeSon(Donor son) {
		getSons().remove(son);
		son.setFather(null);

		return son;
	}

	public List<DonorSetting> getDonorsettings() {
		return this.donorSettings;
	}

	public void setDonorsettings(List<DonorSetting> donorsettings) {
		this.donorSettings = donorsettings;
	}

	public DonorSetting addDonorsetting(DonorSetting donorsetting) {
		getDonorsettings().add(donorsetting);
		donorsetting.setDonor(this);

		return donorsetting;
	}

	public DonorSetting removeDonorsetting(DonorSetting donorsetting) {
		getDonorsettings().remove(donorsetting);
		donorsetting.setDonor(null);

		return donorsetting;
	}

	public List<NotificationDonor> getNotificationdonors() {
		return this.notificationDonors;
	}

	public void setNotificationdonors(List<NotificationDonor> notificationdonors) {
		this.notificationDonors = notificationdonors;
	}

	public NotificationDonor addNotificationdonor(NotificationDonor notificationdonor) {
		getNotificationdonors().add(notificationdonor);
		notificationdonor.setDonor(this);

		return notificationdonor;
	}

	public NotificationDonor removeNotificationdonor(NotificationDonor notificationdonor) {
		getNotificationdonors().remove(notificationdonor);
		notificationdonor.setDonor(null);

		return notificationdonor;
	}
	
	
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Card addCard(Card card) {
		getCards().add(card);
		card.setDonor(this);

		return card;
	}

	public Card removeCard(Card card) {
		getCards().remove(card);
		card.setDonor(null);

		return card;
	}



	public List<UserGeneral> getUsergenerals() {
		return this.userGenerals;
	}

	public void setUsergenerals(List<UserGeneral> usergenerals) {
		this.userGenerals = usergenerals;
	}

	public UserGeneral addUsergeneral(UserGeneral usergeneral) {
		getUsergenerals().add(usergeneral);
		usergeneral.setDonor(this);

		return usergeneral;
	}

	public UserGeneral removeUsergeneral(UserGeneral usergeneral) {
		getUsergenerals().remove(usergeneral);
		usergeneral.setDonor(null);

		return usergeneral;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getStripeId() {
		return stripeId;
	}

	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}



}