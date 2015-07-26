package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.LoginRequest;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.repositories.LoginRepository;

@Service
public class LoginService implements LoginServiceInterface{

	@Autowired
	LoginRepository loginRepository;
	
	@Override
	@Transactional
	public UserGeneralWrapper checkUser(String email, String password) {
		
		UserGeneralWrapper user= new UserGeneralWrapper();
		
		user.setWrapperObject(loginRepository.findByEmailAndPassword(email, password));
		
		return user;
	}
	
	@Override
	@Transactional
	public UserGeneralWrapper checkFacebookUser(String facebookId) {
		
		UserGeneralWrapper user= new UserGeneralWrapper();
		user.setWrapperObject(loginRepository.findByFacebookId(facebookId));
		
		return user;
	}
	
}