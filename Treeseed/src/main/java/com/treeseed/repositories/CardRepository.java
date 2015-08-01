package com.treeseed.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Card;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;

public interface CardRepository extends 
	CrudRepository<Card,Integer> {
	
	public static final int PAGE_SIZE = 5;
	
	
	UserGeneral findByDonorId (int idDonor);
 		
 	@Modifying
	@Transactional
	@Query("UPDATE Card u SET isActive = 0  where u.id = :id") 
	  public void deleteUserGeneral(
			   @Param("id") int id
			   );

}
