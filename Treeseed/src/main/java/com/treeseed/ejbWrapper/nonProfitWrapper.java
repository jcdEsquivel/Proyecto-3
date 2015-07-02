package com.treeseed.ejbWrapper;

import java.util.Date;
import java.util.List;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.Nonprofitsetting;
import com.treeseed.ejb.Notificationnonprofit;
import com.treeseed.ejb.Postnonprofit;
import com.treeseed.ejb.Recurrabledonation;
import com.treeseed.ejb.Transparencyreport;
import com.treeseed.ejb.Usergeneral;

class nonProfitWrapper {
	private Nonprofit wrapperObject;

	public int getId() {
		return wrapperObject.getId();
	}

	public Date getDateTime() {
		return wrapperObject.getDateTime();
	}

	public String getDescription() {
		return wrapperObject.getDescription();
	}

	public String getMainPicture() {
		return wrapperObject.getMainPicture();
	}

	public String getMision() {
		return wrapperObject.getMision();
	}

	public String getName() {
		return wrapperObject.getName();
	}

	public String getProfilePicture() {
		return wrapperObject.getProfilePicture();
	}

	public String getReason() {
		return wrapperObject.getReason();
	}

	public String getWebPage() {
		return wrapperObject.getWebPage();
	}

	public Campaign addCampaign(Campaign campaign) {
		return wrapperObject.addCampaign(campaign);
	}

	public List<Nonprofitsetting> getNonprofitsettings() {
		return wrapperObject.getNonprofitsettings();
	}

	public Nonprofitsetting addNonprofitsetting(
			Nonprofitsetting nonprofitsetting) {
		return wrapperObject.addNonprofitsetting(nonprofitsetting);
	}

	public List<Notificationnonprofit> getNotificationnonprofits() {
		return wrapperObject.getNotificationnonprofits();
	}

	public Notificationnonprofit addNotificationnonprofit(
			Notificationnonprofit notificationnonprofit) {
		return wrapperObject.addNotificationnonprofit(notificationnonprofit);
	}

	public Postnonprofit addPostnonprofit(Postnonprofit postnonprofit) {
		return wrapperObject.addPostnonprofit(postnonprofit);
	}

	public Recurrabledonation addRecurrabledonation(
			Recurrabledonation recurrabledonation) {
		return wrapperObject.addRecurrabledonation(recurrabledonation);
	}

	public Transparencyreport addTransparencyreport(
			Transparencyreport transparencyreport) {
		return wrapperObject.addTransparencyreport(transparencyreport);
	}

	public Usergeneral addUsergeneral(Usergeneral usergeneral) {
		return wrapperObject.addUsergeneral(usergeneral);
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public String getBanKAccount() {
		return wrapperObject.getBanKAccount();
	}

	public List<Campaign> getCampaigns() {
		return wrapperObject.getCampaigns();
	}

	public Catalog getCause() {
		return wrapperObject.getCause();
	}

	public Catalog getConutry() {
		return wrapperObject.getConutry();
	}

	public List<Postnonprofit> getPostnonprofits() {
		return wrapperObject.getPostnonprofits();
	}

	public List<Recurrabledonation> getRecurrabledonations() {
		return wrapperObject.getRecurrabledonations();
	}

	public List<Transparencyreport> getTransparencyreports() {
		return wrapperObject.getTransparencyreports();
	}

	public List<Usergeneral> getUsergenerals() {
		return wrapperObject.getUsergenerals();
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public void setId(int id) {
		wrapperObject.setId(id);
	}

	public void setBanKAccount(String banKAccount) {
		wrapperObject.setBanKAccount(banKAccount);
	}

	public void setDateTime(Date dateTime) {
		wrapperObject.setDateTime(dateTime);
	}

	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	public void setMainPicture(String mainPicture) {
		wrapperObject.setMainPicture(mainPicture);
	}

	public void setMision(String mision) {
		wrapperObject.setMision(mision);
	}

	public void setName(String name) {
		wrapperObject.setName(name);
	}

	public void setProfilePicture(String profilePicture) {
		wrapperObject.setProfilePicture(profilePicture);
	}

	public void setReason(String reason) {
		wrapperObject.setReason(reason);
	}

	public void setWebPage(String webPage) {
		wrapperObject.setWebPage(webPage);
	}

	public void setCampaigns(List<Campaign> campaigns) {
		wrapperObject.setCampaigns(campaigns);
	}

	public Campaign removeCampaign(Campaign campaign) {
		return wrapperObject.removeCampaign(campaign);
	}

	public void setCause(Catalog cause) {
		wrapperObject.setCause(cause);
	}

	public void setConutry(Catalog conutry) {
		wrapperObject.setConutry(conutry);
	}

	public void setNonprofitsettings(List<Nonprofitsetting> nonprofitsettings) {
		wrapperObject.setNonprofitsettings(nonprofitsettings);
	}

	public Nonprofitsetting removeNonprofitsetting(
			Nonprofitsetting nonprofitsetting) {
		return wrapperObject.removeNonprofitsetting(nonprofitsetting);
	}

	public void setNotificationnonprofits(
			List<Notificationnonprofit> notificationnonprofits) {
		wrapperObject.setNotificationnonprofits(notificationnonprofits);
	}

	public Notificationnonprofit removeNotificationnonprofit(
			Notificationnonprofit notificationnonprofit) {
		return wrapperObject.removeNotificationnonprofit(notificationnonprofit);
	}

	public void setPostnonprofits(List<Postnonprofit> postnonprofits) {
		wrapperObject.setPostnonprofits(postnonprofits);
	}

	public Postnonprofit removePostnonprofit(Postnonprofit postnonprofit) {
		return wrapperObject.removePostnonprofit(postnonprofit);
	}

	public void setRecurrabledonations(
			List<Recurrabledonation> recurrabledonations) {
		wrapperObject.setRecurrabledonations(recurrabledonations);
	}

	public Recurrabledonation removeRecurrabledonation(
			Recurrabledonation recurrabledonation) {
		return wrapperObject.removeRecurrabledonation(recurrabledonation);
	}

	public void setTransparencyreports(
			List<Transparencyreport> transparencyreports) {
		wrapperObject.setTransparencyreports(transparencyreports);
	}

	public Transparencyreport removeTransparencyreport(
			Transparencyreport transparencyreport) {
		return wrapperObject.removeTransparencyreport(transparencyreport);
	}

	public void setUsergenerals(List<Usergeneral> usergenerals) {
		wrapperObject.setUsergenerals(usergenerals);
	}

	public Usergeneral removeUsergeneral(Usergeneral usergeneral) {
		return wrapperObject.removeUsergeneral(usergeneral);
	}

	public String toString() {
		return wrapperObject.toString();
	}

}
