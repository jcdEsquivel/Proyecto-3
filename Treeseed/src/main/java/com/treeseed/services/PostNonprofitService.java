package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.repositories.PostNonprofitRepository;

@Service
public class PostNonprofitService implements PostNonprofitServiceInterface{

	@Autowired
	PostNonprofitRepository postRepository;
	
	
	@Override
	public int savePostNonprofit(PostNonprofitWrapper wrapper) {
	
		PostNonprofit  post = postRepository.save(wrapper.getWrapperObject());
		return post.getId();
	}

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
	
}
