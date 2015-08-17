package com.treeseed.ejbWrapper;

import java.util.List;


import com.treeseed.ejb.Card;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.DonorSetting;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.NotificationDonor;
import com.treeseed.ejb.RecurrableDonation;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.pojo.DonorPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class DonorWrapper.
 */
public class DonorWrapper extends ParentUserWrapper{
	

	/** The wrapper object. */
	private Donor wrapperObject;
	


	/** The complete name. */
	private String completeName;

	/**
	 * Gets the complete name.
	 *
	 * @return the complete name
	 */
	public String getCompleteName() {
		return completeName;
	}

	/**
	 * Sets the complete name.
	 *
	 * @param completeName the new complete name
	 */
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	
	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public List<Card> getCards() {
		return wrapperObject.getCards();
	}

	/**
	 * Sets the cards.
	 *
	 * @param cards the new cards
	 */
	public void setCards(List<Card> cards) {
		wrapperObject.setCards(cards);
	}

	/**
	 * Adds the card.
	 *
	 * @param card the card
	 * @return the card
	 */
	public Card addCard(Card card) {
		return wrapperObject.addCard(card);
	}

	/**
	 * Removes the card.
	 *
	 * @param card the card
	 * @return the card
	 */
	public Card removeCard(Card card) {
		return wrapperObject.removeCard(card);
	}
	
	/**
	 * Gets the stripe id.
	 *
	 * @return the stripe id
	 */
	public String getStripeId() {
		return wrapperObject.getStripeId();
	}

	/**
	 * Sets the stripe id.
	 *
	 * @param setStripeId the new stripe id
	 */
	public void setStripeId(String setStripeId) {
		wrapperObject.setStripeId(setStripeId);
	}

	/**
	 * Instantiates a new donor wrapper.
	 */
	public DonorWrapper() {
		super();
		setWrapperObject(new Donor());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new donor wrapper.
	 *
	 * @param donor the donor
	 */
	public DonorWrapper(Donor donor) {
		super();
		setWrapperObject(donor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the wrapper object.
	 *
	 * @return the wrapper object
	 */
	public Donor getWrapperObject() {
		return wrapperObject;
	}

	/**
	 * Sets the wrapper object.
	 *
	 * @param wrapperObject the new wrapper object
	 */
	public void setWrapperObject(Donor wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return wrapperObject.hashCode();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return wrapperObject.getId();
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		wrapperObject.setId(id);
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return wrapperObject.getDescription();
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return wrapperObject.getLastName();
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		wrapperObject.setLastName(lastName);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return wrapperObject.getName();
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		wrapperObject.setName(name);
	}

	/**
	 * Gets the profile picture.
	 *
	 * @return the profile picture
	 */
	public String getProfilePicture() {
		return wrapperObject.getProfilePicture();
	}

	/**
	 * Sets the profile picture.
	 *
	 * @param profilePicture the new profile picture
	 */
	public void setProfilePicture(String profilePicture) {
		wrapperObject.setProfilePicture(profilePicture);
	}

	/**
	 * Gets the web page.
	 *
	 * @return the web page
	 */
	public String getWebPage() {
		return wrapperObject.getWebPage();
	}

	/**
	 * Sets the web page.
	 *
	 * @param webPage the new web page
	 */
	public void setWebPage(String webPage) {
		wrapperObject.setWebPage(webPage);
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Catalog getCountry() {
		return wrapperObject.getCountry();
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Catalog country) {
		wrapperObject.setCountry(country);
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Catalog getType() {
		return wrapperObject.getType();
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(Catalog type) {
		wrapperObject.setType(type);
	}

	/**
	 * Gets the father.
	 *
	 * @return the father
	 */
	public Donor getFather() {
		return wrapperObject.getFather();
	}

	/**
	 * Sets the father.
	 *
	 * @param father the new father
	 */
	public void setFather(Donor father) {
		wrapperObject.setFather(father);
	}

	/**
	 * Gets the sons.
	 *
	 * @return the sons
	 */
	public List<Donor> getSons() {
		return wrapperObject.getSons();
	}

	/**
	 * Sets the sons.
	 *
	 * @param sons the new sons
	 */
	public void setSons(List<Donor> sons) {
		wrapperObject.setSons(sons);
	}

	/**
	 * Adds the son.
	 *
	 * @param son the son
	 * @return the donor
	 */
	public Donor addSon(Donor son) {
		return wrapperObject.addSon(son);
	}

	/**
	 * Removes the son.
	 *
	 * @param son the son
	 * @return the donor
	 */
	public Donor removeSon(Donor son) {
		return wrapperObject.removeSon(son);
	}

	/**
	 * Gets the donorsettings.
	 *
	 * @return the donorsettings
	 */
	public List<DonorSetting> getDonorsettings() {
		return wrapperObject.getDonorsettings();
	}

	/**
	 * Sets the donorsettings.
	 *
	 * @param donorsettings the new donorsettings
	 */
	public void setDonorsettings(List<DonorSetting> donorsettings) {
		wrapperObject.setDonorsettings(donorsettings);
	}

	/**
	 * Adds the donorsetting.
	 *
	 * @param donorsetting the donorsetting
	 * @return the donor setting
	 */
	public DonorSetting addDonorsetting(DonorSetting donorsetting) {
		return wrapperObject.addDonorsetting(donorsetting);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	/**
	 * Removes the donorsetting.
	 *
	 * @param donorsetting the donorsetting
	 * @return the donor setting
	 */
	public DonorSetting removeDonorsetting(DonorSetting donorsetting) {
		return wrapperObject.removeDonorsetting(donorsetting);
	}

	/**
	 * Gets the notificationdonors.
	 *
	 * @return the notificationdonors
	 */
	public List<NotificationDonor> getNotificationdonors() {
		return wrapperObject.getNotificationdonors();
	}

	/**
	 * Sets the notificationdonors.
	 *
	 * @param notificationdonors the new notificationdonors
	 */
	public void setNotificationdonors(List<NotificationDonor> notificationdonors) {
		wrapperObject.setNotificationdonors(notificationdonors);
	}

	/**
	 * Adds the notificationdonor.
	 *
	 * @param notificationdonor the notificationdonor
	 * @return the notification donor
	 */
	public NotificationDonor addNotificationdonor(
			NotificationDonor notificationdonor) {
		return wrapperObject.addNotificationdonor(notificationdonor);
	}

	/**
	 * Removes the notificationdonor.
	 *
	 * @param notificationdonor the notificationdonor
	 * @return the notification donor
	 */
	public NotificationDonor removeNotificationdonor(
			NotificationDonor notificationdonor) {
		return wrapperObject.removeNotificationdonor(notificationdonor);
	}

	/**
	 * Gets the usergenerals.
	 *
	 * @return the usergenerals
	 */
	public List<UserGeneral> getUsergenerals() {
		return wrapperObject.getUsergenerals();
	}

	/**
	 * Sets the usergenerals.
	 *
	 * @param usergenerals the new usergenerals
	 */
	public void setUsergenerals(List<UserGeneral> usergenerals) {
		wrapperObject.setUsergenerals(usergenerals);
	}

	/**
	 * Adds the usergeneral.
	 *
	 * @param usergeneral the usergeneral
	 * @return the user general
	 */
	public UserGeneral addUsergeneral(UserGeneral usergeneral) {
		return wrapperObject.addUsergeneral(usergeneral);
	}

	/**
	 * Removes the usergeneral.
	 *
	 * @param usergeneral the usergeneral
	 * @return the user general
	 */
	public UserGeneral removeUsergeneral(UserGeneral usergeneral) {
		return wrapperObject.removeUsergeneral(usergeneral);
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return wrapperObject.isActive();
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return wrapperObject.toString();
	}
	
	/**
	 * Gets the subscription card.
	 *
	 * @return the subscription card
	 */
	public Card getSubscriptionCard() {
		return wrapperObject.getSubscriptionCard();
	}

	/**
	 * Sets the subscription card.
	 *
	 * @param subscriptionCard the new subscription card
	 */
	public void setSubscriptionCard(Card subscriptionCard) {
		wrapperObject.setSubscriptionCard(subscriptionCard);
	}
	
	/**
	 * Gets the donor pojo.
	 *
	 * @return the donor pojo
	 */
	public DonorPOJO getDonorPojo() {
		DonorPOJO donorPojo = new DonorPOJO();
		
		donorPojo.setName(getName());
		donorPojo.setLastName(getLastName());
		donorPojo.setProfilePicture(getProfilePicture());
		if(getCountry()!=null){
			donorPojo.setCountryS(getCountry().getName());
		}
		if(getType()!=null){
			donorPojo.setTypeSSpanish(getType().getSpanish());
			donorPojo.setTypeSEnglish(getType().getEnglish());
		}

		return donorPojo;
	}

}


