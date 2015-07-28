package com.treeseed.sprint1;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.AbstractTestController;

public class TestRegistrarPerfilDeONG extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }
	  
	  
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
			
			
	        String uri = "/rest/protected/nonprofit/register";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
	        				.file("file", file.getBytes())
		                    .param("name", name) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country)
		                    .param("cause", cause))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	        
	        Assert.assertEquals("user created successfully", response.getCodeMessage());
	      

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
			
			
	        String uri = "/rest/protected/nonprofit/register";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
	        		  		.file("file", file.getBytes())
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
			
	        String uri = "/rest/protected/nonprofit/register";
	               
	        
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
	        
	        Assert.assertEquals("user created successfully", response.getCodeMessage());
	      
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
			
			
	        String uri = "/rest/protected/nonprofit/register";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
	        				.file("file", file.getBytes())
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
