package com.osi.ems.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.OsiLocationDao;
import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiOrgAddresses;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.domain.OsiRegions;
import com.osi.ems.domain.OsiTimezones;
import com.osi.ems.repository.OsiLocationRepository;
import com.osi.ems.repository.OsiOrganizationsRepository;
import com.osi.ems.repository.custom.OsiLocationRepositoryCustom;
import com.osi.ems.service.OsiRollUpsService;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiLocationDaoImpl implements OsiLocationDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OsiLocationRepository locationRepository;

	@Autowired
	OsiLocationRepositoryCustom locationRepositoryCustom;

	@Autowired
	OsiOrganizationsRepository organizationsRepo;
	
	@Autowired
	OsiRollUpsService osiRollUpsService;
	
	@Autowired
	CommonService cs;

	@Override
	/*
	 * Add the creation date and created by id to location and address data and
	 * save
	 */
	@Transactional(rollbackForClassName = { "Exception" })
	public OsiLocations createLocation(OsiLocations osiLocations, Integer userId) throws BusinessException, DataAccessException {
		OsiOrganizations osiOrganization = new OsiOrganizations();
		LOGGER.info("createLocation : Begin");
		try {
			if (osiLocations.getOsiOrganizations().getOrgId() == null) {
				osiOrganization = osiLocations.getOsiOrganizations();
				osiOrganization.setCreatedBy(userId);
				osiOrganization.setLastUpdatedBy(userId);
				osiOrganization.setCreationDate(cs.getCurrentDateinUTC());
				osiOrganization.setLastUpdateDate(cs.getCurrentDateinUTC());
				osiLocations.setOsiOrganizations(osiOrganization);
				
				osiOrganization = organizationsRepo.save(osiOrganization);
				
			} else {
				osiOrganization = osiLocations.getOsiOrganizations();
				osiOrganization.setLastUpdatedBy(userId);
				osiOrganization.setLastUpdateDate(cs.getCurrentDateinUTC());
				osiLocations.setOsiOrganizations(osiOrganization);
				
				osiOrganization = organizationsRepo.save(osiOrganization);
				
			}
			osiLocations.setCreatedBy(userId);
			osiLocations.setLastUpdateDate(cs.getCurrentDateinUTC());
			osiLocations.setLastUpdatedBy(userId);
			osiLocations.setCreationDate(cs.getCurrentDateinUTC());
			
			OsiOrgAddresses osiOrgAddress = new OsiOrgAddresses();
			if(osiLocations.getOsiAddresses()!=null)
			{
			osiOrgAddress = osiLocations.getOsiAddresses();
			osiOrgAddress.setCreatedBy(userId);
			osiOrgAddress.setLastUpdatedBy(userId);
			osiOrgAddress.setLastUpdateDate(cs.getCurrentDateinUTC());
			osiOrgAddress.setCreationDate(cs.getCurrentDateinUTC());
			osiLocations.setOsiAddresses(osiOrgAddress);
			}
			
			osiLocations = locationRepository.save(osiLocations);
			if (osiLocations != null && osiLocations.getOsiAddresses()!=null) {
				osiOrgAddress.setObjectId(osiLocations.getLocationId());
				osiOrgAddress.setObjectType("location");
				osiLocations.setOsiAddresses(osiOrgAddress);
				osiLocations = locationRepository.save(osiLocations);
			}
		} catch(BusinessException e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 
		LOGGER.info("createLocation : End");
		return osiLocations;
	}

	@Override
	public List<OsiLocations> locationbyOrgId(Integer orgId) throws BusinessException, DataAccessException {
		List<OsiLocations> osiLocations = new ArrayList<OsiLocations>();
		LOGGER.info("locationbyOrgId : Begin");
		try {
			osiLocations.addAll(locationRepositoryCustom.getLocationsByOrgId(orgId));

		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 
		LOGGER.info("locationbyOrgId : End");
		return osiLocations;
	}

	@Override
	/*
	 * Add the update date and updated by id to location and address data and
	 * save
	 */
	public OsiLocations updateLocation(OsiLocations osiLocations, Integer userId)
			throws BusinessException, DataAccessException {
		LOGGER.info("updateLocation : Begin");
		String flag="0";
		Boolean continueUpdate=true;
		try {
			if (osiLocations.getActive() == 0) {
				flag = osiRollUpsService.checkActiveStatus(osiLocations.getLocationName());
				if (flag.equals("1")) {
					continueUpdate = false;
				}
			}
			if (continueUpdate) {
				try{
					if (osiLocations.getOsiOrganizations().getActive() == 0) {
						flag = osiRollUpsService
								.checkActiveStatus(osiLocations.getOsiOrganizations().getOrgShortName());
						if (flag.equals("1")) {
							continueUpdate = false;
						}
					}
					
					if (continueUpdate) {
						OsiOrganizations osiOrganization = new OsiOrganizations();
						osiOrganization = osiLocations.getOsiOrganizations();
						osiOrganization.setLastUpdatedBy(userId);
						osiOrganization.setLastUpdateDate(cs.getCurrentDateinUTC());
						osiLocations.setOsiOrganizations(osiOrganization);

						osiLocations.setLastUpdateDate(cs.getCurrentDateinUTC());
						osiLocations.setLastUpdatedBy(userId);
						if (osiLocations.getOsiAddresses() != null) {
							OsiOrgAddresses osiOrgAddress = new OsiOrgAddresses();
							osiOrgAddress = osiLocations.getOsiAddresses();
							if (osiOrgAddress.getCreatedBy() != null) {
								osiOrgAddress.setCreatedBy(userId);
								osiOrgAddress.setCreationDate(cs.getCurrentDateinUTC());
							}
							osiOrgAddress.setLastUpdateDate(cs.getCurrentDateinUTC());
							osiOrgAddress.setLastUpdatedBy(userId);
							osiLocations.setOsiAddresses(osiOrgAddress);
						}

						osiLocations = locationRepository.save(osiLocations);
					}else{
						LOGGER.error("Exception : Cannot deactivate the organization.");
						throw new DataAccessException("ERR_1000", "Cannot deactivate the organization.");
					}
				}catch (DataAccessException e) {
					LOGGER.error("Exception : "+e.getMessage());
					throw new DataAccessException("ERR_1000", e.getSystemMessage());
				} catch (Exception e) {
					LOGGER.error("Exception : "+e.getMessage());
					throw new DataAccessException("ERR_1000", e.getMessage());
				}

			} else {
				LOGGER.error("Exception : cannot deactivate this location");
				throw new DataAccessException("ERR_1000", "Cannot deactivate this location.");
			}
		} catch(BusinessException e) {
			LOGGER.error("Exception : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 
		LOGGER.info("updateLocation : End");
		return osiLocations;
	}

	@Override
	public OsiLocations locationbyLocId(Integer locId) throws BusinessException, DataAccessException {
		OsiLocations osiLocations = new OsiLocations();
		try {
			osiLocations = locationRepositoryCustom.getLocationsByLocId(locId);
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 

		return osiLocations;
	}

	@Override
	public List<OsiRegions> getRegionsInfo() throws BusinessException, DataAccessException {
		List<OsiRegions> osiRegions = new ArrayList<OsiRegions>();
		try {
			osiRegions = locationRepositoryCustom.getRegionsInfo();
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 
		return osiRegions;
	}
	
	@Override
	public List<OsiTimezones> getTimezonesInfo() throws BusinessException, DataAccessException {
		List<OsiTimezones> osiTimezones = new ArrayList<OsiTimezones>();
		try {
			osiTimezones = locationRepositoryCustom.getTimezonesInfo();
		} catch(DataAccessException de) {
			LOGGER.error("Exception : " + de.getMessage());			
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 
		return osiTimezones;
	}

}
