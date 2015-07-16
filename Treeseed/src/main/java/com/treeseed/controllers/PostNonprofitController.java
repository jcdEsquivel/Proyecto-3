package com.treeseed.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.PostNonprofitServiceInterface;
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
			@RequestPart(value="file", required=false) MultipartFile file1,
			@RequestPart(value="data") PostNonprofitRequest data) {

		String resultFileName = "";
		HttpSession currentSession = request.getSession();
		PostNonprofitResponse response = new PostNonprofitResponse();
		int sessionId = (int) currentSession.getAttribute("idUser");

		Nonprofit nonprofit = nonprofitServiceInterface
				.getSessionNonprofit(data.getNonprofitId());

		if (nonprofit != null && nonprofit.getUsergenerals().get(0).getId() == sessionId) {

			PostNonprofitWrapper wrapper = new PostNonprofitWrapper(new PostNonprofit());

			if (file == null) {
				resultFileName = "resources/file-storage/1436319975812.jpg";
			} else {
				resultFileName = Utils.writeToFile(file, servletContext);
			}

			/*wrapper.setTittle(title);
			wrapper.setDescription(description);
			wrapper.setIsActive(true);
			wrapper.setPicture(resultFileName);
			wrapper.setNonprofit(nonprofit);*/

			postNonprofitService.savePostNonprofit(wrapper);

			response.setCode(200);
			response.setCodeMessage("Post created");

		} else {
			response.setCode(401);
			response.setCodeMessage("Invalid request");
		}

		return response;

	}

}
