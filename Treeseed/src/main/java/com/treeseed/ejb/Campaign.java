package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the campaign database table.
 * 
 */
@Entity
@NamedQuery(name="Campaign.findAll", query="SELECT c FROM Campaign c")
public class Campaign implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/** The amount collected. */
	private double amountCollected;

	/** The amount goal. */
	private double amountGoal;

	/** The creation date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	/** The description. */
	@Column(length=1000)
	private String description;

	/** The start date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	/** The due date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	/** The name. */
	private String name;

	/** The picture. */
	private String picture;
	
	/** The is active. */
	private boolean isActive;


	/** The nonprofit. */
	//bi-directional many-to-one association to Nonprofit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idNonProfit")
	private Nonprofit nonprofit;

	/** The post campaigns. */
	//bi-directional many-to-one association to Postcampaign
	@OneToMany(mappedBy="campaign")
	private List<PostCampaign> postCampaigns;

	/**
	 * Instantiates a new campaign.
	 */
	public Campaign() {
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
	 * Gets the amount collected.
	 *
	 * @return the amount collected
	 */
	public double getAmountCollected() {
		return this.amountCollected;
	}

	/**
	 * Sets the amount collected.
	 *
	 * @param amountCollected the new amount collected
	 */
	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}

	/**
	 * Gets the amount goal.
	 *
	 * @return the amount goal
	 */
	public double getAmountGoal() {
		return this.amountGoal;
	}

	/**
	 * Sets the amount goal.
	 *
	 * @param amountGoal the new amount goal
	 */
	public void setAmountGoal(double amountGoal) {
		this.amountGoal = amountGoal;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public Date getDueDate() {
		return this.dueDate;
	}

	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return this.picture;
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
	 * Gets the nonprofit.
	 *
	 * @return the nonprofit
	 */
	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	/**
	 * Sets the nonprofit.
	 *
	 * @param nonprofit the new nonprofit
	 */
	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

	/**
	 * Gets the postcampaigns.
	 *
	 * @return the postcampaigns
	 */
	public List<PostCampaign> getPostcampaigns() {
		return this.postCampaigns;
	}

	/**
	 * Sets the postcampaigns.
	 *
	 * @param postcampaigns the new postcampaigns
	 */
	public void setPostcampaigns(List<PostCampaign> postcampaigns) {
		this.postCampaigns = postcampaigns;
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
	 * Adds the postcampaign.
	 *
	 * @param postcampaign the postcampaign
	 * @return the post campaign
	 */
	public PostCampaign addPostcampaign(PostCampaign postcampaign) {
		getPostcampaigns().add(postcampaign);
		postcampaign.setCampaign(this);

		return postcampaign;
	}

	/**
	 * Removes the postcampaign.
	 *
	 * @param postcampaign the postcampaign
	 * @return the post campaign
	 */
	public PostCampaign removePostcampaign(PostCampaign postcampaign) {
		getPostcampaigns().remove(postcampaign);
		postcampaign.setCampaign(null);

		return postcampaign;
	}

}