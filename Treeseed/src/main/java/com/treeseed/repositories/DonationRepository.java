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

}