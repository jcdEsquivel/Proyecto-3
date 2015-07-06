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

	@Query("SELECT p FROM Nonprofit p WHERE ( :nameNull is null or p.name like :name)")
	   public Page<Nonprofit> find(@Param("nameNull") String nameNull, @Param("name") String name,
			   Pageable pageable);
	
	
	@Query("SELECT p FROM Nonprofit p inner join p.cause c inner join p.country d WHERE ( :nameNull is null or p.name like :name) and "
			+ "( :country = 0 or d.id = :country) and "
			+ "( :cause = 0 or c.id = :cause)")
	   public Page<Nonprofit> findConTodo(@Param("nameNull") String nameNull, @Param("name") String name,
			   @Param("country") int country,
			   @Param("cause") int cause,
			   Pageable pageable);
	
	
	@Query("SELECT p FROM Nonprofit p WHERE ( :nameNull is null or p.name like :name) and "
			+ "( :countryNull is null or p.country like :country) and "
			+ "( :causeNull is null or p.cause like :cause)")
	
	Page<Nonprofit> findAll(Pageable pageable);
	Page<Nonprofit> findByNameContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByReasonContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByCountryContaining(String name,
			Pageable pageable);
	
	
;
}
