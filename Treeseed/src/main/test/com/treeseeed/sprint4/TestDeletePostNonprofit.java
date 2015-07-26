package com.treeseeed.sprint4;

import java.io.FileInputStream;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.treeseed.contracts.LoginRequest;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestDeletePostNonprofit extends AbstractTestController {

	 @Autowired WebApplicationContext wac; 
	
	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	 @Before
	    public void setUp() {
	        super.setUp();

	    }

	

	 @Test  //Visualizar perfil propio
	 public void testDeletePostNGOProfile() throws Exception {

    	NonprofitWrapper nonprofit = createRandomNonprofit();
    	
    	
    	
        PostNonprofitRequest request = new PostNonprofitRequest();
        
       // request.setPostNonprofit(post.getID());
        
        String jsonObject = mapToJson(request);
      
            String uri = "/rest/protected/nonprofit/getNonProfitProfile";

            MvcResult result = mvc.perform(
              MockMvcRequestBuilders.post(uri)
                         .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON).content(jsonObject))               
	                .andReturn();
	            
	        
	            String content = result.getResponse().getContentAsString();
	            
	            NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
   
	            Assert.assertEquals(true, response.isOwner());
	      

	    }

}
