package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.treeseed.ejb.PostCampaign;

public interface PostCampaignRepository extends CrudRepository<PostCampaign,Integer>{
	
	@Query("SELECT p FROM PostCampaign p inner join p.campaign n WHERE "
			+ " n.id = :campaignId and "
			+ " p.isActive = 1")
	public Page<PostCampaign> fillWithAll( @Param("campaignId") int campaignId,  Pageable pageable);
	
}
