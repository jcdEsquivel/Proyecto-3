package com.treeseed.services;

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

@Service
public class CampaignService implements CampaignServiceInterface{
	
	@Autowired
	CampaignRepository campaignRepository;

	@Transactional
	public Page<Campaign> getAllCampaigns(CampaignRequest ur) {

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
		
		Date startDate = null;
		if(ur.getStartDate() > 0)
		{
			startDate = new Date(ur.getStartDate());
		}
		
		Date endDate = null;
		if(ur.getDueDate() > 0)
		{	
			endDate = new Date(ur.getDueDate());
		}
		
		result = campaignRepository.findWithAll(campaignName, "%" + campaignName + "%",
				nonProfitName, "%" + nonProfitName+ "%", causeId, startDate, endDate, pr);

		return result;
	}
	
	
	
	@Transactional
	public PageWrapper<CampaignWrapper> findCampaignsFromNonprofit(CampaignRequest ur) {

		PageRequest pr = null;
		Sort.Direction direction = Sort.Direction.DESC;
		PageWrapper<CampaignWrapper> pageWrapper = new PageWrapper<CampaignWrapper>();
		Date startDate = null;
		Date endDate = null;
		
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

		int nonprofitId = ur.getId();
		String campaignName = ur.getName();
		String nonProfitName = ur.getNonprofitName();
		int causeId = ur.getCauseId();
		
	
		if(ur.getStartDate() > 0)
		{
			startDate = new Date(ur.getStartDate());
		}
		
		
		if(ur.getDueDate() > 0)
		{	
			endDate = new Date(ur.getDueDate());
		}
		
		result = campaignRepository.findFromNonprofit(campaignName, "%" + campaignName + "%",
				nonProfitName, "%" + nonProfitName+ "%", causeId, startDate, endDate,nonprofitId, pr);

		for (Campaign c : result.getContent()) {
		    pageWrapper.getResults().add(new CampaignWrapper(c));
		  }
		
		pageWrapper.setTotalItems(result.getTotalElements());
		
		return pageWrapper;
	}
	
	

	@Override
	@Transactional
	public int saveCampaign(CampaignWrapper campaign) {
		
		Campaign camp = campaignRepository.save(campaign.getWrapperObject());
		return camp.getId();
		
	}

	@Override
	public PageWrapper<CampaignWrapper> getCampaignsByNonprofit(CampaignRequest ur) {
		PageRequest pr;
		int nonprofitId=0;
		PageWrapper<CampaignWrapper> pageWrapper = new PageWrapper<CampaignWrapper>();
		Page<Campaign> result = null;
		
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
		
		
		nonprofitId = ur.getNonprofitId();	
		
		result = campaignRepository.findByNonprofitId(nonprofitId, pr);

		for (Campaign c : result.getContent()) {
		    pageWrapper.getResults().add(new CampaignWrapper(c));
		  }
		
		pageWrapper.setTotalItems(result.getTotalElements());
		
		return pageWrapper ;
	}
}
