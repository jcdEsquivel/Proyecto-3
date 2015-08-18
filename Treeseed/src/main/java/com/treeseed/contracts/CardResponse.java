package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.CardPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class CardResponse.
 */
public class CardResponse extends BaseResponse {
	
/** The cards. */
private List<CardPOJO> cards;
	
	/**
	 * Instantiates a new card response.
	 */
	public CardResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public List<CardPOJO> getCards() {
		return cards;
	}

	/**
	 * Sets the cards.
	 *
	 * @param cards the new cards
	 */
	public void setCards(List<CardPOJO> cards) {
		this.cards = cards;
	}

}
