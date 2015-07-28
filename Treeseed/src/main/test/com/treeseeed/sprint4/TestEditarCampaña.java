package com.treeseeed.sprint4;

import java.io.FileInputStream;
import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.mock.web.MockMultipartFile;

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.testBase.AbstractTestController;

public class TestEditarCampaña extends AbstractTestController  {
	
	

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
	 * Test to edit campaign.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEditCampaign() throws Exception {

		CampaignWrapper campaign = createRandomCampaign();
		
		CampaignRequest request = new CampaignRequest();
		CampaignPOJO pojo = new CampaignPOJO();

		pojo.setId(campaign.getId());
		request.setName(campaign.getName());
		request.setAmountGoal(campaign.getAmountGoal());
		request.setAmountCollected(campaign.getAmountCollected());
		request.setDescription(campaign.getDescription());
		request.setCreationDate(new Date(156132));
		request.setDueDateData(new Date(1456151));
		request.setStartDateData(new Date(1546548));	
		request.setCampaign(pojo);
		request.setId(campaign.getId());
		request.setIdUser(0);

		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/campaing/editCampaign";
		
		MockMultipartFile jsonFile = new MockMultipartFile("data", "", "application/json", jsonObject.getBytes());
		  
		FileInputStream inputFile = new FileInputStream(
		    "src/main/webapp/resources/file-storage/1436073230483.jpg");
		  MockMultipartFile file = new MockMultipartFile("testImage",
		    "1436073230483", "multipart/form-data", inputFile);
		  
		  MvcResult result = mvc.perform(
		          MockMvcRequestBuilders.fileUpload(uri)
		            .file(file)
		                     .file(jsonFile)
		                     .sessionAttr("idUser", campaign.getNonprofit().getUsergenerals().get(0).getId()))
		            .andReturn();
		  
		String content = result.getResponse().getContentAsString();

		CampaignResponse response = mapFromJson(content,
				CampaignResponse.class);

		Assert.assertEquals("Campaign updated sucessfully", response.getCodeMessage());

	}
}
