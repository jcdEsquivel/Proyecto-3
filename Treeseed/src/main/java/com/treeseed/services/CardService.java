package com.treeseed.services;

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
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.CardWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.repositories.CardRepository;
import com.treeseed.repositories.UserGeneralRepository;

@Service
public class CardService implements CardServiceInterface{
	@Autowired
	CardRepository cardRepository;

	@Override
	public Boolean saveCard(CardWrapper card) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardWrapper getCardByID(int idCard) {
		// TODO Auto-generated method stub
		return new CardWrapper(cardRepository.findOne(idCard));
	}

	@Override
	public void updateUserGeneral(CardWrapper card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CardWrapper> getUserByDonorId(int idDonor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCard(CardWrapper card) {
		// TODO Auto-generated method stub
		
	}

	
}