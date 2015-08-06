package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.Card;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface CardServiceInterface.
 */
public interface CardServiceInterface {

	/**
	 * Save card.
	 *
	 * @param card the card
	 * @return the boolean
	 */
	Boolean saveCard(CardWrapper card);
	
	/**
	 * Gets the card by id.
	 *
	 * @param idUCard the id u card
	 * @return the card by id
	 */
	CardWrapper getCardByID(int idUCard);
	
	/**
	 * Update card.
	 *
	 * @param card the card
	 */
	void updateCard(CardWrapper card);
		
	/**
	 * Gets the card by donor id.
	 *
	 * @param donor the donor
	 * @return the card by donor id
	 */
	List<CardWrapper> getCardByDonorId(Donor donor);

	/**
	 * Delete card.
	 *
	 * @param card the card
	 */
	void deleteCard(CardWrapper card);
}
