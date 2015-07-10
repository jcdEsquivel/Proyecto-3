package com.treeseed.contracts;

import java.util.List;
import com.treeseed.pojo.DonorPOJO;

public class DonorResponse extends BaseResponse {
	
	private List<DonorPOJO> donors;
	
	public DonorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<DonorPOJO> getUsuarios() {
		return donors;
	}

	public void setUsuarios(List<DonorPOJO> donors) {
		this.donors = donors;
	}

}
