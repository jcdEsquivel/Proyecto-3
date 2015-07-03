package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.CatalogRequest;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;

public interface CatalogServiceInterface {
	
	Page<Catalog> getAll(CatalogRequest ur);

	Boolean saveCatalog(Catalog user);

	Donor getSessionCatalog(int idUser);


}
