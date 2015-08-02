package com.treeseed.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Donor;

public interface DonorRepository extends 
 CrudRepository<Donor,Integer> {
 
 public static final int PAGE_SIZE = 10;

 @Query("SELECT p FROM Donor p WHERE ( :nameNull is null or p.name like :name)")
    public Page<Donor> find(@Param("nameNull") String nameNull, @Param("name") String name,
      Pageable pageable);
 
 
 @Query("SELECT p FROM Donor p inner join p.country d WHERE ( :nameNull is null or p.name like :name) and "
   + "( :country = 0 or d.id = :country) and "
   + "( :lastNameNull is null or p.lastName like :lastName) and p.isActive = 1")
    public Page<Donor> findAll(@Param("nameNull") String nameNull, @Param("name") String name,
      @Param("country") int country,
      @Param("lastNameNull") String lastNameNull,
      @Param("lastName") String lastName,
      Pageable pageable);
 
 //Page<Donor> findAll(Pageable pageable);
 Page<Donor> findByNameContaining(String name,
   Pageable pageable);
 Page<Donor> findByLastNameContaining(String lastName,
   Pageable pageable);
 Page<Donor> findByCountryContaining(String name,
   Pageable pageable);
 Donor findByid(int id);
 
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
 	
 	@Modifying
	@Transactional
	@Query("UPDATE Donor d SET isActive = 0 where d.id = :id") 
	  public void deleteDonor( @Param("id") int id);
 	
 	//public Donor update(Donor donor);

}