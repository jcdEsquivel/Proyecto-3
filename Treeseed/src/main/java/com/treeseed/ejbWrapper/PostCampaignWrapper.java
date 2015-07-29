package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignWrapper.
 */
public class PostCampaignWrapper {

	/** The post. */
	private PostCampaign post;
	
	
	/**
	 * Instantiates a new post campaign wrapper.
	 *
	 * @param post the post
	 */
	public PostCampaignWrapper(PostCampaign post) {
		super();
		
		setWrapperObject(post);
	}
	
	
	public PostCampaignWrapper( ) {
		super();
		setPost(new PostCampaign());
	}
	
	
	public PostCampaign getPost() {
		return post;
	}




	public void setPost(PostCampaign post) {
		this.post = post;
	}




	/**
	 * Gets the wrapper object.
	 *
	 * @return the wrapper object
	 */
	public PostCampaign getWrapperObject() {
		return post;
	}

	/**
	 * Sets the wrapper object.
	 *
	 * @param wrapperObject the new wrapper object
	 */
	public void setWrapperObject(PostCampaign wrapperObject) {
		this.post = wrapperObject;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return post.equals(obj);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return post.getId();
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		post.setId(id);
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return post.getCreationDate();
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		post.setCreationDate(creationDate);
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return post.getDescription();
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		post.setDescription(description);
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return post.isActive();
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		post.setActive(isActive);
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return post.getPicture();
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(String picture) {
		post.setPicture(picture);
	}

	/**
	 * Gets the tittle.
	 *
	 * @return the tittle
	 */
	public String getTittle() {
		return post.getTittle();
	}

	/**
	 * Gets the campaign.
	 *
	 * @return the campaign
	 */
	public Campaign getCampaign() {
		return post.getCampaign();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return post.hashCode();
	}

	/**
	 * Sets the tittle.
	 *
	 * @param tittle the new tittle
	 */
	public void setTittle(String tittle) {
		post.setTittle(tittle);
	}

	/**
	 * Sets the campaign.
	 *
	 * @param campaign the new campaign
	 */
	public void setCampaign(Campaign campaign) {
		post.setCampaign(campaign);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return post.toString();
	}

	
}
