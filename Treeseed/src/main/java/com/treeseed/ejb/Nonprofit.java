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

	private String banKAccount;

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
	
	private boolean isActive;

	//bi-directional many-to-one association to Campaign
	@OneToMany(mappedBy="nonprofit")
	private List<Campaign> campaigns;

	//uni-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cause")
	private Catalog cause;

	//uni-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country")
	private Catalog country;

	//bi-directional many-to-one association to Nonprofitsetting
	@OneToMany(mappedBy="nonprofit")
	private List<NonprofitSetting> nonprofitSettings;

	//bi-directional many-to-one association to Notificationnonprofit
	@OneToMany(mappedBy="nonprofit")
	private List<NotificationNonprofit> notificationNonprofits;

	//bi-directional many-to-one association to Postnonprofit
	@OneToMany(mappedBy="nonprofit")
	private List<PostNonprofit> postNonprofits;

	//bi-directional many-to-one association to Recurrabledonation
	@OneToMany(mappedBy="nonprofit")
	private List<RecurrableDonation> recurrableDonations;

	//bi-directional many-to-one association to Transparencyreport
	@OneToMany(mappedBy="nonprofit")
	private List<TransparencyReport> transparencyReports;

	//bi-directional many-to-one association to Usergeneral
	@OneToMany(mappedBy="nonprofit")
	private List<UserGeneral> userGenerals;

	public Nonprofit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBanKAccount() {
		return this.banKAccount;
	}

	public void setBanKAccount(String banKAccount) {
		this.banKAccount = banKAccount;
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

	public Catalog getCause() {
		return this.cause;
	}

	public void setCause(Catalog cause) {
		this.cause = cause;
	}

	public Catalog getConutry() {
		return this.country;
	}

	public void setConutry(Catalog conutry) {
		this.country = conutry;
	}

	public List<NonprofitSetting> getNonprofitsettings() {
		return this.nonprofitSettings;
	}

	public void setNonprofitsettings(List<NonprofitSetting> nonprofitsettings) {
		this.nonprofitSettings = nonprofitsettings;
	}

	public NonprofitSetting addNonprofitsetting(NonprofitSetting nonprofitsetting) {
		getNonprofitsettings().add(nonprofitsetting);
		nonprofitsetting.setNonprofit(this);

		return nonprofitsetting;
	}

	public NonprofitSetting removeNonprofitsetting(NonprofitSetting nonprofitsetting) {
		getNonprofitsettings().remove(nonprofitsetting);
		nonprofitsetting.setNonprofit(null);

		return nonprofitsetting;
	}

	public List<NotificationNonprofit> getNotificationnonprofits() {
		return this.notificationNonprofits;
	}

	public void setNotificationnonprofits(List<NotificationNonprofit> notificationnonprofits) {
		this.notificationNonprofits = notificationnonprofits;
	}

	public NotificationNonprofit addNotificationnonprofit(NotificationNonprofit notificationnonprofit) {
		getNotificationnonprofits().add(notificationnonprofit);
		notificationnonprofit.setNonprofit(this);

		return notificationnonprofit;
	}

	public NotificationNonprofit removeNotificationnonprofit(NotificationNonprofit notificationnonprofit) {
		getNotificationnonprofits().remove(notificationnonprofit);
		notificationnonprofit.setNonprofit(null);

		return notificationnonprofit;
	}

	public List<PostNonprofit> getPostnonprofits() {
		return this.postNonprofits;
	}

	public void setPostnonprofits(List<PostNonprofit> postnonprofits) {
		this.postNonprofits = postnonprofits;
	}

	public PostNonprofit addPostnonprofit(PostNonprofit postnonprofit) {
		getPostnonprofits().add(postnonprofit);
		postnonprofit.setNonprofit(this);

		return postnonprofit;
	}

	public PostNonprofit removePostnonprofit(PostNonprofit postnonprofit) {
		getPostnonprofits().remove(postnonprofit);
		postnonprofit.setNonprofit(null);

		return postnonprofit;
	}

	public List<RecurrableDonation> getRecurrabledonations() {
		return this.recurrableDonations;
	}

	public void setRecurrabledonations(List<RecurrableDonation> recurrabledonations) {
		this.recurrableDonations = recurrabledonations;
	}

	public RecurrableDonation addRecurrabledonation(RecurrableDonation recurrabledonation) {
		getRecurrabledonations().add(recurrabledonation);
		recurrabledonation.setNonprofit(this);

		return recurrabledonation;
	}

	public RecurrableDonation removeRecurrabledonation(RecurrableDonation recurrabledonation) {
		getRecurrabledonations().remove(recurrabledonation);
		recurrabledonation.setNonprofit(null);

		return recurrabledonation;
	}

	public List<TransparencyReport> getTransparencyreports() {
		return this.transparencyReports;
	}

	public void setTransparencyreports(List<TransparencyReport> transparencyreports) {
		this.transparencyReports = transparencyreports;
	}

	public TransparencyReport addTransparencyreport(TransparencyReport transparencyreport) {
		getTransparencyreports().add(transparencyreport);
		transparencyreport.setNonprofit(this);

		return transparencyreport;
	}

	public TransparencyReport removeTransparencyreport(TransparencyReport transparencyreport) {
		getTransparencyreports().remove(transparencyreport);
		transparencyreport.setNonprofit(null);

		return transparencyreport;
	}

	public List<UserGeneral> getUsergenerals() {
		return this.userGenerals;
	}

	public void setUsergenerals(List<UserGeneral> usergenerals) {
		this.userGenerals = usergenerals;
	}

	public UserGeneral addUsergeneral(UserGeneral usergeneral) {
		getUsergenerals().add(usergeneral);
		usergeneral.setNonprofit(this);

		return usergeneral;
	}

	public UserGeneral removeUsergeneral(UserGeneral usergeneral) {
		getUsergenerals().remove(usergeneral);
		usergeneral.setNonprofit(null);

		return usergeneral;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}