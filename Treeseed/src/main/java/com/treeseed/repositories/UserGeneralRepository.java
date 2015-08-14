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

// TODO: Auto-generated Javadoc
/**
 * The Interface UserGeneralRepository.
 */
public interface UserGeneralRepository extends 
	CrudRepository<UserGeneral,Integer> {
	
	/** The Constant PAGE_SIZE. */
	public static final int PAGE_SIZE = 5;
	
	/**
	 * Find by email and password.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the user general
	 */
	UserGeneral findByEmailAndPassword(String email, 
			String password);
	
	/**
	 * Find by email and password and is active.
	 *
	 * @param email the email
	 * @param password the password
	 * @param isActive the is active
	 * @return the user general
	 */
	UserGeneral findByEmailAndPasswordAndIsActive(String email, 
			String password, boolean isActive);
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<UserGeneral> findAll(Pageable pageable);
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user general
	 */
	UserGeneral findByEmail(String email);
	
	/**
	 * Find by facebook id.
	 *
	 * @param facebookId the facebook id
	 * @return the user general
	 */
	UserGeneral findByFacebookId (String facebookId);
	
	/**
	 * Find by nonprofit id.
	 *
	 * @param idNonprofit the id nonprofit
	 * @return the user general
	 */
	UserGeneral findByNonprofitId (int idNonprofit);
	
	/**
	 * Find by donor id.
	 *
	 * @param idDonor the id donor
	 * @return the user general
	 */
	UserGeneral findByDonorId (int idDonor);

	
 	/**
	  * Update.
	  *
	  * @param id the id
	  * @param email the email
	  */
	 @Modifying
	@Transactional
	@Query("UPDATE UserGeneral u SET email = :email where u.id = :id") 
	  public void update(
			   @Param("id") int id,
			   @Param("email") String email
			   );
 		
 	/**
	  * Delete user general.
	  *
	  * @param id the id
	  */
	 @Modifying
	@Transactional
	@Query("UPDATE UserGeneral u SET isActive = 0  where u.id = :id") 
	  public void deleteUserGeneral(
			   @Param("id") int id
			   );

}
