package com.treeseed.sprint3;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.junit.Assert;
import org.junit.Before;

import com.treeseed.testBase.AbstractTestController;
import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.services.UserGeneralServiceInterface;



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
			String edate= "2015-07-24T06:00:00.000Z";
			String sdate= "2015-07-25T06:00:00.000Z";
			String amount =  "800";
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/campaing/create";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .file("file", file.getBytes())
		                    .param("name", name) 
		                    .param("description", description)
		                    .param("date1", sdate)
		                    .param("date2", edate)
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
			String edate= "2015-07-24T06:00:00.000Z";
			String sdate= "2015-07-25T06:00:00.000Z";
			String amount =  "800";
			
	        String uri = "/rest/protected/campaing/create";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .param("name", name) 
		                    .param("description", description)
		                    .param("date1", sdate)
		                    .param("date2", edate)
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
			String edate= "2015-07-24T06:00:00.000Z";
			String sdate= "2015-07-25T06:00:00.000Z";
			String amount =  "800";
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/campaing/create";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .file(file)
		                    .param("name", name) 
		                    .param("description", description)
		                    .param("date1", sdate)
		                    .param("date2", edate)
		                    .param("amount", amount)
	        				.param("idNonprofit", "0"))
	        				.andReturn();
	        
	
	        String content = result.getResponse().getContentAsString();
	        
	        CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	        
	        Assert.assertEquals("NONPROFIT DO NOT EXIST", response.getCodeMessage());
	      

	    }
	    
	    @Test
	    public void getCampaignsByNonprofitWithCampaigns() throws Exception {
		 	
		   
		    CampaignRequest req = new CampaignRequest();
		    List<String> sort=new ArrayList<String>();
		    sort.add("StartDate");
			
			
			req.setPageNumber(1);
			req.setPageSize(10);
			req.setDirection("DES");
			req.setSortBy(sort);
			req.setSearchColumn("ALL");
			req.setNonprofitId(4);
			
			
	        String uri = "/rest/protected/campaing/nonprofitCampaigns";
	               
	        String jsonObject = mapToJson(req);
	        
	        MvcResult result =mvc.perform(MockMvcRequestBuilders.post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON).content(jsonObject)).andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	        
	        Assert.assertEquals("campaigns fetch success", response.getCodeMessage());
	      

	    }
	    
	    @Test
	    public void getCampaignsByNonprofitWithoutCampaigns() throws Exception {
		 	
		   
		    CampaignRequest req = new CampaignRequest();
		    List<String> sort=new ArrayList<String>();
		    sort.add("StartDate");
			
			
			req.setPageNumber(1);
			req.setPageSize(10);
			req.setDirection("DES");
			req.setSortBy(sort);
			req.setSearchColumn("ALL");
			req.setNonprofitId(0);
			
			
	        String uri = "/rest/protected/campaing/nonprofitCampaigns";
	               
	        String jsonObject = mapToJson(req);
	        
	        MvcResult result =mvc.perform(MockMvcRequestBuilders.post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON).content(jsonObject)).andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	        
	        Assert.assertEquals("campaigns fetch unsuccessful", response.getErrorMessage());
	      

	    }
}
