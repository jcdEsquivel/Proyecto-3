package com.treeseed.sprint3;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.AbstractTestController;

public class TestEditarPerfilDeONG extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }
	  
	  
	  @Test
	    public void testEditNGO() throws Exception {
		 
		  	NonprofitWrapper nonprofit = createRandomNonprofit();
		   
		  	NonprofitRequest request = new NonprofitRequest();
			   
		  	
		  	request.setIdUser(nonprofit.getUsergenerals().get(0).getId());
		  	request.setId(nonprofit.getId());
		  	request.setEmail(nonprofit.getUsergenerals().get(0).getEmail());
		  	request.setName(getRandomString());
		  	request.setMision(getRandomString());
		  	request.setReason(getRandomString());
		  	request.setDescription(getRandomString());
		  	request.setWebPage(getRandomString());
		  	
		  	String jsonObject = mapToJson(request);
		  	
		  	MockMultipartFile jsonFile = new MockMultipartFile("data", "", "application/json", jsonObject.getBytes());
		  	
		  	FileInputStream inputFile = new FileInputStream( "src/main/webapp/resources/file-storage/1436085937763.jpg");
		  	FileInputStream inputFile2 = new FileInputStream( "src/main/webapp/resources/file-storage/1436085937763.jpg");
		  	
		  	MockMultipartFile fileCover = new MockMultipartFile("fileProfile", "1436085937763", "multipart/form-data", inputFile);
			
			MockMultipartFile fileProfile = new MockMultipartFile("fileCover", "1436085937763", "multipart/form-data", inputFile2);
			
		  	
		  	String uri = "/rest/protected/nonprofit/editNonProfit";
	               
	        //Sending the file image null because of the servletContext problem
		  	MvcResult result = mvc.perform(
	        		MockMvcRequestBuilders.fileUpload(uri)
	        		.file(jsonFile)		
	        		.file(fileCover)
    				.file(fileProfile)
                    .sessionAttr("idUser", nonprofit.getUsergenerals().get(0).getId()))
    				.andReturn();
	        
			
		  	String content = result.getResponse().getContentAsString();
	        
		  	NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	        
		  	Assert.assertEquals("Nonprofit updated sucessfully", response.getCodeMessage());
	      

	    }
	
	  
	  
	  
}
