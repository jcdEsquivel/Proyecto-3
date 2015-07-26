package com.treeseed.services;

import com.treeseed.ejbWrapper.UserGeneralWrapper;

public interface LoginServiceInterface {

	UserGeneralWrapper checkUser(String email, String password);

	UserGeneralWrapper checkFacebookUser(String facebookId);
}
