package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.common.CommonService;
import com.osi.ems.dao.OsiLocationDao;
import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiRegions;
import com.osi.ems.domain.OsiTimezones;
import com.osi.ems.mapper.OsiLocationMapper;
import com.osi.ems.service.OsiLocationService;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRegionsDTO;
import com.osi.ems.service.dto.OsiTimezonesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiLocationServiceImpl implements OsiLocationService {

	@Autowired
	private OsiLocationDao locationDao;

	@Autowired
	private OsiLocationMapper osiLocationMapper;

	private final Logger log = LoggerFactory.getLogger(OsiLocationServiceImpl.class);
	
	@Override
	/* Create or update locations along with the address data */
	public OsiLocationsDTO createLocation(OsiLocationsDTO osiLocationsDTO, Integer userId)
			throws BusinessException, DataAccessException {
		log.info("createLocation : Begin");
		OsiLocations OsiLocations = new OsiLocations();
		CommonService cs =  new CommonService();
		osiLocationsDTO.setLastUpdatedBy(userId);
		osiLocationsDTO.setLastUpdateDate(cs.getCurrentDateinUTC());
		OsiLocationsDTO osiLocations = new OsiLocationsDTO();
		try {
			if (osiLocationsDTO.getLocationId() != null) {
				
				OsiLocations = locationDao.updateLocation(osiLocationMapper.osiLocationDTOToOsiLocation(osiLocationsDTO), userId);
			} else {
				osiLocationsDTO.setCreatedBy(userId);
				osiLocationsDTO.setCreationDate(cs.getCurrentDateinUTC());
				OsiLocations = locationDao.createLocation(osiLocationMapper.osiLocationDTOToOsiLocation(osiLocationsDTO), userId);
			}
			osiLocations= osiLocationMapper.osiLocationToOsiLocationDTO(OsiLocations);
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the location");
		}
		log.info("createLocation : End");
		return osiLocations;
	}

	@Override
	/* Search Location data along with the associated address */
	public List<OsiLocationsDTO> locationbyOrgId(Integer orgId) throws BusinessException, DataAccessException {
		log.info("locationbyOrgId : Begin");
		List<OsiLocationsDTO> osiLocationsDTOList = new ArrayList<OsiLocationsDTO>();
		List<OsiLocations> osiLocations = new ArrayList<OsiLocations>();
		try {
			osiLocations = locationDao.locationbyOrgId(orgId);
			osiLocationsDTOList = osiLocationMapper.osiLocationToOsiLocationDTOList(osiLocations);
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting location by orgId");
		}
		log.info("locationbyOrgId : End");
		return osiLocationsDTOList;
	}

	@Override
	public OsiLocationsDTO locationbyLocId(Integer locId) throws BusinessException, DataAccessException {
		log.info("locationbyLocId : Begin");
		OsiLocationsDTO osilocationsDTO = new OsiLocationsDTO();
		OsiLocations osiLocations = new OsiLocations();
		try {
			osiLocations = locationDao.locationbyLocId(locId);
			osilocationsDTO = osiLocationMapper.osiLocationToOsiLocationDTO(osiLocations);
		}catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting location by locId");
		}
		log.info("locationbyOrgId : End");
		return osilocationsDTO;
	}

	@Override
	public List<OsiRegionsDTO> getRegionInfo() throws BusinessException, DataAccessException {
		log.info("getRegionInfo : Begin");
		List<OsiRegionsDTO> osiRegionsDTOList = new ArrayList<OsiRegionsDTO>();
		OsiRegionsDTO osiRegionsDTO = null;
		try {
			List<OsiRegions> osiRegionsList = locationDao.getRegionsInfo();
			if (osiRegionsList == null || (osiRegionsList != null && osiRegionsList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			for (OsiRegions osiRegions : osiRegionsList) {
				osiRegionsDTO = new OsiRegionsDTO();
				osiRegionsDTO.setRegionId(osiRegions.getRegionId());
				osiRegionsDTO.setRegionName(osiRegions.getRegionName());
				osiRegionsDTO.setRegionShortName(osiRegions.getRegionShortName());
				osiRegionsDTOList.add(osiRegionsDTO);
			}
		}catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting region information");
		}
		log.info("getRegionInfo : End");
		return osiRegionsDTOList;
	}
	
	@Override
	public List<OsiTimezonesDTO> getTimezoneInfo() throws BusinessException, DataAccessException {
		List<OsiTimezonesDTO> osiTimezonesDTOList = new ArrayList<OsiTimezonesDTO>();
		OsiTimezonesDTO osiTimezonesDTO = null;
		log.info("getTimezoneInfo : Begin");
		try {
			List<OsiTimezones> osiTimezonesList = locationDao.getTimezonesInfo();
			if (osiTimezonesList == null || (osiTimezonesList != null && osiTimezonesList.size() == 0)) {
				throw new DataAccessException("ERR_1002", null);
			}
			for (OsiTimezones osiTimezones : osiTimezonesList) {
				osiTimezonesDTO = new OsiTimezonesDTO();
				osiTimezonesDTO.setTzId(osiTimezones.getTzId());
				osiTimezonesDTO.setTzName(osiTimezones.getTzName());
				osiTimezonesDTO.setTzShortName(osiTimezones.getTzShortName());
				osiTimezonesDTOList.add(osiTimezonesDTO);
			}
		}catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting time zone information");
		}
		log.info("getTimezoneInfo : End");
		
		return osiTimezonesDTOList;
	}
}
