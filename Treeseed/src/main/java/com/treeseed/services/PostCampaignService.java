package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.repositories.PostCampaignRepository;

@Service
public class PostCampaignService implements PostCampaignServiceInteface{
	
	@Autowired
	PostCampaignRepository postCampaign;
	
	@Override
	public  Page<PostCampaign> getPosts(PostCampaignRequest postRequest){
		
		PageRequest pageRequest = null;
		int campaignId = 0;
		Sort sort;
		
		Sort.Direction direction = Sort.Direction.DESC;
		
		if(postRequest.getSortBy().size() > 0){
			sort = new Sort(direction, postRequest.getSortBy() );
			pageRequest = new PageRequest(postRequest.getPageNumber()-1,
					postRequest.getPageSize(),sort);
		}else{
			pageRequest = new PageRequest(postRequest.getPageNumber()-1,
					postRequest.getPageSize());
		}
		
		Page<PostCampaign> pageResult = null;
		campaignId = postRequest.getPostCampaign().getCampaignId();
	
		pageResult = postCampaign.fillWithAll(campaignId, pageRequest);
		
		return pageResult ;
		
	}
}
