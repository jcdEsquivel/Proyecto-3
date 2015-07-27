package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.repositories.PostNonprofitRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PostNonprofitService.
 */
@Service
public class PostNonprofitService implements PostNonprofitServiceInterface{

	/** The post repository. */
	@Autowired
	PostNonprofitRepository postRepository;
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.PostNonprofitServiceInterface#savePostNonprofit(com.treeseed.ejbWrapper.PostNonprofitWrapper)
	 */
	@Override
	public int savePostNonprofit(PostNonprofitWrapper wrapper) {
	
		PostNonprofit  post = postRepository.save(wrapper.getWrapperObject());
		return post.getId();
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.PostNonprofitServiceInterface#getPosts(com.treeseed.contracts.PostNonprofitRequest)
	 */
	@Override
	public  Page<PostNonprofit> getPosts(PostNonprofitRequest postRequest){
		
		PageRequest pageRequest;
		int nonprofitId = 0;
		Sort sort;
		
		Sort.Direction direction = Sort.Direction.DESC;
		/*if(postRequest.getDirection().equals("ASC")){
			direction = Sort.Direction.ASC;
		}*/
		
		if(postRequest.getSortBy().size() > 0){
			sort = new Sort(direction, postRequest.getSortBy() );
			pageRequest = new PageRequest(postRequest.getPageNumber()-1,
					postRequest.getPageSize(),sort);
		}else{
			pageRequest = new PageRequest(postRequest.getPageNumber()-1,
					postRequest.getPageSize());
		}
		
		Page<PostNonprofit> pageResult = null;
		nonprofitId = postRequest.getPostNonprofit().getNonprofitId();
	
		pageResult = postRepository.fillWithAll(nonprofitId, pageRequest);
		
		return pageResult ;
		
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.PostNonprofitServiceInterface#updatePostNonprofit(com.treeseed.ejbWrapper.PostNonprofitWrapper)
	 */
	@Override
	@Transactional
	public PostNonprofitWrapper updatePostNonprofit(PostNonprofitWrapper wrapper) {
		postRepository.update(wrapper.getId(),
				wrapper.getTittle(), 
				wrapper.getDescription(),
				wrapper.getPicture()
				
				);
		
		PostNonprofit post = new PostNonprofit();
		PostNonprofitWrapper postWrapper = new PostNonprofitWrapper();
		
		post= postRepository.findOne(wrapper.getId());
		
		postWrapper.setId(post.getId());
		postWrapper.setDescription(post.getDescription());
		postWrapper.setTittle(post.getTittle());
		postWrapper.setPicture(post.getPicture());
		
		return postWrapper;
	}
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.PostNonprofitServiceInterface#deletePostNonprofit(com.treeseed.contracts.PostNonprofitRequest)
	 */
	@Override
	@Transactional
	public void deletePostNonprofit(PostNonprofitRequest request)  {
		int id =request.getPostNonprofit().getId();
		postRepository.deletePost(id);
		
	}
	
}
