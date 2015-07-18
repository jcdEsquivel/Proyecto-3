package com.treeseed.sprint3;

import java.io.FileInputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.hibernate.jpa.criteria.compile.CriteriaQueryTypeQueryAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.treeseed.testBase.AbstractTestController;
import com.mongodb.util.JSON;
import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.*;



@Transactional
public class TestsCampaignController extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }
	 
	   
	  ////////////////////////////////////////////////////////////////////////////////////
	    @Test
	    public void testCreateCampaignSuccessfully() throws Exception {

	    	NonprofitWrapper nonprofit = createRandomNonprofit();
		   
		   
		   	String name = "pruebaCrearCampaña";
		   	String description =getRandomString();
			String date= "2015-07-24T06:00:00.000Z";
			String amount =  "800";
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/campaing/create";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .file("file", file.getBytes())
		                    .param("name", name) 
		                    .param("description", description)
		                    .param("date", date)
		                    .param("amount", amount)
	        				.param("idNonprofit", ""+nonprofit.getId()))
	        				.andReturn();
	        
	
	        String content = result.getResponse().getContentAsString();
	        
	        CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	        
	        Assert.assertEquals("campaign created successfully", response.getCodeMessage());
	      

	    }
	    
	    @Test
	    public void testCreateCampaignWithoutImage() throws Exception {

	    	NonprofitWrapper nonprofit = createRandomNonprofit();
		   
		   
		   	String name = "pruebaCrearCampaña";
		   	String description =getRandomString();
			String date= "2015-07-24T06:00:00.000Z";
			String amount =  "800";
			
	        String uri = "/rest/protected/campaing/create";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .param("name", name) 
		                    .param("description", description)
		                    .param("date", date)
		                    .param("amount", amount)
	        				.param("idNonprofit", ""+nonprofit.getId()))
	        				.andReturn();
	        
	
	        String content = result.getResponse().getContentAsString();
	        
	        CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	        
	        Assert.assertEquals("NO IMAGE SPECIFIED", response.getCodeMessage());
	      

	    }
	    
	    @Test
	    public void testCreateCampaignWithoutNonprofit() throws Exception {
		   
		   
		   	String name = "pruebaCrearCampaña";
		   	String description =getRandomString();
			String date= "2015-07-24T06:00:00.000Z";
			String amount =  "800";
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/campaing/create";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .file(file)
		                    .param("name", name) 
		                    .param("description", description)
		                    .param("date", date)
		                    .param("amount", amount)
	        				.param("idNonprofit", "0"))
	        				.andReturn();
	        
	
	        String content = result.getResponse().getContentAsString();
	        
	        CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	        
	        Assert.assertEquals("NONPROFIT DO NOT EXIST", response.getCodeMessage());
	      

	    }
}
