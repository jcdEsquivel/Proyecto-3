package com.treeseed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.treeseed.contracts.CatalogRequest;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.repositories.CatalogRepository;

@Service
public class CatalogService implements CatalogServiceInterface{
	
	@Autowired
	CatalogRepository catalogRepository;
	
	@Override
	@Transactional
	public Boolean saveCatalog(Catalog user) {
		Catalog nuser = catalogRepository.save(user);
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

	@Override
	@Transactional
	public Page<Catalog> getAll(CatalogRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public Page<Catalog> getAll() {	
		return null;
	}

	@Override
	@Transactional
	public Catalog getSessionCatalog(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	@Transactional
	public List<CatalogWrapper> getAllByType(String type){	
		
		List<CatalogWrapper> list = jdbcTemplate.query(
                "SELECT id, english, spanish  FROM catalog WHERE type = ?", new Object[] { type },
                (rs, rowNum) -> new CatalogWrapper(rs.getInt("id"), rs.getString("english"),rs.getString("spanish"))
        );

		return list;

	}	
	
	public CatalogWrapper findCatalogById(int id){
		
		List<Catalog> listCat = catalogRepository.findById(id);
		CatalogWrapper resul = new CatalogWrapper(listCat.get(0));
		
		return resul;

	}
	
}
