package com.treeseed.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.CampaignPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Interface CampaignRepository.
 */
public interface CampaignRepository extends CrudRepository<Campaign, Integer> {

	/** The Constant PAGE_SIZE. */
	public static final int PAGE_SIZE = 10;

	/**
	 * Find with all.
	 *
	 * @param campaignNameNull the campaign name null
	 * @param campaignName the campaign name
	 * @param ngoNameNull the ngo name null
	 * @param ngoName the ngo name
	 * @param cause the cause
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT cp FROM Campaign cp inner join cp.nonprofit n inner join n.cause c WHERE ( :campaignNameNull is null or cp.name like :campaignName) and "
			+ "( :ngoNameNull  is null or n.name like :ngoName) and "
			+ "( :cause = 0 or c.id = :cause) and cp.isActive = 1 and "
			+ "( :startDate is null or cp.startDate >= :startDate ) and "
			+ "( :endDate is null or cp.dueDate <= :endDate )")
	public Page<Campaign> findWithAll(
			@Param("campaignNameNull") String campaignNameNull,
			@Param("campaignName") String campaignName,
			@Param("ngoNameNull") String ngoNameNull,
			@Param("ngoName") String ngoName, @Param("cause") int cause,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			Pageable pageable);
	

	/**
	 * Find from nonprofit.
	 *
	 * @param campaignNameNull the campaign name null
	 * @param campaignName the campaign name
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param startDateSoon the start date soon
	 * @param startDateActive the start date active
	 * @param endDateActive the end date active
	 * @param endDateFinished the end date finished
	 * @param nonprofitId the nonprofit id
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT cp FROM Campaign cp inner join cp.nonprofit n inner join n.cause c WHERE"
			+ "( :campaignNameNull is null or cp.name like :campaignName) and "
			+ "( :startDate is null or cp.startDate >= :startDate ) and "
			+ "( :endDate is null or cp.dueDate <= :endDate ) and "		
			+ "( :startDateSoon is null or ( cp.startDate >= :startDateSoon and cp.isActive = 1 ) ) and "		
			+ "( :startDateActive is null or ( cp.startDate <= :startDateActive and cp.isActive = 1 ) ) and "
			+ "( :endDateActive is null or (cp.dueDate >= :endDateActive or cp.dueDate = null)) and "
			+"( :endDateFinished is null or (cp.dueDate <= :endDateFinished or cp.isActive = 0 ) ) and "
			+ "n.id = :nonprofitId ")
	   public Page<Campaign> findFromNonprofit(@Param("campaignNameNull") String campaignNameNull, @Param("campaignName") String campaignName,
			   @Param("startDate") Date startDate,
			   @Param("endDate") Date endDate,
			   @Param("startDateSoon") Date startDateSoon,
			   @Param("startDateActive") Date startDateActive,
			   @Param("endDateActive") Date endDateActive,
			   @Param("endDateFinished") Date endDateFinished,
			   @Param("nonprofitId") int nonprofitId,
			   Pageable pageable);
	

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Campaign> findAll(Pageable pageable);

	/**
	 * Find by nonprofit.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Campaign> findByNonprofit(String name, Pageable pageable);
	
	/**
	 * Find byid.
	 *
	 * @param id the id
	 * @return the campaign
	 */
	Campaign findByid(int id);
	
	/**
	 * Find by id.
	 *
	 * @param Id the id
	 * @param isActive the is active
	 * @return the page
	 */

	/**
	 * Find by nonprofit id.
	 *
	 * @param Id the id
	 * @param pageable the pageable
	 * @return the page
	 */

	/**
	 * Update is active by id.
	 *
	 * @param Id the id
	 * @param isActive the is active
	 * @return the page
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Campaign SET isActive=:state WHERE id=:idCampaign")
	void updateIsActiveById(@Param("idCampaign")int Id,@Param("state") boolean isActive);

	/**
	 * Find by nonprofit id.
	 *
	 * @param Id the id
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Campaign> findByNonprofitId(int Id, Pageable pageable);
	
	/**
	 * Updates the campaign.
	 *
	 * @param id the id
	 * @param name the campaign name
	 * @param description the campaign description
	 * @param dueDate the campaign duedate
	 * @param startDate the start date
	 * @param amountCollected the amount collected
	 * @param amountGoal the amount goal
	 * @param picture the campaign picture
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Campaign c SET name = :name, description = :description, dueDate = :dueDate, startDate= :startDate, "
			+ "amountCollected = :amountCollected, amountGoal = :amountGoal, picture = :picture where c.id = :id")
	public void update(@Param("id") int id, @Param("name") String name,
			@Param("description") String description,
			@Param("dueDate") Date dueDate, @Param("startDate") Date startDate,
			@Param("amountCollected") double amountCollected,
			@Param("amountGoal") double amountGoal,
			@Param("picture") String picture);
	
	/**
	 * Find top10 donor recomendations.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Transactional
	@Query("select DISTINCT c from Campaign c inner join c.nonprofit n  where c.id not in (select distinct d.campaingId from Donation d where d.donorId = :id  and d.campaingId <> 0 or  d.campaingId <> null) and n.id in (select distinct d.nonProfitId from Donation d where d.donorId =:id) and c.isActive = true order by rand()") 
	  public Page<Campaign> findTop10DonorRecomendations(
			   @Param("id") int id, Pageable pageable);
	
	/**
	 * Find top10 donor recomendations random.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Transactional
	@Query("select DISTINCT c from Campaign c where c.isActive = true and (c.dueDate is null or c.dueDate > now()) and c.id not in :ids order by rand()") 
	  public Page<Campaign> findTop10DonorRecomendationsRandom(Pageable pageable, @Param("ids") List<Integer> list);
}
