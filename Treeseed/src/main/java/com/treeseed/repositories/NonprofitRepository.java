package com.treeseed.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface NonprofitRepository.
 */
public interface NonprofitRepository extends 
	CrudRepository<Nonprofit,Integer> {
	
	/** The Constant PAGE_SIZE. */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * Find con todo.
	 *
	 * @param nameNull the name null
	 * @param name the name
	 * @param country the country
	 * @param cause the cause
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT p FROM Nonprofit p inner join p.cause c inner join p.country d WHERE ( :nameNull is null or p.name like :name) and "
			+ "( :country = 0 or d.id = :country) and "
			+ "( :cause = 0 or c.id = :cause) and p.isActive = 1")
	   public Page<Nonprofit> findConTodo(@Param("nameNull") String nameNull, @Param("name") String name,
			   @Param("country") int country,
			   @Param("cause") int cause,
			   Pageable pageable);

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Nonprofit> findAll(Pageable pageable);
	
	/**
	 * Find by name containing.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Nonprofit> findByNameContaining(String name,
			Pageable pageable);
	
	/**
	 * Find by reason containing.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Nonprofit> findByReasonContaining(String name,
			Pageable pageable);
	
	/**
	 * Find by country containing.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Nonprofit> findByCountryContaining(String name,
			Pageable pageable);
	
	/**
	 * Find byid.
	 *
	 * @param id the id
	 * @return the nonprofit
	 */
	Nonprofit findByid(int id);
	
	/**
	 * Update.
	 *
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 * @param mision the mision
	 * @param reason the reason
	 * @param mainPicture the main picture
	 * @param profilePicture the profile picture
	 * @param webPage the web page
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Nonprofit n SET name = :name, description = :description, mision= :mision, "
			+ "reason= :reason, mainPicture = :mainPicture, profilePicture = :profilePicture, "
			+ "webPage = :webPage where n.id = :id") 
	  public void update(
			   @Param("id") int id,
			   @Param("name") String name,
			   @Param("description") String description,
			   @Param("mision") String mision,
			   @Param("reason") String reason,
			   @Param("mainPicture") String mainPicture,
			   @Param("profilePicture") String profilePicture,
			   @Param("webPage") String webPage)
			   ;
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Nonprofit n SET isActive = 0 where n.id = :id") 
	  public void delete(
			   @Param("id") int id);
	
	/**
	 * Find top10 donor recomendations.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Transactional
	@Query("SELECT DISTINCT p FROM Nonprofit p WHERE p.id not in (SELECT distinct d.nonProfitId FROM Donation d WHERE d.donorId = :id  AND d.campaingId = 0 OR  d.campaingId = null) and p.cause in (SELECT distinct p.cause FROM Donation d,Nonprofit p WHERE d.donorId = :id) and p.isActive = true ORDER BY rand()") 
	  public Page<Nonprofit> findTop10DonorRecomendations(
			   @Param("id") int id, Pageable pageable);
	
	/**
	 * Find top10 donor recomendations random.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Transactional
	@Query("select DISTINCT p from Nonprofit p where p.isActive = true order by rand()") 
	  public Page<Nonprofit> findTop10DonorRecomendationsRandom(Pageable pageable);
			  
	
	public Nonprofit findByName(String name);
	
}
