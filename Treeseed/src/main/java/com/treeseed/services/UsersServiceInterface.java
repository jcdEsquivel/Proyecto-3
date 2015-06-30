package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.UsersRequest;
import com.treeseed.ejb.Usuario;

public interface UsersServiceInterface {

	Page<Usuario> getAll(UsersRequest ur);

	Boolean saveUser(Usuario user);

	Usuario getSessionUser(int idUser);

}
