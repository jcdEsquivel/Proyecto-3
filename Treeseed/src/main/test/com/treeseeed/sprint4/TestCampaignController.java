package com.treeseeed.sprint4;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestCampaignController.
 */
public class TestCampaignController extends AbstractTestController  {
	
	

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
	
	/**
	 * Test finish campaign successfully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFinishCampaignSuccessfully() throws Exception {
		
		

		CampaignWrapper campaign = createRandomCampaign();
		
		CampaignRequest requestCampaign = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();
		
		 

		pojo.setId(campaign.getId());
		requestCampaign.setCampaign(pojo);
		requestCampaign.setIdUser(99999);
		
		
		String jsonObject = mapToJson(requestCampaign);

		String uri = "/rest/protected/campaing/close";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("campaign closed", response.getCodeMessage());


	}
	
	/**
	 * Test finish campaign with wrong id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFinishCampaignWithWrongId() throws Exception {
		
		

		CampaignWrapper campaign = createRandomCampaign();
		
		CampaignRequest requestCampaign = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();
		
		 

		pojo.setId(999999);
		requestCampaign.setCampaign(pojo);
		requestCampaign.setIdUser(99999);
		
		
		String jsonObject = mapToJson(requestCampaign);

		String uri = "/rest/protected/campaing/close";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("campaign do not found", response.getErrorMessage());


	}
	
	/**
	 * Test finish campaign without id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFinishCampaignWithoutId() throws Exception {
		
		

		CampaignWrapper campaign = createRandomCampaign();
		
		CampaignRequest requestCampaign = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();
		
		 

		pojo.setId(0);
		requestCampaign.setCampaign(pojo);
		requestCampaign.setIdUser(99999);
		
		
		String jsonObject = mapToJson(requestCampaign);

		String uri = "/rest/protected/campaing/close";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("campaign do not received", response.getErrorMessage());


	}
	
	/**
	 * Test finish campaign with no owner.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFinishCampaignWithNoOwner() throws Exception {
		
		

		CampaignWrapper campaign = createRandomCampaign();
		
		CampaignRequest requestCampaign = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();
		
		 

		pojo.setId(campaign.getId());
		requestCampaign.setCampaign(pojo);
		requestCampaign.setIdUser(99998);
		
		
		String jsonObject = mapToJson(requestCampaign);

		String uri = "/rest/protected/campaing/close";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("No owner", response.getErrorMessage());


	}
	
}
