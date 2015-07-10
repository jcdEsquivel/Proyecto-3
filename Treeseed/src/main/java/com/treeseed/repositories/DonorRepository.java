package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.treeseed.ejb.Donor;

public interface DonorRepository extends 
	CrudRepository<Donor,Integer> {
	
	public static final int PAGE_SIZE = 5;
		
	Page<Donor> findAll(Pageable pageable);
	
	Page<Donor> findByNameContaining(String name,
			Pageable pageable);
	Page<Donor> findByLastNameContaining(String lastName,
			Pageable pageable);
}
