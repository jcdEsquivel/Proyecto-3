package com.treeseed.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;

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
	


	@Modifying
	@Transactional
	@Query("UPDATE PostCampaign n SET tittle = :tittle, description = :description, picture= :picture "
			+ "where n.id = :id") 
	  public void update(
			   @Param("id") int id,
			   @Param("tittle") String tittle,
			   @Param("description") String description,
			   @Param("picture") String picture)
			   ;
	
}