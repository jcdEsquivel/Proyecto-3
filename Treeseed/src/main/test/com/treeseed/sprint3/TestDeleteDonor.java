package com.treeseed.sprint3;

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

import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.testBase.AbstractTestController;

// TODO: Auto-generated Javadoc
/**
 * The Class TestDeleteDonor.
 */
public class TestDeleteDonor extends AbstractTestController {

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
	public void testDeleteDonorProfile() throws Exception {
		DonorWrapper donor = createRandomDonor();
		
		DonorRequest request = new DonorRequest();
		DonorPOJO pojo = new DonorPOJO();
		
		pojo.setId(donor.getId());
		
		request.setId(donor.getId());
     
		String jsonObject = mapToJson(request);
      
	    String uri = "/rest/protected/donor/deleteDonor";
	
	    MvcResult result = mvc.perform(
    		MockMvcRequestBuilders.post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .content(jsonObject))
                 .andReturn();
  
	   String content = result.getResponse().getContentAsString();
	            
       DonorResponse response = mapFromJson(content,DonorResponse.class);

       Assert.assertEquals("Donor deleted sucessfully", response.getCodeMessage());

	 }
	 
	 
	 

}
