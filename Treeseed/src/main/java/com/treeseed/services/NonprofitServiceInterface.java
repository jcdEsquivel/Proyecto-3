package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.NonprofitWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface NonprofitServiceInterface.
 */
public interface NonprofitServiceInterface {

	/**
	 * Gets the non profit.
	 *
	 * @param ur the ur
	 * @return the non profit
	 */
	Page<Nonprofit> getNonProfit(NonprofitRequest ur);

	/**
	 * Save nonprofit.
	 *
	 * @param nonprofit the nonprofit
	 * @return the int
	 */
	int saveNonprofit(NonprofitWrapper nonprofit);

	/**
	 * Gets the session nonprofit.
	 *
	 * @param idNonprofit the id nonprofit
	 * @return the session nonprofit
	 */
	NonprofitWrapper getSessionNonprofit(int idNonprofit);

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	public Page<Nonprofit> getByName(String name);
	
	/**
	 * Gets the by country.
	 *
	 * @param ur the ur
	 * @return the by country
	 */
	Page<Nonprofit> getByCountry(NonprofitRequest ur);
	
	/**
	 * Gets the by cause.
	 *
	 * @param ur the ur
	 * @return the by cause
	 */
	Page<Nonprofit> getByCause(NonprofitRequest ur);
	
	/**
	 * Gets the non profit by id.
	 *
	 * @param ur the ur
	 * @return the non profit by id
	 */
	NonprofitWrapper getNonProfitByID(NonprofitRequest ur);
	
	/**
	 * Gets the non profit by id.
	 *
	 * @param id the id
	 * @return the non profit by id
	 */
	NonprofitWrapper getNonProfitById(int id);
	
	/**
	 * Update non profit.
	 *
	 * @param nonprofit the nonprofit
	 * @return the nonprofit
	 */
	Nonprofit updateNonProfit(NonprofitWrapper nonprofit);
	
	/**
	 * Detete nonprofit.
	 *
	 * @param nonProfit the non profit
	 * @return the nonprofit
	 */
	Nonprofit deteteNonprofit(NonprofitWrapper nonProfit);	
	
	
	boolean isNameUnique(String name);
	
	/**
	 * Donor recomendation.
	 *
	 * @param idDonor the id donor
	 * @return the list
	 */
	List<NonprofitWrapper> donorRecomendation(int idDonor);
	
	/**
	 * Donor recomendation random.
	 *
	 * @return the list
	 */
	List<NonprofitWrapper> donorRecomendationRandom();
}
