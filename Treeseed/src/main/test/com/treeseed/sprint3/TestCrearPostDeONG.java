package com.treeseed.sprint3;
import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.AbstractTestController;

public class TestCrearPostDeONG extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }



		
		@Test
		public void testCreatePostONG() throws Exception {

			NonprofitWrapper nonprofit = createRandomNonprofit();
			int userId = nonprofit.getUsergenerals().get(0).getId();
					
			PostNonprofitRequest request = new PostNonprofitRequest();
			PostNonprofitPOJO pojo = new PostNonprofitPOJO();
			pojo.setTitle("ONG Post");
			pojo.setDescription("Post description");
			pojo.setNonprofitId(nonprofit.getId());
			
			request.setPostNonprofit(pojo);

			String jsonObject = mapToJson(request);

			String uri = "/rest/protected/postNonprofit/register";

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

			PostNonprofitResponse response = mapFromJson(content,
					PostNonprofitResponse.class);

			Assert.assertEquals("Post created", response.getCodeMessage());


		}
		

		
		@Test
		public void testCreatePostONGWithoutFile() throws Exception {

			NonprofitWrapper nonprofit = createRandomNonprofit();
			int userId = nonprofit.getUsergenerals().get(0).getId();
					
			PostNonprofitRequest request = new PostNonprofitRequest();
			PostNonprofitPOJO pojo = new PostNonprofitPOJO();
			pojo.setTitle("ONG Post");
			pojo.setDescription("Post description");
			pojo.setNonprofitId(nonprofit.getId());
			
			request.setPostNonprofit(pojo);

			String jsonObject = mapToJson(request);

			String uri = "/rest/protected/postNonprofit/register";

			MockMultipartFile jsonFile = new MockMultipartFile("data", "", "application/json", jsonObject.getBytes());
		

			
			MvcResult result = mvc.perform(
			        		MockMvcRequestBuilders.fileUpload(uri)
			        				//.file(file)
				                    .file(jsonFile)
				                    .sessionAttr("idUser", userId))
			        				.andReturn();
				
			
			String content = result.getResponse().getContentAsString();

			PostNonprofitResponse response = mapFromJson(content,
					PostNonprofitResponse.class);

			Assert.assertEquals("Post created", response.getCodeMessage());


		}
		
		
		

		/**
		 * Test get campaign from nonprofit.
		 *
		 * @throws Exception the exception
		 */
		@Test
		public void testCreatePostONGInvalid() throws Exception {

			NonprofitWrapper nonprofit = createRandomNonprofit();
			int userId = -1;
					
			PostNonprofitRequest request = new PostNonprofitRequest();
			PostNonprofitPOJO pojo = new PostNonprofitPOJO();
			pojo.setTitle("ONG Post");
			pojo.setDescription("Post description");
			pojo.setNonprofitId(nonprofit.getId());
			
			request.setPostNonprofit(pojo);

			String jsonObject = mapToJson(request);

			String uri = "/rest/protected/postNonprofit/register";

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

			PostNonprofitResponse response = mapFromJson(content,
					PostNonprofitResponse.class);

			Assert.assertEquals("Invalid request", response.getCodeMessage());


		}
	  
	  
}
