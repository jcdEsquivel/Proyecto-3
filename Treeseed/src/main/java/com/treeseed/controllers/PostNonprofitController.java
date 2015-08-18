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

import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.PostNonprofitServiceInterface;
import com.treeseed.utils.TreeseedConstants;
import com.treeseed.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class PostNonprofitController.
 */
@RestController
@RequestMapping(value = "rest/protected/postNonprofit")
public class PostNonprofitController {

	/** The post nonprofit service. */
	@Autowired
	PostNonprofitServiceInterface postNonprofitService;

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
	 * @return the post nonprofit response
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public PostNonprofitResponse create(@RequestPart(value="file", required=false) MultipartFile file,
			@RequestPart(value="data") PostNonprofitRequest requestObj) {
		
		PostNonprofitResponse response = new PostNonprofitResponse();
		
		try{
			String resultFileName = "";
			int generalUserId = 0;
			HttpSession currentSession = request.getSession();
			
			
			int sessionId = (int) currentSession.getAttribute("idUser");
			
			NonprofitWrapper nonprofit = nonprofitServiceInterface
					.getSessionNonprofit(requestObj.getPostNonprofit().getNonprofitId());
			
			generalUserId = nonprofit.getUsergenerals().get(0).getId();
			
			
			//Checks if the request comes from the logged user.
			if (nonprofit != null && generalUserId == sessionId) {
	
				PostNonprofitWrapper post = new PostNonprofitWrapper(new PostNonprofit());
	
				if (file == null) {
					resultFileName = TreeseedConstants.DEFAULT_POST_IMAGE;
				} else {
					resultFileName = Utils.writeToFile(file, servletContext);
				}
	
				post.setTittle(requestObj.getPostNonprofit().getTitle());
				post.setDescription(requestObj.getPostNonprofit().getDescription());
				post.setIsActive(true);
				post.setPicture(resultFileName);
				post.setNonprofit(nonprofit.getWrapperObject());
				post.setCreationDate(new Date());
	
				postNonprofitService.savePostNonprofit(post);
	
				response.setCode(200);
				response.setCodeMessage("Post created");
	
			} else {
				response.setCode(401);
				response.setCodeMessage("Invalid request");
			}
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				response.setCode(10);
				response.setErrorMessage("Data Base error");
			}else{
				response.setCode(500);
			}
			
		}
		return response;

	}
	
	
	/**
	 * Gets the nonprofit post.
	 *
	 * @param postRequest the post request
	 * @return the nonprofit post
	 */
	@RequestMapping(value = "/getNonprofitPost", method = RequestMethod.POST)
	public PostNonprofitResponse getNonprofitPost(@RequestBody PostNonprofitRequest postRequest) {

		PostNonprofitResponse response = new PostNonprofitResponse();
		
		try{
		
			Page<PostNonprofit> postsResults = postNonprofitService.getPosts(postRequest);
			List<PostNonprofitPOJO> pojos = new ArrayList<PostNonprofitPOJO>();
			PostNonprofitPOJO pojoTemp;
			
			for(PostNonprofit objeto:postsResults.getContent())
			{
				pojoTemp = new PostNonprofitPOJO();
				pojoTemp.setId(objeto.getId());
				pojoTemp.setTitle(objeto.getTittle());
				pojoTemp.setDescription(objeto.getDescription());
				pojoTemp.setPicture(objeto.getPicture());
				pojoTemp.setDate(new SimpleDateFormat("dd MMMMM yyyy").format(objeto.getCreationDate()));
				pojos.add(pojoTemp);
			}
			
			response.setTotalElements(postsResults.getTotalElements());
			response.setTotalPages(postsResults.getTotalPages());
			response.setPosts(pojos);
			response.setCode(200);
			response.setCodeMessage("Nonprofit posts");
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				response.setCode(10);
				response.setErrorMessage("Data Base error");
			}else{
				response.setCode(500);
			}
			
		}
		
		return response;

	}
	
	/**
	 * Edits the post non profit.
	 *
	 * @param pr the Post Non Profit Request
	 * @param file the file
	 * @return the post nonprofit response
	 */
	@RequestMapping(value ="/editPostNonProfit", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public PostNonprofitResponse editPostNonProfit(@RequestPart(value="data") PostNonprofitRequest pr, @RequestPart(value="file", required=false) MultipartFile file)
	{
		PostNonprofitResponse us = new PostNonprofitResponse();
		
		try{
		
			HttpSession currentSession = request.getSession();
			
			int sessionId = (int) currentSession.getAttribute("idUser");
			
			if(pr.getPostNonprofit().getNonprofitId()==sessionId){
				
				String imagePost = "";
				PostNonprofitPOJO postNonprofitPOJO = new PostNonprofitPOJO();
				postNonprofitPOJO= pr.getPostNonprofit();		
				PostNonprofitWrapper postNonprofit = new PostNonprofitWrapper();
				
				if(file!=null){
					imagePost = Utils.writeToFile(file,servletContext);
				}
	
	
				if(!imagePost.equals("")){
					postNonprofit.setPicture(imagePost);
				}else{
					postNonprofit.setPicture(postNonprofitPOJO.getPicture());
				}
				
				
				postNonprofit.setId(postNonprofitPOJO.getId());
				postNonprofit.setTittle(pr.getPostNonprofit().getTitle());
				postNonprofit.setDescription(pr.getPostNonprofit().getDescription());
				
				postNonprofit= postNonprofitService.updatePostNonprofit(postNonprofit);
				
				
				postNonprofitPOJO.setTitle(postNonprofit.getTittle());
				postNonprofitPOJO.setDescription(postNonprofit.getDescription());
				postNonprofitPOJO.setPicture(postNonprofit.getPicture());
				
				us.setPost(postNonprofitPOJO);
				us.setCode(200);
				us.setCodeMessage("Post of Nonprofit updated sucessfully");
				
			}else{
				us.setCode(401);
				us.setCodeMessage("Invalid request");
			}
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				us.setCode(10);
				us.setErrorMessage("Data Base error");
			}else{
				us.setCode(500);
			}
		}
		
		
		return us;		
	}
	
	
	
	/**
	 * Delete post non profit.
	 *
	 * @param pnr the Post Non Profit Request
	 * @return the post nonprofit response
	 */
	@RequestMapping(value ="/deletePostNonProfit", method = RequestMethod.POST)
	public PostNonprofitResponse deletePostNonProfit(@RequestBody PostNonprofitRequest pnr)
	{
		PostNonprofitResponse us = new PostNonprofitResponse();
	
		try{
		
			HttpSession currentSession = request.getSession();
			int sessionId = (int) currentSession.getAttribute("idUser");

			if(pnr.getPostNonprofit().getNonprofitId()== sessionId){
			
				try{
					postNonprofitService.deletePostNonprofit(pnr);
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
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				us.setCode(10);
				us.setErrorMessage("Data Base error");
			}else{
				us.setCode(500);
			}
		}
		
		return us;				
	}

}
