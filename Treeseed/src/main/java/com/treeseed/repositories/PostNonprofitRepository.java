package com.treeseed.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;

public interface PostNonprofitRepository extends CrudRepository<PostNonprofit,Integer>{

	@Query("SELECT p FROM PostNonprofit p inner join p.nonprofit n WHERE "
			+ " n.id = :nonprofitId and "
			+ " p.isActive = 1")
	public Page<PostNonprofit> fillWithAll( @Param("nonprofitId") int nonprofitId,  Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("UPDATE PostNonprofit n SET tittle = :tittle, description = :description, picture= :picture "
			+ "where n.id = :id") 
	  public void update(
			   @Param("id") int id,
			   @Param("tittle") String tittle,
			   @Param("description") String description,
			   @Param("picture") String picture)
			   ;

	@Modifying
	@Transactional
	@Query("UPDATE PostNonprofit n SET isActive = 0 where n.id = :id") 
	  public void deletePost(
			   @Param("id") int id);
	
}
