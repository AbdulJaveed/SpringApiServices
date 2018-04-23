package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiSkillTags;
import com.osi.ems.mapper.OsiSkillTagsMapper;
import com.osi.ems.service.dto.OsiSkillTagsDto;

@Component
public class OsiSkillTagsMapperImpl implements OsiSkillTagsMapper {

	@Override
	public OsiSkillTagsDto OsiSkillTagToOsiSkillTagsDto(OsiSkillTags osiSkillTags) {
		OsiSkillTagsDto osiSkillTagsDto = new OsiSkillTagsDto();
		if (osiSkillTags == null) {
			return null;
		}
		osiSkillTagsDto.setTagId(osiSkillTags.getTagId());
		osiSkillTagsDto.setTagName(osiSkillTags.getTagName());
		osiSkillTagsDto.setDescription(osiSkillTags.getDescription());
		osiSkillTagsDto.setIsActive(osiSkillTags.getIsActive());
		osiSkillTagsDto.setCreatedBy(osiSkillTags.getCreatedBy());
		osiSkillTagsDto.setCreationDate(osiSkillTags.getCreationDate());
		osiSkillTagsDto.setLastUpdatedBy(osiSkillTags.getLastUpdatedBy());
		osiSkillTagsDto.setLastUpdateDate(osiSkillTags.getLastUpdateDate());

		return osiSkillTagsDto;
	}

	@Override
	public OsiSkillTags osiSkillTagsDtoToOsiSkillTags(OsiSkillTagsDto osiSkillTagsDto) {
		if (osiSkillTagsDto == null) {
			return null;
		}
		OsiSkillTags osiSkillTags = new OsiSkillTags();
		osiSkillTags.setTagId(osiSkillTagsDto.getTagId());
		osiSkillTags.setTagName(osiSkillTagsDto.getTagName());
		osiSkillTags.setDescription(osiSkillTagsDto.getDescription());
		osiSkillTags.setIsActive(osiSkillTagsDto.getIsActive());
		osiSkillTags.setCreatedBy(osiSkillTagsDto.getCreatedBy());
		osiSkillTags.setCreationDate(osiSkillTagsDto.getCreationDate());
		osiSkillTags.setLastUpdatedBy(osiSkillTagsDto.getLastUpdatedBy());
		osiSkillTags.setLastUpdateDate(osiSkillTagsDto.getLastUpdateDate());

		return osiSkillTags;
	}

	@Override
	public List<OsiSkillTags> osiSkillTagsDtoListToOsiSkillTagsList(List<OsiSkillTagsDto> osiSkillTagsDtoList) {
		if (osiSkillTagsDtoList == null || osiSkillTagsDtoList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiSkillTags> osiSkillTagsList = new ArrayList<>();
		for (OsiSkillTagsDto osiSkillTagsDto : osiSkillTagsDtoList) {
			osiSkillTagsList.add(this.osiSkillTagsDtoToOsiSkillTags(osiSkillTagsDto));
		}
		return osiSkillTagsList;
	}

	@Override
	public List<OsiSkillTagsDto> osiSkillTagsListToOsiSkillTagsDtoList(List<OsiSkillTags> osiSkillTagsList) {
		if (osiSkillTagsList == null || osiSkillTagsList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiSkillTagsDto> osiSkillTagsDtoList = new ArrayList<>();
		for (OsiSkillTags osiSkillTags : osiSkillTagsList) {
			osiSkillTagsDtoList.add(this.OsiSkillTagToOsiSkillTagsDto(osiSkillTags));
		}
		return osiSkillTagsDtoList;
	}

}
