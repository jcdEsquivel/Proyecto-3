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

import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.PostNonprofitServiceInterface;
import com.treeseed.utils.TreeseedConstants;
import com.treeseed.utils.Utils;

@RestController
@RequestMapping(value = "rest/protected/postNonprofit")
public class PostNonprofitController {

	@Autowired
	PostNonprofitServiceInterface postNonprofitService;

	@Autowired
	NonprofitServiceInterface nonprofitServiceInterface;

	@Autowired
	ServletContext servletContext;

	@Autowired
	HttpServletRequest request;
	

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public PostNonprofitResponse create(@RequestPart(value="file", required=false) MultipartFile file,
			@RequestPart(value="data") PostNonprofitRequest requestObj) {

		String resultFileName = "";
		int generalUserId = 0;
		HttpSession currentSession = request.getSession();
		PostNonprofitResponse response = new PostNonprofitResponse();
		
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

		return response;

	}
	
	
	@RequestMapping(value = "/getNonprofitPost", method = RequestMethod.POST)
	public PostNonprofitResponse getNonprofitPost(@RequestBody PostNonprofitRequest postRequest) {

		PostNonprofitResponse response = new PostNonprofitResponse();
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
		
		return response;

	}

}
