package com.treeseed.sprint5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostCampaignResponse;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestListarActualizacionesCampania extends AbstractTestController  {

	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Before
	public void setUp() {
		super.setUp();

	}

	@Test
	public void testGetPostsFromCampaign() throws Exception {

		Campaign campaign = createRandomCampaign().getWrapperObject();
		createRandomPostCampaign(campaign);
		createRandomPostCampaign(campaign);
		createRandomPostCampaign(campaign);
		createRandomPostCampaign(campaign);
		createRandomPostCampaign(campaign);
		
		PostCampaignRequest request = new PostCampaignRequest();
		PostCampaignPOJO pojo = new PostCampaignPOJO();
		pojo.setCampaignId(campaign.getId());
		request.setNonprofitId( campaign.getNonprofit().getId());
		request.setPostCampaign(pojo);
		request.setSortBy(new ArrayList<String>());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/postCampaign/getPostFromCampaign";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		PostCampaignResponse response = mapFromJson(content,
				PostCampaignResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
	@Test
	public void testGetZeroPostsFromCampaign() throws Exception {

		Campaign campaign = createRandomCampaign().getWrapperObject();
		
		
		PostCampaignRequest request = new PostCampaignRequest();
		PostCampaignPOJO pojo = new PostCampaignPOJO();
		pojo.setCampaignId(campaign.getId());
		request.setNonprofitId( campaign.getNonprofit().getId());
		request.setSortBy(new ArrayList<String>());
		request.setPostCampaign(pojo);
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/postCampaign/getPostFromCampaign";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		PostCampaignResponse response = mapFromJson(content,
				PostCampaignResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
	
	
}
