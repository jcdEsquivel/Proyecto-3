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
		
		if(ur.getSearchColumn().toLowerCase().equals("all")){
			result = DonorRepository.findAll(pr);
		}else if(ur.getSearchColumn().toLowerCase().
				equals("name")){
			result = DonorRepository.
					findByNameContaining(
							ur.getSearchTerm(),pr);
		} else if(ur.getSearchColumn().toLowerCase().equals("lastName")){
			result = DonorRepository.
					findByLastNameContaining(ur.getSearchTerm(),pr);
		}else{
			result = DonorRepository.findAll(pr);
		}
		return result;
		
	}

	@Override
	@Transactional
	public Boolean saveDonor(Donor user) {
		Donor nuser = DonorRepository.save(user);
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
}