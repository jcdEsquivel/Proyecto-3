package com.treeseed.sprint2;

import java.io.FileInputStream;
import java.util.Date;

import javax.servlet.http.HttpSession;

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



// TODO: Auto-generated Javadoc
/**
 * The Class TestsVisualizarPerfilDeONG.
 */
@Transactional
public class TestsVisualizarPerfilDeONG extends AbstractTestController{


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
    	 * Test show my own ngo profile.
    	 *
    	 * @throws Exception the exception
    	 */
    	@Test  //Visualizar perfil propio
	    public void testShowMyOwnNGOProfile() throws Exception {

	    	NonprofitWrapper nonprofit = createRandomNonprofit();
	        NonprofitRequest request = new NonprofitRequest();
	        
	        request.setId(nonprofit.getId());//Nonprofit Id
	        request.setIdUser(nonprofit.getUsergenerals().get(0).getId());
	        String jsonObject = mapToJson(request);
	      
	            String uri = "/rest/protected/nonprofit/getNonProfitProfile";

	            MvcResult result = mvc.perform(
	              MockMvcRequestBuilders.post(uri)
	                         .contentType(MediaType.APPLICATION_JSON)
	                            .accept(MediaType.APPLICATION_JSON).content(jsonObject)
	                            .sessionAttr("idUser",request.getIdUser()))
	                            
	                .andReturn();
	            
	        
	            String content = result.getResponse().getContentAsString();
	            
	            NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
      
	            Assert.assertEquals(true, response.isOwner());
	      

	    }
	    
	    
	    /**
    	 * Test show other ngo profile as logged.
    	 *
    	 * @throws Exception the exception
    	 */
    	@Test //Visualizar perfil de otro usuario y estando logueado en el sistema
	    public void testShowOtherNGOProfileAsLogged() throws Exception {

	    	NonprofitWrapper nonprofit = createRandomNonprofit();
	        NonprofitRequest request = new NonprofitRequest();
	        
	        request.setId(nonprofit.getId());//Nonprofit Id
	        request.setIdUser(nonprofit.getUsergenerals().get(0).getId());
	        
	        String jsonObject = mapToJson(request);
	      
	            String uri = "/rest/protected/nonprofit/getNonProfitProfile";

	            MvcResult result = mvc.perform(
	              MockMvcRequestBuilders.post(uri)
	                         .contentType(MediaType.APPLICATION_JSON)
	                            .accept(MediaType.APPLICATION_JSON).content(jsonObject)
	                            .sessionAttr("idUser", 1))
	                            
	                .andReturn();
	            
	        
	            String content = result.getResponse().getContentAsString();
	            
	            NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	                 
	            Assert.assertEquals(false, response.isOwner());
	      

	    }

	    
	    
	    /**
    	 * Test show other ngo profile as guest.
    	 *
    	 * @throws Exception the exception
    	 */
    	@Test //Visualizar perfil de otro usuario y siendo visitante
	    public void testShowOtherNGOProfileAsGuest() throws Exception {

	    	NonprofitWrapper nonprofit = createRandomNonprofit();
	        NonprofitRequest request = new NonprofitRequest();
	        request.setId(nonprofit.getId());
	        
	        String jsonObject = mapToJson(request);
	      
	            String uri = "/rest/protected/nonprofit/getNonProfitProfile";

	            MvcResult result = mvc.perform(
	              MockMvcRequestBuilders.post(uri)
	                         .contentType(MediaType.APPLICATION_JSON)
	                            .accept(MediaType.APPLICATION_JSON).content(jsonObject)
	                            .sessionAttr("idUser", 1))
	                            
	                .andReturn();
	            
	        
	            String content = result.getResponse().getContentAsString();
	            
	            NonprofitResponse response = mapFromJson(content, NonprofitResponse.class);
	            
      
	            Assert.assertEquals(false, response.isOwner());

	    }	    
	   
	
}
