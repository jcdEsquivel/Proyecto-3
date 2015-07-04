package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.UserGeneralWrapper;

public interface UserGeneralServiceInterface {

	Page<UserGeneral> getAll(UserGeneralRequest ur);

	Boolean saveUserGeneral(UserGeneralWrapper userGeneral);

	UserGeneral getSessionUserGeneral(int idUserGeneral);
	
	UserGeneral getUserByEmailAndPassword(String ur, String pas );


}
