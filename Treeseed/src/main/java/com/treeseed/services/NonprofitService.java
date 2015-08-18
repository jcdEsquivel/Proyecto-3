package com.treeseed.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.NonprofitRequest;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.repositories.NonprofitRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class NonprofitService.
 */
@Service
public class NonprofitService implements NonprofitServiceInterface{

	/** The nonprofits repository. */
	@Autowired
	NonprofitRepository nonprofitsRepository;

	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#getNonProfit(com.treeseed.contracts.NonprofitRequest)
	 */
	@Override
	@Transactional
	public Page<Nonprofit> getNonProfit(NonprofitRequest ur) {
	
		PageRequest pr;
		Sort.Direction direction = Sort.Direction.DESC;
		if(ur.getDirection().equals("ASC")){
			direction = Sort.Direction.ASC;
		}
		
		if(ur.getSortBy().size() > 0){
			Sort sort = new Sort(direction,ur.getSortBy());
			pr = new PageRequest(ur.getPageNumber(),
					ur.getPageSize(),sort);
		}else{
			pr = new PageRequest(ur.getPageNumber(),
					ur.getPageSize());
		}
		
		Page<Nonprofit> pageResult = null;
		
		String filterName = ur.getName();
		int filterCause= 0;
		int filterCountry= 0;
		
		
		if(ur.getCause()!=null){
			filterCause = Integer.parseInt(ur.getCause());
		}else if(ur.getCountry()!=null){
			filterCountry = Integer.parseInt(ur.getCountry());
		}
		
	
		pageResult = nonprofitsRepository.findConTodo(filterName, "%"+filterName+"%",filterCountry,filterCause, pr);
		
		return pageResult ;
		
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#saveNonprofit(com.treeseed.ejbWrapper.NonprofitWrapper)
	 */
	@Override
	@Transactional
	public int saveNonprofit(NonprofitWrapper nonProfit) {
		
		Nonprofit nuser = nonprofitsRepository.save(nonProfit.getWrapperObject());
		return nuser.getId();
		
	}


	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#getSessionNonprofit(int)
	 */
	@Override
	public NonprofitWrapper getSessionNonprofit(int idNonprofit) {
		return new NonprofitWrapper(nonprofitsRepository.findOne(idNonprofit));
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#getByName(java.lang.String)
	 */
	@Override
	public Page<Nonprofit> getByName(String name) {
		PageRequest pr = null;
		return nonprofitsRepository.findByNameContaining(name,pr);	
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#getByCountry(com.treeseed.contracts.NonprofitRequest)
	 */
	@Override
	public Page<Nonprofit> getByCountry(NonprofitRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#getByCause(com.treeseed.contracts.NonprofitRequest)
	 */
	@Override
	public Page<Nonprofit> getByCause(NonprofitRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#getNonProfitByID(com.treeseed.contracts.NonprofitRequest)
	 */
	@Override
	public NonprofitWrapper getNonProfitByID(NonprofitRequest ur) {
		
		Nonprofit nonProfit= new Nonprofit();
		NonprofitWrapper nonProfitWrapper= new NonprofitWrapper();
		
		nonProfit= nonprofitsRepository.findByid(ur.getId());
		nonProfitWrapper.setWrapperObject(nonProfit);
		
		return nonProfitWrapper;
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#getNonProfitById(int)
	 */
	@Override
	public NonprofitWrapper getNonProfitById(int id) {
		return new NonprofitWrapper(nonprofitsRepository.findOne(id));
	}
	
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#updateNonProfit(com.treeseed.ejbWrapper.NonprofitWrapper)
	 */
	@Override
	@Transactional
	public Nonprofit updateNonProfit(NonprofitWrapper nonProfit) {
		nonprofitsRepository.update(nonProfit.getId(),
				nonProfit.getName(), 
				nonProfit.getDescription(),
				nonProfit.getMision(),
				nonProfit.getReason(),
				nonProfit.getMainPicture(),
				nonProfit.getProfilePicture(),
				nonProfit.getWebPage()
				);
		
		return nonprofitsRepository.findOne(nonProfit.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#deteteNonprofit(com.treeseed.ejbWrapper.NonprofitWrapper)
	 */
	@Override
	@Transactional
	public Nonprofit deteteNonprofit(NonprofitWrapper nonProfit) {
		nonprofitsRepository.delete(nonProfit.getId());
		
		return nonprofitsRepository.findOne(nonProfit.getId());
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#donorRecomendation(int)
	 */
	@Override
	public List<NonprofitWrapper> donorRecomendation(int idDonor) {
		PageRequest pr=new PageRequest(0,10);
		List<Nonprofit> nonprofits = nonprofitsRepository.findTop10DonorRecomendations(idDonor,pr).getContent();
		List<NonprofitWrapper> nonprofitsWrapper = new ArrayList<NonprofitWrapper>();
		
		
		for(Nonprofit nonprofit:nonprofits){
			NonprofitWrapper nonprofitWrapper = new NonprofitWrapper(nonprofit);
			nonprofitsWrapper.add(nonprofitWrapper);
		}
		
		return  nonprofitsWrapper;
		
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.NonprofitServiceInterface#donorRecomendationRandom()
	 */
	@Override
	public List<NonprofitWrapper> donorRecomendationRandom() {
		PageRequest pr=new PageRequest(0,10);
		
		List<Nonprofit> nonprofits = nonprofitsRepository.findTop10DonorRecomendationsRandom(pr).getContent();
		List<NonprofitWrapper> nonprofitsWrapper = new ArrayList<NonprofitWrapper>();
		
		
		for(Nonprofit nonprofit:nonprofits){
			NonprofitWrapper nonprofitWrapper = new NonprofitWrapper(nonprofit);
			nonprofitsWrapper.add(nonprofitWrapper);
		}
		
		return  nonprofitsWrapper;
	}
	
	@Override
	public boolean isNameUnique(String name){
		Nonprofit n = nonprofitsRepository.findByName(name);
		
		if(n == null){
			return true;
		}else{
			return false;
		}
	}
	

}