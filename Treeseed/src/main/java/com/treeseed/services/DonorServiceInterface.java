package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.DonorWrapper;

public interface DonorServiceInterface {

	Page<Donor> getAll(DonorRequest ur);

	Boolean saveDonor(DonorWrapper user);

	Donor getSessionDonor(int idUser);

}