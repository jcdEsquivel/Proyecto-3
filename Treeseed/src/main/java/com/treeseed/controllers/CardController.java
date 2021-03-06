package com.treeseed.controllers;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.mapping.Array;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.treeseed.utils.Utils;
import com.mysql.fabric.xmlrpc.base.Params;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.treeseed.contracts.CardRequest;
import com.treeseed.contracts.CardResponse;
import com.treeseed.contracts.CatalogRequest;
import com.treeseed.contracts.CatalogResponse;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.pojo.CardPOJO;
import com.treeseed.pojo.CatalogPOJO;
import com.treeseed.services.CardServiceInterface;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.utils.PojoUtils;
import com.treeseed.utils.StripeUtils;

// TODO: Auto-generated Javadoc
/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/card")
public class CardController {
	
	
	/** The catalog service. */
	@Autowired
	CardServiceInterface cardService;
	
	/** The donor service. */
	@Autowired
	DonorServiceInterface donorService;
	
	/** The jdbc template. */
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	/**
	 * Gets the card by donor.
	 *
	 * @param prams the card Request
	 * @return the card by donor
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	@RequestMapping(value ="/getByDonor", method = RequestMethod.POST)
	public CardResponse getCardByDonor(@RequestBody CardRequest prams) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		CardResponse us = new CardResponse();
		
		try{
		DonorWrapper donor=donorService.getDonorProfileByID(prams.getCard().getDonor().getId());
		List<CardPOJO> viewCardPOJO = new ArrayList<CardPOJO>();
		
		us.setErrorMessage("Success");
		
		if(donor.getWrapperObject()!=null){
			if(donor.getStripeId()!=null){
				List<CardWrapper> list = cardService.getCardByDonorId(donor.getWrapperObject());

				for(CardWrapper card:list){
					CardPOJO cardPojo = new CardPOJO();
					com.stripe.model.Card cardStripe = StripeUtils.getCard(donor.getStripeId(), card.getStripeId());
					cardPojo.setId(card.getId());
					cardPojo.setStripeId(cardStripe.getId());
					cardPojo.setBrand(cardStripe.getBrand());
					cardPojo.setExpMonth(cardStripe.getExpMonth());
					cardPojo.setExpYear(cardStripe.getExpYear());
					cardPojo.setLast4Numbers(cardStripe.getLast4());
					cardPojo.setActive(card.isActive());
					if(donor.getSubscriptionCard().getId()==card.getId()){
						cardPojo.setDefault(true);
					}
					viewCardPOJO.add(cardPojo);
				}
			
			}
			us.setCode(200);
			us.setErrorMessage("Fetch cards");
		}else{
			us.setCode(400);
			us.setErrorMessage("Donor not found");
		}
		us.setCards(viewCardPOJO);		
		return us;
		
	}catch(Exception e){
		if(e.getMessage().contains("Could not open JPA EntityManager for transaction")){
			us.setCode(10);
			us.setErrorMessage("Data Base error");
		}else{
			us.setCode(500);
		}
		
		return us;
	}
		
	}
}



