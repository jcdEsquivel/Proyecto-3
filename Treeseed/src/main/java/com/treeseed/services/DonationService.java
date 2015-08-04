package com.treeseed.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donation;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;
import com.treeseed.repositories.DonationRepository;
import com.treeseed.utils.PageWrapper;

@Service
public class DonationService implements DonationServiceInterface{
	
	@Autowired
	DonationRepository donationRepository;

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
	 * @see com.treeseed.services.DonationServiceInterface#findDonorsPerCampaign(int)
	 */
	public PageWrapper<DonationWrapper> findDonationsOfDonor(DonationRequest dr){
		Sort.Direction direction = Sort.Direction.DESC;
		PageRequest pr = null;
		PageWrapper<DonationWrapper> pageWrapper = new PageWrapper<DonationWrapper>();
		int month = 0;
		int year = 0;
		
		if (dr.getSortBy().size() > 0) {
			Sort sort = new Sort(direction, dr.getSortBy());
			pr = new PageRequest(dr.getPageNumber(), dr.getPageSize(), sort);
		} else {
			pr = new PageRequest(dr.getPageNumber(), dr.getPageSize());
		}
		
		//If we have correct data for month and year we should retrieve it
		//Otherwise it should always be null in order to create the query
		if(dr.getMonth() != null && dr.getMonth() != ""){
			month = Integer.parseInt(dr.getMonth());
		}
		else{
			dr.setMonth(null);
		}
		
		if(dr.getYear() != null && dr.getYear() != ""){
			year = Integer.parseInt(dr.getYear());
		}
		else{
			dr.setYear(null);
		}
			
		
		Page<Donation> donations = donationRepository.findDonationsOfDonor(dr.getDonorId(), 
																		   dr.getMonth(),
																		   month,
																	       dr.getYear(), 
																		   year,
																		   pr);
		 
		for(Donation d : donations.getContent()){
			pageWrapper.getResults().add(new DonationWrapper(d));
		}
		
		pageWrapper.setTotalItems(donations.getTotalElements());
		
		return pageWrapper;		
	}
}
