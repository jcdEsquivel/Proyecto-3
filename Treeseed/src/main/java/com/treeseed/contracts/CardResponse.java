package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.CardPOJO;

public class CardResponse extends BaseResponse {
	
private List<CardPOJO> cards;
	
	public CardResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<CardPOJO> getCards() {
		return cards;
	}

	public void setCards(List<CardPOJO> cards) {
		this.cards = cards;
	}

}
