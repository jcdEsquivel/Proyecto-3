package com.treeseed.repositories;

import org.springframework.data.repository.CrudRepository;

import com.treeseed.ejb.UserGeneral;



public interface LoginRepository extends CrudRepository<UserGeneral,Integer> {
	
	public static final int PAGE_SIZE = 5;
	
	UserGeneral findByEmailAndPassword(String email, String password);
}
