package com.treeseed.contracts;

import java.util.List;
<<<<<<< HEAD

import com.treeseed.pojo.DonorPOJO;

public class DonorResponse extends BaseResponse{
	
	private List<DonorPOJO> donors;

=======
import com.treeseed.pojo.DonorPOJO;

public class DonorResponse extends BaseResponse {
	
	private List<DonorPOJO> donors;
	
>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
	public DonorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
<<<<<<< HEAD
	public List<DonorPOJO> getDonor() {
		return donors;
	}

	public void setDonor(List<DonorPOJO> donors) {
=======
	public List<DonorPOJO> getUsuarios() {
		return donors;
	}

	public void setUsuarios(List<DonorPOJO> donors) {
>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
		this.donors = donors;
	}

}
