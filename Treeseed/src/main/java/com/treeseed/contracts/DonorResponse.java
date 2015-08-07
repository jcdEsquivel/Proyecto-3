package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.DonorTreePOJO;
import com.treeseed.pojo.UserGeneralPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class DonorResponse.
 */
public class DonorResponse extends BaseResponse {

	/** The donors. */
	private List<DonorPOJO> donors;

	/** The tree. */
	private DonorTreePOJO tree;

	/** The donor. */
	private DonorPOJO donor;

	/** The is owner. */
	private boolean isOwner;

	/** The donor id. */
	private int donorId;

	/** The user general. */
	private UserGeneralPOJO userGeneral;

	/**
	 * Gets the user general.
	 *
	 * @return the user general
	 */
	public UserGeneralPOJO getUserGeneral() {
		return userGeneral;
	}

	/**
	 * Sets the user general.
	 *
	 * @param userGeneral
	 *            the new user general
	 */
	public void setUserGeneral(UserGeneralPOJO userGeneral) {
		this.userGeneral = userGeneral;
	}

	/**
	 * Gets the donor id.
	 *
	 * @return the donor id
	 */
	public int getDonorId() {
		return donorId;
	}

	/**
	 * Sets the donor id.
	 *
	 * @param donorId
	 *            the new donor id
	 */
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	/**
	 * Checks if is owner.
	 *
	 * @return true, if is owner
	 */
	public boolean isOwner() {
		return isOwner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param isOwner
	 *            the new owner
	 */
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	/**
	 * Instantiates a new donor response.
	 */
	public DonorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the donor.
	 *
	 * @return the donor
	 */
	public DonorPOJO getDonor() {
		return donor;
	}

	/**
	 * Sets the donor.
	 *
	 * @param donor
	 *            the new donor
	 */
	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}

	/**
	 * Gets the donors.
	 *
	 * @return the donors
	 */
	public List<DonorPOJO> getDonors() {
		return donors;
	}

	/**
	 * Sets the donors.
	 *
	 * @param donors
	 *            the new donors
	 */
	public void setDonors(List<DonorPOJO> donors) {
		this.donors = donors;
	}

	/**
	 * Sets the usuarios.
	 *
	 * @param donors
	 *            the new usuarios
	 */
	public void setUsuarios(List<DonorPOJO> donors) {
		this.donors = donors;
	}

	/**
	 * Gets the tree.
	 *
	 * @return the tree
	 */
	public DonorTreePOJO getTree() {
		return tree;
	}


	/**
	 * Sets the tree.
	 *
	 * @param tree
	 *            the new tree
	 */
	public void setTree(DonorTreePOJO tree) {
		this.tree = tree;
	}

}
