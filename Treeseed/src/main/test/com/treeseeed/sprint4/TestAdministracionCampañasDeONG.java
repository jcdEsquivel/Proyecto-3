package com.treeseeed.sprint4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestBuscarCampañasDeONG.
 */
public class TestAdministracionCampañasDeONG  extends AbstractTestController {

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
	 * Test search campaigns for nonprofit no filters.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSearchCampaignsForNonprofitNoFilters() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		
		
		CampaignRequest request = new CampaignRequest();
		request.setNonprofitId(nonprofit.getId());

		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/searchCampaignsForNonprofit";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals(5, response.getCampaigns().size());


	}
	
	
	
	/**
	 * Test search campaigns for nonprofit by campaign name.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSearchCampaignsForNonprofitByCampaignName() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		CampaignWrapper capaign = createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		
		
		CampaignRequest request = new CampaignRequest();
		request.setNonprofitId(nonprofit.getId());

		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		request.setName(capaign.getName());
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/searchCampaignsForNonprofit";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals(1, response.getCampaigns().size());


	}
	
	

	/**
	 * Test search campaigns for nonprofit by dates.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSearchCampaignsForNonprofitByDates() throws Exception {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, -5); // Adding 5 days
		Date startDate = c.getTime();
		
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 25); // Adding 5 days
		Date endDate = c.getTime();
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		createRandomCampaign(nonprofit);
		
		
		CampaignRequest request = new CampaignRequest();
		request.setNonprofitId(nonprofit.getId());

		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/searchCampaignsForNonprofit";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals(5, response.getCampaigns().size());


	}
	
	

	/**
	 * Test search campaigns for nonprofit by state soon.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSearchCampaignsForNonprofitByStateSoon() throws Exception {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 1); // Adding 5 days
		Date startDate = c.getTime();
		
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 25); // Adding 5 days
		Date endDate = c.getTime();
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		createRandomCampaign(nonprofit, startDate, endDate);
		createRandomCampaign(nonprofit, startDate, endDate);
		createRandomCampaign(nonprofit, startDate, endDate);
		
		
		CampaignRequest request = new CampaignRequest();
		request.setNonprofitId(nonprofit.getId());

		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		request.setState("soon");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/searchCampaignsForNonprofit";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals(3, response.getCampaigns().size());


	}
	
	
	/**
	 * Test search campaigns for nonprofit by state active.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSearchCampaignsForNonprofitByStateActive() throws Exception {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, -10); // Adding 5 days
		Date startDate = c.getTime();
		
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 25); // Adding 5 days
		Date endDate = c.getTime();
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		createRandomCampaign(nonprofit, startDate, endDate);
		createRandomCampaign(nonprofit, startDate, endDate);
		createRandomCampaign(nonprofit, startDate, endDate);
		
		
		CampaignRequest request = new CampaignRequest();
		request.setNonprofitId(nonprofit.getId());

		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		request.setState("active");
		
		
		String jsonObject = mapToJson(request);
												
		String uri = "/rest/protected/campaing/searchCampaignsForNonprofit";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals(3, response.getCampaigns().size());
	}
	
	
	
	/**
	 * Test search campaigns for nonprofit by state finished.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSearchCampaignsForNonprofitByStateFinished() throws Exception {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, -20); // Adding 5 days
		Date startDate = c.getTime();
		
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, -10); // Adding 5 days
		Date endDate = c.getTime();
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		createRandomCampaign(nonprofit, startDate, endDate);
		createRandomCampaign(nonprofit, startDate, endDate);
		createRandomCampaign(nonprofit, startDate, endDate);
		
		
		CampaignRequest request = new CampaignRequest();
		request.setNonprofitId(nonprofit.getId());

		request.setSortBy(new ArrayList());
		request.getSortBy().add("creationDate");
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		request.setState("finished");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/searchCampaignsForNonprofit";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals(3, response.getCampaigns().size());


	}
	
	
}
