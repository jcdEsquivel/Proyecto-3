package com.treeseed.controllers;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostCampaignResponse;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.PostCampaignServiceInterface;
import com.treeseed.services.PostNonprofitServiceInterface;
import com.treeseed.utils.TreeseedConstants;
import com.treeseed.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCampaignController.
 */
@RestController
@RequestMapping(value = "rest/protected/postCampaign")
public class PostCampaignController {

	/** The post campaign service. */
	@Autowired
	PostCampaignServiceInterface postCampaignService;
	
	/** The campaign service. */
	@Autowired
	CampaignServiceInterface campaignService;

	/** The nonprofit service interface. */
	@Autowired
	NonprofitServiceInterface nonprofitServiceInterface;

	/** The servlet context. */
	@Autowired
	ServletContext servletContext;

	/** The request. */
	@Autowired
	HttpServletRequest request;
	

	/**
	 * Creates the.
	 *
	 * @param file the file
	 * @param requestObj the request obj
	 * @return the post campaign response
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public PostCampaignResponse create(@RequestPart(value="file", required=false) MultipartFile file,
			@RequestPart(value="data") PostCampaignRequest requestObj) {

		String resultFileName = "";
		int generalUserId = 0;
		HttpSession currentSession = request.getSession();
		PostCampaignResponse response = new PostCampaignResponse();
		CampaignWrapper campaignWrapper = campaignService.getCampaignById(requestObj.getPostCampaign().getCampaignId());
		
		int sessionId = (int) currentSession.getAttribute("idUser");
		
		NonprofitWrapper nonprofit = nonprofitServiceInterface
				.getSessionNonprofit(requestObj.getNonprofitId());
		
		generalUserId = nonprofit.getUsergenerals().get(0).getId();
		
		
		//Checks if the request comes from the logged user.
		if (nonprofit != null && generalUserId == sessionId) {

			PostCampaignWrapper post = new PostCampaignWrapper(new PostCampaign());

			if (file == null) {
				resultFileName = TreeseedConstants.DEFAULT_POST_IMAGE;
			} else {
				resultFileName = Utils.writeToFile(file, servletContext);
			}

			post.setTittle(requestObj.getPostCampaign().getTitle());
			post.setDescription(requestObj.getPostCampaign().getDescription());
			post.setActive(true);
			post.setPicture(resultFileName);
			post.setCampaign(campaignWrapper.getWrapperObject());
			post.setCreationDate(new Date());

			postCampaignService.savePost(post);

			response.setCode(200);
			response.setCodeMessage("Post created");

		} else {
			response.setCode(401);
			response.setCodeMessage("Invalid request");
		}

		return response;

	}
	
	
	
	@RequestMapping(value ="/deletePostNonProfit", method = RequestMethod.POST)
	public PostCampaignResponse deletePostCampaign(@RequestBody PostCampaignRequest pnr)
	{
		int generalUserId = 0;
		HttpSession currentSession = request.getSession();
		int sessionId = (int) currentSession.getAttribute("idUser");
		PostCampaignResponse us = new PostCampaignResponse();
		
		NonprofitWrapper nonprofit = nonprofitServiceInterface
				.getSessionNonprofit(pnr.getNonprofitId());
		
		generalUserId = nonprofit.getUsergenerals().get(0).getId();
		
		if(generalUserId== sessionId){
		
			try{
				postCampaignService.deletePostCampaign(pnr);
				us.setCode(200);
				us.setCodeMessage("Post deleted sucessfully");
		
			}catch(Exception e){
				us.setCode(400);
				us.setCodeMessage("Invalid request");
			}
		}else{
			us.setCode(400);
			us.setCodeMessage("Invalid request");
		}
		
		return us;				
	}
	
	
}
