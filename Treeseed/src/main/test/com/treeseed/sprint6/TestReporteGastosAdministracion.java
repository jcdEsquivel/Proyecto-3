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
import com.treeseed.controllers.NonprofitController;
import com.treeseed.testBase.AbstractTestController;

public class TestReporteGastosAdministracion extends AbstractTestController{
	
	
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
	public void testReporteGastosAdministrativos() throws Exception {
		
		//waiting on the create donation method
		//DonationWrapper donation = createRandomDonation();
		DonationRequest request = new DonationRequest();
		
		//request.setNonProfitId(reques.getWrapperObject().getId())

		//burn data while is ready
		request.setDonorId(1);
		request.setSortBy(new ArrayList());
		request.setPageNumber(1);
		request.setPageSize(5);
		request.setDirection("DESC");
		request.setSearchTerm("");
		request.setSearchColumn("ALL");
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donation/getDonationDonorReport";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject))
								.andReturn();

		String content = result.getResponse().getContentAsString();

		DonationResponse response = mapFromJson(content,
				DonationResponse.class);

		Assert.assertEquals("200", response.getCode().toString());
		Assert.assertTrue(response.getTotalElements()>0);
		Assert.assertEquals("Donations fetch success", response.getCodeMessage());

	}
	


}
