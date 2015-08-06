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
	 * Fill with all.
	 *
	 * @param campaignId the campaign id
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT p FROM PostCampaign p inner join p.campaign n WHERE "
			+ " n.id = :campaignId and "
			+ " p.isActive = 1")
	public Page<PostCampaign> findPosts( @Param("campaignId") int campaignId,  Pageable pageable);
	


	/**
	 * Update.
	 *
	 * @param id the id
	 * @param tittle the tittle
	 * @param description the description
	 * @param picture the picture
	 */
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
	

	/**
	 * Delete post.
	 *
	 * @param id the id
	 */
	@Modifying
	@Transactional
	@Query("UPDATE PostCampaign n SET isActive = 0 where n.id = :id") 
	  public void deletePost(
			   @Param("id") int id);
	
}
