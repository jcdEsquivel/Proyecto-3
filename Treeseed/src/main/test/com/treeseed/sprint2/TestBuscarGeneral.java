package com.treeseed.sprint2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.GeneralSearchRequest;
import com.treeseed.contracts.GeneralSearchResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.GeneralSearchResultPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestBuscarGeneral extends AbstractTestController {

	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testSearchFilterNullFilters() throws Exception {

		createRandomNonprofit();
		createRandomNonprofit();
		createRandomNonprofit();
		createRandomNonprofit();
		createRandomDonor();
		createRandomDonor();
		createRandomDonor();
		createRandomDonor();
		
		String filter = "";
		String country = "";

		GeneralSearchRequest request = new GeneralSearchRequest();
		request.setCountry(country);
		request.setFilter(filter);
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/generalSearch/search";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		GeneralSearchResponse response = mapFromJson(content,
				GeneralSearchResponse.class);
		
		int count = response.getResults().size();

		Assert.assertEquals("200", response.getCode().toString());
		Assert.assertEquals(9, count);

	}


	@Test
	public void testSearchFilterWithNameFilter() throws Exception {
		String random = getRandomString();
		NonprofitWrapper wrapper = createRandomNonprofit(random);

		String filter = wrapper.getName();
		String country = "";

		GeneralSearchRequest request = new GeneralSearchRequest();
		request.setCountry(country);
		request.setFilter(filter);
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/generalSearch/search";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		GeneralSearchResponse response = mapFromJson(content,
				GeneralSearchResponse.class);
		
		int count = response.getResults().size();

		Assert.assertEquals("200", response.getCode().toString());
		Assert.assertEquals(1, count);

	

	}
	
	

	@Test
	public void testSearchFilterWithCountry() throws Exception {

		String random = getRandomString();
		NonprofitWrapper wrapper = createRandomNonprofit(random);

		String filter = "";
		String country = wrapper.getConutry().getDescription();

		GeneralSearchRequest request = new GeneralSearchRequest();
		request.setCountry(country);
		request.setFilter(filter);
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/generalSearch/search";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		GeneralSearchResponse response = mapFromJson(content,
				GeneralSearchResponse.class);
		
		int count = response.getResults().size();

		Assert.assertEquals("200", response.getCode().toString());
		Assert.assertNotEquals(0, count);

	

	}
	
	
	@Test
	public void testSearchFilterWithAllFilters() throws Exception {

		String random = getRandomString();
		NonprofitWrapper wrapper = createRandomNonprofit(random);

		String filter =  wrapper.getName();
		String country = wrapper.getConutry().getDescription();

		GeneralSearchRequest request = new GeneralSearchRequest();
		request.setCountry(country);
		request.setFilter(filter);
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/generalSearch/search";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		GeneralSearchResponse response = mapFromJson(content,
				GeneralSearchResponse.class);
		
		int count = response.getResults().size();

		Assert.assertEquals("200", response.getCode().toString());
		Assert.assertEquals(1, count);

	

	}
	
	
}
