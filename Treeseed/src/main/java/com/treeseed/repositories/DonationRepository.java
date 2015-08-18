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
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.TransparencyReport;

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
	 * @param id the month
	 * @param id of the nonprofit
	 */
	@Query("SELECT COALESCE(SUM(d.amount),0) FROM Donation d "
			+ "WHERE d.nonProfitId=:nonProfitid and MONTH(d.dateTime)=:month")
	public double findAmountPerMonthOfNonProfit(@Param("month") int month,
												@Param("nonProfitid") int id);
	
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
	
	/**
	 * Find donations of donor.
	 *
	 * @param donorId the donor id
	 * @param monthNull the month null
	 * @param month the month
	 * @param yearNull the year null
	 * @param year the year
	 * @param pageable the pageable
	 * @return the page
	 */
	/*
	 * Find by nonprofitId.
	 *
	 * @param nonProfit id
	 * @Param String monthNull,
	 * @Param String month,
	 * @Param String yearNull,
	 * @Param String year,
     * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT d FROM Donation d WHERE( donorId = :donorId) and "
			+ "( :monthNull is null or MONTH(dateTime)=:month) and "
			+ "( :yearNull is null or YEAR(dateTime)=:year)")
	public Page<Donation> findDonationsOfDonor(@Param("donorId") int donorId,
											   @Param("monthNull") String monthNull,
											   @Param("month") int month,
											   @Param("yearNull") String yearNull,
											   @Param("year") int year,
											   Pageable pageable);
	
	/**
	 * Find all donations.
	 *
	 * @param nonProfitId the non profit id
	 * @param monthNull the month null
	 * @param month the month
	 * @param yearNull the year null
	 * @param year the year
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT p FROM Donation p WHERE (:nonProfitId = nonProfitId) and p.isActive = 1 and "
			+ "( :monthNull is null or MONTH(p.dateTime)=:month) and "
			+ "( :yearNull is null or YEAR(p.dateTime)=:year)")
	   public Page<Donation> findAllDonations(
			   	@Param("nonProfitId") int nonProfitId,
			   	@Param("monthNull") String monthNull,
				@Param("month") int month,
				@Param("yearNull") String yearNull,
				@Param("year") int year,
			   Pageable pageable);


	/**
	 * Sum amount by donor.
	 *
	 * @param id the id
	 * @return the double
	 */
	@Query("select COALESCE(sum(amount),0) From Donation where donorId = :id") 
 	public double sumAmountByDonor(@Param("id") int id);
	
	/**
	 * Gets the by non profit id.
	 *
	 * @param nonProfitId the non profit id
	 * @return the by non profit id
	 */
	@Query("SELECT d FROM Donation d where d.nonProfitId = :nonProfitId and d.isActive = true order by d.dateTime Desc ") 
	Page<Donation> findTop10getByNonProfitIdDashboard(@Param("nonProfitId") int nonProfitId, Pageable pageable);
	
}