package com.treeseed.sprint1;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.treeseed.testBase.AbstractTestController;

import com.mongodb.util.JSON;
import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.UsersController;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.services.UserGeneralServiceInterface;



@Transactional
public class TestsUsersController extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(UsersController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	     
	    }
	 
	  
	  
	  
	  
	   @Test
	    public void testEmailIsUnique() throws Exception {

		   UserGeneralWrapper userGeneral = createRandomUserGeneral();
		   
	        String uri = "/rest/protected/users/isEmailUnique";
	        
	       String email = "test-"+userGeneral.getEmail();
	       
	        MvcResult result = mvc.perform(
	                MockMvcRequestBuilders.post(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(email))
	                .andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        BaseResponse response = mapFromJson(content, BaseResponse.class);
	        
	        Assert.assertEquals(response.getCodeMessage(), "UNIQUE");
	      

	    }
	   
	   
	   @Test
	    public void testEmailIsNotUnique() throws Exception {

		   UserGeneralWrapper userGeneral = createRandomUserGeneral();
		   
	        String uri = "/rest/protected/users/isEmailUnique";
	        
	       String email = userGeneral.getEmail();
	       
	        MvcResult result = mvc.perform(
	                MockMvcRequestBuilders.post(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(email))
	                .andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        BaseResponse response = mapFromJson(content, BaseResponse.class);
	        
	        Assert.assertEquals(response.getCodeMessage(), "UNIQUE");
	      
	    }
	   
	  
	  
	    @Test
	    public void testCreateNGO() throws Exception {

		   CatalogWrapper countryCat = createRandomCatalog();
		   CatalogWrapper causeCat = createRandomCatalog();
		   
		   String email = getRandomString()+"@gmail.com";
		   String password =getRandomString();
			String name= getRandomString();
			String cause =  getRandomString();
			String country = getRandomString();
		   
	        String uri = "/rest/protected/users/registerNonProfit";
	               
	       /* MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.post(uri)
	        				.param("name", name) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country)
		                    .param("cause", cause))
	                        .accept(MediaType.APPLICATION_JSON))
	                        .andReturn();
	        */

	        String content = result.getResponse().getContentAsString();
	        
	        NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	        
	        Assert.assertEquals(response.getCodeMessage(), "UNIQUE");
	      

	    }
	
}
