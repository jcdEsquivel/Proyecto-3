package com.treeseed.sprint1;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class TestsSearchNGO extends AbstractTestController{

	@Autowired UserGeneralServiceInterface userGeneralService;
	
	
	
	 protected void setUp(NonprofitController controller) {
	        mvc = MockMvcBuilders.standaloneSetup(controller).build();
	    }
	
	  @Before
	    public void setUp() {
	        super.setUp();
	        
	    }
	 
	  @Test
	    public void testSearchNGOAll() throws Exception {

		  	
		  
		  	NonprofitRequest request = new NonprofitRequest();
	
		  	request.setPageSize(10);
	    	request.setDirection("DESC");
	    	request.setSearchColumn("ALL");
	    	request.setSearchTerm("");
	    	request.setPageNumber(1);
	        request.setName("Territorio de Zaguates");
			request.setCountry("2");
			request.setCause("3");
	

	        String jsonObject = mapToJson(request);
	      
	            String uri = "/rest/protected/nonprofit/advanceGet";

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
