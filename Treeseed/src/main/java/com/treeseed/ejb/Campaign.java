package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the campaign database table.
 * 
 */
@Entity
@NamedQuery(name="Campaign.findAll", query="SELECT c FROM Campaign c")
public class Campaign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double amountCollected;

	private double amountGoal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Column(length=1000)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	private String name;

	private String picture;
	
	private boolean isActive;


	//bi-directional many-to-one association to Nonprofit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idNonProfit")
	private Nonprofit nonprofit;

	//bi-directional many-to-one association to Postcampaign
	@OneToMany(mappedBy="campaign")
	private List<PostCampaign> postCampaigns;

	public Campaign() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmountCollected() {
		return this.amountCollected;
	}

	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}

	public double getAmountGoal() {
		return this.amountGoal;
	}

	public void setAmountGoal(double amountGoal) {
		this.amountGoal = amountGoal;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

	public List<PostCampaign> getPostcampaigns() {
		return this.postCampaigns;
	}

	public void setPostcampaigns(List<PostCampaign> postcampaigns) {
		this.postCampaigns = postcampaigns;
	}
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public PostCampaign addPostcampaign(PostCampaign postcampaign) {
		getPostcampaigns().add(postcampaign);
		postcampaign.setCampaign(this);

		return postcampaign;
	}

	public PostCampaign removePostcampaign(PostCampaign postcampaign) {
		getPostcampaigns().remove(postcampaign);
		postcampaign.setCampaign(null);

		return postcampaign;
	}

}