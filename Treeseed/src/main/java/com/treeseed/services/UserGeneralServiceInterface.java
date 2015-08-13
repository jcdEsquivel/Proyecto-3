package com.treeseed.services;

import org.springframework.data.domain.Page;

import com.treeseed.contracts.UserGeneralRequest;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.UserGeneralWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserGeneralServiceInterface.
 */
public interface UserGeneralServiceInterface {

	/**
	 * Gets the all.
	 *
	 * @param ur the General Request User
	 * @return the all
	 */
	Page<UserGeneral> getAll(UserGeneralRequest ur);

	/**
	 * Save user general.
	 *
	 * @param userGeneral the user general
	 * @return the boolean
	 */
	Boolean saveUserGeneral(UserGeneralWrapper userGeneral);

	/**
	 * Gets the session user general.
	 *
	 * @param idUserGeneral the id user general
	 * @return the session user general
	 */
	UserGeneral getSessionUserGeneral(int idUserGeneral);
	
	/**
	 * Gets the user by email and password.
	 *
	 * @param ur the ur
	 * @param pas the pas
	 * @return the user by email and password
	 */
	UserGeneral getUserByEmailAndPassword(String ur, String pas );
	
	/**
	 * Gets the user by email and password and is active.
	 *
	 * @param ur the ur
	 * @param pas the pas
	 * @param active the active
	 * @return the user by email and password and is active
	 */
	UserGeneral getUserByEmailAndPasswordAndIsActive(String ur, String pas, boolean active);

	/**
	 * Checks if is email unique.
	 *
	 * @param email the email
	 * @return the boolean
	 */
	Boolean isEmailUnique(String email);
	
	/**
	 * User exist.
	 *
	 * @param email the email
	 * @return the boolean
	 */
	Boolean userExist(String email);
	
	/**
	 * Validate facebook id.
	 *
	 * @param facebookId the facebook id
	 * @return the boolean
	 */
	Boolean validateFacebookId(String facebookId);
	
	/**
	 * Gets the UG by id.
	 *
	 * @param idUserGeneral the id user general
	 * @return the UG by id
	 */
	UserGeneral getUGByID(int idUserGeneral);
	
	/**
	 * Update user general.
	 *
	 * @param UserGeneral the user general
	 */
	void updateUserGeneral(UserGeneralWrapper UserGeneral);
	
	/**
	 * Gets the user by nonprofit id.
	 *
	 * @param idNonprofit the id nonprofit
	 * @return the user by nonprofit id
	 */
	UserGeneral getUserByNonprofitId(int idNonprofit);
	
	/**
	 * Gets the user by donor id.
	 *
	 * @param idDonor the id donor
	 * @return the user by donor id
	 */
	UserGeneralWrapper getUserByDonorId(int idDonor);

	/**
	 * Delete user general.
	 *
	 * @param ugw the user general wrapper
	 */
	void deleteUserGeneral(UserGeneralWrapper ugw);


}
