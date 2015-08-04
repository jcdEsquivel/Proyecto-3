package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.repositories.DonorRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DonorService.
 */
@Service
public class DonorService implements DonorServiceInterface {

	/** The Donor repository. */
	@Autowired
	DonorRepository DonorRepository;

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonorServiceInterface#getAll(com.treeseed.contracts.DonorRequest)
	 */
	@Override
	@Transactional
	public Page<Donor> getAll(DonorRequest ur) {

		PageRequest pr;
		Sort.Direction direction = Sort.Direction.DESC;
		if (ur.getDirection().equals("ASC")) {
			direction = Sort.Direction.ASC;
		}

		if (ur.getSortBy().size() > 0) {
			Sort sort = new Sort(direction, ur.getSortBy());
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize(), sort);
		} else {
			pr = new PageRequest(ur.getPageNumber(), ur.getPageSize());
		}

		Page<Donor> result;

		String filterName = ur.getName();
		String filterLastName = ur.getLastName();
		int filterCountry = 0;

		if (ur.getCountry() != null) {
			filterCountry = Integer.parseInt(ur.getCountry());
		}

		result = DonorRepository.findAll(filterName, "%" + filterName + "%",
				filterCountry, filterLastName, "%" + filterLastName + "%", pr);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonorServiceInterface#saveDonor(com.treeseed.ejbWrapper.DonorWrapper)
	 */
	@Override
	@Transactional
	public int saveDonor(DonorWrapper user) {
		Donor nuser = DonorRepository.save(user.getWrapperObject());
		return nuser.getId();
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonorServiceInterface#getSessionDonor(int)
	 */
	@Override
	@Transactional
	public Donor getSessionDonor(int idUser) {
		return DonorRepository.findOne(idUser);
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonorServiceInterface#getDonorProfileByID(com.treeseed.contracts.DonorRequest)
	 */
	@Override
	public Donor getDonorProfileByID(DonorRequest dr) {
		return DonorRepository.findByid(dr.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonorServiceInterface#deleteDonor(com.treeseed.contracts.DonorRequest)
	 */
	@Override
	public void deleteDonor(DonorRequest dr)
	{	
		DonorRepository.deleteDonor(dr.getId());		
	}

	/* (non-Javadoc)
	 * @see com.treeseed.services.DonorServiceInterface#updateDonor(com.treeseed.ejbWrapper.DonorWrapper)
	 */
	@Override
	@Transactional
	public void updateDonor(DonorWrapper donor) {
		DonorRepository.update(donor.getId(),
				donor.getName(), 
				donor.getLastName(),
				donor.getDescription(),
				donor.getProfilePicture(),
				donor.getWebPage()
				);		
	}
	
	/* (non-Javadoc)
	 * @see com.treeseed.services.DonorServiceInterface#getDonorById(int)
	 */
	@Override
	public DonorWrapper getDonorById(int id){
		DonorWrapper donor;
		
		donor= new DonorWrapper(DonorRepository.findByid(id));
		
		return donor;
		
	}
}