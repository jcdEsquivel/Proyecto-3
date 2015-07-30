package com.treeseed.sprint5;

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
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostCampaignResponse;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestEditPostNonprofit.
 */
public class TestEditarActualizacionCampaign extends AbstractTestController {

	 /** The wac. */
 	@Autowired WebApplicationContext wac; 
	
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
	 * Test edit post campaign.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEditPostCampaign() throws Exception {
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		CampaignWrapper campaign =  createRandomCampaign(nonprofit);
		PostCampaignWrapper postcampaign = createRandomPostCampaign(campaign.getWrapperObject());
		
		String title = "Post Change";
		String description= "Description Change";
		
		PostCampaignRequest request = new PostCampaignRequest();

		PostCampaignPOJO pojo = new PostCampaignPOJO();
		
		pojo.setTitle(title);
		pojo.setDescription(description);
		pojo.setId(postcampaign.getId());
		pojo.setPicture(postcampaign.getPicture());
		pojo.setCampaignId(campaign.getId());
		
		request.setPostCampaign(pojo);
		request.setNonprofitId(nonprofit.getId());
		
		String jsonObject = mapToJson(request);
		
		String uri = "/rest/protected/postCampaign/editPostCampaign";
		
		MockMultipartFile jsonFile = new MockMultipartFile("data", "", "application/json", jsonObject.getBytes());
		
		FileInputStream inputFile = new FileInputStream(
				"src/main/webapp/resources/file-storage/1436073230483.jpg");
		MockMultipartFile file = new MockMultipartFile("testImage",
				"1436073230483", "multipart/form-data", inputFile);

		
		MvcResult result = mvc.perform(
        		MockMvcRequestBuilders.fileUpload(uri)
        				.file(file)
	                    .file(jsonFile)
	                    .sessionAttr("idUser", nonprofit.getUsergenerals().get(0).getId()))
        				.andReturn();

		String content = result.getResponse().getContentAsString();

		PostCampaignResponse response = mapFromJson(content,
				PostCampaignResponse.class);

		Assert.assertEquals("Post of Campaign updated sucessfully", response.getCodeMessage());

	}
	

	/**
	 * Test edit post campaign without user id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEditPostCampaignWithoutUserId() throws Exception {
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		CampaignWrapper campaign =  createRandomCampaign(nonprofit);
		PostCampaignWrapper postcampaign = createRandomPostCampaign(campaign.getWrapperObject());
		
		String title = "Post Change";
		String description= "Description Change";
		
		PostCampaignRequest request = new PostCampaignRequest();

		PostCampaignPOJO pojo = new PostCampaignPOJO();
		
		pojo.setTitle(title);
		pojo.setDescription(description);
		pojo.setId(postcampaign.getId());
		pojo.setPicture(postcampaign.getPicture());
		pojo.setCampaignId(campaign.getId());
		
		request.setPostCampaign(pojo);
		request.setNonprofitId(nonprofit.getId());
		
		String jsonObject = mapToJson(request);
		
		String uri = "/rest/protected/postCampaign/editPostCampaign";
		
		MockMultipartFile jsonFile = new MockMultipartFile("data", "", "application/json", jsonObject.getBytes());
		
		FileInputStream inputFile = new FileInputStream(
				"src/main/webapp/resources/file-storage/1436073230483.jpg");
		MockMultipartFile file = new MockMultipartFile("testImage",
				"1436073230483", "multipart/form-data", inputFile);

		
		MvcResult result = mvc.perform(
        		MockMvcRequestBuilders.fileUpload(uri)
        				.file(file)
	                    .file(jsonFile)
	                    .sessionAttr("idUser", 0))
        				.andReturn();

		String content = result.getResponse().getContentAsString();

		PostCampaignResponse response = mapFromJson(content,
				PostCampaignResponse.class);

		Assert.assertNotEquals("Post of Campaign updated sucessfully", response.getCodeMessage());
		Assert.assertEquals("Invalid request", response.getCodeMessage());

	}
	
	/**
	 * Test edit post campaign with wrong user id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEditPostCampaignWithWrongUserId() throws Exception {
		
		NonprofitWrapper nonprofit = createRandomNonprofit();
		CampaignWrapper campaign =  createRandomCampaign(nonprofit);
		PostCampaignWrapper postcampaign = createRandomPostCampaign(campaign.getWrapperObject());
		
		String title = "Post Change";
		String description= "Description Change";
		
		PostCampaignRequest request = new PostCampaignRequest();

		PostCampaignPOJO pojo = new PostCampaignPOJO();
		
		pojo.setTitle(title);
		pojo.setDescription(description);
		pojo.setId(postcampaign.getId());
		pojo.setPicture(postcampaign.getPicture());
		pojo.setCampaignId(campaign.getId());
		
		request.setPostCampaign(pojo);
		request.setNonprofitId(nonprofit.getId());
		
		String jsonObject = mapToJson(request);
		
		String uri = "/rest/protected/postCampaign/editPostCampaign";
		
		MockMultipartFile jsonFile = new MockMultipartFile("data", "", "application/json", jsonObject.getBytes());
		
		FileInputStream inputFile = new FileInputStream(
				"src/main/webapp/resources/file-storage/1436073230483.jpg");
		MockMultipartFile file = new MockMultipartFile("testImage",
				"1436073230483", "multipart/form-data", inputFile);

		
		MvcResult result = mvc.perform(
        		MockMvcRequestBuilders.fileUpload(uri)
        				.file(file)
	                    .file(jsonFile)
	                    .sessionAttr("idUser", 0))
        				.andReturn();

		String content = result.getResponse().getContentAsString();

		PostCampaignResponse response = mapFromJson(content,
				PostCampaignResponse.class);

		Assert.assertNotEquals("Post of Campaign updated sucessfully", response.getCodeMessage());
		Assert.assertEquals("Invalid request", response.getCodeMessage());
		
	}

}
