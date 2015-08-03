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

public interface DonationRepository extends CrudRepository<Donation, Integer> {

	public static final int PAGE_SIZE = 5;

	Page<Donation> findAll(Pageable pageable);

	List<Donation> findById(int id);

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
	
	
	@Query("SELECT p FROM Donation p WHERE (:nonProfitId = nonProfitId) and p.isActive = 1")
	   public Page<Donation> findAllSingleDonations(@Param("nonProfitId") int nonProfitId,
			   Pageable pageable);
	
	@Query("SELECT t FROM RecurrableDonation t inner join t.nonprofit n WHERE (n.id = :nonProfitId) and t.isActive = 1")
	   public Page<Donation> findRecurrentDonations(@Param("nonProfitId") int nonProfitId,
			   Pageable pageable);
	
	/*@Query("SELECT t FROM TransparencyReport t inner join t.nonprofit n WHERE( n.id = :nonprofitId) and "
			+ "( :monthNull is null or MONTH(t.dateTime)=:month) and "
			+ "( :yearNull is null or YEAR(t.dateTime)=:year)")
	public Page<TransparencyReport> findByNonprofitIdAndDate(@Param("nonprofitId") int nonprofitId,
															@Param("monthNull") String monthNull,
															@Param("month") int month,
															@Param("yearNull") String yearNull,
															@Param("year") int year,
															Pageable pageable);*/

}