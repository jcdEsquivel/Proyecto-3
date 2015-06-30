package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.LoginRequest;
import com.treeseed.ejb.Usuario;
import com.treeseed.repositories.LoginRepository;

@Service
public class LoginService implements LoginServiceInterface{

	@Autowired
	LoginRepository loginRepository;
	
	@Override
	@Transactional
	public Usuario checkUser(LoginRequest lr) {
		return loginRepository.findByEmailAndPassword(lr.getEmail(), lr.getPassword());
	}		
}