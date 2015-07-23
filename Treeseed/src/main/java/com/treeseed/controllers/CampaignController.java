package com.treeseed.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;




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

import com.treeseed.contracts.CampaignRequest;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.contracts.DonorResponse;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.DonorPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.pojo.UserGeneralPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
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
	 * @param cr the cr
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
				
				startDate.set(Integer.parseInt(dateTmp[0]), Integer.parseInt(dateTmp[1])-1, Integer.parseInt(dateTmp[2])-1, 23, 59,0);
			
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
	 * @param cr the cr
	 * @return the nonprofit campaigns
	 */
	@RequestMapping(value ="/nonprofitCampaigns", method = RequestMethod.POST)
	@Transactional
	public CampaignResponse getNonprofitCampaigns(@RequestBody CampaignRequest cr){	
		CampaignPOJO campaignPojo = null;
		cr.setPageNumber(cr.getPageNumber() - 1);
		Page<Campaign> viewCampaign = campaignService.getCampaignsByNonprofit(cr);
		
		CampaignResponse cs = new CampaignResponse();
		
		
		cs.setTotalElements(viewCampaign.getTotalElements());
		cs.setTotalPages(viewCampaign.getTotalPages());
		
		List<CampaignPOJO> viewCampaignsPOJO = new ArrayList<CampaignPOJO>();
		
		for(Campaign objeto:viewCampaign.getContent())
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
			
			if(objeto.getStartDate().after(new Date())){
				campaignPojo.setStart(true);
			}else{
				campaignPojo.setStart(false);
			}
			
			if(objeto.getDueDate().after(new Date())){
				campaignPojo.setEnd(true);
			}else{
				campaignPojo.setEnd(false);
			}
			
			Date current = new Date();

			if(objeto.getStartDate().after(current)){
				campaignPojo.setState("soon"); 
			} else if( objeto.getStartDate().before(current) && objeto.getDueDate().after(current) ){
				campaignPojo.setState("active"); 
			}else{
				campaignPojo.setState("finished"); 
			}
			
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
			
	}
	
	/**
	 * Edits the campaign.
	 *
	 * @param dr the dr
	 * @param fileCover the file cover
	 * @param fileProfile the file profile
	 * @return the donor response
	 */
	@RequestMapping(value ="/editDonor", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public CampaignResponse editCampaign(@RequestPart(value="data") CampaignRequest cr, @RequestPart(value="fileCampaign", required=false) MultipartFile fileCampaign){
		
		String campaignImageName = "";
		CampaignResponse cs = new CampaignResponse();
		CampaignPOJO campaignPOJO = new CampaignPOJO();
		CampaignWrapper campaign = new CampaignWrapper();
		Campaign campaignObject = new Campaign();
		
		if(fileCampaign!=null){
			campaignImageName = Utils.writeToFile(fileCampaign,servletContext);
		}

		if(!campaignImageName.equals("")){
			campaign.setPicture(campaignImageName);
		}else{
			campaign.setPicture(cr.getPicture());
		}
		
		campaign.setId(cr.getId());
		campaign.setName(cr.getName());
		//campaign.setStartDate(cr.getStartDate());
		//campaign.setDueDate(cr.getDueDate());
		campaign.setDescription(cr.getDescription());
		campaign.setAmountGoal(cr.getAmountGoal());
		campaign.setAmountCollected(cr.getAmountCollected());
		
		campaignService.updateCampaign(campaign);
		
		campaignPOJO.setId(cr.getId());
		campaignPOJO.setName(cr.getName());
		//campaign.setStartDate(cr.getStartDate());
		//campaign.setDueDate(cr.getDueDate());
		campaignPOJO.setDescription(cr.getDescription());
		campaignPOJO.setAmountGoal(cr.getAmountGoal());
		campaignPOJO.setAmountCollected(cr.getAmountCollected());
		
		cs.setCode(200);
		cs.setCodeMessage("Campaign updated sucessfully");
		cs.setCampaign(campaignPOJO);

		return cs;		
	}

}
