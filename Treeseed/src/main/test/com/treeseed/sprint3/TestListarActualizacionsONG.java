package com.treeseed.sprint3;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestListarActualizacionsONG extends AbstractTestController  {

	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Before
	public void setUp() {
		super.setUp();

	}

	
	@Test
	public void testGetPostsFromNonprofit() throws Exception {

		Nonprofit nonprofit = createRandomNonprofit().getWrapperObject();
		createRandomPost(nonprofit);
		createRandomPost(nonprofit);
		createRandomPost(nonprofit);
		createRandomPost(nonprofit);
		createRandomPost(nonprofit);
		
		PostNonprofitRequest request = new PostNonprofitRequest();
		PostNonprofitPOJO pojo = new PostNonprofitPOJO();
		pojo.setNonprofitId(nonprofit.getId());
		request.setPostNonprofit(pojo);
		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/postNonprofit/getNonprofitPost";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		PostNonprofitResponse response = mapFromJson(content,
				PostNonprofitResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
	
	@Test
	public void testGetZeroPostsFromNonprofit() throws Exception {

		Nonprofit nonprofit = createRandomNonprofit().getWrapperObject();
		
		PostNonprofitRequest request = new PostNonprofitRequest();
		PostNonprofitPOJO pojo = new PostNonprofitPOJO();
		pojo.setNonprofitId(nonprofit.getId());
		request.setPostNonprofit(pojo);
		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/postNonprofit/getNonprofitPost";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		PostNonprofitResponse response = mapFromJson(content,
				PostNonprofitResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
}
