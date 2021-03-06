package com.treeseed.sprint3;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestListarCampannaONG.
 */
public class TestListarCampannaONG extends AbstractTestController{

	/** The user general service. */
	@Autowired UserGeneralServiceInterface userGeneralService;
	 
	 
	 
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
     	 * Gets the campaigns by nonprofit with campaigns.
     	 *
     	 * @return the campaigns by nonprofit with campaigns
     	 * @throws Exception the exception
     	 */
     	@Test
	     public void getCampaignsByNonprofitWithCampaigns() throws Exception {
	    
	     
	      CampaignRequest req = new CampaignRequest();
	      List<String> sort=new ArrayList<String>();
	      sort.add("StartDate");
	   
	   
	   req.setPageNumber(1);
	   req.setPageSize(10);
	   req.setDirection("DES");
	   req.setSortBy(sort);
	   req.setSearchColumn("ALL");
	   req.setNonprofitId(4);
	   
	   
	         String uri = "/rest/protected/campaing/nonprofitCampaigns";
	                
	         String jsonObject = mapToJson(req);
	         
	         MvcResult result =mvc.perform(MockMvcRequestBuilders.post(uri)
	               .contentType(MediaType.APPLICATION_JSON)
	               .accept(MediaType.APPLICATION_JSON).content(jsonObject)).andReturn();
	         

	         String content = result.getResponse().getContentAsString();
	         
	         CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	         
	         Assert.assertEquals("campaigns fetch success", response.getCodeMessage());
	       

	     }
	     
	     /**
     	 * Gets the campaigns by nonprofit without campaigns.
     	 *
     	 * @return the campaigns by nonprofit without campaigns
     	 * @throws Exception the exception
     	 */
     	@Test
	     public void getCampaignsByNonprofitWithoutCampaigns() throws Exception {
	    
	     
	      CampaignRequest req = new CampaignRequest();
	      List<String> sort=new ArrayList<String>();
	      sort.add("StartDate");
	   
	   
	   req.setPageNumber(1);
	   req.setPageSize(10);
	   req.setDirection("DES");
	   req.setSortBy(sort);
	   req.setSearchColumn("ALL");
	   req.setNonprofitId(0);
	   
	   
	         String uri = "/rest/protected/campaing/nonprofitCampaigns";
	                
	         String jsonObject = mapToJson(req);
	         
	         MvcResult result =mvc.perform(MockMvcRequestBuilders.post(uri)
	               .contentType(MediaType.APPLICATION_JSON)
	               .accept(MediaType.APPLICATION_JSON).content(jsonObject)).andReturn();
	         

	         String content = result.getResponse().getContentAsString();
	         
	         CampaignResponse response = mapFromJson(content, CampaignResponse.class);
	         
	         Assert.assertEquals("campaigns fetch unsuccessful", response.getErrorMessage());
	       

	     }
}
