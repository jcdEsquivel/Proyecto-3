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
	 * @param id the id
	 * @return the list
	 */
	List<RecurrableDonation> findByNonprofitId(int id);
	
	/**
	 * Find by campaign id.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<RecurrableDonation> findByCampaignId(int id);
	
}