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
	CatalogRepository catalog;
	
	@Override
	@Transactional
	public Boolean saveCatalog(Catalog user) {
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
	public List<CatalogWrapper> getAllCatalogByType(String type){	
		
		List<CatalogWrapper> list = jdbcTemplate.query(
                "SELECT id, name FROM catalog WHERE type = ?", new Object[] { type },
                (rs, rowNum) -> new CatalogWrapper(rs.getInt("id"), rs.getString("name"))
        );

		return list;

	}	
	
	public CatalogWrapper findCatalogById(int id){	
		List<CatalogWrapper> catalogList = jdbcTemplate.query(
                "SELECT id, description, english, name, spanish, type, is_active FROM catalog WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new CatalogWrapper(rs.getInt("id"), 
                							rs.getString("description"),
                							rs.getString("english"),
                							rs.getString("name"),
                							rs.getString("spanish"),
                							rs.getString("type"),
                							rs.getBoolean("is_active")));
		CatalogWrapper result = catalogList.get(0);
		return result;

	}
	
}
