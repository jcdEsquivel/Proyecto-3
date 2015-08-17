package com.treeseed.repositories;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Donation;
import com.treeseed.ejb.RecurrableDonation;

// TODO: Auto-generated Javadoc
/**
 * The Interface RecurrableDonationRepository.
 */
public interface RecurrableDonationRepository extends CrudRepository<RecurrableDonation, Integer> {

	/** The Constant PAGE_SIZE. */
	public static final int PAGE_SIZE = 5;

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<RecurrableDonation> findAll(Pageable pageable);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<RecurrableDonation> findById(int id);
	
	/**
	 * Find by nonprofit id.
	 *
	 * @param nonprofitId the nonprofit id
	 * @param donorId the donor id
	 * @return the list
	 */
	List<RecurrableDonation> findByNonProfitIdAndDonorId(int nonprofitId, int donorId);
	
	/**
	 * Find by campaign id.
	 *
	 * @param campaignId the campaign id
	 * @param donorId the donor id
	 * @return the list
	 */
	List<RecurrableDonation> findByCampaingIdAndDonorId(int campaignId, int donorId);
	
	/**
	 * Find by donor id.
	 *
	 * @param donorId the donor id
	 * @return the list
	 */
	List<RecurrableDonation> findByDonorId(int donorId);
	
	/**
	 * Gets the by non profit id dashboard.
	 *
	 * @param nonProfitId the non profit id
	 * @return the by non profit id dashboard
	 */
	@Query("SELECT r FROM RecurrableDonation r where r.nonProfitId = :nonProfitId and r.isActive = true order by r.dateTime Desc ") 
	List<RecurrableDonation> findTop10getByNonProfitIdDashboard(@Param("nonProfitId") int nonProfitId);
	
}