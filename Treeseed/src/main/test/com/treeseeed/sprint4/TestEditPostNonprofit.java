package com.treeseeed.sprint4;

import java.io.FileInputStream;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.treeseed.contracts.LoginRequest;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestEditPostNonprofit extends AbstractTestController {

	 @Autowired WebApplicationContext wac; 
	
	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	 @Before
	    public void setUp() {
	        super.setUp();

	    }

	

	@Test
	public void testEditarPostNonprofit() throws Exception {
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		PostNonprofitWrapper postnonprofit = createRandomPost(nonprofit.getWrapperObject());
		
		String title = "Post Change";
		String description= "Description";
		
		PostNonprofitRequest request = new PostNonprofitRequest();

		PostNonprofitPOJO pojo = new PostNonprofitPOJO();
		
		pojo.setTitle(title);
		pojo.setDescription(description);
		pojo.setId(postnonprofit.getId());
		pojo.setPicture(postnonprofit.getPicture());
		
		request.setPostNonprofit(pojo);
		
		
		String jsonObject = mapToJson(request);

		FileInputStream inputFile = new FileInputStream(
				"src/main/webapp/resources/file-storage/1436073230483.jpg");
		MockMultipartFile file = new MockMultipartFile("testImage",
				"1436073230483", "multipart/form-data", inputFile);

		String uri = "/rest/protected/postNonprofit/editPostNonProfit";

		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.fileUpload(uri)		
				.file("file",file.getBytes())
				.contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON)
		        .content(jsonObject))
		        
				.andReturn();

		String content = result.getResponse().getContentAsString();

		NonprofitResponse response = mapFromJson(content,
				NonprofitResponse.class);

		Assert.assertEquals("Post created", response.getCodeMessage());

	}

}
