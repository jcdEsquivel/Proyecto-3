package com.treeseed.sprint2;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.LoginRequest;
import com.treeseed.contracts.LoginResponse;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.controllers.NonprofitController;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.services.LoginServiceInterface;
import com.treeseed.testBase.AbstractTestController;

public class TestVisualizarPerfilDonante extends AbstractTestController {

	protected void setUp(NonprofitController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Before
	public void setUp() {
		super.setUp();

	}

	@Test
	// Visualizar perfil propio
	public void testShowMyOwnNGOProfile() throws Exception {
		try {
			DonorWrapper donor = createRandomDonor();
			DonorRequest request = new DonorRequest();

			request.setId(donor.getId());// Nonprofit Id
			request.setIdUser(donor.getUsergenerals().get(0).getId());
			String jsonObject = mapToJson(request);

			String uri = "/rest/protected/donor/getDonorProfile";

			MvcResult result = mvc.perform(
					MockMvcRequestBuilders.post(uri)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON)
							.content(jsonObject)
							.sessionAttr("idUser", request.getIdUser()))

			.andReturn();

			String content = result.getResponse().getContentAsString();

			DonorResponse response = mapFromJson(content, DonorResponse.class);

			Assert.assertEquals(true, response.isOwner());

		} catch (Exception ex) {
			String s = "";
		}
	}

	@Test
	// Visualizar perfil de otro usuario y estando logueado en el sistema
	public void testShowOtherNGOProfileAsLogged() throws Exception {

		DonorWrapper donor = createRandomDonor();
		DonorRequest request = new DonorRequest();

		request.setId(donor.getId());// Nonprofit Id
		request.setIdUser(donor.getUsergenerals().get(0).getId());

		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/getDonorProfile";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post(uri)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(jsonObject)
						.sessionAttr("idUser", 1))

		.andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content, DonorResponse.class);

		Assert.assertEquals(false, response.isOwner());

	}

	@Test
	// Visualizar perfil de otro usuario y siendo visitante
	public void testShowOtherNGOProfileAsGuest() throws Exception {

		DonorWrapper donor = createRandomDonor();
		DonorRequest request = new DonorRequest();
		request.setId(donor.getId());

		String jsonObject = mapToJson(request);

		String uri = "/rest/protected/donor/getDonorProfile";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post(uri)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(jsonObject)
						.sessionAttr("idUser", 1))

		.andReturn();

		String content = result.getResponse().getContentAsString();

		DonorResponse response = mapFromJson(content, DonorResponse.class);

		Assert.assertEquals(false, response.isOwner());

	}
}
