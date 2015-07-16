package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.ejb.Campaign;
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
}
