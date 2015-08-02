package com.treeseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.treeseed.contracts.DonorRequest;
import com.treeseed.ejb.Donor;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.repositories.DonorRepository;

@Service
public class DonorService implements DonorServiceInterface {

	@Autowired
	DonorRepository DonorRepository;

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

	@Override
	@Transactional
	public int saveDonor(DonorWrapper user) {
		Donor nuser = DonorRepository.save(user.getWrapperObject());
		return nuser.getId();
	}

	@Override
	@Transactional
	public Donor getSessionDonor(int idUser) {
		return DonorRepository.findOne(idUser);
	}

	@Override
	public DonorWrapper getDonorProfileByID(int id) {
		return new DonorWrapper(DonorRepository.findByid(id));
	}
	
	@Override
	public void deleteDonor(DonorRequest dr)
	{	
		DonorRepository.deleteDonor(dr.getId());		
	}

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
	
	@Override
	public DonorWrapper update(DonorWrapper donor) {
		return null;//return new DonorWrapper(DonorRepository.update(donor.getWrapperObject()));	
	}
}