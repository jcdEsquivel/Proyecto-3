package com.treeseed.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostCampaignWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostCampaignRepository.
 */
public interface PostCampaignRepository extends CrudRepository<PostCampaign,Integer>{

	/**
	 * Find post.
	 *
	 * @param campaignId the campaign id
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT p FROM PostCampaign p inner join p.campaign c WHERE "
			+ " c.id = :campaignId and "
			+ " p.isActive = 1")
	public Page<PostNonprofit> findPost( @Param("campaignId") int campaignId,  Pageable pageable);

	/**
	 * Fill with all.
	 *
	 * @param campaignId the campaign id
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT p FROM PostCampaign p inner join p.campaign n WHERE "
			+ " n.id = :campaignId and "
			+ " p.isActive = 1")
	public Page<PostCampaign> fillWithAll( @Param("campaignId") int campaignId,  Pageable pageable);

	
}
