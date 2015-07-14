package com.treeseed.contracts;

import java.util.List;
import com.treeseed.pojo.DonorPOJO;

public class DonorResponse extends BaseResponse {
	
	private List<DonorPOJO> donors;
	private DonorPOJO donor;
	private boolean isOwner;

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	public DonorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<DonorPOJO> getDonors() {
		return donors;
	}

	public DonorPOJO getDonor() {
		return donor;
	}

	public void setDonor(DonorPOJO donor) {
		this.donor = donor;
	}

	public void setDonor(List<DonorPOJO> donors) {
		this.donors = donors;
	}

	public List<DonorPOJO> getUsuarios() {
		return donors;
	}

	public void setUsuarios(List<DonorPOJO> donors) {
		this.donors = donors;
	}

}
