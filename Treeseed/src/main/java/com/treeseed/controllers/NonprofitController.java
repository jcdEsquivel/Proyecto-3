package com.treeseed.controllers;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Plan;
import com.treeseed.contracts.BaseResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.contracts.EmailUniqueRequest;
import com.treeseed.contracts.NameUniqueRequest;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.contracts.UserGeneralResponse;
import com.treeseed.utils.TreeseedConstants;
import com.treeseed.utils.Utils;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.pojo.DonationPOJO;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.RecurrableDonationPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.repositories.UserGeneralRepository;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.RecurrableDonationServiceInterface;
import com.treeseed.services.UserGeneralService;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.utils.PojoUtils;
import com.treeseed.utils.StripeUtils;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.DonationWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.ParentUserWrapper;
import com.treeseed.ejbWrapper.RecurrableDonationWrapper;

// TODO: Auto-generated Javadoc
/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "rest/protected/nonprofit")
public class NonprofitController extends UserGeneralController {

	/** The catalog service. */
	@Autowired
	CatalogServiceInterface catalogService;

	/** The campaign service. */
	@Autowired
	CampaignServiceInterface campaignService;

	/** The donor service. */
	@Autowired
	DonorServiceInterface donorService;

	/** The validator. */
	EmailValidator validator = EmailValidator.getInstance();

	/** The non profit service. */
	@Autowired
	NonprofitServiceInterface nonProfitService;

	/** The servlet context. */
	@Autowired
	ServletContext servletContext;

	/** The user general service. */
	@Autowired
	UserGeneralServiceInterface userGeneralService;

	/** The donation service. */
	@Autowired
	DonationServiceInterface donationService;

	/** The recurrable donation service. */
	@Autowired
	RecurrableDonationServiceInterface recurrableDonationService;

	/** The request. */
	@Autowired
	HttpServletRequest request;

	/**
	 * Delete non profit.
	 *
	 * @param dr
	 *            the donor request
	 * @return the nonprofit response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public NonprofitResponse deleteNonProfit(@RequestBody DonorRequest dr) {

		NonprofitResponse us = new NonprofitResponse();

		NonprofitWrapper nonWrapper = new NonprofitWrapper();

		nonWrapper.setId(dr.getId());

		try {
			nonProfitService.deteteNonprofit(nonWrapper);
			us.setCode(200);
			us.setCodeMessage("USER DELETE");

			UserGeneral ug = userGeneralService
					.getUserByNonprofitId(dr.getId());
			UserGeneralWrapper ugw = new UserGeneralWrapper();
			ugw.setId(ug.getId());
			userGeneralService.deleteUserGeneral(ugw);

		} catch (Exception e) {
			us.setCode(400);
			us.setCodeMessage("DATABASE ERROR");
		}

		return us;
	}

	/**
	 * Non profit create.
	 *
	 * @param name
	 *            the name
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @param country
	 *            the country
	 * @param cause
	 *            the cause
	 * @param file
	 *            the file
	 * @return the nonprofit response
	 * @throws AuthenticationException
	 *             the authentication exception
	 * @throws InvalidRequestException
	 *             the invalid request exception
	 * @throws APIConnectionException
	 *             the API connection exception
	 * @throws CardException
	 *             the card exception
	 * @throws APIException
	 *             the API exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@Transactional(rollbackFor = { AuthenticationException.class,
			InvalidRequestException.class, APIConnectionException.class,
			CardException.class, APIException.class })
	public NonprofitResponse nonProfitCreate(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("country") String country,
			@RequestParam("cause") String cause,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		String resultFileName = null;
		NonprofitResponse us = new NonprofitResponse();
		Boolean alreadyUser = userGeneralService.userExist(email);
		email = email.toLowerCase();
		Boolean statePlans = false;
		int[] amounts = { 1000, 1800, 3600, 5000, 10000, 25000 };

		try {
			if (validator.isValid(email)) {
				if (!alreadyUser) {

					CatalogWrapper countryW = catalogService
							.findCatalogById(Integer.parseInt(country));
					CatalogWrapper causeW = catalogService
							.findCatalogById(Integer.parseInt(cause));

					// if (file != null) {
					// resultFileName = Utils.writeToFile(file, servletContext);
					// } else {
					resultFileName = "resources/file-storage/1436319975812.jpg";
					// }

					UserGeneralWrapper userGeneral = new UserGeneralWrapper();
					NonprofitWrapper user = new NonprofitWrapper();

					if (!resultFileName.equals("")) {
						user.setProfilePicture(resultFileName);
					} else {
						user.setProfilePicture("");
					}

					user.setName(name);
					user.setMainPicture(TreeseedConstants.DEFAULT_COVER_IMAGE);
					user.setActive(true);
					user.setCause(causeW.getWrapperObject());
					user.setConutry(countryW.getWrapperObject());

					int nonProfitId = nonProfitService.saveNonprofit(user);

					if (nonProfitId > 0) {
						UserGeneralRequest ug = new UserGeneralRequest();
						UserGeneralResponse ugr = new UserGeneralResponse();
						UserGeneralPOJO userG = new UserGeneralPOJO();
						userG.setEmail(email);
						userG.setPassword(password);
						ug.setUserGeneral(userG);
						ugr = userGeneralCreate(ug, user);

						if (ugr.getCode() == 200) {
							statePlans = createNonprofitPlans(nonProfitId,
									user.getName(), amounts);
							us.setNonProfitId(nonProfitId);
							if (statePlans) {
								us.setCode(200);
								us.setCodeMessage("user created successfully");
							} else {
								us.setCode(400);
								us.setErrorMessage("can't create plans");
							}

						} else {
							us.setCode(ugr.getCode());
							us.setCodeMessage(ugr.getCodeMessage());
						}
					}
				} else {
					us.setCode(400);
					us.setCodeMessage("EMAIL ALREADY IN USE");
				}

			} else {
				us.setCode(400);
				us.setCodeMessage("BAD EMAIL");
			}
		} catch (Exception e) {
			if (e.getMessage().contains(
					"Could not open JPA EntityManager for transaction")) {
				us.setCode(10);
				us.setErrorMessage("Data Base error");
			} else {
				us.setCode(500);
			}
		}

		return us;

	}

	/**
	 * Creates the nonprofit plans.
	 *
	 * @param idNonprofit
	 *            the id nonprofit
	 * @param nameNonprofit
	 *            the name nonprofit
	 * @param amounts
	 *            the amounts
	 * @return the boolean
	 * @throws AuthenticationException
	 *             the authentication exception
	 * @throws InvalidRequestException
	 *             the invalid request exception
	 * @throws APIConnectionException
	 *             the API connection exception
	 * @throws CardException
	 *             the card exception
	 * @throws APIException
	 *             the API exception
	 */
	private Boolean createNonprofitPlans(int idNonprofit, String nameNonprofit,
			int[] amounts) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {

		int count = 1;

		for (int amount : amounts) {
			if (StripeUtils
					.createPlan(count, idNonprofit, 0, nameNonprofit, "",
							amount).getId().equals(null)) {
				return false;
			}
			count++;
		}

		return true;
	}

	/**
	 * Gets the nonprofits.
	 *
	 * @param npr
	 *            the nonProfitrequest
	 * @return the nonprofits
	 */
	@RequestMapping(value = "/advanceGet", method = RequestMethod.POST)
	public NonprofitResponse getNonprofits(@RequestBody NonprofitRequest npr) {

		npr.setPageNumber(npr.getPageNumber() - 1);
		NonprofitResponse nps = new NonprofitResponse();

		try {

			Page<Nonprofit> viewNonprofits = nonProfitService.getNonProfit(npr);

			nps.setCodeMessage("nonprofits fetch success");

			nps.setTotalElements(viewNonprofits.getTotalElements());
			nps.setTotalPages(viewNonprofits.getTotalPages());

			List<NonprofitPOJO> viewNonprofitsPOJO = new ArrayList<NonprofitPOJO>();

			for (Nonprofit objeto : viewNonprofits.getContent()) {
				NonprofitPOJO nnonprofit = new NonprofitPOJO();
				nnonprofit.setId(objeto.getId());
				nnonprofit.setName(objeto.getName());
				nnonprofit.setDescription(objeto.getDescription());
				nnonprofit.setWebPage(objeto.getWebPage());
				nnonprofit.setProfilePicture(objeto.getProfilePicture());
				viewNonprofitsPOJO.add(nnonprofit);
			}
			;

			nps.setNonprofits(viewNonprofitsPOJO);
			nps.setCode(200);

		} catch (Exception e) {
			if (e.getMessage().contains(
					"Could not open JPA EntityManager for transaction")) {
				nps.setCode(10);
				nps.setErrorMessage("Data Base error");
			} else {
				nps.setCode(500);
			}

		}
		return nps;

	}

	/**
	 * Gets the non profit profile.
	 *
	 * @param npr
	 *            the nonProfitRequest
	 * @return the non profit profile
	 */
	@RequestMapping(value = "/getNonProfitProfile", method = RequestMethod.POST)
	@Transactional
	public NonprofitResponse getNonProfitProfile(
			@RequestBody NonprofitRequest npr) {

		NonprofitResponse nps = new NonprofitResponse();
		HttpSession currentSession = request.getSession();
		int tempId = 0;

		if (npr.getIdUser() != 0) {
			tempId = (int) currentSession.getAttribute("idUser");
		}

		try {

			NonprofitWrapper nonprofit = nonProfitService.getNonProfitByID(npr);

			if (nonprofit.getWrapperObject() != null) {

				if (tempId == nonprofit.getWrapperObject().getUsergenerals()
						.get(0).getId()) {
					nps.setOwner(true);
				} else {
					nps.setOwner(false);
				}

				nps.setCode(200);
				nps.setCodeMessage("nonprofit search success");

				NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();

				nonprofitPOJO.setId(nonprofit.getWrapperObject().getId());
				nonprofitPOJO.setName(nonprofit.getWrapperObject().getName());
				nonprofitPOJO.setDescription(nonprofit.getWrapperObject()
						.getDescription());
				nonprofitPOJO.setWebPage(nonprofit.getWrapperObject()
						.getWebPage());
				nonprofitPOJO.setProfilePicture(nonprofit.getWrapperObject()
						.getProfilePicture());
				nonprofitPOJO.setMainPicture(nonprofit.getWrapperObject()
						.getMainPicture());
				nonprofitPOJO.setMision(nonprofit.getWrapperObject()
						.getMision());
				nonprofitPOJO.setReason(nonprofit.getWrapperObject()
						.getReason());
				nonprofitPOJO.setCantDonors(donationService
						.findDonorsPerNonprofit(nonprofit.getId()));

				nonprofitPOJO.setCantMoney(donationService
						.findNonprofitMoney(nonprofit.getId()));

				UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
				UserGeneral userGeneral;
				userGeneral = nonprofit.getWrapperObject().getUsergenerals()
						.get(0);

				userGeneralPOJO.setEmail(userGeneral.getEmail());

				nonprofitPOJO.setUserGeneral(userGeneralPOJO);

				nps.setNonprofit(nonprofitPOJO);
				nps.setCode(200);
			}else{
				nps.setCode(404);
				nps.setErrorMessage("Nonprofit not found");
			}
		} catch (Exception e) {
			if (e.getMessage().contains(
					"Could not open JPA EntityManager for transaction")) {
				nps.setCode(10);
				nps.setErrorMessage("Data Base error");
			} else {
				nps.setCode(500);
			}
		}

		return nps;

	}

	/**
	 * Edits the non profit.
	 *
	 * @param npr
	 *            the NonProfitRequest
	 * @param fileCover
	 *            the file cover
	 * @param fileProfile
	 *            the file profile
	 * @return the nonprofit response
	 */
	@RequestMapping(value = "/editNonProfit", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public NonprofitResponse editNonProfit(
			@RequestPart(value = "data") NonprofitRequest npr,
			@RequestPart(value = "fileCover", required = false) MultipartFile fileCover,
			@RequestPart(value = "fileProfile", required = false) MultipartFile fileProfile) {

		String coverImageName = "";
		String profileImageName = "";

		NonprofitResponse us = new NonprofitResponse();

		try {

			UserGeneral ug = new UserGeneral();
			ug = userGeneralService.getUGByID(npr.getIdUser());

			NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();

			if (ug.getEmail().equals(npr.getEmail())) {

				NonprofitWrapper nonprofit = new NonprofitWrapper();

				if (fileCover != null) {
					coverImageName = Utils.writeToFile(fileCover,
							servletContext);
				}

				if (fileProfile != null) {
					profileImageName = Utils.writeToFile(fileProfile,
							servletContext);
				}

				if (!coverImageName.equals("")) {
					nonprofit.setMainPicture(coverImageName);
				} else {
					nonprofit.setMainPicture(npr.getMainPicture());
				}

				if (!profileImageName.equals("")) {
					nonprofit.setProfilePicture(profileImageName);
				} else {
					nonprofit.setProfilePicture(npr.getProfilePicture());
				}

				nonprofit.setId(npr.getId());
				nonprofit.setName(npr.getName());
				nonprofit.setDescription(npr.getDescription());
				nonprofit.setMision(npr.getMision());
				nonprofit.setReason(npr.getReason());
				nonprofit.setWebPage(npr.getWebPage());

				nonprofitPOJO = new NonprofitPOJO();

				nonProfitService.updateNonProfit(nonprofit);

				NonprofitWrapper nonprofitobject = nonProfitService
						.getNonProfitByID(npr);

				nonprofitPOJO.setName(nonprofitobject.getWrapperObject()
						.getName());
				nonprofitPOJO.setDescription(nonprofitobject.getWrapperObject()
						.getDescription());
				nonprofitPOJO.setMision(nonprofitobject.getWrapperObject()
						.getMision());
				nonprofitPOJO.setReason(nonprofitobject.getWrapperObject()
						.getReason());
				nonprofitPOJO.setWebPage(nonprofitobject.getWrapperObject()
						.getWebPage());
				nonprofitPOJO.setMainPicture(nonprofitobject.getWrapperObject()
						.getMainPicture());
				nonprofitPOJO.setProfilePicture(nonprofitobject
						.getWrapperObject().getProfilePicture());

				us.setNonprofit(nonprofitPOJO);
				us.setCode(200);
				us.setCodeMessage("Nonprofit updated sucessfully");
			} else {

				Boolean alreadyUser = userGeneralService.userExist(npr
						.getEmail());
				npr.setEmail(npr.getEmail().toLowerCase());

				if (validator.isValid(npr.getEmail())) {
					if (!alreadyUser) {

						UserGeneralWrapper userGeneral = new UserGeneralWrapper();

						userGeneral.setEmail(npr.getEmail());
						userGeneral.setId(npr.getIdUser());

						UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();

						userGeneralService.updateUserGeneral(userGeneral);

						userGeneralPOJO.setEmail(userGeneral.getEmail());

						nonprofitPOJO.setName(npr.getName());
						nonprofitPOJO.setDescription(npr.getDescription());
						nonprofitPOJO.setMision(npr.getMision());
						nonprofitPOJO.setReason(npr.getReason());
						nonprofitPOJO.setWebPage(npr.getWebPage());
						nonprofitPOJO.setId(npr.getId());
						nonprofitPOJO.setMainPicture(npr.getMainPicture());
						nonprofitPOJO
								.setProfilePicture(npr.getProfilePicture());

						us.setNonprofit(nonprofitPOJO);

						us.setCode(200);
						us.setCodeMessage("Nonprofit updated sucessfully");

					} else {
						us.setCode(400);
						us.setCodeMessage("EMAIL ALREADY IN USE");
						UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
						userGeneralPOJO.setEmail(ug.getEmail());
						nonprofitPOJO.setUserGeneral(userGeneralPOJO);
						us.setNonprofit(nonprofitPOJO);
					}
				} else {
					us.setCode(400);
					us.setCodeMessage("BAD EMAIL");
					UserGeneralPOJO userGeneralPOJO = new UserGeneralPOJO();
					userGeneralPOJO.setEmail(ug.getEmail());
					nonprofitPOJO.setUserGeneral(userGeneralPOJO);
					us.setNonprofit(nonprofitPOJO);
				}
			}
		} catch (Exception e) {
			if (e.getMessage().contains(
					"Could not open JPA EntityManager for transaction")) {
				us.setCode(10);
				us.setErrorMessage("Data Base error");
			} else {
				us.setCode(500);
			}

		}

		return us;
	}

	/**
	 * Creates the.
	 *
	 * @param email
	 *            the email
	 * @return the base response
	 */
	@RequestMapping(value = "/isNameUnique", method = RequestMethod.POST)
	public BaseResponse isNameUnique(@RequestBody NameUniqueRequest request) {

		BaseResponse response = new BaseResponse();

		try {

			Boolean isNameUnique = nonProfitService.isNameUnique(request
					.getName());

			response.setCode(200);

			if (isNameUnique) {
				response.setCodeMessage("UNIQUE");
			} else {
				response.setCodeMessage("NOT-UNIQUE");
			}

		} catch (Exception e) {
			if (e.getMessage().contains(
					"Could not open JPA EntityManager for transaction")) {
				response.setCode(10);
				response.setErrorMessage("Data Base error");
			} else {
				response.setCode(500);
			}
		}
		return response;
	}

	/**
	 * Gets the dashboard.
	 *
	 * @param nonprofitRequest
	 *            the nonprofit request
	 * @return the dashboard information
	 */
	@RequestMapping(value = "/getdashboard", method = RequestMethod.POST)
	public NonprofitResponse getDashboard(
			@RequestBody NonprofitRequest nonprofitRequest) {

		NonprofitResponse us = new NonprofitResponse();

		try {

			HttpSession currentSession = request.getSession();
			List<DonationPOJO> donationsPojo = new ArrayList<DonationPOJO>();
			List<RecurrableDonationPOJO> subscriptionsPojo = new ArrayList<RecurrableDonationPOJO>();
			List<DonationWrapper> donations;
			List<RecurrableDonationWrapper> subscriptions;

			if (nonprofitRequest.getIdUser() > 0) {
				if (nonprofitRequest.getId() == (int) currentSession
						.getAttribute("idUser")) {
					donations = donationService
							.getDonationsByNonprofit(nonprofitRequest
									.getIdUser());
					subscriptions = recurrableDonationService
							.getRecurrableDonationsByNonprofit(nonprofitRequest
									.getIdUser());

					for (DonationWrapper donation : donations) {
						DonationPOJO donationPojo = new DonationPOJO();
						donationPojo = new DonationPOJO();
						donationPojo.setId(donation.getId());
						donationPojo.setAmount(donation.getAmount());
						donationPojo.setCampaignId(donation.getCampaingId());
						if (donation.getCampaingId() > 0) {
							donationPojo.setCampaign(campaignService
									.getCampaignById(donation.getCampaingId())
									.getCampaignPojo());
						}
						donationPojo.setNonProfitId(donation.getNonProfitId());
						donationPojo.setNonprofitName(nonProfitService
								.getNonProfitById(donation.getNonProfitId())
								.getName());
						donationPojo.setDateS(new SimpleDateFormat(
								"dd MMM yyyy").format(donation.getDateTime()));
						donationPojo.setDonor(donorService.getDonorById(
								donation.getDonorId()).getDonorPojo());
						donationsPojo.add(donationPojo);
					}

					for (RecurrableDonationWrapper subscription : subscriptions) {
						RecurrableDonationPOJO subscriptionPojo = new RecurrableDonationPOJO();

						subscriptionPojo.setId(subscription.getId());
						subscriptionPojo.setAmount(subscription.getAmount());
						subscriptionPojo.setCampaingId(subscription
								.getCampaingId());
						if (subscription.getCampaingId() > 0) {
							subscriptionPojo.setCampaignName(campaignService
									.getCampaignById(
											subscription.getCampaingId())
									.getName());
						}
						subscriptionPojo.setDateS(new SimpleDateFormat(
								"dd MMM yyyy").format(subscription
								.getDateTime()));
						subscriptionPojo.setDonor(donorService.getDonorById(
								subscription.getDonorId()).getDonorPojo());
						subscriptionsPojo.add(subscriptionPojo);

					}

					us.setDashboardSubscription(subscriptionsPojo);
					us.setDashboardDonations(donationsPojo);

					us.setCode(200);
					us.setErrorMessage("Success");
				} else {
					us.setCode(400);
					us.setErrorMessage("User session do not match");
				}
			} else {
				us.setCode(400);
				us.setErrorMessage("Nonprofit do not receive");
			}

		} catch (Exception e) {
			if (e.getMessage().contains(
					"Could not open JPA EntityManager for transaction")) {
				us.setCode(10);
				us.setErrorMessage("Data Base error");
			} else {
				us.setCode(500);
			}

		}

		return us;
	}
}
