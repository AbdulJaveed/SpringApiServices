package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiRegions;
import com.osi.ems.domain.OsiTimezones;
import com.osi.ems.mapper.OsiLocationMapper;
import com.osi.ems.mapper.OsiOrgAddressMapper;
import com.osi.ems.mapper.OsiOrganizationsMapper;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRegionsDTO;
import com.osi.ems.service.dto.OsiTimezonesDTO;
import com.osi.urm.exception.BusinessException;
@Component
public class OsiLocationMapperImpl implements OsiLocationMapper {

	@Autowired
	private OsiOrgAddressMapper osiOrgAddressMapper;
	
	@Autowired
	private OsiOrganizationsMapper osiOrgMapper;

	@Override
	public OsiLocationsDTO osiLocationToOsiLocationDTO(OsiLocations osiLocation) {
		
		if (osiLocation == null) {
			return null;
		}

		OsiLocationsDTO osiLocationsDTO = new OsiLocationsDTO();
		osiLocationsDTO.setActive(osiLocation.getActive());
		osiLocationsDTO.setCreatedBy(osiLocation.getCreatedBy());
		osiLocationsDTO.setCreationDate(osiLocation.getCreationDate());
		osiLocationsDTO.setIsPrimary(osiLocation.getIsPrimary());
		osiLocationsDTO.setLastUpdateDate(osiLocation.getLastUpdateDate());
		osiLocationsDTO.setLastUpdatedBy(osiLocation.getLastUpdatedBy());
		osiLocationsDTO.setLocationId(osiLocation.getLocationId());
		osiLocationsDTO.setLocationName(osiLocation.getLocationName());
		osiLocationsDTO.setOsiOrganizations(osiOrgMapper.osiOrganizationsToOsiOrganizationsDTO(osiLocation.getOsiOrganizations()));
		osiLocationsDTO.setOsiAddresses(osiOrgAddressMapper.osiOrgAddressToOsiOrgAddressDTO(osiLocation.getOsiAddresses()));
		if(osiLocation.getOsiRegionsId()!=null){
			OsiRegionsDTO osiRegions=new OsiRegionsDTO();
			osiRegions.setRegionId(osiLocation.getOsiRegionsId().getRegionId());
			osiRegions.setRegionName(osiLocation.getOsiRegionsId().getRegionName());
			osiRegions.setRegionShortName(osiLocation.getOsiRegionsId().getRegionShortName());
			osiLocationsDTO.setOsiRegionsId(osiRegions);
		}
		if(osiLocation.getOsiTimezonesId()!=null){
			OsiTimezonesDTO osiTimezones=new OsiTimezonesDTO();
			osiTimezones.setTzId(osiLocation.getOsiTimezonesId().getTzId());
			osiTimezones.setTzName(osiLocation.getOsiTimezonesId().getTzName());
			osiTimezones.setTzShortName(osiLocation.getOsiTimezonesId().getTzShortName());
			osiLocationsDTO.setOsiTimezonesId(osiTimezones);
		}
		return osiLocationsDTO;
	}

	@Override
	public List<OsiLocationsDTO> osiLocationToOsiLocationDTOList(List<OsiLocations> osiLocationsList) {
		
		if (osiLocationsList == null) {
			return null;
		}

		List<OsiLocationsDTO> osiLocationsDTOList = new ArrayList<OsiLocationsDTO>();
		for (OsiLocations osiLocations : osiLocationsList) {
			osiLocationsDTOList.add(osiLocationToOsiLocationDTO(osiLocations));
		}

		return osiLocationsDTOList;
	}

	@Override
	public OsiLocations osiLocationDTOToOsiLocation(OsiLocationsDTO osiLocationDTO) throws BusinessException {
		
		if (osiLocationDTO == null) {
			return null;
		}
		
		OsiLocations osiLocations = new OsiLocations();
		osiLocations.setActive(osiLocationDTO.getActive());
		osiLocations.setCreatedBy(osiLocationDTO.getCreatedBy());
		osiLocations.setCreationDate(osiLocationDTO.getCreationDate());
		osiLocations.setIsPrimary(osiLocationDTO.getIsPrimary());
		osiLocations.setLastUpdateDate(osiLocationDTO.getLastUpdateDate());
		osiLocations.setLastUpdatedBy(osiLocationDTO.getLastUpdatedBy());
		osiLocations.setLocationId(osiLocationDTO.getLocationId());
		osiLocations.setLocationName(osiLocationDTO.getLocationName());
		/*osiLocations.setCreationDate(osiLocationDTO.getCreationDate());*/
		/*osiLocations.setLastUpdateDate(osiLocationDTO.getLastUpdateDate());*/
		osiLocations.setOsiOrganizations(osiOrgMapper.osiOrganizationsDTOToOsiOrganizations(osiLocationDTO.getOsiOrganizations()));
		osiLocations.setOsiAddresses(osiOrgAddressMapper.osiOrgAddressDTOToOsiOrgAddress(osiLocationDTO.getOsiAddresses()));
		if(osiLocationDTO.getOsiTimezonesId()!=null){
			OsiTimezones osiTimezones=new OsiTimezones();
			osiTimezones.setTzId(osiLocationDTO.getOsiTimezonesId().getTzId());
			osiTimezones.setTzName(osiLocationDTO.getOsiTimezonesId().getTzName());
			osiTimezones.setTzShortName(osiLocationDTO.getOsiTimezonesId().getTzShortName());
			/*osiTimezones.setCreationDate(osiLocationDTO.getCreationDate());
			osiTimezones.setLastUpdateDate(osiLocationDTO.getLastUpdateDate());
			osiTimezones.setCreatedBy(osiLocationDTO.getCreatedBy());
			osiTimezones.setLastUpdatedBy(osiLocationDTO.getLastUpdatedBy());*/
			osiLocations.setOsiTimezonesId(osiTimezones);
		}
		if(osiLocationDTO.getOsiRegionsId()!=null){
			OsiRegions osiRegions=new OsiRegions();
			osiRegions.setRegionId(osiLocationDTO.getOsiRegionsId().getRegionId());
			osiRegions.setRegionName(osiLocationDTO.getOsiRegionsId().getRegionName());
			osiRegions.setRegionShortName(osiLocationDTO.getOsiRegionsId().getRegionShortName());
			/*osiRegions.setCreationDate(osiLocationDTO.getCreationDate());
			osiRegions.setLastUpdateDate(osiLocationDTO.getLastUpdateDate());
			osiRegions.setCreatedBy(osiLocationDTO.getCreatedBy());
			osiRegions.setLastUpdatedBy(osiLocationDTO.getLastUpdatedBy());*/
			osiLocations.setOsiRegionsId(osiRegions);
		}

		return osiLocations;
	}

	@Override
	public List<OsiLocations> osiLocationDTOToOsiLocation(List<OsiLocationsDTO> osiLocationDTOList) throws BusinessException {
		
		if (osiLocationDTOList == null) {
			return null;
		}
		List<OsiLocations> osiLocationsList = new ArrayList<OsiLocations>();
		for (OsiLocationsDTO osiLocationsDTO : osiLocationDTOList) {
			osiLocationsList.add(osiLocationDTOToOsiLocation(osiLocationsDTO));
		}
		return osiLocationsList;
	}

}
