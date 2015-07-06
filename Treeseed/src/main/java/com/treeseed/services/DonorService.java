package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;
import com.treeseed.repositories.DonorRepository;

@Service
public class DonorService implements DonorServiceInterface{

	@Autowired
	DonorRepository donorRepository;

	@Override
	@Transactional
	public Page<Donor> getDonor(DonorRequest dr) {
	
		PageRequest pr;
		Sort.Direction direction = Sort.Direction.DESC;
		if(dr.getDirection().equals("ASC")){
			direction = Sort.Direction.ASC;
		}
		
		if(dr.getSortBy().size() > 0){
			Sort sort = new Sort(direction,dr.getSortBy());
			pr = new PageRequest(dr.getPageNumber(),
					dr.getPageSize(),sort);
		}else{
			pr = new PageRequest(dr.getPageNumber(),
					dr.getPageSize());
		}
		
		Page<Donor> pageResult = null;
		Page<Donor> pageResultado = null;
		
		String filterName = dr.getName();
		String filterLastName = dr.getLastName();
		int filterCountry= 0;
		
		
		if(dr.getCountry()!=null){
			filterCountry = Integer.parseInt(dr.getCountry());
		}
		
		
		
		pageResult = donorRepository.findConTodo(filterName, "%"+filterName+"%",filterCountry,filterLastName,"%"+filterLastName+"%", pr);
		
		return pageResult ;
		
	}

	@Override
	@Transactional
	public Boolean saveDonor(Donor donor) {
		
		Donor nuser = donorRepository.save(donor);
		Boolean result = true;
		if(nuser == null){
			result = false;
		}
		return result;
		
	}

	@Override
	public Donor getSessionDonor(int idDonor) {
		return donorRepository.findOne(idDonor);
	}

	@Override
	public Page<Donor> getByName(DonorRequest dr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Donor> getByCountry(DonorRequest dr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Donor> getByLastName(DonorRequest dr) {
		// TODO Auto-generated method stub
		return null;
	}
}