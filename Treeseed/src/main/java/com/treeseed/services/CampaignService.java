package com.treeseed.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.repositories.CampaignRepository;
import com.treeseed.utils.PageWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignService.
 */
@Service
public class CampaignService implements CampaignServiceInterface{
	
	
	/** The campaign repository. */
	@Autowired
	CampaignRepository campaignRepository;

	/* (non-Javadoc)
	 * @see com.treeseed.services.CampaignServiceInterface#getAllCampaigns(com.treeseed.contracts.CampaignRequest)
	 */
	@Transactional
	public Page<Campaign> getAllCampaigns(CampaignRequest ur) {
		Date startDate = null;
		Date endDate = null;
		PageRequest pr = null;
		Sort.Direction direction = Sort.Direction.DESC;
		if (ur.getDirection().equals("ASC")) {
			direction = Sort.Direction.ASC;
		}

		if (ur.getSortBy().size() > 0) {
			Sort sort = new Sort(direction, ur.getSortBy());
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize(), sort);
		} else {
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize());
		}

		Page<Campaign> result = null;

		String campaignName = ur.getName();
		String nonProfitName = ur.getNonprofitName();
		int causeId = ur.getCauseId();

		if (ur.getStartDate() > 0) {
			startDate = new Date(ur.getStartDate());
		}

		if (ur.getDueDate() > 0) {
			endDate = new Date(ur.getDueDate());
		}

		result = campaignRepository.findWithAll(campaignName, "%"
				+ campaignName + "%", nonProfitName, "%" + nonProfitName + "%",
				causeId, startDate, endDate, pr);

		return result;
	}


	/* (non-Javadoc)
	 * @see com.treeseed.services.CampaignServiceInterface#findCampaignsFromNonprofit(com.treeseed.contracts.CampaignRequest)
	 */
	@Transactional
	public PageWrapper<CampaignWrapper> findCampaignsFromNonprofit(CampaignRequest ur) {

		PageRequest pr = null;
		Sort.Direction direction = Sort.Direction.DESC;
		PageWrapper<CampaignWrapper> pageWrapper = new PageWrapper<CampaignWrapper>();
		Date startDate = null;
		Date endDate = null;
		Date startDateSoon = null;
		Date startDateActive = null;
		Date endDateActive = null;
		Date endDateFinished = null;
		Calendar cal = Calendar.getInstance();
		
		ur.setPageNumber(ur.getPageNumber()-1);
		
		if (ur.getDirection().equals("ASC")) {
			direction = Sort.Direction.ASC;
		}

		if (ur.getSortBy().size() > 0) {
			Sort sort = new Sort(direction, ur.getSortBy());
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize(), sort);
		} else {
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize());
		}

		Page<Campaign> result = null;

		int nonprofitId = ur.getNonprofitId();
		String campaignName = ur.getName();
		

		if(ur.getState() == null){//search by date range
			
			if(ur.getStartDate() > 0)
			{
				startDate = new Date(ur.getStartDate());
			}

			if(ur.getDueDate() > 0)
			{	
				endDate = new Date(ur.getDueDate());
			}
			
		}else if(ur.getState() != null){//search by state
			
			if(ur.getState().equals("soon")){
		
				startDateSoon = new Date();
				
			}else if(ur.getState().equals("active")){

				startDateActive = new Date();
				endDateActive = new Date();
			}else{
				endDateFinished = new Date(); 
			}
		}
		
		result = campaignRepository.findFromNonprofit(campaignName, "%" + campaignName + "%",
				 startDate, endDate,startDateSoon, startDateActive, endDateActive,endDateFinished,
				 nonprofitId, pr);

		for (Campaign c : result.getContent()) {
		    pageWrapper.getResults().add(new CampaignWrapper(c));
		  }
		
		pageWrapper.setTotalItems(result.getTotalElements());
		
		return pageWrapper;
	}


	/* (non-Javadoc)
	 * @see com.treeseed.services.CampaignServiceInterface#saveCampaign(com.treeseed.ejbWrapper.CampaignWrapper)
	 */
	@Override
	@Transactional
	public int saveCampaign(CampaignWrapper campaign) {

		Campaign camp = campaignRepository.save(campaign.getWrapperObject());
		return camp.getId();

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.treeseed.services.CampaignServiceInterface#getCampaignsByNonprofit
	 * (com.treeseed.contracts.CampaignRequest)
	 */
	@Override
	public PageWrapper<CampaignWrapper> getCampaignsByNonprofit(
			CampaignRequest ur) {
		PageRequest pr;
		int nonprofitId = 0;
		PageWrapper<CampaignWrapper> pageWrapper = new PageWrapper<CampaignWrapper>();
		Page<Campaign> result = null;

		Sort.Direction direction = Sort.Direction.DESC;
		if (ur.getDirection().equals("ASC")) {
			direction = Sort.Direction.ASC;
		}

		if (ur.getSortBy().size() > 0) {
			Sort sort = new Sort(direction, ur.getSortBy());
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize(), sort);
		} else {
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize());
		}

		nonprofitId = ur.getNonprofitId();

		result = campaignRepository.findByNonprofitId(nonprofitId, pr);

		for (Campaign c : result.getContent()) {
			pageWrapper.getResults().add(new CampaignWrapper(c));
		}

		pageWrapper.setTotalItems(result.getTotalElements());

		return pageWrapper;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.treeseed.services.CampaignServiceInterface#updateCampaign
	 * (com.treeseed.contracts.CampaignRequest)
	 */
	@Override
	@Transactional
	public void updateCampaign(CampaignWrapper campaign) {
		campaignRepository.update(campaign.getId(),
								  campaign.getName(),
								  campaign.getDescription(),
								  campaign.getDueDate(),
								  campaign.getStartDate(),
								  campaign.getAmountCollected(),
								  campaign.getAmountGoal(),
								  campaign.getPicture());
	}
	/* (non-Javadoc)
	 * @see com.treeseed.services.CampaignServiceInterface#getCampaignById(int)
	 */
	@Override
	public CampaignWrapper getCampaignById(int id){
		CampaignWrapper campaign;
		
		campaign= new CampaignWrapper(campaignRepository.findByid(id));
		
		return campaign;
		
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.CampaignServiceInterface#closeCampaign(int)
	 */
	@Override
	public void closeCampaign(int id){
		campaignRepository.updateIsActiveById(id, false);
	}
}
