package com.treeseed.sprint4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestVisualizarCampania extends AbstractTestController  {
	
	

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
	 * Test get campaign from nonprofit.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetCampaignFromNonprofit() throws Exception {

		CampaignWrapper campaign = createRandomCampaign();
		
		
		CampaignRequest request = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();

		pojo.setId(campaign.getId());
		request.setCampaign(pojo);
		request.setIdUser(0);
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/getCampignProfile";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("campaign search success", response.getCodeMessage());


	}
	
	
	/**
	 * Test get campaign without id from nonprofit.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetCampaignWithoutIDFromNonprofit() throws Exception {

		
		
		CampaignRequest request = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();

		pojo.setId(0);
		request.setCampaign(pojo);
		request.setIdUser(0);
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/getCampignProfile";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("campaign do not received", response.getErrorMessage());


	}
	
	/**
	 * Test get campaign with wrong id from nonprofit.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetCampaignWithWrongIdFromNonprofit() throws Exception {

		
		
		CampaignRequest request = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();

		pojo.setId(99999999);
		request.setCampaign(pojo);
		request.setIdUser(0);
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/getCampignProfile";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("campaign search unsuccessful", response.getErrorMessage());


	}
}
