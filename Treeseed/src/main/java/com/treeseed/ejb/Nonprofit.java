package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the nonprofit database table.
 * 
 */
@Entity
@NamedQuery(name="Nonprofit.findAll", query="SELECT n FROM Nonprofit n")
public class Nonprofit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String banckAccount;

	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	private String description;

	private String mainPicture;

	private String mision;

	private String name;

	private String profilePicture;

	private String reason;

	private String webPage;

	//bi-directional many-to-one association to Campaign
	@OneToMany(mappedBy="nonprofit")
	private List<Campaign> campaigns;

	//bi-directional many-to-one association to Cause
	@ManyToOne
	@JoinColumn(name="cause")
	private Cause causeBean;

	//bi-directional many-to-one association to Nonprofitsetting
	@OneToMany(mappedBy="nonprofit")
	private List<Nonprofitsetting> nonprofitsettings;

	//bi-directional many-to-one association to Notificationnonprofit
	@OneToMany(mappedBy="nonprofit")
	private List<Notificationnonprofit> notificationnonprofits;

	//bi-directional many-to-one association to Postnonprofit
	@OneToMany(mappedBy="nonprofit")
	private List<Postnonprofit> postnonprofits;

	//bi-directional many-to-one association to Recurrabledonation
	@OneToMany(mappedBy="nonprofit")
	private List<Recurrabledonation> recurrabledonations;

	//bi-directional many-to-one association to Transparencyreport
	@OneToMany(mappedBy="nonprofit")
	private List<Transparencyreport> transparencyreports;

	//bi-directional many-to-one association to Usergeneral
	@OneToMany(mappedBy="nonprofit")
	private List<Usergeneral> usergenerals;

	public Nonprofit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBanckAccount() {
		return this.banckAccount;
	}

	public void setBanckAccount(String banckAccount) {
		this.banckAccount = banckAccount;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMainPicture() {
		return this.mainPicture;
	}

	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}

	public String getMision() {
		return this.mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
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

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWebPage() {
		return this.webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public List<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public Campaign addCampaign(Campaign campaign) {
		getCampaigns().add(campaign);
		campaign.setNonprofit(this);

		return campaign;
	}

	public Campaign removeCampaign(Campaign campaign) {
		getCampaigns().remove(campaign);
		campaign.setNonprofit(null);

		return campaign;
	}

	public Cause getCauseBean() {
		return this.causeBean;
	}

	public void setCauseBean(Cause causeBean) {
		this.causeBean = causeBean;
	}

	public List<Nonprofitsetting> getNonprofitsettings() {
		return this.nonprofitsettings;
	}

	public void setNonprofitsettings(List<Nonprofitsetting> nonprofitsettings) {
		this.nonprofitsettings = nonprofitsettings;
	}

	public Nonprofitsetting addNonprofitsetting(Nonprofitsetting nonprofitsetting) {
		getNonprofitsettings().add(nonprofitsetting);
		nonprofitsetting.setNonprofit(this);

		return nonprofitsetting;
	}

	public Nonprofitsetting removeNonprofitsetting(Nonprofitsetting nonprofitsetting) {
		getNonprofitsettings().remove(nonprofitsetting);
		nonprofitsetting.setNonprofit(null);

		return nonprofitsetting;
	}

	public List<Notificationnonprofit> getNotificationnonprofits() {
		return this.notificationnonprofits;
	}

	public void setNotificationnonprofits(List<Notificationnonprofit> notificationnonprofits) {
		this.notificationnonprofits = notificationnonprofits;
	}

	public Notificationnonprofit addNotificationnonprofit(Notificationnonprofit notificationnonprofit) {
		getNotificationnonprofits().add(notificationnonprofit);
		notificationnonprofit.setNonprofit(this);

		return notificationnonprofit;
	}

	public Notificationnonprofit removeNotificationnonprofit(Notificationnonprofit notificationnonprofit) {
		getNotificationnonprofits().remove(notificationnonprofit);
		notificationnonprofit.setNonprofit(null);

		return notificationnonprofit;
	}

	public List<Postnonprofit> getPostnonprofits() {
		return this.postnonprofits;
	}

	public void setPostnonprofits(List<Postnonprofit> postnonprofits) {
		this.postnonprofits = postnonprofits;
	}

	public Postnonprofit addPostnonprofit(Postnonprofit postnonprofit) {
		getPostnonprofits().add(postnonprofit);
		postnonprofit.setNonprofit(this);

		return postnonprofit;
	}

	public Postnonprofit removePostnonprofit(Postnonprofit postnonprofit) {
		getPostnonprofits().remove(postnonprofit);
		postnonprofit.setNonprofit(null);

		return postnonprofit;
	}

	public List<Recurrabledonation> getRecurrabledonations() {
		return this.recurrabledonations;
	}

	public void setRecurrabledonations(List<Recurrabledonation> recurrabledonations) {
		this.recurrabledonations = recurrabledonations;
	}

	public Recurrabledonation addRecurrabledonation(Recurrabledonation recurrabledonation) {
		getRecurrabledonations().add(recurrabledonation);
		recurrabledonation.setNonprofit(this);

		return recurrabledonation;
	}

	public Recurrabledonation removeRecurrabledonation(Recurrabledonation recurrabledonation) {
		getRecurrabledonations().remove(recurrabledonation);
		recurrabledonation.setNonprofit(null);

		return recurrabledonation;
	}

	public List<Transparencyreport> getTransparencyreports() {
		return this.transparencyreports;
	}

	public void setTransparencyreports(List<Transparencyreport> transparencyreports) {
		this.transparencyreports = transparencyreports;
	}

	public Transparencyreport addTransparencyreport(Transparencyreport transparencyreport) {
		getTransparencyreports().add(transparencyreport);
		transparencyreport.setNonprofit(this);

		return transparencyreport;
	}

	public Transparencyreport removeTransparencyreport(Transparencyreport transparencyreport) {
		getTransparencyreports().remove(transparencyreport);
		transparencyreport.setNonprofit(null);

		return transparencyreport;
	}

	public List<Usergeneral> getUsergenerals() {
		return this.usergenerals;
	}

	public void setUsergenerals(List<Usergeneral> usergenerals) {
		this.usergenerals = usergenerals;
	}

	public Usergeneral addUsergeneral(Usergeneral usergeneral) {
		getUsergenerals().add(usergeneral);
		usergeneral.setNonprofit(this);

		return usergeneral;
	}

	public Usergeneral removeUsergeneral(Usergeneral usergeneral) {
		getUsergenerals().remove(usergeneral);
		usergeneral.setNonprofit(null);

		return usergeneral;
	}

}