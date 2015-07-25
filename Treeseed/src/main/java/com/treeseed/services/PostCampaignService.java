package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.repositories.PostCampaignRepository;
import com.treeseed.repositories.PostNonprofitRepository;

@Service
public class PostCampaignService implements PostCampaignServiceInterface{

	@Autowired
	PostCampaignRepository postRepository;
	
	
	@Override
	public int savePost(PostCampaignWrapper wrapper) {
	
		PostCampaign  post = postRepository.save(wrapper.getWrapperObject());
		return post.getId();
	}

	
}
