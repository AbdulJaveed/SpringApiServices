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

import com.osi.ems.domain.OsiStates;
import com.osi.ems.repository.OsiStatesRepository;
import com.osi.ems.service.OsiStatesService;
import com.osi.urm.exception.BusinessException;

/**
 * Class that publish the behavior provided by {@link OsiStatesService}
 * interface.
 * 
 * @author jkolla
 *
 */
@Service
@Transactional
public class OsiStatesServiceImpl implements OsiStatesService {

	private final Logger log = LoggerFactory.getLogger(OsiStatesServiceImpl.class);

	/**
	 * Field for communicating with database.
	 */
	@Autowired
	private OsiStatesRepository osiStatesRepository;

	@Override
	public OsiStates saveState(OsiStates osiCountry, Integer userId) throws BusinessException {

		String createdDate = null;
		Timestamp timestamp = null;

		/*
		 * log the message
		 */
		log.debug("Request to save osiCountry : {}", osiCountry);

		log.info("saveState : Begin");
		
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

			osiCountry = osiStatesRepository.save(osiCountry);
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the states information");
		}
		log.info("saveState : End");
		return osiCountry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiCountryService#updateCountry(com.osi.ems.domain.
	 * OsiStates, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public OsiStates updateState(OsiStates osiStates, Integer stateId, Integer userId) throws BusinessException {
		String createdDate = null;
		Timestamp timestamp = null;
		OsiStates updatedState = null;
		log.info("updateState : Begin");
		
		/*
		 * log the message
		 */
		log.debug("Request to Update osiCountry : {}", osiStates);

		/*
		 * Check if object exists with this id or not.
		 */
		try {
			if ((updatedState = this.osiStatesRepository.findOne(stateId)) != null) {
				timestamp = new Timestamp(System.currentTimeMillis());
				createdDate = new SimpleDateFormat("yyyy/MM/dd").format(timestamp);
				osiStates.setStateId(stateId);
				osiStates.setCreatedBy(updatedState.getCreatedBy());
				osiStates.setCreationDate(updatedState.getCreationDate());
				osiStates.setLastUpdatedBy(userId);
				osiStates.setLastUpdateDate(createdDate);

				/*
				 * Save the object
				 */
				this.osiStatesRepository.save(osiStates);
			} else {
				osiStates = this.saveState(osiStates, userId);
			}
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the states information");
		}
		log.info("updateState : End");
		return osiStates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiCountryService#getAllCountriesList()
	 */
	@Override
	public List<OsiStates> getAllStatesList() throws BusinessException {
		// declare local variables.
		List<OsiStates> osiStatesList = null;
		log.info("getAllStatesList : Begin");
		log.debug("Request to getall osiStates ");
		try {
			osiStatesList = this.osiStatesRepository.findAll();
		} catch (NoResultException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the states");
		}
		log.info("getAllStatesList : End");
		return osiStatesList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiCountryService#getCountryByCountryId(java.lang.
	 * Integer)
	 */
	@Override
	public OsiStates getStateByStateId(Integer countryId) throws BusinessException {
		OsiStates osiCountry = null;
		log.debug("Request to get osiStates ");
		log.info("getStateByStateId : Begin");
		try {
			osiCountry = this.osiStatesRepository.findOne(countryId);
		} catch (NoResultException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the states by state id "+countryId);
		}
		log.info("getStateByStateId : End");
		return osiCountry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osi.ems.service.OsiCountryService#deleteOsiStates(java.lang.Integer)
	 */
	@Override
	public void deleteOsiStates(Integer countryId) throws BusinessException {
		log.debug("Request to delete osiStates ");
		log.info("deleteOsiStates : Begin");
		try {
			this.osiStatesRepository.delete(countryId);
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the states by state id "+countryId);
		}
		log.info("deleteOsiStates : End");
		return;
	}

	/**
	 * Method for finding the states list by country id.
	 * @param countryId
	 * @return List of states.
	 * @throws BusinessException Throws {@link BusinessException} if any error occurs.
	 */
	@Override
	public List<OsiStates> findStatesListByCountryId(Integer countryId) throws BusinessException {
		// declare local variables.
		List<OsiStates> osiStatesList = null;
		log.debug("Request to getall osiStates by country id {} ",countryId);
		log.info("findStatesListByCountryId : Begin");
		try {
			osiStatesList = this.osiStatesRepository.findByCountryCountryId(countryId);
			
		} catch (NoResultException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the states by country id "+countryId);
		}
		log.info("findStatesListByCountryId : End");
		return osiStatesList;
	}

}
