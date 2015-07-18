package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.repositories.PostNonprofitRepository;

@Service
public class PostNonprofitService implements PostNonprofitServiceInterface{

	@Autowired
	PostNonprofitRepository nonprofitsRepository;
	
	
	@Override
	public int savePostNonprofit(PostNonprofitWrapper wrapper) {
	
		PostNonprofit  post = nonprofitsRepository.save(wrapper.getWrapperObject());
		return post.getId();
	}

}
