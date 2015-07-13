package com.treeseed.sprint1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import com.treeseed.contracts.GeneralSearchRequest;
import com.treeseed.contracts.GeneralSearchResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.pojo.GeneralSearchResultPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestGeneralSearch  extends AbstractTestController{


	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }
	  
	  
	  
	  @Test
	    public void testSearchFilterNullFilters() throws Exception {
	   
		   String filter = "";
		   String country = "";
		
		   GeneralSearchRequest request = new GeneralSearchRequest();
		   request.setCountry(country);
		   request.setFilter(filter);
		   String jsonObject = mapToJson(request);
			
	        String uri = "/rest/protected/generalSearch/search";
	               
	      
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.post(uri)
		                    .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(jsonObject))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        GeneralSearchResponse response = mapFromJson(content, GeneralSearchResponse.class);
	        
	       
	        
	        Assert.assertEquals("200", response.getCode().toString());
  	Assert.assertEquals(false, response.getResults().isEmpty());
	        	
  	
	       GeneralSearchResultPOJO pojo = response.getResults().get(0);

	        
	        Assert.assertEquals("campaign 6", pojo.getName());
	      

	    }
	  
	  
	
	  
	  @Test
	    public void testSearchFilterNameCountry() throws Exception {
	   
		   String filter = "1";
		   String country = "CR";
		
			
		   GeneralSearchRequest request = new GeneralSearchRequest();
		   request.setCountry(country);
		   request.setFilter(filter);
		   String jsonObject = mapToJson(request);
			
	        String uri = "/rest/protected/generalSearch/search";
	               
	      
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.post(uri)
		                    .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(jsonObject))
	        				.andReturn();

	        String content = result.getResponse().getContentAsString();
	        
	        GeneralSearchResponse response = mapFromJson(content, GeneralSearchResponse.class);
	        
	       
	        
	        Assert.assertEquals("200", response.getCode().toString());
      	Assert.assertEquals(false, response.getResults().isEmpty());
	        	
      	
	       GeneralSearchResultPOJO pojo = response.getResults().get(response.getResults().size()-1);

	        
	        Assert.assertEquals("donor 3 don1", pojo.getName());
	      

	    }
	  
	  
		@Test
	    public void testSearchFilterName() throws Exception {
	   
		   String filter = "1";
		   String country = "";
		
		   GeneralSearchRequest request = new GeneralSearchRequest();
		   request.setCountry(country);
		   request.setFilter(filter);
		   String jsonObject = mapToJson(request);
			
	        String uri = "/rest/protected/generalSearch/search";
	               
	      
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.post(uri)
		                    .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(jsonObject))
	        				.andReturn();

	        String content = result.getResponse().getContentAsString();
	        
	        GeneralSearchResponse response = mapFromJson(content, GeneralSearchResponse.class);
	        
	       
	        
	        Assert.assertEquals("200", response.getCode().toString());
        	Assert.assertEquals(false, response.getResults().isEmpty());
	        	
        	
	       GeneralSearchResultPOJO pojo = response.getResults().get(0);

	        
	        Assert.assertEquals("campaign 1 ", pojo.getName());
	      

	    }
	  
	  
	  	@Test
	    public void testSearchFilterCountry() throws Exception {
	   
		   String filter = "";
		   String country = "CR";
		
			
		   GeneralSearchRequest request = new GeneralSearchRequest();
		   request.setCountry(country);
		   request.setFilter(filter);
		   String jsonObject = mapToJson(request);
			
	        String uri = "/rest/protected/generalSearch/search";
	               
	      
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.post(uri)
		                    .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(jsonObject))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        GeneralSearchResponse response = mapFromJson(content, GeneralSearchResponse.class);
	        
	       
	        
	        Assert.assertEquals("200", response.getCode().toString());
        	Assert.assertEquals(false, response.getResults().isEmpty());
	        	
        	
	       GeneralSearchResultPOJO pojo = response.getResults().get(0);

	        
	        Assert.assertEquals("campaign 6", pojo.getName());
	      

	    }
	
}
