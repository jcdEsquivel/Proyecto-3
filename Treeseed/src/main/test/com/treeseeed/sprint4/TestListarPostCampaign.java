package com.treeseeed.sprint4;

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

// TODO: Auto-generated Javadoc
/**
 * The Class TestListarPostCampaign.
 */
public class TestListarPostCampaign extends AbstractTestController  {

	/**
	 * Sets the up.
	 *
	 * @param controller the new up
	 */
	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/* (non-Javadoc)
	 * @see com.treeseed.testBase.AbstractTestController#setUp()
	 */
	@Before
	public void setUp() {
		super.setUp();

	}

	/**
	 * Test get posts from campaign.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetPostsFromCampaign() throws Exception {

		Campaign campaign = createRandomCampaign().getWrapperObject();
		
		PostCampaignRequest request = new PostCampaignRequest();
		PostCampaignPOJO pojo = new PostCampaignPOJO();
		pojo.setCampaignId(campaign.getId());
		request.setPostNonprofit(pojo);
		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/postCampaign/getCampaignPost";

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
