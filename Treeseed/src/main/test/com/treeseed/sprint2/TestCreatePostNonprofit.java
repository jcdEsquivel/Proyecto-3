package com.treeseed.sprint2;

import java.io.FileInputStream;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.treeseed.contracts.LoginRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.testBase.AbstractTestController;

public class TestCreatePostNonprofit extends AbstractTestController {

	 @Autowired WebApplicationContext wac; 
	
	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	HttpSession session;
	NonprofitWrapper nonprofit;

	@Before
	public void setUp() {
		try {
			super.setUp();
				
			logIn();
		} catch (Exception e) {
			
		}

	}

	private void logIn() throws Exception {

		 nonprofit = createRandomNonprofit();
		String email = nonprofit.getUsergenerals().get(0).getEmail();

		LoginRequest req = new LoginRequest(email, "123456789");
		String jsonObject = mapToJson(req);

		String uri = "/rest/login/checkuser";

		session = mvc
				.perform(
						MockMvcRequestBuilders.post(uri)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(jsonObject)).andReturn().getRequest()
				.getSession();

	}

	@Test
	public void testCrearPostNonprofit() throws Exception {

		String title = "Post 1";
		String description = "This is a test description";
		String idNonprofit = nonprofit.getId() + "";

		FileInputStream inputFile = new FileInputStream(
				"src/main/webapp/resources/file-storage/1436073230483.jpg");
		MockMultipartFile file = new MockMultipartFile("testImage",
				"1436073230483", "multipart/form-data", inputFile);

		String uri = "/rest/protected/postNonprofit/register";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.fileUpload(uri)
						
				.file(file).session((MockHttpSession) session).param("title", title)
						.param("description", description)
						.param("idNonprofit", "")
						).andReturn();

		String content = result.getResponse().getContentAsString();

		NonprofitResponse response = mapFromJson(content,
				NonprofitResponse.class);

		Assert.assertEquals("Post created", response.getCodeMessage());

	}

}
