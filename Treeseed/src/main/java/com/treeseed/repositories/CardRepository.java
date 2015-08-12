package com.treeseed.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.treeseed.ejb.Card;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;

// TODO: Auto-generated Javadoc
/**
 * The Interface CardRepository.
 */
public interface CardRepository extends 
	CrudRepository<Card,Integer> {
	
	/** The Constant PAGE_SIZE. */
	public static final int PAGE_SIZE = 5;
	
	/**
	 * Find by donor.
	 *
	 * @param donor the donor
	 * @return the list
	 */
	public List<Card> findByDonor(Donor donor);
}
