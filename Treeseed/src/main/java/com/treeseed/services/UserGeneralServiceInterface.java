package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.UserGeneral;

public interface UserGeneralServiceInterface {

	Page<UserGeneral> getAll(UserGeneralRequest ur);

	Boolean saveUserGeneral(UserGeneral userGeneral);

	UserGeneral getSessionUserGeneral(int idUserGeneral);

}
