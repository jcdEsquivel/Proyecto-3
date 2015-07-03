package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.CatalogRequest;
import com.treeseed.ejb.Catalog;
import com.treeseed.repositories.CatalogRepository;

@Service
public class CatalogService {
	
	@Autowired
	CatalogRepository catalog;
	
	@Transactional
	public Boolean saveDonor(Catalog user) {
		Catalog nuser = catalog.save(user);
		Boolean result = true;
		if(nuser == null){
			result = false;
		}
		return result;
	}

	@Transactional
	public Catalog getSessionDonor(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
