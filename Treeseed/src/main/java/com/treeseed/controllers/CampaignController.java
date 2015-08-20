package com.treeseed.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.utils.PageWrapper;
import com.treeseed.utils.StripeUtils;
import com.treeseed.utils.TreeseedConstants;
import com.treeseed.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignController.
 */
@RestController
@RequestMapping(value = "rest/protected/campaing")
public class CampaignController {

	/** The campaign service. */
	@Autowired
	CampaignServiceInterface campaignService;
	
	/** The donation service. */
	@Autowired
	DonationServiceInterface donationService;
	

	/** The servlet context. */
	@Autowired
	ServletContext servletContext;

	/** The request. */
	@Autowired
	HttpServletRequest request;

	/** The nonprofit service. */
	@Autowired
	NonprofitServiceInterface nonprofitService;

	/**
	 * Gets the nonprofits.
	 *
	 * @param cr
	 *            the Campaign Request
	 * @return the nonprofits
	 */
	@RequestMapping(value = "/advanceGet", method = RequestMethod.POST)
	public CampaignResponse getNonprofits(@RequestBody CampaignRequest cr) {

		CampaignResponse cs = new CampaignResponse();
		
		try{
		
			cr.setPageNumber(cr.getPageNumber() - 1);
	
			Page<Campaign> viewCampaign = campaignService.getAllCampaigns(cr);
	
			cs.setCodeMessage("campaigns fetch success");
	
			cs.setTotalElements(viewCampaign.getTotalElements());
			cs.setTotalPages(viewCampaign.getTotalPages());
	
			List<CampaignPOJO> viewCampaignPOJO = new ArrayList<CampaignPOJO>();
	
			for (Campaign objeto : viewCampaign.getContent()) {
				CampaignPOJO campaign = new CampaignPOJO();
				campaign.setId(objeto.getId());
				campaign.setName(objeto.getName());
				campaign.setDescription(objeto.getDescription());
				campaign.setAmountCollected(objeto.getAmountCollected());
				campaign.setAmountGoal(objeto.getAmountGoal());
				campaign.setPicture(objeto.getPicture());
	
				NonprofitPOJO cp = new NonprofitPOJO();
				cp.setName(objeto.getNonprofit().getName());
				cp.setId(objeto.getNonprofit().getId());
	
				campaign.setNonprofit(cp);
				viewCampaignPOJO.add(campaign);
			};
	
			cs.setCampaigns(viewCampaignPOJO);
			cs.setCode(200);
			
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				cs.setCode(10);
				cs.setErrorMessage("Data Base error");
			}else{
				cs.setCode(500);
			}
		}
			return cs;

	}

	/**
	 * Campaing create.
	 *
	 * @param name            the name
	 * @param description            the description
	 * @param date1            the date1
	 * @param date2            the date2
	 * @param amount            the amount
	 * @param idNonprofit            the id nonprofit
	 * @param file            the file
	 * @return the campaign response
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Transactional(rollbackFor = { AuthenticationException.class, InvalidRequestException.class,
			APIConnectionException.class, CardException.class, APIException.class })
	public CampaignResponse campaingCreate(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("date1") String date1,
			@RequestParam(value = "date2", required = false) String date2, @RequestParam(value = "amount", required = false) String amount,
			@RequestParam("idNonprofit") String idNonprofit,
			@RequestParam(value = "file", required = false) MultipartFile file) throws AuthenticationException,
					InvalidRequestException, APIConnectionException, CardException, APIException {
		
		CampaignResponse response = new CampaignResponse();
		
		try{
		
			String resultFileName = null;
			NonprofitWrapper nonprofit = nonprofitService.getSessionNonprofit(Integer.parseInt(idNonprofit));
			
			String[] dateTmp;
			String[] dateTmp1;
			GregorianCalendar dueDate = new GregorianCalendar();
			GregorianCalendar startDate = new GregorianCalendar();
			Boolean statePlans = false;
			int[] amounts = {1000,1800,3600,5000,10000,25000};
	
			if (nonprofit.getWrapperObject() != null) {
				if (file != null) {
	
					CampaignWrapper campaign = new CampaignWrapper();
	
					resultFileName = Utils.writeToFile(file, servletContext);
	
					if (!resultFileName.equals("")) {
						campaign.setPicture(resultFileName);
					} else {
						campaign.setPicture(TreeseedConstants.DEFAULT_CAPAIGN_IMAGE);
					}
					
	
					date1 = date1.replace('"', '0');
					dateTmp1 = date1.split("-");
					dateTmp1[2] = dateTmp1[2].split("T")[0];
	
					startDate.set(Integer.parseInt(dateTmp1[0]), Integer.parseInt(dateTmp1[1]) - 1,
							Integer.parseInt(dateTmp1[2]), 0, 00, 0);
	
					if (!date2.equals("undefined")) {
						date2 = date2.replace('"', '0');
						dateTmp = date2.split("-");
						dateTmp[2] = dateTmp[2].split("T")[0];
	
						dueDate.set(Integer.parseInt(dateTmp[0]), Integer.parseInt(dateTmp[1]) - 1,
								Integer.parseInt(dateTmp[2]), 23, 59, 0);
						campaign.setDueDate(dueDate.getTime());
						campaign.setAmountGoal(Double.parseDouble(amount));
					}
					
					campaign.setName(name);
					campaign.setActive(true);
					campaign.setCreationDate(new Date());
					campaign.setDescription(description);
					campaign.setStartDate(startDate.getTime());
					campaign.setNonprofit(nonprofit.getWrapperObject());
	
					int campaingId = campaignService.saveCampaign(campaign);
	
					if (campaingId > 0) {
						if (date2.equals("undefined")) {
							statePlans = createCampaignPlans(campaign.getNonprofit().getId(), campaingId,
									campaign.getNonprofit().getName(), campaign.getName(),amounts);
						} else {
							statePlans = true;
						}
						response.setCampaignId(campaingId);
						if (statePlans) {
	
							response.setCode(200);
							response.setCodeMessage("campaign created successfully");
						} else {
							response.setErrorMessage("can't create plans");
							response.setCode(400);
						}
	
					} else {
						response.setCode(400);
						response.setCodeMessage("campaign creation unsuccessful");
					}
				} else {
					response.setCode(400);
					response.setCodeMessage("NO IMAGE SPECIFIED");
				}
	
			} else
	
			{
				response.setCode(400);
				response.setCodeMessage("NONPROFIT DO NOT EXIST");
			}

		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				response.setCode(10);
				response.setErrorMessage("Data Base error");
			}else{
				response.setCode(500);
			}
		}
		return response;

	}

	/**
	 * Gets the nonprofit campaigns.
	 *
	 * @param cr
	 *            the Campaign Request
	 * @return the nonprofit campaigns
	 */
	@RequestMapping(value = "/nonprofitCampaigns", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse getNonprofitCampaigns(@RequestBody CampaignRequest cr) {
		
		CampaignResponse cs = new CampaignResponse();
		
		try{
		
			CampaignPOJO campaignPojo = null;
			cr.setPageNumber(cr.getPageNumber() - 1);
			PageWrapper<CampaignWrapper> pageResults = campaignService.getCampaignsByNonprofit(cr);
			cs.setTotalElements(pageResults.getTotalItems());
	
			List<CampaignPOJO> viewCampaignsPOJO = new ArrayList<CampaignPOJO>();
	
			for (CampaignWrapper objeto : pageResults.getResults()) {
				campaignPojo = new CampaignPOJO();
	
				campaignPojo.setId(objeto.getId());
				campaignPojo.setName(objeto.getName());
				campaignPojo.setDescription(objeto.getDescription());
				campaignPojo.setPicture(objeto.getPicture());
				campaignPojo.setAmountCollected(objeto.getAmountCollected());
				campaignPojo.setAmountGoal(objeto.getAmountGoal());
				campaignPojo.setPercent((int) Math.round((objeto.getAmountCollected() / objeto.getAmountGoal()) * 100));
				campaignPojo.setStartDate(objeto.getStartDate());
				campaignPojo.setStartDateS(objeto.getStartDateS());
				campaignPojo.setDueDate(objeto.getDueDate());
				campaignPojo.setDueDateS(objeto.getDueDateS());
				campaignPojo.setState(objeto.getState());
				campaignPojo.setStart(objeto.isStart());
				campaignPojo.setEnd(objeto.isEnd());
	
				viewCampaignsPOJO.add(campaignPojo);
	
			}
			;
	
			cs.setCampaigns(viewCampaignsPOJO);
	
			
				cs.setCodeMessage("campaigns fetch success");
				cs.setCode(200);
			

		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				cs.setCode(10);
				cs.setErrorMessage("Data Base error");
			}else{
				cs.setCode(500);
			}
		}
		
		return cs;

	}



	/**
	 * Creates the campaign plans.
	 *
	 * @param idNonprofit the id nonprofit
	 * @param idCampaign the id campaign
	 * @param nameNonprofit the name nonprofit
	 * @param nameCampaign the name campaign
	 * @param amounts the amounts
	 * @return the boolean
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	private Boolean createCampaignPlans(int idNonprofit, int idCampaign, String nameNonprofit, String nameCampaign, int[] amounts)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		int count = 1;

		for (int amount : amounts) {
			if (StripeUtils.createPlan(count, idNonprofit, idCampaign, nameNonprofit, nameCampaign, amount).getId()
					.equals(null)) {
				return false;
			}
			count++;
		}

		return true;
	}

	/**
	 * Search campaigns for nonprofit.
	 *
	 * @param cr
	 *            as CampaignRequest
	 * @return the campaign response
	 */
	@RequestMapping(value = "/searchCampaignsForNonprofit", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse searchCampaignForNonprofit(@RequestBody CampaignRequest cr) {

		CampaignResponse cs = new CampaignResponse();
		
		try{
		
			CampaignPOJO campaignPojo = null;
	
			PageWrapper<CampaignWrapper> pageResults = campaignService.findCampaignsFromNonprofit(cr);
	
			cs.setTotalElements(pageResults.getTotalItems());
	
			List<CampaignPOJO> viewCampaignsPOJO = new ArrayList<CampaignPOJO>();
	
			for (CampaignWrapper objeto : pageResults.getResults()) {
				campaignPojo = new CampaignPOJO();
				campaignPojo.setId(objeto.getId());
				campaignPojo.setName(objeto.getName());
				campaignPojo.setDescription(objeto.getDescription());
				campaignPojo.setPicture(objeto.getPicture());
				campaignPojo.setAmountCollected(objeto.getAmountCollected());
				campaignPojo.setAmountGoal(objeto.getAmountGoal());
				campaignPojo.setPercent((int) Math.round((objeto.getAmountCollected() / objeto.getAmountGoal()) * 100));
				campaignPojo.setStartDate(objeto.getStartDate());
				campaignPojo.setStartDateS(objeto.getStartDateS());
				campaignPojo.setDueDate(objeto.getDueDate());
				campaignPojo.setDueDateS(objeto.getDueDateS());
				campaignPojo.setState(objeto.getState());
				campaignPojo.setStart(objeto.isStart());
				campaignPojo.setEnd(objeto.isEnd());
	
				viewCampaignsPOJO.add(campaignPojo);
			}
			;
	
			cs.setCampaigns(viewCampaignsPOJO);
	
			cs.setCodeMessage("campaigns fetch success");
			cs.setCode(200);
			
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				cs.setCode(10);
				cs.setErrorMessage("Data Base error");
			}else{
				cs.setCode(500);
			}
		}

		return cs;

	}

	/**
	 * Gets the campaign profile.
	 *
	 * @param cr
	 *            the Campaign Request
	 * @return the campaign profile
	 */
	@RequestMapping(value = "/getCampignProfile", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse getCampaignProfile(@RequestBody CampaignRequest cr) {

		CampaignResponse cs = new CampaignResponse();
		
		try{
		
			HttpSession currentSession = request.getSession();
			int tempId = 0;
			
			if (cr.getIdUser() != 0) {
				tempId = (int) currentSession.getAttribute("idUser");
			}
	
			if (cr.getCampaign().getId() != 0) {
				CampaignWrapper campaign = campaignService.getCampaignById(cr.getCampaign().getId());
	
				if (campaign.getWrapperObject() != null) {
					if (tempId == campaign.getNonprofit().getUsergenerals().get(0).getId()) {
						cs.setOwner(true);
					} else {
						cs.setOwner(false);
					}
	
					CampaignPOJO campaignPojo = new CampaignPOJO();
	
					campaignPojo  = campaign.getCampaignPojo();
	
					NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();
	
					nonprofitPOJO.setId(campaign.getNonprofit().getId());
					nonprofitPOJO.setName(campaign.getNonprofit().getName());
					nonprofitPOJO.setProfilePicture(campaign.getNonprofit().getProfilePicture());
					campaignPojo.setCantDonors(donationService.findDonorsPerCampaign(campaign.getId()));
	
					campaignPojo.setNonprofit(nonprofitPOJO);
	
					cs.setCampaign(campaignPojo);
	
					cs.setCode(200);
					cs.setCodeMessage("campaign search success");
	
				} else {
					cs.setCode(400);
					cs.setErrorMessage("campaign search unsuccessful");
	
				}
			} else {
				cs.setCode(400);
				cs.setErrorMessage("campaign do not received");
			}
		
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				cs.setCode(10);
				cs.setErrorMessage("Data Base error");
			}else{
				cs.setCode(500);
			}
		}
		
		return cs;

	}

	/**
	 * Edits the campaign.
	 *
	 * @param cr
	 *            the campaign request
	 * @param fileCampaign
	 *            the campaign picture
	 * @return the campaign response
	 */
	@RequestMapping(value = "/editCampaign", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public CampaignResponse editCampaign(@RequestPart(value = "data") CampaignRequest cr,
			@RequestPart(value = "fileCampaign", required = false) MultipartFile fileCampaign) {

		String campaignImageName = "";
		CampaignResponse cs = new CampaignResponse();
		
		try{
			CampaignPOJO campaignPOJO = new CampaignPOJO();
			CampaignWrapper campaign = new CampaignWrapper();
			GregorianCalendar dueDate = new GregorianCalendar();
			GregorianCalendar startDate = new GregorianCalendar();
			Date date2 = cr.getDueDateData();
			Date date1 = cr.getStartDateData();
			dueDate.setTime(date2);
			startDate.setTime(date1);
	
			if (fileCampaign != null) {
				campaignImageName = Utils.writeToFile(fileCampaign, servletContext);
			}
	
			if (!campaignImageName.equals("")) {
				campaign.setPicture(campaignImageName);
			} else {
				campaign.setPicture(cr.getPicture());
			}
	
			campaign.setId(cr.getId());
			campaign.setName(cr.getName());
			campaign.setStartDate(startDate.getTime());
			campaign.setDueDate(dueDate.getTime());
			campaign.setDescription(cr.getDescription());
			campaign.setAmountGoal(cr.getAmountGoal());
			campaign.setAmountCollected(cr.getAmountCollected());
	
			campaignService.updateCampaign(campaign);
			CampaignWrapper campaignWrapper = campaignService.getCampaignById(cr.getId());
	
			campaignPOJO=campaignWrapper.getCampaignPojo();
			campaignPOJO.setCantDonors(donationService.findDonorsPerCampaign(cr.getId()));
	
			NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();
	
			nonprofitPOJO.setId(campaignWrapper.getNonprofit().getId());
			nonprofitPOJO.setName(campaignWrapper.getNonprofit().getName());
			nonprofitPOJO.setProfilePicture(campaignWrapper.getNonprofit().getProfilePicture());
	
			campaignPOJO.setNonprofit(nonprofitPOJO);
	
			cs.setCode(200);
			cs.setCodeMessage("Campaign updated sucessfully");
			cs.setCampaign(campaignPOJO);
		
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				cs.setCode(10);
				cs.setErrorMessage("Data Base error");
			}else{
				cs.setCode(500);
			}
		}
		return cs;
	}

	/**
	 * Delete non profit.
	 *
	 * @param cr
	 *            the campaign request
	 * @return the campaign response
	 */
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public CampaignResponse deleteNonProfit(@RequestBody CampaignRequest cr) {

		CampaignResponse cs = new CampaignResponse();
		
		try{
		
			CampaignWrapper wrapper;
			HttpSession currentSession = request.getSession();
			int tempId = 0;
	
			if (cr.getIdUser() != 0) {
				tempId = (int) currentSession.getAttribute("idUser");
			}
			if (tempId == cr.getIdUser()) {
				if (cr.getCampaign().getId() != 0) {
					wrapper = campaignService.getCampaignById(cr.getCampaign().getId());
					if (wrapper.getWrapperObject() != null) {
						try {
							campaignService.closeCampaign(wrapper.getId());
	
							cs.setCode(200);
							cs.setCodeMessage("campaign closed");
	
						} catch (Exception e) {
							cs.setCode(400);
							cs.setErrorMessage("ERROR DATABASE");
						}
					} else {
						cs.setCode(400);
						cs.setErrorMessage("campaign do not found");
					}
	
				} else {
					cs.setCode(400);
					cs.setErrorMessage("campaign do not received");
				}
	
			} else {
				cs.setCode(400);
				cs.setErrorMessage("No owner");
			}
		}catch(Exception e){
			if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
				cs.setCode(10);
				cs.setErrorMessage("Data Base error");
			}else{
				cs.setCode(500);
			}
		}

		return cs;
	}

}
