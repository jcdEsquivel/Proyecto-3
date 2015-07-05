package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;

public interface NonprofitRepository extends 
	CrudRepository<Nonprofit,Integer> {
	
	public static final int PAGE_SIZE = 10;
	
	String queryString = "select * from nonprofit p where ( :nombreNull is null or p.nombre like :nombre) and "+
            "( :apellido1Null is null or p.apellido1 like :apellido1) and "+
            "( :apellido2Null is null or p.apellido2 like :apellido2) and";
           

	
	
	Page<Nonprofit> findAll(Pageable pageable);
	Page<Nonprofit> findByNameContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByReasonContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByCountryContaining(String name,
			Pageable pageable);
	
	
;
}
