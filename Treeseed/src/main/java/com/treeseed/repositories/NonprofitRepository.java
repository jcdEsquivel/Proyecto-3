package com.treeseed.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;

public interface NonprofitRepository extends 
	CrudRepository<Nonprofit,Integer> {
	
	public static final int PAGE_SIZE = 10;

	@Query("SELECT p FROM Nonprofit p WHERE ( :nombreNull is null or p.name like :nombre)")
	   public Page<Nonprofit> find(@Param("nombreNull") String nombreNull, @Param("nombre") String nombre, Pageable pageable);
	
	
	
	
	Page<Nonprofit> findAll(Pageable pageable);
	Page<Nonprofit> findByNameContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByReasonContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByCountryContaining(String name,
			Pageable pageable);
	
	
;
}
