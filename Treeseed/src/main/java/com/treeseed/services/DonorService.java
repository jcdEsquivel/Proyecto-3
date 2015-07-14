package com.treeseed.services;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.repositories.DonorRepository;

@Service
public class DonorService implements DonorServiceInterface{

	@Autowired
	DonorRepository DonorRepository;
	
	@Override
	@Transactional
	public Page<Donor> getAll(DonorRequest ur) {
	
		PageRequest pr;
		Sort.Direction direction = Sort.Direction.DESC;
		if(ur.getDirection().equals("ASC")){
			direction = Sort.Direction.ASC;
		}
		
		if(ur.getSortBy().size() > 0){
			Sort sort = new Sort(direction,ur.getSortBy());
			pr = new PageRequest(ur.getPageNumber(),
					ur.getPageSize(),sort);
		}else{
			pr = new PageRequest(ur.getPageNumber(),
					ur.getPageSize());
		}
		
		Page<Donor> result;
		
		String filterName = ur.getName();
		String filterLastName = ur.getLastName();
		int filterCountry = 0;
		
		if(ur.getCountry()!=null){
			filterCountry = Integer.parseInt(ur.getCountry());
		}
		
		result = DonorRepository.findAll(filterName, "%"+filterName+"%", filterCountry, filterLastName, "%"+filterLastName+"%", pr);
		return result;
	}

	@Override
	@Transactional
	public Boolean saveDonor(DonorWrapper user) {
		Donor nuser = DonorRepository.save(user.getWrapperObject());
		Boolean result = true;
		if(nuser == null){
			result = false;
		}
		return result;
	}

	@Override
	@Transactional
	public Donor getSessionDonor(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	@Transactional
	public String validateFacebookId(String facebookId){
		
		String id = "1010";
		if(!facebookId.equals(""))
		{
			id = facebookId;	
		}
		
		String sql = "select id from user_general where facebook_id = "+ id +"";
	    List<String> certs = jdbcTemplate.queryForList(sql, String.class); 
	    if (certs.isEmpty()) {
	        return null;
	    } else {
	        return certs.get(0);
	    }

	}
		
}