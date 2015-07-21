package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	

	
}
