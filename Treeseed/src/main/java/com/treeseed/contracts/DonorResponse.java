package com.treeseed.contracts;

import java.util.List;
import com.treeseed.pojo.DonorPOJO;

public class DonorResponse extends BaseResponse {
	
	private List<DonorPOJO> donors;
	private DonorPOJO donor;
	
	public DonorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<DonorPOJO> getDonor() {
		return donors;
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
