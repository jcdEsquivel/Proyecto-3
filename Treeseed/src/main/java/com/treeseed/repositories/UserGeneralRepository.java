package com.treeseed.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;

public interface UserGeneralRepository extends 
	CrudRepository<UserGeneral,Integer> {
	
	public static final int PAGE_SIZE = 5;
	
	UserGeneral findByEmailAndPassword(String email, 
			String password);
	
	UserGeneral findByEmailAndPasswordAndIsActive(String email, 
			String password, boolean isActive);
	
	Page<UserGeneral> findAll(Pageable pageable);
	
	UserGeneral findByEmail(String email);
	
	UserGeneral findByFacebookId (String facebookId);
	
	UserGeneral findByNonprofitId (int idNonprofit);
	
	UserGeneral findByDonorId (int idDonor);

	
 	@Modifying
	@Transactional
	@Query("UPDATE UserGeneral u SET email = :email where u.id = :id") 
	  public void update(
			   @Param("id") int id,
			   @Param("email") String email
			   );
 		
 	@Modifying
	@Transactional
	@Query("UPDATE UserGeneral u SET isActive = 0  where u.id = :id") 
	  public void deleteUserGeneral(
			   @Param("id") int id
			   );

}
