package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.DonationPOJO;

public class DonationResponse extends BaseResponse {
	
private List<DonationPOJO> donations;
private DonationPOJO donation;
	
	public DonationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<DonationPOJO> getDonations() {
		return donations;
	}

	public void setDonations(List<DonationPOJO> donations) {
		this.donations = donations;
	}

	public DonationPOJO getDonation() {
		return donation;
	}

	public void setDonation(DonationPOJO donation) {
		this.donation = donation;
	}	
	
}
