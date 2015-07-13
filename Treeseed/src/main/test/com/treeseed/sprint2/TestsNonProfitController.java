package com.treeseed.sprint2;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.treeseed.testBase.AbstractTestController;

import com.mongodb.util.JSON;
import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.*;



@Transactional
public class TestsNonProfitController extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }
	 
	  
	  
	  
	  /*
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
	   
	  */
	  
	    @Test
	    public void testCreateNGO() throws Exception {

		   CatalogWrapper countryCat = createRandomCatalog();
		   CatalogWrapper causeCat = createRandomCatalog();
		   String idCatalog = "1";
		   
		   
		   String email = "pruebaderegistrarong@gmail.com";
		   String password =getRandomString();
			String name= getRandomString();
			String cause =  idCatalog;
			String country = idCatalog;
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/users/registerNonProfit";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .file(file)
		                    .param("name", name) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country)
		                    .param("cause", cause))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	        
	        Assert.assertEquals("user created succesfully", response.getCodeMessage());
	      

	    }
	
	    
	    @Test
	    public void testCreateNGOWithSameEmail() throws Exception {


		   String idCatalog = "1";
		   createRandomUserGeneral();
		   String email = "prueba1@prueba1.com";
		   String password =getRandomString();
			String name= getRandomString();
			String cause =  idCatalog;
			String country = idCatalog;
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/users/registerNonProfit";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .file(file)
		                    .param("name", name) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country)
		                    .param("cause", cause))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	        
	        Assert.assertEquals("EMAIL ALREADY IN USE", response.getCodeMessage());
	      
	    }
	    
	    @Test
	    public void testCreateNGOWithoutImage() throws Exception {

		   CatalogWrapper countryCat = createRandomCatalog();
		   CatalogWrapper causeCat = createRandomCatalog();
		   String idCatalog = "1";
		   
		   
		   String email = "pruebaderegistrarong@gmail.com";
		   String password =getRandomString();
			String name= getRandomString();
			String cause =  idCatalog;
			String country = idCatalog;
			
	        String uri = "/rest/protected/users/registerNonProfit";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .param("name", name) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country)
		                    .param("cause", cause))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	        
	        Assert.assertEquals("user created succesfully", response.getCodeMessage());
	      

	    }
	    
	    @Test
	    public void testCreateNGOWithBadEmailSyntaxis() throws Exception {

		   CatalogWrapper countryCat = createRandomCatalog();
		   CatalogWrapper causeCat = createRandomCatalog();
		   String idCatalog = "1";
		   
		   
		   String email = "pruebaderegistraonggmail.com";
		   String password =getRandomString();
			String name= getRandomString();
			String cause =  idCatalog;
			String country = idCatalog;
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/users/registerNonProfit";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
		                    .file(file)
		                    .param("name", name) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country)
		                    .param("cause", cause))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	        
	        Assert.assertEquals("BAD EMAIL", response.getCodeMessage());
	      
	    }
}
