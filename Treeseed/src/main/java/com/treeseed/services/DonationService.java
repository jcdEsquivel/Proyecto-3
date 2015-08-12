package com.treeseed.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;
import com.treeseed.repositories.DonationRepository;
import com.treeseed.repositories.RecurrableDonationRepository;
import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donation;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.ejbWrapper.TransparencyReportWrapper;
import com.treeseed.utils.PageWrapper;

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
	 * @see com.treeseed.services.DonationServiceInterface#saveDonation(com.treeseed.ejbWrapper.DonationWrapper)
	 */
	@Override
	public DonationWrapper saveDonation(DonationWrapper donation) {
		DonationWrapper donationSaved = new DonationWrapper(donationRepository.save(donation.getWrapperObject()));
		return donationSaved;
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#updateDonation(com.treeseed.ejbWrapper.DonationWrapper)
	 */
	@Override
	public void updateDonation(DonationWrapper donation) {
		donationRepository.update(donation.getId(),donation.getStripeId());	
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#getDonationByStripeId(java.lang.String)
	 */
	@Override
	public DonationWrapper getDonationByStripeId(String stripeId) {
		return new DonationWrapper(donationRepository.findByStripeId(stripeId));	
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
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#getSumDonationsByDonor(int)
	 */
	public double getSumDonationsByDonor(int idDonor){
		return donationRepository.sumAmountByDonor(idDonor);
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonationServiceInterface#getDonationsByNonprofit(int, int)
	 */
	@Override
	public List<DonationWrapper> getDonationsByNonprofit(int nonProfitId, int cant) {
		
		List<Donation> donations =donationRepository.getByNonProfitIdDashboard(nonProfitId, cant);
		List<DonationWrapper> donationsWrapper = new ArrayList<DonationWrapper>();
		
		for(Donation donation:donations){
			DonationWrapper donationWrapper = new DonationWrapper(donation);
			donationsWrapper.add(donationWrapper);
		}
		
		return donationsWrapper;
	}
}
