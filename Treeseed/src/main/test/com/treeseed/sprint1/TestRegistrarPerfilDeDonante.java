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

import com.treeseed.contracts.DonorResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.AbstractTestController;

public class TestRegistrarPerfilDeDonante extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }
	  
	
	  @Test
	    public void testCreateDonor() throws Exception {

		    CatalogWrapper countryCat = createRandomCatalog();

		    String email = getRandomString()+"@gmail.com";
		    String password =getRandomString();
		    String name= getRandomString();
		    String lastName= getRandomString();
			int country = countryCat.getId();
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
	        String uri = "/rest/protected/donor/register";
	               
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
	        				.file("file", file.getBytes())
		                    .param("name", name) 
		                    .param("lastName", lastName) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country+"")
		                    .param("facebookId", country+"")
		                    .param("facebookToken", country+""))
	        				.andReturn();
	        
	        String content = result.getResponse().getContentAsString();
	        
	        DonorResponse response = mapFromJson(content, DonorResponse.class);
	        
	        Assert.assertEquals("user created succesfully", response.getCodeMessage());
	      
	    }
	  
	   @Test
	    public void testCreatDonorWithSameEmail() throws Exception {

		   DonorWrapper donor = createRandomDonor();
		   
		   String idCatalog = "1";
		   createRandomUserGeneral();
		   String email = donor.getUsergenerals().get(0).getEmail();
		   String password =getRandomString();
			String name= getRandomString();
			String lastName= getRandomString();
			String cause =  idCatalog;
			String country = idCatalog;
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/donor/register";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
	        				.file("file", file.getBytes())
		                    .param("name", name) 
		                    .param("lastName", lastName) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country+"")
		                    .param("facebookId", country+"")
		                    .param("facebookToken", country+""))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        DonorResponse response = mapFromJson(content, DonorResponse.class);
	        
	        Assert.assertEquals("EMAIL ALREADY IN USE", response.getCodeMessage());
	      
	    }
	   
	   
	   /**
   	 * Test create donor with bad email syntaxis.
   	 *
   	 * @throws Exception the exception
   	 */
   	@Test
	    public void testCreateDonorWithBadEmailSyntaxis() throws Exception {

		   CatalogWrapper countryCat = createRandomCatalog();
		   CatalogWrapper causeCat = createRandomCatalog();
		   String idCatalog = "1";
		   
		   
		   String email = "pruebaderegistraonggmail.com";
		   String password =getRandomString();
			String name= getRandomString();
			String lastName= getRandomString();
			String cause =  idCatalog;
			String country = idCatalog;
			FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436073230483.jpg");
			MockMultipartFile file = new MockMultipartFile("testImage", "1436073230483", "multipart/form-data", inputFile);
			
			
	        String uri = "/rest/protected/donor/register";
	               
	        
	        MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
	        				.file("file", file.getBytes())
		                    .param("name", name) 
		                    .param("lastName", lastName) 
		                    .param("email", email)
		                    .param("password", password)
		                    .param("country", country+"")
		                    .param("facebookId", country+"")
		                    .param("facebookToken", country+""))
	        				.andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        DonorResponse response = mapFromJson(content, DonorResponse.class);
	        
	        Assert.assertEquals("BAD EMAIL", response.getCodeMessage());
	      
	    }
	  
}
