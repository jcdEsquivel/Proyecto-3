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
import com.treeseed.contracts.NonprofitRequest;
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
	  
	    @Test
	    public void testShowNGO() throws Exception {

	    	int id = 39;
	       
	        NonprofitRequest request = new NonprofitRequest();
	        
	        request.setId(id);
	        String jsonObject = mapToJson(request);
	      
	            String uri = "/rest/protected/nonprofit/getNonProfitProfile";

	            MvcResult result = mvc.perform(
	              MockMvcRequestBuilders.post(uri)
	                         .contentType(MediaType.APPLICATION_JSON)
	                            .accept(MediaType.APPLICATION_JSON).content(jsonObject))
	                .andReturn();
	            

	            String content = result.getResponse().getContentAsString();
	            
	            NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	            
	           
	            
	            Assert.assertEquals("200", response.getCode().toString());
	      
	            NonprofitPOJO pojo = response.getNonprofit();

	            
	            Assert.assertEquals("Causa 1", pojo.getName());
	      

	    }
	    
	   
	
}
