package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;

public interface DonorServiceInterface {

	Page<Donor> getAll(DonorRequest ur);

	int saveDonor(DonorWrapper user);

	Donor getSessionDonor(int idUser);
	
	DonorWrapper getDonorProfileByID(int id);
	
	void deleteDonor(DonorRequest dr);
	
	void updateDonor(DonorWrapper donor);
}
