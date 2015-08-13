package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.RecurrableDonationPOJO;
import com.treeseed.pojo.UserGeneralPOJO;

public class NonprofitResponse extends BaseResponse{
	
	private List<NonprofitPOJO> nonprofits;
	private NonprofitPOJO nonprofit;
	private int nonProfitId;
	private boolean isOwner;
	private UserGeneralPOJO userGeneral;
	List<DonationPOJO> dashboardDonations;
	List<RecurrableDonationPOJO> dashboardSubscription;



	public List<DonationPOJO> getDashboardDonations() {
		return dashboardDonations;
	}

	public void setDashboardDonations(List<DonationPOJO> dashboardDonations) {
		this.dashboardDonations = dashboardDonations;
	}

	public List<RecurrableDonationPOJO> getDashboardSubscription() {
		return dashboardSubscription;
	}

	public void setDashboardSubscription(List<RecurrableDonationPOJO> dashboardSubscription) {
		this.dashboardSubscription = dashboardSubscription;
	}

	public UserGeneralPOJO getUserGeneral() {
		return userGeneral;
	}

	public void setUserGeneral(UserGeneralPOJO userGeneral) {
		this.userGeneral = userGeneral;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public NonprofitResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<NonprofitPOJO> getNonprofits() {
		return nonprofits;
	}

	public void setNonprofits(List<NonprofitPOJO> nonprofits) {
		this.nonprofits = nonprofits;
	}
	
	public void setNonprofit(NonprofitPOJO nonprofit) {
		this.nonprofit = nonprofit;
	}
	

	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}

	public int getNonProfitId() {
		return nonProfitId;
	}

	public void setNonProfitId(int nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	
	

}
