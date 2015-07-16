package com.treeseed.services;

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
import com.treeseed.repositories.CampaignRepository;



@Service
public class CampaignService implements CampaignServiceInterface{
	
	@Autowired
	CampaignRepository campaignRepository;
	
	@Override
	@Transactional
	public int saveCampaign(CampaignWrapper campaign) {
		
		Campaign camp = campaignRepository.save(campaign.getWrapperObject());
		return camp.getId();
		
	}

	@Override
	public Page<Campaign> getCampaignsByNonprofit(CampaignRequest ur) {
		PageRequest pr;
		int nonprofitId=0;
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
		
		Page<Campaign> pageResult = null;
		

		nonprofitId = ur.getNonprofit().getId();	
		
		pageResult = campaignRepository.findByNonprofitId(nonprofitId, pr);
		
		return pageResult ;
	}
}
