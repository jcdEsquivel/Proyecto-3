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

public interface NonprofitRepository extends 
	CrudRepository<Nonprofit,Integer> {
	
	public static final int PAGE_SIZE = 10;
	
	@Query("SELECT p FROM Nonprofit p inner join p.cause c inner join p.country d WHERE ( :nameNull is null or p.name like :name) and "
			+ "( :country = 0 or d.id = :country) and "
			+ "( :cause = 0 or c.id = :cause) and p.isActive = 1")
	   public Page<Nonprofit> findConTodo(@Param("nameNull") String nameNull, @Param("name") String name,
			   @Param("country") int country,
			   @Param("cause") int cause,
			   Pageable pageable);

	Page<Nonprofit> findAll(Pageable pageable);
	Page<Nonprofit> findByNameContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByReasonContaining(String name,
			Pageable pageable);
	Page<Nonprofit> findByCountryContaining(String name,
			Pageable pageable);
	
	Nonprofit findByid(int id);
	
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
}
