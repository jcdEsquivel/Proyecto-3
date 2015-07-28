package com.treeseed.sprint2;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.LoginRequest;
import com.treeseed.contracts.LoginResponse;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.LoginController;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.services.LoginServiceInterface;
import com.treeseed.testBase.AbstractTestController;


public class TestIniciarSessionController extends AbstractTestController{

	
	@Autowired
	LoginServiceInterface loginService;

	
	protected void setUp(NonprofitController controller) {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

  @Before
    public void setUp() {
        super.setUp();
        
    }
	
	 @Test
	    public void loginSuccessful() throws Exception {
		 	
		 	UserGeneralWrapper userGeneral = createRandomUserGeneral();
		    String email=userGeneral.getEmail();
		    String password=userGeneral.getPassword();
		   
		    LoginRequest req = new LoginRequest(email, "123456789");
		    String jsonObject = mapToJson(req);
			
	        String uri = "/rest/login/checkuser";
	               
	       
	        
	        MvcResult result =mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON).content(jsonObject)).andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        LoginResponse response = mapFromJson(content, LoginResponse.class);
	        
	        Assert.assertEquals("User authorized", response.getCodeMessage());
	      

	    }
	 
	 @Test
	    public void loginWrongEmail() throws Exception {
		 	
		 	UserGeneralWrapper userGeneral = createRandomUserGeneral();
		    String email=userGeneral.getEmail();
		    String password=userGeneral.getPassword();
		   
		    LoginRequest req = new LoginRequest(email+"5", "123456789");
			
			
	        String uri = "/rest/login/checkuser";
	               
	        String jsonObject = mapToJson(req);
	        
	        MvcResult result =mvc.perform(MockMvcRequestBuilders.post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON).content(jsonObject)).andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        
	        LoginResponse response = mapFromJson(content, LoginResponse.class);
	        
	        Assert.assertEquals("Unauthorized User", response.getErrorMessage());
	      

	    }
	 
	 @Test
	    public void loginWrongPass() throws Exception {
		 	
		 	UserGeneralWrapper userGeneral = createRandomUserGeneral();
		    String email=userGeneral.getEmail();
		    String password=userGeneral.getPassword();
		   
		    LoginRequest req = new LoginRequest(email, "123456789"+"5");
			
			
	        String uri = "/rest/login/checkuser";
	               
	        String jsonObject = mapToJson(req);
	        
	        MvcResult result =mvc.perform(MockMvcRequestBuilders.post(uri)
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON).content(jsonObject)).andReturn();
	        

	        String content = result.getResponse().getContentAsString();
	        LoginResponse response = mapFromJson(content, LoginResponse.class);
	        
	        Assert.assertEquals("Unauthorized User", response.getErrorMessage());
	      

	    }
	 
	  
	  


}
