package com.treeseed.sprint6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.RecurrableInformationRequest;
import com.treeseed.contracts.RecurrableInformationResponse;
import com.treeseed.controllers.RecurrableInformationController;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.testBase.AbstractTestController;

public class TestVisualizarPortafolioDonante extends AbstractTestController  {

	protected void setUp(RecurrableInformationController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Before
	public void setUp() {
		super.setUp();

	}

	@Test
	public void testGetPostsFromCampaign() throws Exception {

		RecurrableInformationRequest request = new RecurrableInformationRequest();
		RecurrableInformationResponse response = new RecurrableInformationResponse();
		DonorWrapper donor = createRandomDonor();
		
		request.setDonorId(donor.getId());
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/recurrableInformation/getRecurrableInformation";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		response = mapFromJson(content,
				RecurrableInformationResponse.class);

		Assert.assertEquals("200", response.getCode().toString());
	}	
}

