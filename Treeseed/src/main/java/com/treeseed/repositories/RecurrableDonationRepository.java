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

public interface RecurrableDonationRepository extends CrudRepository<RecurrableDonation, Integer> {

	public static final int PAGE_SIZE = 5;

	Page<RecurrableDonation> findAll(Pageable pageable);

	List<RecurrableDonation> findById(int id);
	
}