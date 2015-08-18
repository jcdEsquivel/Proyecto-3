package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the postcampaign database table.
 * 
 */
@Entity
@NamedQuery(name="PostCampaign.findAll", query="SELECT p FROM PostCampaign p")
public class PostCampaign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(length=500)
	private String description;

	private String picture;

	private String tittle;
	
	private boolean isActive;

	//bi-directional many-to-one association to Campaign
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCampaign")
	private Campaign campaign;

	public PostCampaign() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTittle() {
		return this.tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

}