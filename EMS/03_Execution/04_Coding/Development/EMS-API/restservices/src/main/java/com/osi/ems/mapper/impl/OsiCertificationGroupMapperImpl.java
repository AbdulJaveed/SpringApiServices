package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCertificationGroups;
import com.osi.ems.mapper.OsiCertificationGroupMapper;
import com.osi.ems.service.dto.OsiCertificationGroupsDTO;

@Component
public class OsiCertificationGroupMapperImpl implements OsiCertificationGroupMapper {

	@Override
	public OsiCertificationGroups osiCertificationGroupsDtoToOsiCertificationGroups(OsiCertificationGroupsDTO osiCertificationGroupsDTO) {
		OsiCertificationGroups osiCertificationGroups = new OsiCertificationGroups();
		if (osiCertificationGroupsDTO == null) {
			return null;
		}

		osiCertificationGroups.setGroupId(osiCertificationGroupsDTO.getGroupId());
		osiCertificationGroups.setGroupName(osiCertificationGroupsDTO.getGroupName());
		osiCertificationGroups.setGroupDescription(osiCertificationGroupsDTO.getGroupDescription());
		osiCertificationGroups.setCreatedBy(osiCertificationGroupsDTO.getCreatedBy());
		osiCertificationGroups.setIsActive(osiCertificationGroupsDTO.getIsActive());
		osiCertificationGroups.setCreationDate(osiCertificationGroupsDTO.getCreationDate());
		osiCertificationGroups.setLastUpdateDate(osiCertificationGroupsDTO.getLastUpdateDate());
		osiCertificationGroups.setLastUpdatedBy(osiCertificationGroupsDTO.getLastUpdatedBy());

		return osiCertificationGroups;
	}

	@Override
	public OsiCertificationGroupsDTO osiCertificationGroupsToOsiCertificationGroupsDTO(OsiCertificationGroups osiCertificationGroups) {
		OsiCertificationGroupsDTO osiCertificationGroupsDto = new OsiCertificationGroupsDTO();
		if (osiCertificationGroups == null) {
			return null;
		}

		osiCertificationGroupsDto.setGroupId(osiCertificationGroups.getGroupId());
		osiCertificationGroupsDto.setGroupName(osiCertificationGroups.getGroupName());
		osiCertificationGroupsDto.setGroupDescription(osiCertificationGroups.getGroupDescription());
		osiCertificationGroupsDto.setIsActive(osiCertificationGroups.getIsActive());
		osiCertificationGroupsDto.setCreatedBy(osiCertificationGroups.getCreatedBy());
		osiCertificationGroupsDto.setCreationDate(osiCertificationGroups.getCreationDate());
		osiCertificationGroupsDto.setLastUpdateDate(osiCertificationGroups.getLastUpdateDate());
		osiCertificationGroupsDto.setLastUpdatedBy(osiCertificationGroups.getLastUpdatedBy());

		return osiCertificationGroupsDto;
	}

	@Override
	public List<OsiCertificationGroups> osiCertificationGroupsDtoListToOsiCertificationGroupsList(
			List<OsiCertificationGroupsDTO> osiCertificationGroupsDtoList) {
		if (osiCertificationGroupsDtoList == null || osiCertificationGroupsDtoList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiCertificationGroups> osiCertificationGroupList = new ArrayList<>();
		for (OsiCertificationGroupsDTO osiCertificationGroupDto : osiCertificationGroupsDtoList) {
			osiCertificationGroupList.add(this.osiCertificationGroupsDtoToOsiCertificationGroups(osiCertificationGroupDto));
		}

		return osiCertificationGroupList;
	}

	@Override
	public List<OsiCertificationGroupsDTO> osiCertificationGroupsListToOsiCertificationGroupsDTO(List<OsiCertificationGroups> osiCertificationGroupsList) {
		if (osiCertificationGroupsList == null || osiCertificationGroupsList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiCertificationGroupsDTO> osiCertificationGroupsDtoList = new ArrayList<>();
		for (OsiCertificationGroups osiCertificationGroups : osiCertificationGroupsList) {
			osiCertificationGroupsDtoList.add(this.osiCertificationGroupsToOsiCertificationGroupsDTO(osiCertificationGroups));
		}
		return osiCertificationGroupsDtoList;
	}

}
