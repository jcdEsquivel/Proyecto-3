package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.RecurrableDonationPOJO;
import com.treeseed.pojo.UserGeneralPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class NonprofitResponse.
 */
public class NonprofitResponse extends BaseResponse{
	
	/** The nonprofits. */
	private List<NonprofitPOJO> nonprofits;
	
	/** The nonprofit. */
	private NonprofitPOJO nonprofit;
	
	/** The non profit id. */
	private int nonProfitId;
	
	/** The is owner. */
	private boolean isOwner;
	
	/** The user general. */
	private UserGeneralPOJO userGeneral;
	
	/** The dashboard donations. */
	List<DonationPOJO> dashboardDonations;
	
	/** The dashboard subscription. */
	List<RecurrableDonationPOJO> dashboardSubscription;



	/**
	 * Gets the dashboard donations.
	 *
	 * @return the dashboard donations
	 */
	public List<DonationPOJO> getDashboardDonations() {
		return dashboardDonations;
	}

	/**
	 * Sets the dashboard donations.
	 *
	 * @param dashboardDonations the new dashboard donations
	 */
	public void setDashboardDonations(List<DonationPOJO> dashboardDonations) {
		this.dashboardDonations = dashboardDonations;
	}

	/**
	 * Gets the dashboard subscription.
	 *
	 * @return the dashboard subscription
	 */
	public List<RecurrableDonationPOJO> getDashboardSubscription() {
		return dashboardSubscription;
	}

	/**
	 * Sets the dashboard subscription.
	 *
	 * @param dashboardSubscription the new dashboard subscription
	 */
	public void setDashboardSubscription(List<RecurrableDonationPOJO> dashboardSubscription) {
		this.dashboardSubscription = dashboardSubscription;
	}

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
	 * @param userGeneral the new user general
	 */
	public void setUserGeneral(UserGeneralPOJO userGeneral) {
		this.userGeneral = userGeneral;
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
	 * @param isOwner the new owner
	 */
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	/**
	 * Instantiates a new nonprofit response.
	 */
	public NonprofitResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the nonprofits.
	 *
	 * @return the nonprofits
	 */
	public List<NonprofitPOJO> getNonprofits() {
		return nonprofits;
	}

	/**
	 * Sets the nonprofits.
	 *
	 * @param nonprofits the new nonprofits
	 */
	public void setNonprofits(List<NonprofitPOJO> nonprofits) {
		this.nonprofits = nonprofits;
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
	 * Gets the nonprofit.
	 *
	 * @return the nonprofit
	 */
	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}

	/**
	 * Gets the non profit id.
	 *
	 * @return the non profit id
	 */
	public int getNonProfitId() {
		return nonProfitId;
	}

	/**
	 * Sets the non profit id.
	 *
	 * @param nonProfitId the new non profit id
	 */
	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	
	

}
