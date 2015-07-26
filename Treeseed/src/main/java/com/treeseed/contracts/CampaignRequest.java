package com.treeseed.contracts;

import java.util.Date;
import java.util.List;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.NonprofitPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignRequest.
 */
public class CampaignRequest extends BasePagingRequest {

	/** The campaign. */
	private CampaignPOJO campaign;

	/** The id. */
	private int id;

	/** The amount collected. */
	private double amountCollected;

	/** The amount goal. */
	private double amountGoal;

	/** The creation date. */
	private Date creationDate;

	/** The description. */
	private String description;
	
	/** The start date. */
	private long startDate;

	/** The due date. */
	private long dueDate;

	/** The picture. */
	private String picture;

	/** The nonprofit id. */
	private int nonprofitId;
	
	/** The is active. */
	private boolean isActive;

	/** The id user. */
	private int idUser;

	/** The name. */
	private String name;
	
	/** The nonprofit name. */
	private String nonprofitName;
	
	/** The cause id. */
	private int causeId;
	//private long fechaInicio;
	//private long fechaFin;

	/**
	 * Gets the nonprofit name.
	 *
	 * @return the nonprofit name
	 */
	public String getNonprofitName() {
		return nonprofitName;
	}

	public double getAmountCollected() {
		return amountCollected;
	}

	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}

	public double getAmountGoal() {
		return amountGoal;
	}

	public void setAmountGoal(double amountGoal) {
		this.amountGoal = amountGoal;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** The nonprofit. */
	private NonprofitPOJO nonprofit;
	
	/**
	 * Sets the nonprofit name.
	 *
	 * @param nonprofitName the new nonprofit name
	 */
	public void setNonprofitName(String nonprofitName) {
		this.nonprofitName = nonprofitName;
	}

	/**
	 * Gets the cause id.
	 *
	 * @return the cause id
	 */
	public int getCauseId() {
		return causeId;
	}

	/**
	 * Sets the cause id.
	 *
	 * @param causeId the new cause id
	 */
	public void setCauseId(int causeId) {
		this.causeId = causeId;
	}

	/*public long getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(long fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public long getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(long fechaFin) {
		this.fechaFin = fechaFin;
	}*/


	/** The post campaigns. */
	private List<PostCampaign> postCampaigns;

	/**
	 * Gets the campaign.
	 *
	 * @return the campaign
	 */
	public CampaignPOJO getCampaign() {
		return campaign;
	}

	/**
	 * Sets the campaign.
	 *
	 * @param campaign the new campaign
	 */
	public void setCampaign(CampaignPOJO campaign) {
		this.campaign = campaign;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * Gets the nonprofit.
	 *
	 * @return the nonprofit
	 */
	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}

	/**
	 * Sets the nonprofit.
	 *
	 * @param nonprofit the new nonprofit
	 */
	public void setNonprofit(NonprofitPOJO nonprofit) {
		this.nonprofit = nonprofit;
	}

	/**
	 * Gets the post campaigns.
	 *
	 * @return the post campaigns
	 */
	public List<PostCampaign> getPostCampaigns() {
		return postCampaigns;
	}

	/**
	 * Sets the post campaigns.
	 *
	 * @param postCampaigns the new post campaigns
	 */
	public void setPostCampaigns(List<PostCampaign> postCampaigns) {
		this.postCampaigns = postCampaigns;
	}

	/**
	 * Gets the nonprofit id.
	 *
	 * @return the nonprofit id
	 */
	public int getNonprofitId() {
		return nonprofitId;
	}

	/**
	 * Sets the nonprofit id.
	 *
	 * @param nonprofitId the new nonprofit id
	 */
	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}

	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public long getDueDate() {
		return dueDate;
	}

	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public long getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
