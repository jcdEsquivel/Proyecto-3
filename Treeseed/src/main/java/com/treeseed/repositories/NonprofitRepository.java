package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.treeseed.ejb.Nonprofit;

public interface NonprofitRepository extends 
	CrudRepository<Nonprofit,Integer> {
	
	public static final int PAGE_SIZE = 5;
	
	Page<Nonprofit> findAll(Pageable pageable);
	Page<Nonprofit> findByNameContaining(String firstName,
			Pageable pageable);
	

}
