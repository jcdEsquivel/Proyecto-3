package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.repositories.PostCampaignRepository;
import com.treeseed.repositories.PostNonprofitRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignService.
 */
@Service
public class PostCampaignService implements PostCampaignServiceInterface{

	/** The post repository. */
	@Autowired
	PostCampaignRepository postRepository;
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.PostCampaignServiceInterface#savePost(com.treeseed.ejbWrapper.PostCampaignWrapper)
	 */
	@Override
	public int savePost(PostCampaignWrapper wrapper) {
	
		PostCampaign  post = postRepository.save(wrapper.getWrapperObject());
		return post.getId();
	}

	
	@Override
	@Transactional
	public void deletePostCampaign(PostCampaignRequest request)  {
		int id =request.getPostCampaign().getId();
		postRepository.deletePost(id);
		
	}
	
}
