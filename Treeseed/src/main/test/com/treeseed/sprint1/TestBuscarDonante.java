package com.treeseed.sprint1;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.testBase.AbstractTestController;

public class TestBuscarDonante extends AbstractTestController  {

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
	 * Test get posts from nonprofit.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testBuscarONG() throws Exception {

		createRandomDonor();

		createRandomDonor();

		createRandomDonor();

		createRandomDonor();

		DonorRequest request = new DonorRequest();
		request.setName(null);;
		request.setSortBy(new ArrayList());
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		request.setSearchTerm("");
		request.setSearchColumn("ALL");
		
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/advanceGet";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject))
								.andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content,
				DonorResponse.class);

		Assert.assertEquals("200", response.getCode().toString());
		Assert.assertTrue(response.getTotalElements()>0);
		Assert.assertEquals("donors fetch success", response.getCodeMessage());

	}
	
	

}
