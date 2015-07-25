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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.DonationServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.utils.PageWrapper;
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
	DonationServiceInterface donationService;
	@Autowired
	CampaignServiceInterface campaignService;
	
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
	 * @param cr the Campaign Request
	 * @return the nonprofits
	 */
	@RequestMapping(value ="/advanceGet", method = RequestMethod.POST)
	public CampaignResponse getNonprofits(@RequestBody CampaignRequest cr){	
		
		cr.setPageNumber(cr.getPageNumber() - 1);
	
		Page<Campaign> viewCampaign = campaignService.getAllCampaigns(cr);
		
		CampaignResponse cs = new CampaignResponse();
	
		cs.setCodeMessage("campaigns fetch success");
		
		cs.setTotalElements(viewCampaign.getTotalElements());
		cs.setTotalPages(viewCampaign.getTotalPages());
		
		List<CampaignPOJO> viewCampaignPOJO = new ArrayList<CampaignPOJO>();
		
		for(Campaign objeto:viewCampaign.getContent())
		{
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
		return cs;
			
	}

	/**
	 * Campaing create.
	 *
	 * @param name the name
	 * @param description the description
	 * @param date1 the date1
	 * @param date2 the date2
	 * @param amount the amount
	 * @param idNonprofit the id nonprofit
	 * @param file the file
	 * @return the campaign response
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public CampaignResponse campaingCreate(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("date1") String date1, @RequestParam("date2") String date2,
			@RequestParam("amount") String amount, @RequestParam("idNonprofit") String idNonprofit,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		String resultFileName = null;
		NonprofitWrapper nonprofit = nonprofitService.getSessionNonprofit(Integer.parseInt(idNonprofit));
		CampaignResponse response = new CampaignResponse();
		String[] dateTmp;
		String[] dateTmp1;
		GregorianCalendar dueDate=new GregorianCalendar();
		GregorianCalendar startDate=new GregorianCalendar();

		if (nonprofit.getWrapperObject() != null) {
			if (file != null) {

				CampaignWrapper campaign = new CampaignWrapper();

				resultFileName = Utils.writeToFile(file, servletContext);

				if (!resultFileName.equals("")) {
					campaign.setPicture(resultFileName);
				} else {
					campaign.setPicture(TreeseedConstants.DEFAULT_CAPAIGN_IMAGE);
				}
				date2=date2.replace('"','0');
				dateTmp=date2.split("-");
				dateTmp[2]=dateTmp[2].split("T")[0];
				
				
				
				dueDate.set(Integer.parseInt(dateTmp[0]), Integer.parseInt(dateTmp[1])-1, Integer.parseInt(dateTmp[2]), 23, 59,0);
				
				date1=date1.replace('"','0');
				dateTmp1=date1.split("-");
				dateTmp1[2]=dateTmp1[2].split("T")[0];
				
				startDate.set(Integer.parseInt(dateTmp1[0]), Integer.parseInt(dateTmp1[1])-1, Integer.parseInt(dateTmp1[2]), 0, 00,0);
			
				campaign.setName(name);
				
				campaign.setActive(true);
				campaign.setCreationDate(new Date());
				campaign.setDescription(description);
				campaign.setDueDate(dueDate.getTime());
				campaign.setStartDate(startDate.getTime());
				campaign.setAmountGoal(Double.parseDouble(amount));
				campaign.setNonprofit(nonprofit.getWrapperObject());

				int campaingId = campaignService.saveCampaign(campaign);

				if (campaingId > 0) {
					response.setCampaignId(campaingId);
					response.setCode(200);
					response.setCodeMessage("campaign created successfully");
					
				}else{
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

		return response;

	}
	
	/**
	 * Gets the nonprofit campaigns.
	 *
	 * @param cr the Campaign Request
	 * @return the nonprofit campaigns
	 */
	@RequestMapping(value ="/nonprofitCampaigns", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse getNonprofitCampaigns(@RequestBody CampaignRequest cr){	
		CampaignPOJO campaignPojo = null;
		cr.setPageNumber(cr.getPageNumber() - 1);
		PageWrapper<CampaignWrapper> pageResults = campaignService.getCampaignsByNonprofit(cr);
		
		CampaignResponse cs = new CampaignResponse();
		
		
		cs.setTotalElements(pageResults.getTotalItems());
		
		List<CampaignPOJO> viewCampaignsPOJO = new ArrayList<CampaignPOJO>();
		
		for(CampaignWrapper objeto:pageResults.getResults())
		{
			campaignPojo = new CampaignPOJO();

			campaignPojo.setId(objeto.getId());
			campaignPojo.setName(objeto.getName());
			campaignPojo.setDescription(objeto.getDescription());
			campaignPojo.setPicture(objeto.getPicture());
			campaignPojo.setAmountCollected(objeto.getAmountCollected());
			campaignPojo.setAmountGoal(objeto.getAmountGoal());
			campaignPojo.setPercent((int)Math.round((objeto.getAmountCollected()/objeto.getAmountGoal())*100));
			campaignPojo.setStartDate(objeto.getStartDate());
			campaignPojo.setStartDateS(objeto.getStartDateS());
			campaignPojo.setDueDate(objeto.getDueDate());
			campaignPojo.setDueDateS(objeto.getDueDateS());
			campaignPojo.setState(objeto.getState());
			campaignPojo.setStart(objeto.isStart());
			campaignPojo.setEnd(objeto.isEnd());

			viewCampaignsPOJO.add(campaignPojo);
			
		};
		
		
		cs.setCampaigns(viewCampaignsPOJO);
		
		if(viewCampaignsPOJO.size()>0){
			cs.setCodeMessage("campaigns fetch success");
			cs.setCode(200);
		}else{
			cs.setErrorMessage("campaigns fetch unsuccessful");
			cs.setCode(400);
		}
		
		return cs;
			
	}
	

	

	/**
	 * Search campaigns for nonprofit.
	 *
	 * @param cr as CampaignRequest
	 * @return the campaign response
	 */
	@RequestMapping(value ="/searchCampaignsForNonprofit", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse searchCampaignForNonprofit(@RequestBody CampaignRequest cr){	
		try{
		CampaignPOJO campaignPojo = null;
		
		PageWrapper<CampaignWrapper> pageResults = campaignService.findCampaignsFromNonprofit(cr);
		
		CampaignResponse cs = new CampaignResponse();
		
		
		cs.setTotalElements(pageResults.getTotalItems());
		
		List<CampaignPOJO> viewCampaignsPOJO = new ArrayList<CampaignPOJO>();
		
		for(CampaignWrapper objeto: pageResults.getResults())
		{
			campaignPojo = new CampaignPOJO();
			campaignPojo.setId(objeto.getId());
			campaignPojo.setName(objeto.getName());
			campaignPojo.setDescription(objeto.getDescription());
			campaignPojo.setPicture(objeto.getPicture());
			campaignPojo.setAmountCollected(objeto.getAmountCollected());
			campaignPojo.setAmountGoal(objeto.getAmountGoal());
			campaignPojo.setPercent((int)Math.round((objeto.getAmountCollected()/objeto.getAmountGoal())*100));
			campaignPojo.setStartDate(objeto.getStartDate());
			campaignPojo.setStartDateS(new SimpleDateFormat("dd/MMM/yyyy").format(objeto.getStartDate()));
			campaignPojo.setState(objeto.getState());
			campaignPojo.setDueDate(objeto.getDueDate());
			campaignPojo.setDueDateS(new SimpleDateFormat("dd/MMM/yyyy").format(objeto.getDueDate()));
			viewCampaignsPOJO.add(campaignPojo);
		};

		cs.setCampaigns(viewCampaignsPOJO);
		
		if(viewCampaignsPOJO.size()>0){
			cs.setCodeMessage("campaigns fetch success");
			cs.setCode(200);
		}else{
			cs.setErrorMessage("campaigns fetch unsuccessful");
			cs.setCode(400);
		}
		
		return cs;
		}catch(Exception e){
			String a ="";
			return null;
		}
	}

	/**
	 * Gets the campaign profile.
	 *
	 * @param cr the Campaign Request
	 * @return the campaign profile
	 */
	@RequestMapping(value ="/getCampignProfile", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse getCampaignProfile(@RequestBody CampaignRequest cr){	
		
		HttpSession currentSession = request.getSession();
		int tempId= 0;
		CampaignResponse cs = new CampaignResponse();
		
		if(cr.getIdUser()!=0){
			tempId= (int) currentSession.getAttribute("idUser");
		}

		if(cr.getCampaign().getId()!=0){
			CampaignWrapper campaign = campaignService.getCampaignById(cr.getCampaign().getId());
			
			
			
			if(campaign.getWrapperObject()!= null){
				if(tempId==campaign.getNonprofit().getUsergenerals().get(0).getId()){
					cs.setOwner(true);
				}else{
					cs.setOwner(false);
				}
				
				CampaignPOJO campaignPojo = new CampaignPOJO();

				campaignPojo = new CampaignPOJO();
				campaignPojo.setId(campaign.getId());
				campaignPojo.setName(campaign.getName());
				campaignPojo.setDescription(campaign.getDescription());
				campaignPojo.setPicture(campaign.getPicture());
				campaignPojo.setAmountCollected(campaign.getAmountCollected());
				campaignPojo.setAmountGoal(campaign.getAmountGoal());
				campaignPojo.setPercent((int)Math.round(campaign.getPercent()));
				campaignPojo.setStartDate(campaign.getStartDate());
				campaignPojo.setStartDateS(campaign.getStartDateS());
				campaignPojo.setStart(campaign.isStart());
				campaignPojo.setEnd(campaign.isEnd());
				campaignPojo.setCantDonors(donationService.findDonorsPerCampaign(campaign.getId()));
				campaignPojo.setDueDate(campaign.getDueDate());
				campaignPojo.setDueDateS(campaign.getDueDateS());
				campaignPojo.setState(campaign.getState());
				
				NonprofitPOJO nonprofitPOJO = new NonprofitPOJO();
				
				nonprofitPOJO.setId(campaign.getNonprofit().getId());
				nonprofitPOJO.setName(campaign.getNonprofit().getName());
				nonprofitPOJO.setProfilePicture(campaign.getNonprofit().getProfilePicture());
				
				campaignPojo.setNonprofit(nonprofitPOJO);
				
				
				cs.setCampaign(campaignPojo);
				
				cs.setCode(200);
				cs.setCodeMessage("campaign search success");
				
			}else{
				cs.setCode(400);
				cs.setErrorMessage("campaign search unsuccessful");
				
			}
		}else{
			cs.setCode(400);
			cs.setErrorMessage("campaign do not received");
		}
		

		return cs;
			
	}
	
	

}
