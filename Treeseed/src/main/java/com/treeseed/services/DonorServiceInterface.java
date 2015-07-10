package com.treeseed.services;

import org.springframework.data.domain.Page;
<<<<<<< HEAD
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;

public interface DonorServiceInterface {

	Page<Donor> getDonor(DonorRequest dr);

	Boolean saveDonor(Donor donor);

	Donor getSessionDonor(int idDonor);

	Page<Donor> getByName(DonorRequest dr);
	
	Page<Donor> getByCountry(DonorRequest dr);
	
	Page<Donor> getByLastName(DonorRequest dr);
=======

import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.DonorWrapper;

public interface DonorServiceInterface {

	Page<Donor> getAll(DonorRequest ur);

	Boolean saveDonor(DonorWrapper user);

	Donor getSessionDonor(int idUser);

>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
}
