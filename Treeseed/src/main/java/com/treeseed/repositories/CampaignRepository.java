package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;

public interface CampaignRepository extends CrudRepository <Campaign,Integer>{
	
	public static final int PAGE_SIZE = 10;
	
	
	/*select cp.* from campaign cp inner join nonprofit n on (cp.id_non_profit = n.id) inner join catalog c on (n.cause = c.id)
    where (cp.name is null or cp.name like '%%') and 
		  (n.name is null or n.name like '%%') and
	      (c.name is null or c.name like '%Ca%');*/
	
	
	/*@Query("SELECT c FROM Campaign c inner join c.nonprofit c inner join c.cause d WHERE ( :nameNull is null or p.name like :name) and "
			+ "( :country = 0 or d.id = :country) and "
			+ "( :cause = 0 or c.id = :cause) and p.isActive = 1")
	   public Page<Campaign> findWithAll(@Param("nameNull") String nameNull, @Param("name") String name,
			   @Param("country") int country,
			   @Param("cause") int cause,
			   Pageable pageable);

	Page<Campaign> findAll(Pageable pageable);
	Page<Campaign> findByNameContaining(String name, Pageable pageable);
	Page<Campaign> findByNonprofit(String name, Pageable pageable);	
	Campaign findByid(int id);*/
	

}
