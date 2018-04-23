package com.osi.ems.service;

import java.util.List;

import com.osi.ems.domain.OsiCountries;
import com.osi.urm.exception.BusinessException;

/**
 * The interface for publishing the method to deal with country details.
 * @author jkolla
 *
 */
public interface OsiCountriesService {
	
	
	/**
	 * Method for saving the countries.
	 * @param osiCountry
	 * @return Returns the Same {@link OsiCountries} object.
	 * @throws BusinessException 
	 */
	public OsiCountries saveCountry(OsiCountries osiCountry,Integer userId) throws BusinessException;
	
	/**
	 * Method for updating the {@link OsiCountries} object.
	 * @param osiCountries
	 * @param countryId
	 * @return Returns the updated {@link OsiCountries} object.
	 * @throws BusinessException 
	 */
	public OsiCountries updateCountry(OsiCountries osiCountries,Integer countryId, Integer userId) throws BusinessException;
	
	/**
	 * Method for getting all the countries list.
	 * @return Returns the {@link List} of {@link OsiCountries}.
	 * @throws BusinessException 
	 */
	public List<OsiCountries> getAllCountriesList() throws BusinessException;
	
	/**
	 * Method for getting the country by id.
	 * @param countryId
	 * @return Returns the {@link OsiCountries} object.
	 * @throws BusinessException 
	 */
	public OsiCountries getCountryByCountryId(Integer countryId) throws BusinessException;
	
	/**
	 * Method for deleting the country id.
	 * @param countryId Returns void.
	 * @throws BusinessException 
	 */
	public void deleteOsiCountries(Integer countryId) throws BusinessException;

	public List<OsiCountries> getCountryCodesList() throws BusinessException;


	

}
