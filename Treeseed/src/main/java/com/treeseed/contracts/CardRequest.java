package com.treeseed.contracts;

import com.treeseed.pojo.CardPOJO;
import com.treeseed.pojo.CatalogPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class CardRequest.
 */
public class CardRequest {
	
	/** The card. */
	private CardPOJO card;
	
	/**
	 * Instantiates a new card request.
	 */
	public CardRequest() {
		super();
	}

	/**
	 * Gets the card.
	 *
	 * @return the card
	 */
	public CardPOJO getCard() {
		return card;
	}

	/**
	 * Sets the card.
	 *
	 * @param card the new card
	 */
	public void setCard(CardPOJO card) {
		this.card = card;
	}
	
}
