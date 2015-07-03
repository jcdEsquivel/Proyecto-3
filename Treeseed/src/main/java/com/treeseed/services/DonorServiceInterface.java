package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;

public interface DonorServiceInterface {

	Page<Donor> getAll(DonorRequest ur);

	Boolean saveDonor(Donor user);

	Donor getSessionDonor(int idUser);

}
