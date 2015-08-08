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

// TODO: Auto-generated Javadoc
/**
 * The Interface DonorRepository.
 */
public interface DonorRepository extends 
 CrudRepository<Donor,Integer> {
 
 /** The Constant PAGE_SIZE. */
 public static final int PAGE_SIZE = 10;

 /**
  * Find.
  *
  * @param nameNull the name null
  * @param name the name
  * @param pageable the pageable
  * @return the page
  */
 @Query("SELECT p FROM Donor p WHERE ( :nameNull is null or p.name like :name)")
    public Page<Donor> find(@Param("nameNull") String nameNull, @Param("name") String name,
      Pageable pageable);
 
 
 /**
  * Find all.
  *
  * @param nameNull the name null
  * @param name the name
  * @param country the country
  * @param lastNameNull the last name null
  * @param lastName the last name
  * @param pageable the pageable
  * @return the page
  */
 @Query("SELECT p FROM Donor p inner join p.country d WHERE ( :nameNull is null or p.name like :name) and "
   + "( :country = 0 or d.id = :country) and "
   + "( :lastNameNull is null or p.lastName like :lastName) and p.isActive = 1")
    public Page<Donor> findAll(@Param("nameNull") String nameNull, @Param("name") String name,
      @Param("country") int country,
      @Param("lastNameNull") String lastNameNull,
      @Param("lastName") String lastName,
      Pageable pageable);
 
 /**
  * Find by name containing.
  *
  * @param name the name
  * @param pageable the pageable
  * @return the page
  */
 //Page<Donor> findAll(Pageable pageable);
 Page<Donor> findByNameContaining(String name,
   Pageable pageable);
 
 /**
  * Find by last name containing.
  *
  * @param lastName the last name
  * @param pageable the pageable
  * @return the page
  */
 Page<Donor> findByLastNameContaining(String lastName,
   Pageable pageable);
 
 /**
  * Find by country containing.
  *
  * @param name the name
  * @param pageable the pageable
  * @return the page
  */
 Page<Donor> findByCountryContaining(String name,
   Pageable pageable);
 
 /**
  * Find byid.
  *
  * @param id the id
  * @return the donor
  */
 Donor findByid(int id);
 
 	/**
	  * Update.
	  *
	  * @param id the id
	  * @param name the name
	  * @param lastName the last name
	  * @param description the description
	  * @param profilePicture the profile picture
	  * @param webPage the web page
	  */
	 @Modifying
	@Transactional
	@Query("UPDATE Donor d SET name = :name, lastName = :lastName, description = :description, webPage= :webPage, "
	+"profilePicture = :profilePicture where d.id = :id") 
	  public void update(
			   @Param("id") int id,
			   @Param("name") String name,
			   @Param("lastName") String lastName,
			   @Param("description") String description,
			   @Param("profilePicture") String profilePicture,
			   @Param("webPage") String webPage)
			   ;
 	
 	/**
	  * Delete donor.
	  *
	  * @param id the id
	  */
	 @Modifying
	@Transactional
	@Query("UPDATE Donor d SET isActive = 0 where d.id = :id") 
	  public void deleteDonor(
			   @Param("id") int id)
			   ;
 	
	 /**
	  * Update stripe id.
	  *
	  * @param id the id
	  * @param stripe the stripe
	  */
	 @Modifying
	@Transactional
	@Query("UPDATE Donor d SET stripeId=:stripe where d.id = :id") 
 	public void updateStripeId(@Param("id") int id,   @Param("stripe") String stripe);
 	
 	/**
	  * Update subscription card.
	  *
	  * @param id the id
	  * @param card the card
	  */
	 @Modifying
	@Transactional
	@Query("UPDATE Donor d SET subscriptionCard=:card where d.id = :id") 
 	public void updateSubscriptionCard(@Param("id") int id,   @Param("card") Card card);
 	
 	/**
	  * Update stripe id and subscription card.
	  *
	  * @param id the id
	  * @param stripe the stripe
	  * @param card the card
	  */
	 @Modifying
	@Transactional
	@Query("UPDATE Donor d SET stripeId=:stripe, subscriptionCard=:card where d.id = :id") 
 	public void updateStripeIdAndSubscriptionCard(@Param("id") int id,   @Param("stripe") String stripe,   @Param("card") Card card);
 	
 	

}