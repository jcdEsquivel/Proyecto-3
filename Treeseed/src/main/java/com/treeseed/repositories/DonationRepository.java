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
	
	void update(Donation donation);
}