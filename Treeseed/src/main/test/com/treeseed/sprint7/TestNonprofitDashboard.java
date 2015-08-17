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
public class TestNonprofitDashboard extends AbstractTestController  {

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
	public void testGetDashboardSuccesSful() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		
		
		NonprofitRequest request = new NonprofitRequest();
		
		request.setId(99999);	
		request.setIdUser(nonprofit.getId());
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/nonprofit/getdashboard";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		NonprofitResponse response = mapFromJson(content,
				NonprofitResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
	@Test
	public void testGetDashboardWithoutNonprofit() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		
		
		NonprofitRequest request = new NonprofitRequest();
		
		request.setId(99999);	
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/nonprofit/getdashboard";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		NonprofitResponse response = mapFromJson(content,
				NonprofitResponse.class);

		Assert.assertEquals("400", response.getCode().toString());


	}
	
	@Test
	public void testGetDashboardWithoutSession() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		
		
		NonprofitRequest request = new NonprofitRequest();
		
		request.setId(0);	
		request.setIdUser(nonprofit.getId());
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/nonprofit/getdashboard";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)
								.sessionAttr("idUser", 99999)).andReturn();

		String content = result.getResponse().getContentAsString();

		NonprofitResponse response = mapFromJson(content,
				NonprofitResponse.class);

		Assert.assertEquals("400", response.getCode().toString());


	}
	
}
