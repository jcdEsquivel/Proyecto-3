package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.treeseed.contracts.CatalogRequest;
import com.treeseed.ejb.Catalog;

public interface CatalogServiceInterface {
		
	Page<Catalog> getAll(CatalogRequest ur);

	Boolean saveCatalog(Catalog user);

	Catalog getSessionCatalog(int idUser);

	Page<Catalog> getAll();
}
