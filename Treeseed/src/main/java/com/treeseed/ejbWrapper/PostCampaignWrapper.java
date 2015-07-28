package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.PostCampaign;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignWrapper.
 */
public class PostCampaignWrapper {
	
	/** The wrapper object. */
	private PostCampaign wrapperObject;
	
	/**
	 * Instantiates a new post campaign wrapper.
	 *
	 * @param wrapperObject the wrapper object
	 */
	public PostCampaignWrapper(PostCampaign wrapperObject) {
		this.wrapperObject = wrapperObject;
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
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return wrapperObject.getCreationDate();
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		wrapperObject.setCreationDate(creationDate);
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

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return wrapperObject.getPicture();
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(String picture) {
		wrapperObject.setPicture(picture);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return wrapperObject.hashCode();
	}

	/**
	 * Gets the tittle.
	 *
	 * @return the tittle
	 */
	public String getTittle() {
		return wrapperObject.getTittle();
	}

	/**
	 * Sets the tittle.
	 *
	 * @param tittle the new tittle
	 */
	public void setTittle(String tittle) {
		wrapperObject.setTittle(tittle);
	}

	/**
	 * Gets the campaign.
	 *
	 * @return the campaign
	 */
	public Campaign getCampaign() {
		return wrapperObject.getCampaign();
	}

	/**
	 * Sets the campaign.
	 *
	 * @param campaign the new campaign
	 */
	public void setCampaign(Campaign campaign) {
		wrapperObject.setCampaign(campaign);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return wrapperObject.toString();
	}
	
}
