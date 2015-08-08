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
	public List<RecurrableDonationWrapper> getRecurrableDonation(int id, String type){
		
		List<RecurrableDonationWrapper> wrapperList = new ArrayList<RecurrableDonationWrapper>();
		List<RecurrableDonation> list = new ArrayList<RecurrableDonation>();
		
		if(type == "campaign"){
			list =  recurrableDonationRepository.findByCampaignId(id);
		}else{
			list = recurrableDonationRepository.findByCampaignId(id);
		}
		
		for (RecurrableDonation r : list) {
		    wrapperList.add(new RecurrableDonationWrapper(r));
		}
		
		return wrapperList;
	}

}
