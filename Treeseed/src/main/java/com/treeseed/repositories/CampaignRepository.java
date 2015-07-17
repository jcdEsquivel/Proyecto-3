package com.treeseed.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.CampaignPOJO;

public interface CampaignRepository extends CrudRepository <Campaign,Integer>{
	
	public static final int PAGE_SIZE = 10;
		
	@Query("SELECT cp FROM Campaign cp inner join cp.nonprofit n inner join n.cause c WHERE ( :campaignNameNull is null or cp.name like :campaignName) and "
			+ "( :ngoNameNull  is null or n.name like :ngoName) and "
			+ "( :cause = 0 or c.id = :cause) and cp.isActive = 1 and "
			+ "( :startDate is null or cp.creationDate <= :startDate ) and "
			+ "( :endDate is null or cp.dueDate <= :endDate )")
	   public Page<Campaign> findWithAll(@Param("campaignNameNull") String campaignNameNull, @Param("campaignName") String campaignName,
			   @Param("ngoNameNull") String ngoNameNull, @Param("ngoName") String ngoName,
			   @Param("cause") int cause,
			   @Param("startDate") Date startDate,
			   @Param("endDate") Date endDate,
			   Pageable pageable);
	Page<Campaign> findAll(Pageable pageable);
	Page<Campaign> findByNameContaining(String name, Pageable pageable);
	Page<Campaign> findByNonprofit(String name, Pageable pageable);	
	Campaign findByid(int id);
	Page<Campaign> findById(int id,
			Pageable pageable);
	
}
