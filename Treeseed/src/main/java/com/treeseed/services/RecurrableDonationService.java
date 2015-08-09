package com.treeseed.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.RecurrableDonation;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;
import com.treeseed.pojo.RecurrableDonationPOJO;
import com.treeseed.repositories.DonationRepository;
import com.treeseed.repositories.RecurrableDonationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RecurrableDonationService.
 */
@Service
public class RecurrableDonationService implements RecurrableDonationServiceInterface{
	
	/** The recurrable donation repository. */
	@Autowired
	RecurrableDonationRepository recurrableDonationRepository;


	/* (non-Javadoc)
	 * @see com.treeseed.services.RecurrableDonationServiceInterface#saveRecurrableDonation(com.treeseed.ejbWrapper.RecurrableDonationWrapper)
	 */
	@Override
	public RecurrableDonationWrapper saveRecurrableDonation(RecurrableDonationWrapper recurrableDonation) {
		RecurrableDonationWrapper recurrableDonationSaved = new RecurrableDonationWrapper(recurrableDonationRepository.save(recurrableDonation.getWrapperObject()));
		return recurrableDonationSaved;
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.RecurrableDonationServiceInterface#getRecurrableDonation(int, java.lang.String)
	 */
	@Override
	public List<RecurrableDonationWrapper> getRecurrableDonation(int donorId, int nonprofitId, int campaignId){
		
		List<RecurrableDonationWrapper> wrapperList = new ArrayList<RecurrableDonationWrapper>();
		List<RecurrableDonation> list = new ArrayList<RecurrableDonation>();
		
		if(campaignId  > 0 ){
			list =  recurrableDonationRepository.findByCampaingIdAndDonorId(campaignId, donorId);
		}else{
			list = recurrableDonationRepository.findByNonProfitIdAndDonorId(nonprofitId, donorId);
		}
		
		for (RecurrableDonation r : list) {
		    wrapperList.add(new RecurrableDonationWrapper(r));
		}
		
		return wrapperList;
	}
	
	
	public RecurrableDonationWrapper getById(int id){	
		return new RecurrableDonationWrapper(recurrableDonationRepository.findOne(id));
	}
	
	public void editRecurrableDonation(RecurrableDonationWrapper donation){
		recurrableDonationRepository.save(donation.getWrapperObject());
	}

}
