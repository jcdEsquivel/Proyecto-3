package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.repositories.UserGeneralRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserGeneralService.
 */
@Service
public class UserGeneralService implements UserGeneralServiceInterface{
	
	/** The users repository. */
	@Autowired
	UserGeneralRepository usersRepository;

	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#getAll(com.treeseed.contracts.UserGeneralRequest)
	 */
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
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#saveUserGeneral(com.treeseed.ejbWrapper.UserGeneralWrapper)
	 */
	@Override
	@Transactional
	public Boolean saveUserGeneral(UserGeneralWrapper userGeneral) {
		
		UserGeneral nuser = usersRepository.save(userGeneral.getWrapperObject());
		Boolean result = true;
		if(nuser == null){
			result = false;
		}
		return result;
		
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#getSessionUserGeneral(int)
	 */
	@Override
	public UserGeneral getSessionUserGeneral(int idUserGeneral) {
		return usersRepository.findOne(idUserGeneral);
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#getUserByEmailAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public UserGeneral getUserByEmailAndPassword(String ur, String pas) {
		return  usersRepository.findByEmailAndPassword(ur, pas);
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#getUserByEmailAndPasswordAndIsActive(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public UserGeneral getUserByEmailAndPasswordAndIsActive(String ur, String pas, boolean active) {
		return  usersRepository.findByEmailAndPasswordAndIsActive(ur, pas, active);
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#userExist(java.lang.String)
	 */
	@Override
	public Boolean userExist(String email) {
		Boolean exist = false;
		UserGeneral response= usersRepository.findByEmail(email);
		if(response!=null){
			exist=true;
		}
		
		return exist;
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#isEmailUnique(java.lang.String)
	 */
	@Override
	public Boolean isEmailUnique(String email){
		UserGeneral user = usersRepository.findByEmail(email.toLowerCase());
			
		if(user != null){
			return false;
		}else{
			return true;
		}
	}
	
	/** The jdbc template. */
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#validateFacebookId(java.lang.String)
	 */
	@Transactional
	public Boolean validateFacebookId(String facebookId){
		
		String id = "1010";
		if(!facebookId.equals(""))
		{
			id = facebookId;	
		}
		
		UserGeneral ug = usersRepository.findByFacebookId(id);
		
	    if (ug == null) {
	        return false;
	    } else {
	        return true;
	    }

	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#getUGByID(int)
	 */
	@Override
	public UserGeneral getUGByID(int idUserGeneral) {
		return usersRepository.findOne(idUserGeneral);
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#getUserByNonprofitId(int)
	 */
	@Override
	public UserGeneral getUserByNonprofitId(int idNonprofit) {
		return usersRepository.findByNonprofitId(idNonprofit);
	}
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#updateUserGeneral(com.treeseed.ejbWrapper.UserGeneralWrapper)
	 */
	@Override
	@Transactional
	public void updateUserGeneral(UserGeneralWrapper userGeneral) {
		usersRepository.update(userGeneral.getId(),
				userGeneral.getEmail()
				);		
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#getUserByDonorId(int)
	 */
	@Override
	public UserGeneralWrapper getUserByDonorId(int idDonor) {
		UserGeneral userGeneral = usersRepository.findByDonorId(idDonor);
		UserGeneralWrapper userGeneralWrapper = new UserGeneralWrapper();
		
		userGeneralWrapper.setWrapperObject(userGeneral);
		
		return userGeneralWrapper;
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.UserGeneralServiceInterface#deleteUserGeneral(com.treeseed.ejbWrapper.UserGeneralWrapper)
	 */
	@Override
	@Transactional
	public void deleteUserGeneral(UserGeneralWrapper ugw) {
		usersRepository.deleteUserGeneral(ugw.getId());
	}
}