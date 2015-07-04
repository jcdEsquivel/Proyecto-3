package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.repositories.UserGeneralRepository;

@Service
public class UserGeneralService implements UserGeneralServiceInterface{

	@Autowired
	UserGeneralRepository usersRepository;

	@Override
	@Transactional
	public Page<UserGeneral> getAll(UserGeneralRequest ur) {
	
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
		
		Page<UserGeneral> result = null;
		
		if(ur.getSearchColumn().toLowerCase().equals("all")){
			result = usersRepository.findAll(pr);
		}else if(ur.getSearchColumn().toLowerCase().
				equals("firstname")){
			//result = usersRepository.
					//findByEmailAndPassword(ur.getSearchTerm())(ur.getSearchTerm(),pr);
		} else if(ur.getSearchColumn().toLowerCase().equals("lastname")){
			//result = usersRepository.
					//findByEmailAndPassword(ur.getSearchTerm(),pr);
		}else{
			result = usersRepository.findAll(pr);
		}

		return result;
		
	}

	@Override
	@Transactional
	public Boolean saveUserGeneral(UserGeneral userGeneral) {
		
		UserGeneral nuser = usersRepository.save(userGeneral);
		Boolean result = true;
		if(nuser == null){
			result = false;
		}
		return result;
		
	}

	@Override
	public UserGeneral getSessionUserGeneral(int idUserGeneral) {
		return usersRepository.findOne(idUserGeneral);
	}
}