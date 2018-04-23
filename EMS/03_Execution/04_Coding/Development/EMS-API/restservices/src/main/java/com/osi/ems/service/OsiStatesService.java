package com.osi.ems.service;

import java.util.List;

import com.osi.ems.domain.OsiStates;
import com.osi.urm.exception.BusinessException;

/**
 * The interface for publishing the method to deal with states details.
 * @author jkolla
 *
 */
public interface OsiStatesService {
	
	
	/**
	 * Method for saving the States.
	 * @param osiState
	 * @return Returns the Same {@link OsiStates} object.
	 * @throws BusinessException 
	 */
	public OsiStates saveState(OsiStates osiState,Integer userId) throws BusinessException;
	
	/**
	 * Method for updating the {@link OsiStates} object.
	 * @param osiStates
	 * @param stateId
	 * @return Returns the updated {@link OsiStates} object.
	 * @throws BusinessException 
	 */
	public OsiStates updateState(OsiStates osiStates,Integer stateId, Integer userId) throws BusinessException;
	
	/**
	 * Method for getting all the states list.
	 * @return Returns the {@link List} of {@link OsiStates}.
	 * @throws BusinessException 
	 */
	public List<OsiStates> getAllStatesList() throws BusinessException;
	
	/**
	 * Method for getting the states by id.
	 * @param stateId
	 * @return Returns the {@link OsiStates} object.
	 * @throws BusinessException 
	 */
	public OsiStates getStateByStateId(Integer stateId) throws BusinessException;
	
	/**
	 * Method for deleting the state id.
	 * @param stateId
	 *  Returns void.
	 * @throws BusinessException 
	 */
	public void deleteOsiStates(Integer stateId) throws BusinessException;

	/**
	 * The method for finding the osi states list from the given country id.
	 * @param countryId
	 * @return Returns the country list.
	 * @throws BusinessException Throws {@link BusinessException} if any error occurs.
	 */
	public List<OsiStates> findStatesListByCountryId(Integer countryId) throws BusinessException;

	

}
