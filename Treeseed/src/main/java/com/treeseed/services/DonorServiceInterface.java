package com.treeseed.services;

import org.springframework.data.domain.Page;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;

public interface DonorServiceInterface {

	Page<Donor> getDonor(DonorRequest dr);

	Boolean saveDonor(Donor donor);

	Donor getSessionDonor(int idDonor);

	Page<Donor> getByName(DonorRequest dr);
	
	Page<Donor> getByCountry(DonorRequest dr);
	
	Page<Donor> getByLastName(DonorRequest dr);
}
