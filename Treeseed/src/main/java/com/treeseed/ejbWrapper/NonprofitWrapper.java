package com.treeseed.ejbWrapper;

import java.util.Date;
import java.util.List;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.NonprofitSetting;
import com.treeseed.ejb.NotificationNonprofit;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejb.RecurrableDonation;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejb.UserGeneral;

public class NonprofitWrapper extends ParentUserWrapper{
	private Nonprofit wrapperObject;

	public NonprofitWrapper() {
		super();
		setWrapperObject(new Nonprofit());
	}
	
	public NonprofitWrapper(Nonprofit nonProfit) {
		super();
		setWrapperObject(nonProfit);
	}

	public Nonprofit getWrapperObject() {
		return wrapperObject;
	}

	public void setWrapperObject(Nonprofit wrapperObject) {
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

	public String getBanKAccount() {
		return wrapperObject.getBanKAccount();
	}

	public void setBanKAccount(String banKAccount) {
		wrapperObject.setBanKAccount(banKAccount);
	}

	public Date getDateTime() {
		return wrapperObject.getDateTime();
	}

	public void setDateTime(Date dateTime) {
		wrapperObject.setDateTime(dateTime);
	}

	public String getDescription() {
		return wrapperObject.getDescription();
	}

	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	public String getMainPicture() {
		return wrapperObject.getMainPicture();
	}

	public void setMainPicture(String mainPicture) {
		wrapperObject.setMainPicture(mainPicture);
	}

	public String getMision() {
		return wrapperObject.getMision();
	}

	public void setMision(String mision) {
		wrapperObject.setMision(mision);
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

	public String getReason() {
		return wrapperObject.getReason();
	}

	public void setReason(String reason) {
		wrapperObject.setReason(reason);
	}

	public String getWebPage() {
		return wrapperObject.getWebPage();
	}

	public void setWebPage(String webPage) {
		wrapperObject.setWebPage(webPage);
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public List<Campaign> getCampaigns() {
		return wrapperObject.getCampaigns();
	}

	public void setCampaigns(List<Campaign> campaigns) {
		wrapperObject.setCampaigns(campaigns);
	}

	public Campaign addCampaign(Campaign campaign) {
		return wrapperObject.addCampaign(campaign);
	}

	public Campaign removeCampaign(Campaign campaign) {
		return wrapperObject.removeCampaign(campaign);
	}

	public Catalog getCause() {
		return wrapperObject.getCause();
	}

	public void setCause(Catalog cause) {
		wrapperObject.setCause(cause);
	}

	public Catalog getConutry() {
		return wrapperObject.getConutry();
	}

	public void setConutry(Catalog conutry) {
		wrapperObject.setConutry(conutry);
	}

	public List<NonprofitSetting> getNonprofitsettings() {
		return wrapperObject.getNonprofitsettings();
	}

	public void setNonprofitsettings(List<NonprofitSetting> nonprofitsettings) {
		wrapperObject.setNonprofitsettings(nonprofitsettings);
	}

	public NonprofitSetting addNonprofitsetting(
			NonprofitSetting nonprofitsetting) {
		return wrapperObject.addNonprofitsetting(nonprofitsetting);
	}

	public NonprofitSetting removeNonprofitsetting(
			NonprofitSetting nonprofitsetting) {
		return wrapperObject.removeNonprofitsetting(nonprofitsetting);
	}

	public List<NotificationNonprofit> getNotificationnonprofits() {
		return wrapperObject.getNotificationnonprofits();
	}

	public void setNotificationnonprofits(
			List<NotificationNonprofit> notificationnonprofits) {
		wrapperObject.setNotificationnonprofits(notificationnonprofits);
	}

	public NotificationNonprofit addNotificationnonprofit(
			NotificationNonprofit notificationnonprofit) {
		return wrapperObject.addNotificationnonprofit(notificationnonprofit);
	}

	public NotificationNonprofit removeNotificationnonprofit(
			NotificationNonprofit notificationnonprofit) {
		return wrapperObject.removeNotificationnonprofit(notificationnonprofit);
	}

	public List<PostNonprofit> getPostnonprofits() {
		return wrapperObject.getPostnonprofits();
	}

	public void setPostnonprofits(List<PostNonprofit> postnonprofits) {
		wrapperObject.setPostnonprofits(postnonprofits);
	}

	public PostNonprofit addPostnonprofit(PostNonprofit postnonprofit) {
		return wrapperObject.addPostnonprofit(postnonprofit);
	}

	public PostNonprofit removePostnonprofit(PostNonprofit postnonprofit) {
		return wrapperObject.removePostnonprofit(postnonprofit);
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

	public List<TransparencyReport> getTransparencyreports() {
		return wrapperObject.getTransparencyreports();
	}

	public void setTransparencyreports(
			List<TransparencyReport> transparencyreports) {
		wrapperObject.setTransparencyreports(transparencyreports);
	}

	public TransparencyReport addTransparencyreport(
			TransparencyReport transparencyreport) {
		return wrapperObject.addTransparencyreport(transparencyreport);
	}

	public TransparencyReport removeTransparencyreport(
			TransparencyReport transparencyreport) {
		return wrapperObject.removeTransparencyreport(transparencyreport);
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
