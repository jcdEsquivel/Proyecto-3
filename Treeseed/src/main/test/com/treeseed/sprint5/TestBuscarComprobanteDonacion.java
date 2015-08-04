package com.treeseed.sprint5;

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
import com.treeseed.contracts.TransparencyReportRequest;
import com.treeseed.contracts.TransparencyReportResponse;
import com.treeseed.controllers.DonationController;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.testBase.AbstractTestController;

public class TestBuscarComprobanteDonacion extends AbstractTestController  {

	protected void setUp(DonationController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Before
	public void setUp() {
		super.setUp();

	}
	
	/**
	 * Test gets donations of a donor succesufully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetDonationsOfDonor() throws Exception {

		//waiting on the create donation method
		//DonationWrapper donation = createRandomDonation();
		DonationRequest request = new DonationRequest();
		
		//request.setNonProfitId(reques.getWrapperObject().getId())

		//hardcoded data while is ready
		request.setDonorId(37);
		
		request.setSortBy(new ArrayList<String>());
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		request.setSearchTerm("");
		request.setSearchColumn("ALL");
		request.getSortBy().add("dateTime");
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donation/getDonationOfDonorPerMonth";

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
		Assert.assertTrue(response.getTotalElements()>0);
	}

}
