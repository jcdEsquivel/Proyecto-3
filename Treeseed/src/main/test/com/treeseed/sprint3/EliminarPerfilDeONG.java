package com.treeseed.sprint3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.testBase.AbstractTestController;

public class EliminarPerfilDeONG  extends AbstractTestController {

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
	  * Test delete donor profile.
	  *
	  * @throws Exception the exception
	  */
	 @Test  //Delete Post ONG
	public void testEliminarPerfilONG() throws Exception {
		NonprofitWrapper nonprofit = createRandomNonprofit();
		
		DonorRequest request = new DonorRequest();
		NonprofitPOJO pojo = new NonprofitPOJO();
		
		pojo.setId(nonprofit.getId());
		
		request.setId(nonprofit.getId());
     
		String jsonObject = mapToJson(request);
      
	    String uri = "/rest/protected/nonprofit/delete";
	
	    MvcResult result = mvc.perform(
    		MockMvcRequestBuilders.post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .content(jsonObject))
                 .andReturn();
  
	   String content = result.getResponse().getContentAsString();
	            
       NonprofitResponse response = mapFromJson(content,NonprofitResponse.class);

       Assert.assertEquals("USER DELETE", response.getCodeMessage());

	 }
	 
	 
}
