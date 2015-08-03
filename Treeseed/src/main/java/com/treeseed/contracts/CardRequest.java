package com.treeseed.contracts;

import com.treeseed.pojo.CardPOJO;
import com.treeseed.pojo.CatalogPOJO;

public class CardRequest {
	
	private CardPOJO card;
	
	public CardRequest() {
		super();
	}

	public CardPOJO getCard() {
		return card;
	}

	public void setCard(CardPOJO card) {
		this.card = card;
	}
	
}
