package com.treeseed.sprint7;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.DonationRequest;
import com.treeseed.contracts.DonationResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;

import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestArbolInfluencia.
 */
public class TestDonorDashboard extends AbstractTestController  {

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

	

	@Test
	public void testGetDonorDashboardSuccesSful() throws Exception {

		DonorWrapper donor = createRandomDonor();
		
		
		DonorRequest request = new DonorRequest();
		
		request.setId(99999);	
		request.setIdUser(donor.getId());
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/getdashboard";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content,
				DonorResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
	@Test
	public void testGetDonorDashboardWithoutNonprofit() throws Exception {
		
		
		DonorRequest request = new DonorRequest();
		
		request.setId(99999);	
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/getdashboard";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content,
				DonorResponse.class);

		Assert.assertEquals("400", response.getCode().toString());


	}
	
	@Test
	public void testGetDonorDashboardWithoutSession() throws Exception {

		DonorWrapper donor = createRandomDonor();
		
		
		DonorRequest request = new DonorRequest();
		
		request.setId(0);	
		request.setIdUser(donor.getId());
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/getdashboard";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content,
				DonorResponse.class);

		Assert.assertEquals("400", response.getCode().toString());


	}
	
}
