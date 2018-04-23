package com.osi.ems.mapper.impl;




import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiContacts;
import com.osi.ems.mapper.OsiContactsMapper;
import com.osi.ems.service.dto.OsiContactsDto;

@Component
public class OsiContactMapperImpl implements OsiContactsMapper {

	@Override
	public OsiContactsDto osiContactssToosiContactsDTO(OsiContacts osiContacts) {
		OsiContactsDto dto = new OsiContactsDto();
		dto.setContactId(osiContacts.getContactId());
		dto.setContactName(osiContacts.getContactName());
		dto.setContactNumber(osiContacts.getContactNumber());
		dto.setContactType(osiContacts.getContactType());
		dto.setCountryCode(osiContacts.getCountryCode());
		dto.setCreatedBy(osiContacts.getCreatedBy());
		dto.setCreationDate(osiContacts.getCreationDate());
		dto.setEmployeeId(osiContacts.getEmployeeId());
		dto.setLastUpdateDate(osiContacts.getLastUpdateDate());
		dto.setLastUpdatedBy(osiContacts.getLastUpdatedBy());
		dto.setRelation(osiContacts.getRelation());
		dto.setSeq(osiContacts.getSeq());
		
		return dto;
	}

	@Override
	public OsiContacts osiContactsDTOToOsiContacts(OsiContactsDto osiContactsDTO) {
		
		OsiContacts contacts = new OsiContacts();
		contacts.setContactId(osiContactsDTO.getContactId());
		contacts.setContactName(osiContactsDTO.getContactName());
		contacts.setContactNumber(osiContactsDTO.getContactNumber());
		contacts.setContactType(osiContactsDTO.getContactType());
		contacts.setCountryCode(osiContactsDTO.getCountryCode());
		contacts.setCreatedBy(osiContactsDTO.getCreatedBy());
		contacts.setCreationDate(osiContactsDTO.getCreationDate());
		contacts.setEmployeeId(osiContactsDTO.getEmployeeId());
		contacts.setLastUpdateDate(osiContactsDTO.getLastUpdateDate());
		contacts.setLastUpdatedBy(osiContactsDTO.getLastUpdatedBy());
		contacts.setRelation(osiContactsDTO.getRelation());
		contacts.setSeq(osiContactsDTO.getSeq());
		
		return contacts;
	}

	@Override
	public List<OsiContactsDto> osiContactsListToOsiContactsDTOList(List<OsiContacts> result) {
		List<OsiContactsDto> osiContactsDto = new ArrayList<>();
		for(OsiContacts c : result) {
			OsiContactsDto contacts = new OsiContactsDto();
			contacts.setContactId(c.getContactId());
			contacts.setContactName(c.getContactName());
			contacts.setContactNumber(c.getContactNumber());
			contacts.setContactType(c.getContactType());
			contacts.setCountryCode(c.getCountryCode());
			contacts.setCreatedBy(c.getCreatedBy());
			contacts.setCreationDate(c.getCreationDate());
			contacts.setEmployeeId(c.getEmployeeId());
			contacts.setLastUpdateDate(c.getLastUpdateDate());
			contacts.setLastUpdatedBy(c.getLastUpdatedBy());
			contacts.setRelation(c.getRelation());
			contacts.setSeq(c.getSeq());
			osiContactsDto.add(contacts);
		}		
		return osiContactsDto;
	}
	
	@Override
	public List<OsiContacts> osiContactsDtoListToOsiContactsList(List<OsiContactsDto> result) {
		List<OsiContacts> osiContactsDto = new ArrayList<>();
		for(OsiContactsDto c : result) {
			OsiContacts contacts = new OsiContacts();
			contacts.setContactId(c.getContactId());
			contacts.setContactName(c.getContactName());
			contacts.setContactNumber(c.getContactNumber());
			contacts.setContactType(c.getContactType());
			contacts.setCountryCode(c.getCountryCode());
			contacts.setCreatedBy(c.getCreatedBy());
			contacts.setCreationDate(c.getCreationDate());
			contacts.setEmployeeId(c.getEmployeeId());
			contacts.setLastUpdateDate(c.getLastUpdateDate());
			contacts.setLastUpdatedBy(c.getLastUpdatedBy());
			contacts.setRelation(c.getRelation());
			contacts.setSeq(c.getSeq());
			osiContactsDto.add(contacts);
		}		
		return osiContactsDto;
	}

	

    
	


}
