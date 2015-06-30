package com.treeseed.repositories;

import org.springframework.data.repository.CrudRepository;

import com.treeseed.ejb.Usuario;

public interface LoginRepository extends CrudRepository<Usuario,Integer> {
	
	public static final int PAGE_SIZE = 5;
	
	Usuario findByEmailAndPassword(String email, String password);
}
