package com.treeseed.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.Card;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;

public interface CardServiceInterface {

	Boolean saveCard(CardWrapper card);
	
	CardWrapper getCardByID(int idUCard);
	
	void updateCard(CardWrapper card);
		
	List<CardWrapper> getCardByDonorId(Donor donor);

	void deleteCard(CardWrapper card);
}
