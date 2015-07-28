package com.treeseeed.sprint4;

import java.io.FileInputStream;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostCampaignResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestCreatePostCampaign extends AbstractTestController{

	MockMvc mockMvc;

	/**
	 * Sets the up.
	 *
	 * @param controller the new up
	 */
	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/* (non-Javadoc)
	 * @see com.treeseed.testBase.AbstractTestController#setUp()
	 */
	@Before
	public void setUp() {
		super.setUp();

	}
	
	

	/**
	 * Test get campaign from nonprofit.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCreatePostCampaign() throws Exception {

		//NonprofitWrapper wrapper =  createRandomNonprofit();
		CampaignWrapper campaign = createRandomCampaign();
		
				
		PostCampaignRequest request = new PostCampaignRequest();
		PostCampaignPOJO pojo = new PostCampaignPOJO();
		pojo.setTitle("Campaign Post");
		pojo.setDescription("Post description");
		pojo.setCampaignId(campaign.getId());
		
		request.setPostCampaign(pojo);
		request.setNonprofitId(campaign.getNonprofit().getId());

		pojo.setId(campaign.getId());
		

		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/postCampaign/register";

		MockMultipartFile jsonFile = new MockMultipartFile("data", "", "application/json", jsonObject.getBytes());
		
		FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
		MockMultipartFile file = new MockMultipartFile("file", "1436073230483", "multipart/form-data", inputFile);
		

		
		MvcResult result = mvc.perform(
		        		MockMvcRequestBuilders.fileUpload(uri)
		        				.file(file)
			                    .file(jsonFile))
		        				.andReturn();
			
		
		String content = result.getResponse().getContentAsString();

		PostCampaignResponse response = mapFromJson(content,
				PostCampaignResponse.class);

		Assert.assertEquals("200", response.getCode());


	}
	
}
