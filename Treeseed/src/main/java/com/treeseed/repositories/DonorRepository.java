package com.treeseed.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;

public interface DonorRepository extends 
	CrudRepository<Donor,Integer> {
	
	public static final int PAGE_SIZE = 10;

	@Query("SELECT p FROM Donor p WHERE ( :nombreNull is null or p.name like :nombre)")
	   public Page<Donor> find(@Param("nombreNull") String nombreNull, @Param("nombre") String nombre, Pageable pageable);
	
	
	
	
	Page<Donor> findAll(Pageable pageable);
	Page<Donor> findByNameContaining(String name,
			Pageable pageable);
	Page<Donor> findByCountryContaining(String name,
			Pageable pageable);
	
	
;
}
