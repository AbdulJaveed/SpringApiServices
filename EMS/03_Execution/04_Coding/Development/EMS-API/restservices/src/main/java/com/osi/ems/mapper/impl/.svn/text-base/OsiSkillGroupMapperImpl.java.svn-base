package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiSkillGroups;
import com.osi.ems.mapper.OsiSkillGroupMapper;
import com.osi.ems.service.dto.OsiSkillGroupsDTO;

@Component
public class OsiSkillGroupMapperImpl implements OsiSkillGroupMapper {

	@Override
	public OsiSkillGroups osiSkillGroupsDtoToOsiSkillGroups(OsiSkillGroupsDTO osiSkillGroupsDTO) {
		OsiSkillGroups skillGroups = new OsiSkillGroups();
		if (osiSkillGroupsDTO == null) {
			return null;
		}

		skillGroups.setGroupId(osiSkillGroupsDTO.getGroupId());
		skillGroups.setGroupName(osiSkillGroupsDTO.getGroupName());
		skillGroups.setGroupDescription(osiSkillGroupsDTO.getGroupDescription());
		skillGroups.setCreatedBy(osiSkillGroupsDTO.getCreatedBy());
		skillGroups.setIsActive(osiSkillGroupsDTO.getIsActive());
		skillGroups.setCreationDate(osiSkillGroupsDTO.getCreationDate());
		skillGroups.setLastUpdateDate(osiSkillGroupsDTO.getLastUpdateDate());
		skillGroups.setLastUpdatedBy(osiSkillGroupsDTO.getLastUpdatedBy());

		return skillGroups;
	}

	@Override
	public OsiSkillGroupsDTO osiSkillGroupsToOsiSkillGroupsDTO(OsiSkillGroups osiSkillGroups) {
		OsiSkillGroupsDTO skillGroupsDto = new OsiSkillGroupsDTO();
		if (osiSkillGroups == null) {
			return null;
		}

		skillGroupsDto.setGroupId(osiSkillGroups.getGroupId());
		skillGroupsDto.setGroupName(osiSkillGroups.getGroupName());
		skillGroupsDto.setGroupDescription(osiSkillGroups.getGroupDescription());
		skillGroupsDto.setIsActive(osiSkillGroups.getIsActive());
		skillGroupsDto.setCreatedBy(osiSkillGroups.getCreatedBy());
		skillGroupsDto.setCreationDate(osiSkillGroups.getCreationDate());
		skillGroupsDto.setLastUpdateDate(osiSkillGroups.getLastUpdateDate());
		skillGroupsDto.setLastUpdatedBy(osiSkillGroups.getLastUpdatedBy());

		return skillGroupsDto;
	}

	@Override
	public List<OsiSkillGroups> osiSkillGroupsDtoListToOsiSkillGroupsList(
			List<OsiSkillGroupsDTO> osiSkillGroupsDtoList) {
		if (osiSkillGroupsDtoList == null || osiSkillGroupsDtoList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiSkillGroups> osiSkillGroupList = new ArrayList<>();
		for (OsiSkillGroupsDTO osiSkillGroupDto : osiSkillGroupsDtoList) {
			osiSkillGroupList.add(this.osiSkillGroupsDtoToOsiSkillGroups(osiSkillGroupDto));
		}

		return osiSkillGroupList;
	}

	@Override
	public List<OsiSkillGroupsDTO> osiSkillGroupsListToOsiSkillGroupsDTO(List<OsiSkillGroups> osiSkillGroupsList) {
		if (osiSkillGroupsList == null || osiSkillGroupsList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiSkillGroupsDTO> osiSkillGroupsDtoList = new ArrayList<>();
		for (OsiSkillGroups osiSkillGroups : osiSkillGroupsList) {
			osiSkillGroupsDtoList.add(this.osiSkillGroupsToOsiSkillGroupsDTO(osiSkillGroups));
		}
		return osiSkillGroupsDtoList;
	}

}
