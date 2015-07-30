package com.treeseed.ejbWrapper;

import java.util.Date;

import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;

// TODO: Auto-generated Javadoc
/**
 * The Class PostNonprofitWrapper.
 */
public class PostNonprofitWrapper {

	/** The wrapper object. */
	private PostNonprofit wrapperObject;

	/**
	 * Instantiates a new post nonprofit wrapper.
	 */
	public PostNonprofitWrapper( ) {
		super();
		setWrapperObject(new PostNonprofit());
	}
	
	/**
	 * Instantiates a new post nonprofit wrapper.
	 *
	 * @param wrapperObject the wrapper object
	 */
	public PostNonprofitWrapper(PostNonprofit wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	/**
	 * Gets the wrapper object.
	 *
	 * @return the wrapper object
	 */
	public PostNonprofit getWrapperObject() {
		return wrapperObject;
	}

	/**
	 * Sets the wrapper object.
	 *
	 * @param wrapperObject the new wrapper object
	 */
	public void setWrapperObject(PostNonprofit wrapperObject) {
		this.wrapperObject = wrapperObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		return wrapperObject.equals(arg0);
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
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public boolean getIsActive() {
		return wrapperObject.getIsActive();
	}

	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(boolean isActive) {
		wrapperObject.setIsActive(isActive);
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

	/**
	 * Gets the tittle.
	 *
	 * @return the tittle
	 */
	public String getTittle() {
		return wrapperObject.getTittle();
	}

	/**
	 * Gets the nonprofit.
	 *
	 * @return the nonprofit
	 */
	public Nonprofit getNonprofit() {
		return wrapperObject.getNonprofit();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return wrapperObject.hashCode();
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
	 * Sets the nonprofit.
	 *
	 * @param nonprofit the new nonprofit
	 */
	public void setNonprofit(Nonprofit nonprofit) {
		wrapperObject.setNonprofit(nonprofit);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return wrapperObject.toString();
	}
}
