package com.osi.ems.mapper;

import java.util.List;

import com.osi.ems.domain.OsiOrgAddresses;
import com.osi.ems.service.dto.OsiOrgAddressesDTO;

public interface OsiOrgAddressMapper {
	OsiOrgAddressesDTO osiOrgAddressToOsiOrgAddressDTO(OsiOrgAddresses osiOrgAddresses);
	
	List<OsiOrgAddressesDTO> osiOrgAddressListToOsiOrgAddressDTOList(List<OsiOrgAddresses> osiOrgAddresses);	
	
	OsiOrgAddresses osiOrgAddressDTOToOsiOrgAddress(OsiOrgAddressesDTO osiOrgAddressesDTO);
	
	List<OsiOrgAddresses> osiOrgAddressDTOListToOsiOrgAddressList(List<OsiOrgAddressesDTO> osiOrgAddressesDTO);
}
