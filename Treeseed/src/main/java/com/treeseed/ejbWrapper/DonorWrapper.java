package com.treeseed.ejbWrapper;

import java.util.List;

import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.DonorSetting;
import com.treeseed.ejb.NotificationDonor;
import com.treeseed.ejb.RecurrableDonation;
import com.treeseed.ejb.UserGeneral;

public class DonorWrapper extends ParentUserWrapper{
	
	private Donor wrapperObject;

	public DonorWrapper() {
		super();
		setWrapperObject(new Donor());
		// TODO Auto-generated constructor stub
	}
	
	public DonorWrapper(Donor donor) {
		super();
		setWrapperObject(donor);
		// TODO Auto-generated constructor stub
	}

	public Donor getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(Donor wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public int getId() {
		return wrapperObject.getId();
	}

	public void setId(int id) {
		wrapperObject.setId(id);
	}

	public String getDescription() {
		return wrapperObject.getDescription();
	}

	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	public String getLastName() {
		return wrapperObject.getLastName();
	}

	public void setLastName(String lastName) {
		wrapperObject.setLastName(lastName);
	}

	public String getName() {
		return wrapperObject.getName();
	}

	public void setName(String name) {
		wrapperObject.setName(name);
	}

	public String getProfilePicture() {
		return wrapperObject.getProfilePicture();
	}

	public void setProfilePicture(String profilePicture) {
		wrapperObject.setProfilePicture(profilePicture);
	}

	public String getWebPage() {
		return wrapperObject.getWebPage();
	}

	public void setWebPage(String webPage) {
		wrapperObject.setWebPage(webPage);
	}

	public Catalog getCountry() {
		return wrapperObject.getCountry();
	}

	public void setCountry(Catalog country) {
		wrapperObject.setCountry(country);
	}

	public Catalog getType() {
		return wrapperObject.getType();
	}

	public void setType(Catalog type) {
		wrapperObject.setType(type);
	}

	public Donor getFather() {
		return wrapperObject.getFather();
	}

	public void setFather(Donor father) {
		wrapperObject.setFather(father);
	}

	public List<Donor> getSons() {
		return wrapperObject.getSons();
	}

	public void setSons(List<Donor> sons) {
		wrapperObject.setSons(sons);
	}

	public Donor addSon(Donor son) {
		return wrapperObject.addSon(son);
	}

	public Donor removeSon(Donor son) {
		return wrapperObject.removeSon(son);
	}

	public List<DonorSetting> getDonorsettings() {
		return wrapperObject.getDonorsettings();
	}

	public void setDonorsettings(List<DonorSetting> donorsettings) {
		wrapperObject.setDonorsettings(donorsettings);
	}

	public DonorSetting addDonorsetting(DonorSetting donorsetting) {
		return wrapperObject.addDonorsetting(donorsetting);
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public DonorSetting removeDonorsetting(DonorSetting donorsetting) {
		return wrapperObject.removeDonorsetting(donorsetting);
	}

	public List<NotificationDonor> getNotificationdonors() {
		return wrapperObject.getNotificationdonors();
	}

	public void setNotificationdonors(List<NotificationDonor> notificationdonors) {
		wrapperObject.setNotificationdonors(notificationdonors);
	}

	public NotificationDonor addNotificationdonor(
			NotificationDonor notificationdonor) {
		return wrapperObject.addNotificationdonor(notificationdonor);
	}

	public NotificationDonor removeNotificationdonor(
			NotificationDonor notificationdonor) {
		return wrapperObject.removeNotificationdonor(notificationdonor);
	}

	public List<RecurrableDonation> getRecurrabledonations() {
		return wrapperObject.getRecurrabledonations();
	}

	public void setRecurrabledonations(
			List<RecurrableDonation> recurrabledonations) {
		wrapperObject.setRecurrabledonations(recurrabledonations);
	}

	public RecurrableDonation addRecurrabledonation(
			RecurrableDonation recurrabledonation) {
		return wrapperObject.addRecurrabledonation(recurrabledonation);
	}

	public RecurrableDonation removeRecurrabledonation(
			RecurrableDonation recurrabledonation) {
		return wrapperObject.removeRecurrabledonation(recurrabledonation);
	}

	public List<UserGeneral> getUsergenerals() {
		return wrapperObject.getUsergenerals();
	}

	public void setUsergenerals(List<UserGeneral> usergenerals) {
		wrapperObject.setUsergenerals(usergenerals);
	}

	public UserGeneral addUsergeneral(UserGeneral usergeneral) {
		return wrapperObject.addUsergeneral(usergeneral);
	}

	public UserGeneral removeUsergeneral(UserGeneral usergeneral) {
		return wrapperObject.removeUsergeneral(usergeneral);
	}

	public boolean isActive() {
		return wrapperObject.isActive();
	}

	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	public String toString() {
		return wrapperObject.toString();
	}
	
}