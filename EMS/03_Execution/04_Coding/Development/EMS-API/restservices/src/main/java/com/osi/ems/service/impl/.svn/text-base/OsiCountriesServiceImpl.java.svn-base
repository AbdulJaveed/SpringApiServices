package com.osi.ems.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.domain.OsiCountries;
import com.osi.ems.repository.CountryRepository;
import com.osi.ems.service.OsiCountriesService;
import com.osi.urm.exception.BusinessException;

/**
 * Class that publish the behavior provided by {@link OsiCountriesService}
 * interface.
 * 
 * @author jkolla
 *
 */
@Service
@Transactional
public class OsiCountriesServiceImpl implements OsiCountriesService {

	private final Logger log = LoggerFactory.getLogger(OsiCountriesServiceImpl.class);

	/**
	 * Field for communicating with database.
	 */
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public OsiCountries saveCountry(OsiCountries osiCountry, Integer userId) throws BusinessException {

		String createdDate = null;
		Timestamp timestamp = null;
		/*
		 * log the message
		 */
		log.debug("Request to save osiCountry : {}", osiCountry);
		log.info("saveCountry : Begin");
		timestamp = new Timestamp(System.currentTimeMillis());
		createdDate = new SimpleDateFormat("yyyy/MM/dd").format(timestamp);
		osiCountry.setCreatedBy(userId);
		osiCountry.setCreationDate(createdDate);
		osiCountry.setLastUpdatedBy(userId);
		osiCountry.setLastUpdateDate(createdDate);
		/*
		 * Save the osicountry object.
		 */
		try {

			osiCountry = countryRepository.save(osiCountry);
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the Country");
		}
		log.info("saveCountry : End");
		return osiCountry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiCountryService#updateCountry(com.osi.ems.domain.
	 * OsiCountries, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public OsiCountries updateCountry(OsiCountries osiCountries, Integer countryId, Integer userId)
			throws BusinessException {
		String createdDate = null;
		Timestamp timestamp = null;
		OsiCountries updatedCountry = null;

		/*
		 * log the message
		 */
		log.debug("Request to Update osiCountry : {}", osiCountries);
		log.info("updateCountry : Begin");
		/*
		 * Check if object exists with this id or not.
		 */
		try {
			if ((updatedCountry = this.countryRepository.findOne(countryId)) != null) {
				timestamp = new Timestamp(System.currentTimeMillis());
				createdDate = new SimpleDateFormat("yyyy/MM/dd").format(timestamp);
				osiCountries.setCountryId(countryId);
				osiCountries.setCreatedBy(updatedCountry.getCreatedBy());
				osiCountries.setCreationDate(updatedCountry.getCreationDate());
				osiCountries.setLastUpdatedBy(userId);
				osiCountries.setLastUpdateDate(createdDate);

				/*
				 * Save the object
				 */
				this.countryRepository.save(osiCountries);
			} else {
				osiCountries = this.saveCountry(osiCountries, userId);
			}
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the Country");
		}
		log.info("updateCountry : End");

		return osiCountries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiCountryService#getAllCountriesList()
	 */
	@Override
	public List<OsiCountries> getAllCountriesList() throws BusinessException {
		log.info("getAllCountriesList : Begin");
		List<OsiCountries> osiCountriesList = null;
		log.debug("Request to getall osiCountries ");
		try {
			osiCountriesList = this.countryRepository.findAll();
			log.info("getAllCountriesList : End");
			return osiCountriesList;
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the countries");
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiCountryService#getCountryByCountryId(java.lang.
	 * Integer)
	 */
	@Override
	public OsiCountries getCountryByCountryId(Integer countryId) throws BusinessException {
		OsiCountries osiCountry = null;
		log.debug("Request to get osiCountries ");
		log.info("getCountryByCountryId : Begin");
		try {
			osiCountry = this.countryRepository.findOne(countryId);
			log.info("getCountryByCountryId : End");
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the countries by country id "+countryId);
		}
		return osiCountry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osi.ems.service.OsiCountryService#deleteOsiCountries(java.lang.Integer)
	 */
	@Override
	public void deleteOsiCountries(Integer countryId) throws BusinessException {
		log.debug("Request to delete osiCountries ");
		log.info("deleteOsiCountries : Begin");
		try {
			this.countryRepository.delete(countryId);
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the countries by country id "+countryId);
		}
		log.info("deleteOsiCountries : End");
		return;
	}

	@Override
	public List<OsiCountries> getCountryCodesList() throws BusinessException {
		List<OsiCountries> osiCountries = null;
		log.info("getCountryCodesList : Begin");
		try{
			osiCountries = this.countryRepository.findAll();
		}catch(NoResultException e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the countries ");
		}
		log.info("getCountryCodesList : End");
		return osiCountries;
	}

}
