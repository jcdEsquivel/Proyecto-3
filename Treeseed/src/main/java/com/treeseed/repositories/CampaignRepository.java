package com.treeseed.repositories;

import java.util.Date;

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
import com.treeseed.pojo.CampaignPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Interface CampaignRepository.
 */
public interface CampaignRepository extends CrudRepository <Campaign,Integer>{
	
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
	   public Page<Campaign> findWithAll(@Param("campaignNameNull") String campaignNameNull, @Param("campaignName") String campaignName,
			   @Param("ngoNameNull") String ngoNameNull, @Param("ngoName") String ngoName,
			   @Param("cause") int cause,
			   @Param("startDate") Date startDate,
			   @Param("endDate") Date endDate,
			   Pageable pageable);
	

	/**
	 * Find from nonprofit.
	 *
	 * @param campaignNameNull the campaign name null
	 * @param campaignName the campaign name
	 * @param ngoNameNull the ngo name null
	 * @param ngoName the ngo name
	 * @param cause the cause
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param nonprofitId the nonprofit id
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT cp FROM Campaign cp inner join cp.nonprofit n inner join n.cause c WHERE"
			+ "( :campaignNameNull is null or cp.name like :campaignName) and "
			+ "( :startDate is null or cp.startDate >= :startDate ) and "
			+ "( :endDate is null or cp.dueDate <= :endDate ) and "
			+ "( :startDateState is null or cp.startDate <= :startDateState ) and "
			+ "( :endDateState is null or cp.dueDate >= :endDateState ) and "
			+ "n.id = :nonprofitId ")
	   public Page<Campaign> findFromNonprofit(@Param("campaignNameNull") String campaignNameNull, @Param("campaignName") String campaignName,
			   @Param("startDate") Date startDate,
			   @Param("endDate") Date endDate,
			   @Param("startDateState") Date startDateState,
			   @Param("endDateState") Date endDateState,
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
	 * @param id the id
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Campaign> findById(int id,
			Pageable pageable);
	
	/**
	 * Find by nonprofit id.
	 *
	 * @param Id the id
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Campaign> findByNonprofitId(int Id,Pageable pageable);

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
	

}
