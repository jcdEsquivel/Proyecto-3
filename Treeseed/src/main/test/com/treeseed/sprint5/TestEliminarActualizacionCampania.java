package com.treeseed.sprint5;

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
import com.treeseed.contracts.PostCampaignRequest;
import com.treeseed.contracts.PostNonprofitRequest;
import com.treeseed.contracts.PostNonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.PostCampaignPOJO;
import com.treeseed.pojo.PostNonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestDeletePostNonprofit.
 */
public class TestEliminarActualizacionCampania extends AbstractTestController {

	/** The wac. */
	@Autowired WebApplicationContext wac; 
	
	/**
	 * Sets the up.
	 *
	 * @param controller the new up
	 */
	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	 /* (non-Javadoc)
 	 * @see com.treeseed.testBase.AbstractTestController#setUp()
 	 */
 	@Before
	    public void setUp() {
	        super.setUp();

	    }

	
 	/**
	  * Test delete post campaign.
	  *
	  * @throws Exception the exception
	  */
	 @Test  //Delete Post Campaign
	 public void testDeletePostCampaign() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		CampaignWrapper campaign =  createRandomCampaign(nonprofit);
		PostCampaignWrapper postCampaign = createRandomPostCampaign(campaign.getWrapperObject());
		
		PostCampaignRequest request = new PostCampaignRequest();
		PostCampaignPOJO pojo = new PostCampaignPOJO();
		
		pojo.setId(postCampaign.getId());
		pojo.setCampaignId(campaign.getId());
		
		request.setPostCampaign(pojo);
		
		String jsonObject = mapToJson(request);
      
	    String uri = "/rest/protected/postCampaign/deletePostCampaign";
	
	    MvcResult result = mvc.perform(
    		MockMvcRequestBuilders.post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .content(jsonObject)
                 .sessionAttr("idUser", nonprofit.getUsergenerals().get(0).getId()))
                 .andReturn();
  
	    String content = result.getResponse().getContentAsString();
	            
        PostNonprofitResponse response = mapFromJson(content, PostNonprofitResponse.class);

        Assert.assertEquals("Post deleted sucessfully", response.getCodeMessage());

	 }
 	
 	
 	
 	/**
	  * Test delete post campaign without session id.
	  *
	  * @throws Exception the exception
	  */
	 @Test  //Delete Post Campaign
	 public void testDeletePostCampaignWithoutSessionId() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		CampaignWrapper campaign =  createRandomCampaign(nonprofit);
		PostCampaignWrapper postCampaign = createRandomPostCampaign(campaign.getWrapperObject());
		
		PostCampaignRequest request = new PostCampaignRequest();
		PostCampaignPOJO pojo = new PostCampaignPOJO();
		
		pojo.setId(postCampaign.getId());
		pojo.setCampaignId(campaign.getId());
		
		request.setPostCampaign(pojo);

		
		String jsonObject = mapToJson(request);
     
	    String uri = "/rest/protected/postCampaign/deletePostCampaign";
	
	    MvcResult result = mvc.perform(
   		MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonObject)
                .sessionAttr("idUser", 0))
                .andReturn();
 
	    String content = result.getResponse().getContentAsString();
	            
       PostNonprofitResponse response = mapFromJson(content, PostNonprofitResponse.class);

       Assert.assertNotEquals("Post deleted sucessfully", response.getCodeMessage());
       Assert.assertEquals("Invalid request", response.getCodeMessage());
       

	 }
 	
 	
 	/**
	  * Test delete post campaign wrong session id.
	  *
	  * @throws Exception the exception
	  */
	 @Test  //Delete Post Campaign
	 public void testDeletePostCampaignWrongSessionId() throws Exception {

		NonprofitWrapper nonprofit = createRandomNonprofit();
		CampaignWrapper campaign =  createRandomCampaign(nonprofit);
		PostCampaignWrapper postCampaign = createRandomPostCampaign(campaign.getWrapperObject());
		
		PostCampaignRequest request = new PostCampaignRequest();
		PostCampaignPOJO pojo = new PostCampaignPOJO();
		
		pojo.setId(postCampaign.getId());
		pojo.setCampaignId(campaign.getId());
		
		request.setPostCampaign(pojo);
		
		String jsonObject = mapToJson(request);
    
	    String uri = "/rest/protected/postCampaign/deletePostCampaign";
	
	    MvcResult result = mvc.perform(
  		MockMvcRequestBuilders.post(uri)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .content(jsonObject)
               .sessionAttr("idUser", 999999))
               .andReturn();

	    String content = result.getResponse().getContentAsString();
	            
      PostNonprofitResponse response = mapFromJson(content, PostNonprofitResponse.class);

      Assert.assertNotEquals("Post deleted sucessfully", response.getCodeMessage());
      Assert.assertEquals("Invalid request", response.getCodeMessage());

	 }

}
