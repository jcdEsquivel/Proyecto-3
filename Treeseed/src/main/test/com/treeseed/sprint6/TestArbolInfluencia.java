package com.treeseed.sprint6;

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
import com.treeseed.controllers.NonprofitController;

import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestArbolInfluencia.
 */
public class TestArbolInfluencia extends AbstractTestController  {

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
	 * Test get tree succes sful.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTreeSuccesSful() throws Exception {

		DonorWrapper donor = createRandomDonor();
		
		
		DonorRequest request = new DonorRequest();
		request.setId(donor.getId());	
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/getTree";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content,
				DonorResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
	/**
	 * Test get tree no donor.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTreeNoDonor() throws Exception {
		
		DonorRequest request = new DonorRequest();

		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/getTree";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content,
				DonorResponse.class);

		Assert.assertEquals("400", response.getCode().toString());


	}
	
	/**
	 * Test get tree donation succesful.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTreeDonationSuccesful() throws Exception {
		
		DonorWrapper donor = createRandomDonor();
		
		
		DonationRequest request = new DonationRequest();
		request.setDonorId(donor.getId());	
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donation/gettreedonation";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		DonationResponse response = mapFromJson(content,
				DonationResponse.class);

		Assert.assertEquals("200", response.getCode().toString());


	}
	
	/**
	 * Test get tree donation no donor.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTreeDonationNoDonor() throws Exception {
		
		
		
		DonationRequest request = new DonationRequest();
	
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donation/gettreedonation";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		DonationResponse response = mapFromJson(content,
				DonationResponse.class);

		Assert.assertEquals("400", response.getCode().toString());


	}
	
}
