package com.treeseed.services;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
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
	public Page<Nonprofit> getNonProfit(NonprofitRequest ur) {
	
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
		
		Page<Nonprofit> pageResult = null;
		Page<Nonprofit> pageResultado = null;
		
		String filterName = ur.getName();
		int filterCause= 0;
		int filterCountry= 0;
		
		
		if(ur.getCause()!=null){
			filterCause = Integer.parseInt(ur.getCause());
		}else if(ur.getCountry()!=null){
			filterCountry = Integer.parseInt(ur.getCountry());
		}
		
		
		
		pageResult = nonprofitsRepository.findConTodo(filterName, "%"+filterName+"%",filterCountry,filterCause, pr);
		
		return pageResult ;
		
	}
	
	
	/*public Page<Donor> getAll(DonorRequest dr) {
		 
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
		    
		    Page<Donor> result = null;
		    
		    if(dr.getSearchColumn().toLowerCase().equals("all")){
		     result = donorRepository.findAll(pr);
		    }else if(dr.getSearchColumn().toLowerCase().
		       equals("firstname")){
		    } else if(dr.getSearchColumn().toLowerCase().equals("lastname")){
		      //nonProfits = usersRepository.
		      //findByEmailAndPassword(ur.getSearchTerm(),pr);
		    }else{
		     result = donorRepository.findAll(pr);
		    }
		    
		    

		    return result;
		  
		 }*/

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