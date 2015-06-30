package com.treeseed.services;

import com.treeseed.contracts.LoginRequest;
import com.treeseed.ejb.Usuario;

public interface LoginServiceInterface {

	Usuario checkUser(LoginRequest lr);

}
