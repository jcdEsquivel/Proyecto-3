package com.treeseed.services;

import com.treeseed.ejbWrapper.UserGeneralWrapper;

public interface LoginServiceInterface {

	UserGeneralWrapper checkUser(String email, String password);
	
	/**
	 * check the facebook user.
	 *
	 * @param facebookId the facebook id
	 */
	UserGeneralWrapper checkFacebookUser(String facebookId);
}
