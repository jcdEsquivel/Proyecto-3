package com.treeseed.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.Card;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.repositories.CardRepository;
import com.treeseed.repositories.UserGeneralRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CardService.
 */
@Service
public class CardService implements CardServiceInterface{
	
	/** The card repository. */
	@Autowired
	CardRepository cardRepository;

	/* (non-Javadoc)
	 * @see com.treeseed.services.CardServiceInterface#saveCard(com.treeseed.ejbWrapper.CardWrapper)
	 */
	@Override
	public Boolean saveCard(CardWrapper card) {
		cardRepository.save(card.getWrapperObject());
		return null;
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.CardServiceInterface#getCardByID(int)
	 */
	@Override
	public CardWrapper getCardByID(int idCard) {
		// TODO Auto-generated method stub
		return new CardWrapper(cardRepository.findOne(idCard));
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.CardServiceInterface#updateCard(com.treeseed.ejbWrapper.CardWrapper)
	 */
	@Override
	public void updateCard(CardWrapper card) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.CardServiceInterface#getCardByDonorId(com.treeseed.ejb.Donor)
	 */
	@Override
	public List<CardWrapper> getCardByDonorId(Donor donor) {
		List<Card> cards = cardRepository.findByDonor(donor);
		List<CardWrapper> cardsWrapper = new ArrayList<CardWrapper>();
		for(Card card:cards){
			CardWrapper cardWrapper = new CardWrapper(card);
			cardsWrapper.add(cardWrapper);
		}
		return cardsWrapper;
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.CardServiceInterface#deleteCard(com.treeseed.ejbWrapper.CardWrapper)
	 */
	@Override
	public void deleteCard(CardWrapper card) {
		// TODO Auto-generated method stub
		
	}

	
}