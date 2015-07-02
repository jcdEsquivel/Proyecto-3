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
	private List<Donorsetting> donorsettings;

	//bi-directional many-to-one association to Notificationdonor
	@OneToMany(mappedBy="donor")
	private List<Notificationdonor> notificationdonors;

	//bi-directional many-to-one association to Recurrabledonation
	@OneToMany(mappedBy="donor")
	private List<Recurrabledonation> recurrabledonations;

	//bi-directional many-to-one association to Usergeneral
	@OneToMany(mappedBy="donor")
	private List<Usergeneral> usergenerals;

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

	public List<Donorsetting> getDonorsettings() {
		return this.donorsettings;
	}

	public void setDonorsettings(List<Donorsetting> donorsettings) {
		this.donorsettings = donorsettings;
	}

	public Donorsetting addDonorsetting(Donorsetting donorsetting) {
		getDonorsettings().add(donorsetting);
		donorsetting.setDonor(this);

		return donorsetting;
	}

	public Donorsetting removeDonorsetting(Donorsetting donorsetting) {
		getDonorsettings().remove(donorsetting);
		donorsetting.setDonor(null);

		return donorsetting;
	}

	public List<Notificationdonor> getNotificationdonors() {
		return this.notificationdonors;
	}

	public void setNotificationdonors(List<Notificationdonor> notificationdonors) {
		this.notificationdonors = notificationdonors;
	}

	public Notificationdonor addNotificationdonor(Notificationdonor notificationdonor) {
		getNotificationdonors().add(notificationdonor);
		notificationdonor.setDonor(this);

		return notificationdonor;
	}

	public Notificationdonor removeNotificationdonor(Notificationdonor notificationdonor) {
		getNotificationdonors().remove(notificationdonor);
		notificationdonor.setDonor(null);

		return notificationdonor;
	}

	public List<Recurrabledonation> getRecurrabledonations() {
		return this.recurrabledonations;
	}

	public void setRecurrabledonations(List<Recurrabledonation> recurrabledonations) {
		this.recurrabledonations = recurrabledonations;
	}

	public Recurrabledonation addRecurrabledonation(Recurrabledonation recurrabledonation) {
		getRecurrabledonations().add(recurrabledonation);
		recurrabledonation.setDonor(this);

		return recurrabledonation;
	}

	public Recurrabledonation removeRecurrabledonation(Recurrabledonation recurrabledonation) {
		getRecurrabledonations().remove(recurrabledonation);
		recurrabledonation.setDonor(null);

		return recurrabledonation;
	}

	public List<Usergeneral> getUsergenerals() {
		return this.usergenerals;
	}

	public void setUsergenerals(List<Usergeneral> usergenerals) {
		this.usergenerals = usergenerals;
	}

	public Usergeneral addUsergeneral(Usergeneral usergeneral) {
		getUsergenerals().add(usergeneral);
		usergeneral.setDonor(this);

		return usergeneral;
	}

	public Usergeneral removeUsergeneral(Usergeneral usergeneral) {
		getUsergenerals().remove(usergeneral);
		usergeneral.setDonor(null);

		return usergeneral;
	}

}