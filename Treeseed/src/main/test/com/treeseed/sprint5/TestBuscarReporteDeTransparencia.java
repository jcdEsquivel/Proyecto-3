package com.treeseed.sprint5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.TransparencyReportRequest;
import com.treeseed.contracts.TransparencyReportResponse;
import com.treeseed.controllers.TransparencyReportController;
import com.treeseed.ejb.TransparencyReport;
import com.treeseed.testBase.AbstractTestController;

public class TestBuscarReporteDeTransparencia extends AbstractTestController  {

	protected void setUp(TransparencyReportController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Before
	public void setUp() {
		super.setUp();

	}

	@Test
	public void testGetTransparencyReports() throws Exception {

		TransparencyReport transparencyReport = createRandomTransparencyReport().getWrapperObject();
		
		TransparencyReportRequest request = new TransparencyReportRequest();
		request.setSortBy(new ArrayList<String>());
		request.setNonProfitId(transparencyReport.getNonprofit().getId());
		request.setPageNumber(1);
		request.setPageSize(10);
		request.setDirection("DESC");
		request.getSortBy().add("dateTime");
		
		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/transparencyReport/getTransparencyReports";

		MvcResult result = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn();

		String content = result.getResponse().getContentAsString();

		TransparencyReportResponse response = mapFromJson(content,
				TransparencyReportResponse.class);

		Assert.assertEquals("200", response.getCode().toString());

	}

}
