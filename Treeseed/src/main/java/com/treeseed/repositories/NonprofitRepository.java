package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.treeseed.ejb.Nonprofit;

public interface NonprofitRepository extends 
	CrudRepository<Nonprofit,Integer> {
	
	public static final int PAGE_SIZE = 10;
	
	Page<Nonprofit> findAll();
	Page<Nonprofit> findByNameContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByReasonContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByCountryContaining(String name,
			Pageable pageable);
	
	
;
}
