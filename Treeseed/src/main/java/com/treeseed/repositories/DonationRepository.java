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
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.DonationWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface DonationRepository.
 */
public interface DonationRepository extends CrudRepository<Donation, Integer> {

	/** The Constant PAGE_SIZE. */
	public static final int PAGE_SIZE = 5;

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Donation> findAll(Pageable pageable);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<Donation> findById(int id);

	/**
	 * Find amount per month of non profit.
	 *
	 * @param id the id
	 * @param start the start
	 * @param end the end
	 * @return the double
	 */
	@Query("SELECT SUM(d.amount) FROM Donation d "
			+ "WHERE d.dateTime BETWEEN :start AND :end "
			+ "AND d.nonProfitId = :nonProfitid")
	public double findAmountPerMonthOfNonProfit(@Param("nonProfitid") int id,
			@Param("start") Date start, @Param("end") Date end);
	
	/**
	 * Count distinc donor id by campaing id.
	 *
	 * @param campaignId the campaign id
	 * @return the int
	 */
	@Query("SELECT COUNT(DISTINCT donorId) From Donation  WHERE campaingId = :campignId")
	int countDistincDonorIdByCampaingId(@Param("campignId") int campaignId);
	
	/**
	 * Update.
	 *
	 * @param id the id
	 * @param stripe the stripe
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Donation d SET stripeId=:stripe where d.id = :id") 
 	public void update(@Param("id") int id,   @Param("stripe") String stripe);
	
	/**
	 * Find by stripe id.
	 *
	 * @param stripeId the stripe id
	 * @return the donation
	 */
	Donation findByStripeId(String stripeId);
}