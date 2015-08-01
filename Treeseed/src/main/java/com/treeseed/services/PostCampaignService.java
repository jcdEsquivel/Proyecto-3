package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.repositories.PostCampaignRepository;
import com.treeseed.repositories.PostNonprofitRepository;
import com.treeseed.utils.PageWrapper;

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
	
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.PostCampaignServiceInterface#getPostsFromCampaign(com.treeseed.contracts.PostCampaignRequest)
	 */
	@Override
	public   PageWrapper<PostCampaignWrapper> getPostsFromCampaign(PostCampaignRequest postRequest){
		
		PageRequest pageRequest = null;
		int campaignId = 0;
		Sort sort;
		Page<PostCampaign> pageResult = null;
		PageWrapper<PostCampaignWrapper> pageWrapper = new PageWrapper<PostCampaignWrapper>();
		
		
		Sort.Direction direction = Sort.Direction.DESC;
		
		if(postRequest.getSortBy().size() > 0){
			sort = new Sort(direction, postRequest.getSortBy() );
			pageRequest = new PageRequest(postRequest.getPageNumber()-1,
					postRequest.getPageSize(),sort);
		}else{
			pageRequest = new PageRequest(postRequest.getPageNumber()-1,
					postRequest.getPageSize());
		}
		
		
		campaignId = postRequest.getPostCampaign().getCampaignId();
	
		pageResult = postRepository.findPosts(campaignId, pageRequest);
		
		for (PostCampaign c : pageResult.getContent()) {
		    pageWrapper.getResults().add(new PostCampaignWrapper(c));
		  }
		
		
		pageWrapper.setTotalItems(pageResult.getTotalElements());
		pageWrapper.setTotalPages(pageResult.getTotalPages());
		
		return pageWrapper ;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.PostCampaignServiceInterface#updatePostCampaign(com.treeseed.ejbWrapper.PostCampaignWrapper)
	 */
	@Override
	@Transactional
	public PostCampaignWrapper updatePostCampaign(PostCampaignWrapper wrapper) {
		postRepository.update(wrapper.getId(),
				wrapper.getTittle(), 
				wrapper.getDescription(),
				wrapper.getPicture()
				
				);
		
		PostCampaign post = new PostCampaign();
		PostCampaignWrapper postWrapper = new PostCampaignWrapper();
		
		post= postRepository.findOne(wrapper.getId());
		
		postWrapper.setId(post.getId());
		postWrapper.setDescription(post.getDescription());
		postWrapper.setTittle(post.getTittle());
		postWrapper.setPicture(post.getPicture());
		
		return postWrapper;
	}

	
}
