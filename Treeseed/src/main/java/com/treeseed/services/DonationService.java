package com.treeseed.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.ejb.Donation;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.repositories.DonationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DonationService.
 */
@Service
public class DonationService implements DonationServiceInterface{
	
	/** The donation repository. */
	@Autowired
	DonationRepository donationRepository;
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#findAmountPerMonthOfNonProfit(int, java.sql.Date, java.sql.Date)
	 */
	@Override
	@Transactional
	public double findAmountPerMonthOfNonProfit(int nonProfitId,
			Date startDate, Date endDate) {
		return donationRepository.findAmountPerMonthOfNonProfit(nonProfitId, startDate, endDate);
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#findDonorsPerCampaign(int)
	 */
	@Override
	public int findDonorsPerCampaign(int campaignId) {

		return donationRepository.countDistincDonorIdByCampaingId(campaignId);
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#getDonations(com.treeseed.contracts.DonationRequest)
	 */
	@Override
	public Page<Donation> getDonations(DonationRequest ur) {
		PageRequest pr;
		int month = 0;
		int year = 0;
		
		Sort.Direction direction = Sort.Direction.DESC;
		if(ur.getDirection().equals("ASC")){
			direction = Sort.Direction.ASC;
		}
		
		if(ur.getSortBy().size() > 0){
			Sort sort = new Sort(direction,ur.getSortBy());
			pr = new PageRequest(ur.getPageNumber(),
					ur.getPageSize(),sort);
		}else{
			pr = new PageRequest(ur.getPageNumber(),
					ur.getPageSize());
		}
		
		Page<Donation> pageResult = null;
		
		int nonProfitId = ur.getNonProfitId();
		
		if(ur.getMonth() != null && ur.getMonth() != ""){
			month = Integer.parseInt(ur.getMonth());
		}
		else{
			ur.setMonth(null);
		}
		
		if(ur.getYear() != null && ur.getYear() != ""){
			year = Integer.parseInt(ur.getYear());
		}
		else{
			ur.setYear(null);
		}
		
		pageResult = donationRepository.findAllDonations(nonProfitId, ur.getMonth(), month, ur.getYear(), year, pr);
		
		return pageResult ;
	}
}
