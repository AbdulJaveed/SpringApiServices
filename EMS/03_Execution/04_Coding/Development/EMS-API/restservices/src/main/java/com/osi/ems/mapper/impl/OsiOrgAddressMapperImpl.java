package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiOrgAddresses;
import com.osi.ems.mapper.OsiLocationMapper;
import com.osi.ems.mapper.OsiOrgAddressMapper;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiOrgAddressesDTO;
import com.osi.urm.exception.BusinessException;
@Component
public class OsiOrgAddressMapperImpl implements OsiOrgAddressMapper{
	
	@Autowired
	private OsiLocationMapper osiLocationMapper;

	@Override
	public OsiOrgAddressesDTO osiOrgAddressToOsiOrgAddressDTO(OsiOrgAddresses osiOrgAddresses) {
		
		if(osiOrgAddresses==null){
			return null;
		}
		
		OsiOrgAddressesDTO osiOrgAddressesDTO=new OsiOrgAddressesDTO();
		osiOrgAddressesDTO.setAddressId(osiOrgAddresses.getAddressId());
		osiOrgAddressesDTO.setAddressLine1(osiOrgAddresses.getAddressLine1());
		osiOrgAddressesDTO.setAddressLine2(osiOrgAddresses.getAddressLine2());
		osiOrgAddressesDTO.setCity(osiOrgAddresses.getCity());
		osiOrgAddressesDTO.setCountryId(osiOrgAddresses.getCountryId());
		osiOrgAddressesDTO.setCreatedBy(osiOrgAddresses.getCreatedBy());
		osiOrgAddressesDTO.setCreationDate(osiOrgAddresses.getCreationDate());
		osiOrgAddressesDTO.setLastUpdateDate(osiOrgAddresses.getLastUpdateDate());
		osiOrgAddressesDTO.setLastUpdatedBy(osiOrgAddresses.getLastUpdatedBy());
		osiOrgAddressesDTO.setObjectId(osiOrgAddresses.getObjectId());
		osiOrgAddressesDTO.setObjectType(osiOrgAddresses.getObjectType());
		osiOrgAddressesDTO.setStateId(osiOrgAddresses.getStateId());
		osiOrgAddressesDTO.setZipcode(osiOrgAddresses.getZipcode());
		
		return osiOrgAddressesDTO;
	}

	@Override
	public List<OsiOrgAddressesDTO> osiOrgAddressListToOsiOrgAddressDTOList(List<OsiOrgAddresses> osiOrgAddressesList) {
		
		if (osiOrgAddressesList == null) {
			return null;
		}

		List<OsiOrgAddressesDTO> osiOrgAddressesDTOList = new ArrayList<OsiOrgAddressesDTO>();
		for (OsiOrgAddresses osiOrgAddresses : osiOrgAddressesList) {
			osiOrgAddressesDTOList.add(osiOrgAddressToOsiOrgAddressDTO(osiOrgAddresses));
		}

		return osiOrgAddressesDTOList;
	}

	@Override
	public OsiOrgAddresses osiOrgAddressDTOToOsiOrgAddress(OsiOrgAddressesDTO osiOrgAddressesDTO) {
		
		if(osiOrgAddressesDTO==null){
			return null;
		}
		
		OsiOrgAddresses osiOrgAddresses=new OsiOrgAddresses();
		osiOrgAddresses.setAddressId(osiOrgAddressesDTO.getAddressId());
		osiOrgAddresses.setAddressLine1(osiOrgAddressesDTO.getAddressLine1());
		osiOrgAddresses.setAddressLine2(osiOrgAddressesDTO.getAddressLine2());
		osiOrgAddresses.setCity(osiOrgAddressesDTO.getCity());
		osiOrgAddresses.setCountryId(osiOrgAddressesDTO.getCountryId());
		osiOrgAddresses.setCreatedBy(osiOrgAddressesDTO.getCreatedBy());
		osiOrgAddresses.setCreationDate(osiOrgAddressesDTO.getCreationDate());
		osiOrgAddresses.setLastUpdateDate(osiOrgAddressesDTO.getLastUpdateDate());
		osiOrgAddresses.setLastUpdatedBy(osiOrgAddressesDTO.getLastUpdatedBy());
		osiOrgAddresses.setObjectId(osiOrgAddressesDTO.getObjectId());
		osiOrgAddresses.setObjectType(osiOrgAddressesDTO.getObjectType());
		osiOrgAddresses.setStateId(osiOrgAddressesDTO.getStateId());
		osiOrgAddresses.setZipcode(osiOrgAddressesDTO.getZipcode());
		
		return osiOrgAddresses;
	}

	@Override
	public List<OsiOrgAddresses> osiOrgAddressDTOListToOsiOrgAddressList(List<OsiOrgAddressesDTO> osiOrgAddressesDTOList) {
		
		if (osiOrgAddressesDTOList == null) {
			return null;
		}

		List<OsiOrgAddresses> osiOrgAddressList = new ArrayList<OsiOrgAddresses>();
		for (OsiOrgAddressesDTO osiOrgAddressesDTO : osiOrgAddressesDTOList) {
			osiOrgAddressList.add(osiOrgAddressDTOToOsiOrgAddress(osiOrgAddressesDTO));
		}

		return osiOrgAddressList;
	}
	
	
	private Set<OsiLocationsDTO> osiLocationsSetTOOsiLocationsDTOSet(Set<OsiLocations> osiLocationsesSet) {
		
		if (osiLocationsesSet == null) {
			return null;
		}

		Set<OsiLocationsDTO> set = new HashSet<OsiLocationsDTO>();
		for (OsiLocations osiLocations : osiLocationsesSet) {
			set.add(osiLocationMapper.osiLocationToOsiLocationDTO(osiLocations));
		}

		return set;
	}
	
	private Set<OsiLocations> osiLocationsDTOSetTOOsiLocationsSet(Set<OsiLocationsDTO> osiLocationsesDTOSet) throws BusinessException {
		
		if (osiLocationsesDTOSet == null) {
			return null;
		}

		Set<OsiLocations> set = new HashSet<OsiLocations>();
		for (OsiLocationsDTO osiLocationsDTO : osiLocationsesDTOSet) {
			set.add(osiLocationMapper.osiLocationDTOToOsiLocation(osiLocationsDTO));
		}

		return set;
	}

	
}
