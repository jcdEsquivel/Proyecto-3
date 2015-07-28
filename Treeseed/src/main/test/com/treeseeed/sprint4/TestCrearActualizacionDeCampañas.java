package com.treeseeed.sprint4;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostCampaignResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.AbstractTestController;

public class TestCrearActualizacionDeCampañas extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
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

			CampaignWrapper campaign = createRandomCampaign();
			int userId = campaign.getNonprofit().getUsergenerals().get(0).getId();
					
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
				                    .file(jsonFile)
				                    .sessionAttr("idUser", userId))
			        				.andReturn();
				
			
			String content = result.getResponse().getContentAsString();

			PostCampaignResponse response = mapFromJson(content,
					PostCampaignResponse.class);

			Assert.assertEquals("Post created", response.getCodeMessage());


		}
		
		
		
		/**
		 * Test get campaign from nonprofit.
		 *
		 * @throws Exception the exception
		 */
		@Test
		public void testCreatePostCampaignNullFile() throws Exception {

			CampaignWrapper campaign = createRandomCampaign();
			int userId = campaign.getNonprofit().getUsergenerals().get(0).getId();
					
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
			
		

			
			MvcResult result = mvc.perform(
			        		MockMvcRequestBuilders.fileUpload(uri)
				                    .file(jsonFile)
				                    .sessionAttr("idUser", userId))
			        				.andReturn();
				
			
			String content = result.getResponse().getContentAsString();

			PostCampaignResponse response = mapFromJson(content,
					PostCampaignResponse.class);

			Assert.assertEquals("Post created", response.getCodeMessage());


		}
		
		
		
		/**
		 * Test get campaign from nonprofit.
		 *
		 * @throws Exception the exception
		 */
		@Test
		public void testCreateInvalidPostCampaign() throws Exception {

			CampaignWrapper campaign = createRandomCampaign();
			int userId = -1;
					
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
				                    .file(jsonFile)
				                    .sessionAttr("idUser", userId))
			        				.andReturn();
				
			
			String content = result.getResponse().getContentAsString();

			PostCampaignResponse response = mapFromJson(content,
					PostCampaignResponse.class);

			Assert.assertEquals("Invalid request", response.getCodeMessage());


		}
	  
	  
}
