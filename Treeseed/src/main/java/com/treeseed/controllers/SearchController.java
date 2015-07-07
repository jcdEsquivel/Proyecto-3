package com.treeseed.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.contracts.NonprofitResponse;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.utils.PojoUtils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/searches")
public class SearchController {

	//Codigo comentado para usar como base
	
	@Autowired
	NonprofitServiceInterface nonProfitService;
	
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value ="/getNonprofits", method = RequestMethod.POST)
	@Transactional
	public NonprofitResponse getNonprofits(@RequestBody NonprofitRequest npr){	
		
		npr.setPageNumber(npr.getPageNumber() - 1);
		
		Page<Nonprofit> viewNonprofits = nonProfitService.getNonProfit(npr);
		
		NonprofitResponse nps = new NonprofitResponse();
		
		nps.setCode(200);
		nps.setCodeMessage("nonprofits fetch success");
		
		
		nps.setTotalElements(viewNonprofits.getTotalElements());
		nps.setTotalPages(viewNonprofits.getTotalPages());
		
		List<NonprofitPOJO> viewNonprofitsPOJO = new ArrayList<NonprofitPOJO>();
		
		for(Nonprofit objeto:viewNonprofits.getContent())
		{
			NonprofitPOJO nnonprofit = new NonprofitPOJO();
			nnonprofit.setName(objeto.getName());
			nnonprofit.setDescription(objeto.getDescription());
			nnonprofit.setWebPage(objeto.getWebPage());
			nnonprofit.setProfilePicture(objeto.getProfilePicture());
			viewNonprofitsPOJO.add(nnonprofit);
		};
		
		
		nps.setNonprofits(viewNonprofitsPOJO);
		nps.setCode(200);
		return nps;
			
	}
	
	
	
	
	public NonprofitResponse setNonProfitResults(Page<Nonprofit> nonprofits){
		NonprofitResponse nps = new NonprofitResponse();
	
		nps.setCode(200);
		nps.setCodeMessage("nonprofits fetch success");
		nps.setTotalElements(nonprofits.getTotalElements());
		nps.setTotalPages(nonprofits.getTotalPages());
		
		
		List<NonprofitPOJO> viewNonprofits = new ArrayList<NonprofitPOJO>();
		
		nonprofits.getContent().forEach(np->{
			NonprofitPOJO nnonprofit = new NonprofitPOJO();
			nnonprofit.setName(np.getName());
			nnonprofit.setReason(np.getReason());
			nnonprofit.setMainPicture(np.getMainPicture());
			viewNonprofits.add(nnonprofit);
		});
		
		nps.setNonprofits(viewNonprofits);
		return nps;		
		
	}
	
	/*@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value ="/getAllCountries", method = RequestMethod.POST)
	public List<Catalog> getAllCountries(){	
	
		List<Catalog> list = jdbcTemplate.query(
                "SELECT id, name FROM catalog WHERE type = ?", new Object[] { "Country" },
                (rs, rowNum) -> new Catalog(rs.getInt("id"), rs.getString("name"))
        );

		return list;

	}
	*/
	
}
