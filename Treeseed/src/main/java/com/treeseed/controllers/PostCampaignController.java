package com.treeseed.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostCampaignResponse;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.services.PostCampaignServiceInteface;

@RestController
@RequestMapping(value = "rest/protected/postCampaign")
public class PostCampaignController {
	
	@Autowired
	PostCampaignServiceInteface postCampaignService;

	/** The servlet context. */
	@Autowired
	ServletContext servletContext;

	@Autowired
	HttpServletRequest request;
	
	/**
	 * Gets the post of campaigns.
	 *
	 * @param PostCampaignRequest postRequest
	 * @return PostCampaignResponse
	 */
	@RequestMapping(value = "/getCampaignPost", method = RequestMethod.POST)
	public PostCampaignResponse getNonprofitPost(@RequestBody PostCampaignRequest postRequest) {

		PostCampaignResponse response = new PostCampaignResponse();
		Page<PostCampaign> postsResults = postCampaignService.getPosts(postRequest);
		List<PostCampaignPOJO> pojos = new ArrayList<PostCampaignPOJO>();
		PostCampaignPOJO pojoTemp;
		
		for(PostCampaign objeto:postsResults.getContent())
		{
			pojoTemp = new PostCampaignPOJO();
			pojoTemp.setId(objeto.getId());
			pojoTemp.setTittle(objeto.getTittle());
			pojoTemp.setDescription(objeto.getDescription());
			pojoTemp.setPicture(objeto.getPicture());
			pojoTemp.setCreationDate(new SimpleDateFormat("dd MMMMM yyyy").format(objeto.getCreationDate()));
			pojos.add(pojoTemp);
		}
		
		response.setTotalElements(postsResults.getTotalElements());
		response.setTotalPages(postsResults.getTotalPages());
		response.setPosts(pojos);
		response.setCode(200);
		response.setCodeMessage("Campaign posts");
		
		return response;

	}
}
