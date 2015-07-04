package com.treeseed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.repositories.NonprofitRepository;

@Service
public class NonprofitService implements NonprofitServiceInterface{

	@Autowired
	NonprofitRepository nonprofitsRepository;

	@Override
	@Transactional
	public Page<Nonprofit> getAll(NonprofitRequest ur) {
	
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
		
		Page<Nonprofit> result = null;
		
		if(ur.getSearchColumn().toLowerCase().equals("all")){
			result = nonprofitsRepository.findAll(pr);
		}else if(ur.getSearchColumn().toLowerCase().equals("name")){
			//result = nonprofitsRepository.
					//findByNameContaining(ur.getSearchTerm())(ur.getSearchTerm(),pr);
		} else if(ur.getSearchColumn().toLowerCase().equals("reason")){
			//result = nonprofitsRepository.
					//findByReasonContaining(ur.getSearchTerm(),pr);
		} else if(ur.getSearchColumn().toLowerCase().equals("country")){
			//result = nonprofitsRepository.
					//findByCountryContaining(ur.getSearchTerm(),pr);
		} else{
			result = nonprofitsRepository.findAll(pr);
		}
		 
		
		return result ;
		
	}

	@Override
	@Transactional
	public Boolean saveNonprofit(Nonprofit nonProfit) {
		
		Nonprofit nuser = nonprofitsRepository.save(nonProfit);
		Boolean result = true;
		if(nuser == null){
			result = false;
		}
		return result;
		
	}

	@Override
	public Nonprofit getSessionNonprofit(int idNonprofit) {
		return nonprofitsRepository.findOne(idNonprofit);
	}

	@Override
	public Page<Nonprofit> getByName(NonprofitRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Nonprofit> getByCountry(NonprofitRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Nonprofit> getByCause(NonprofitRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}
}