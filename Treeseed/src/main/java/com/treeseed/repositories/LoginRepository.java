package com.treeseed.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.UserGeneral;



public interface LoginRepository extends CrudRepository<UserGeneral,Integer> {
	
	public static final int PAGE_SIZE = 5;
	
	@Query("SELECT p FROM UserGeneral p WHERE (p.email = :email) and (p.password = :password) and isActive = 1")
	UserGeneral findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	
	UserGeneral findByEmailAndPasswordAndIsActive(String email, 
			String password, boolean isActive);
}
