package com.treeseed.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.repositories.NonprofitRepository;

@Service
public class NonprofitService implements NonprofitServiceInterface{

	@Autowired
	NonprofitRepository nonprofitsRepository;

	@Override
	@Transactional
	public Page<NonprofitWrapper> getAll(NonprofitRequest ur) {
	
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
		
		Page<Nonprofit> nonProfits = null;
		
		if(ur.getSearchColumn().toLowerCase().equals("all")){
			nonProfits = nonprofitsRepository.findAll(pr);
		}else if(ur.getSearchColumn().toLowerCase().
				equals("firstname")){
			//nonProfits = nonprofitsRepository.
					//findByEmailAndPassword(ur.getSearchTerm())(ur.getSearchTerm(),pr);
		} else if(ur.getSearchColumn().toLowerCase().equals("lastname")){
			//nonProfits = usersRepository.
					//findByEmailAndPassword(ur.getSearchTerm(),pr);
		}else{
			nonProfits = nonprofitsRepository.findAll(pr);
		}
		
		Page<NonprofitWrapper> result = null;
		
		ArrayList<NonprofitWrapper> array=new ArrayList();
		
		for(Nonprofit nonProfit:nonProfits){
			array.add(new NonprofitWrapper(nonProfit));
		}
		
		result=(Page<NonprofitWrapper>)array;

		return result;
		
	}

	@Override
	@Transactional
	public Boolean saveNonprofit(NonprofitWrapper nonProfit) {
		
		Nonprofit nuser = nonprofitsRepository.save(nonProfit.getWrapperObject());
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
}