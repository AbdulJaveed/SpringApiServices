package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCertificationTags;
import com.osi.ems.mapper.OsiCertificationTagsMapper;
import com.osi.ems.service.dto.OsiCertificationTagsDto;

@Component
public class OsiCertificationTagsMapperImpl implements OsiCertificationTagsMapper {

	@Override
	public OsiCertificationTagsDto OsiCertificationTagToOsiCertificationTagsDto(OsiCertificationTags osiCertificationTags) {
		OsiCertificationTagsDto osiCertificationTagsDto = new OsiCertificationTagsDto();
		if (osiCertificationTags == null) {
			return null;
		}
		osiCertificationTagsDto.setTagId(osiCertificationTags.getTagId());
		osiCertificationTagsDto.setTagName(osiCertificationTags.getTagName());
		osiCertificationTagsDto.setDescription(osiCertificationTags.getDescription());
		osiCertificationTagsDto.setIsActive(osiCertificationTags.getIsActive());
		osiCertificationTagsDto.setCreatedBy(osiCertificationTags.getCreatedBy());
		osiCertificationTagsDto.setCreationDate(osiCertificationTags.getCreationDate());
		osiCertificationTagsDto.setLastUpdatedBy(osiCertificationTags.getLastUpdatedBy());
		osiCertificationTagsDto.setLastUpdateDate(osiCertificationTags.getLastUpdateDate());

		return osiCertificationTagsDto;
	}

	@Override
	public OsiCertificationTags osiCertificationTagsDtoToOsiCertificationTags(OsiCertificationTagsDto osiCertificationTagsDto) {
		if (osiCertificationTagsDto == null) {
			return null;
		}
		OsiCertificationTags osiCertificationTags = new OsiCertificationTags();
		osiCertificationTags.setTagId(osiCertificationTagsDto.getTagId());
		osiCertificationTags.setTagName(osiCertificationTagsDto.getTagName());
		osiCertificationTags.setDescription(osiCertificationTagsDto.getDescription());
		osiCertificationTags.setIsActive(osiCertificationTagsDto.getIsActive());
		osiCertificationTags.setCreatedBy(osiCertificationTagsDto.getCreatedBy());
		osiCertificationTags.setCreationDate(osiCertificationTagsDto.getCreationDate());
		osiCertificationTags.setLastUpdatedBy(osiCertificationTagsDto.getLastUpdatedBy());
		osiCertificationTags.setLastUpdateDate(osiCertificationTagsDto.getLastUpdateDate());

		return osiCertificationTags;
	}

	@Override
	public List<OsiCertificationTags> osiCertificationTagsDtoListToOsiCertificationTagsList(List<OsiCertificationTagsDto> osiCertificationTagsDtoList) {
		if (osiCertificationTagsDtoList == null || osiCertificationTagsDtoList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiCertificationTags> osiCertificationTagsList = new ArrayList<>();
		for (OsiCertificationTagsDto osiCertificationTagsDto : osiCertificationTagsDtoList) {
			osiCertificationTagsList.add(this.osiCertificationTagsDtoToOsiCertificationTags(osiCertificationTagsDto));
		}
		return osiCertificationTagsList;
	}

	@Override
	public List<OsiCertificationTagsDto> osiCertificationTagsListToOsiCertificationTagsDtoList(List<OsiCertificationTags> osiCertificationTagsList) {
		if (osiCertificationTagsList == null || osiCertificationTagsList.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<OsiCertificationTagsDto> osiCertificationTagsDtoList = new ArrayList<>();
		for (OsiCertificationTags osiCertificationTags : osiCertificationTagsList) {
			osiCertificationTagsDtoList.add(this.OsiCertificationTagToOsiCertificationTagsDto(osiCertificationTags));
		}
		return osiCertificationTagsDtoList;
	}
	
	@Override
	public Set<OsiCertificationTagsDto> osiCertificationTagsListToOsiCertificationTagsDtoSet(Set<OsiCertificationTags> osiCertificationTagsList) {
		if (osiCertificationTagsList == null || osiCertificationTagsList.isEmpty()) {
			return new TreeSet<OsiCertificationTagsDto>();
		}
		Set<OsiCertificationTagsDto> osiCertificationTagsDtoList = new HashSet<>();
		for (OsiCertificationTags osiCertificationTags : osiCertificationTagsList) {
			osiCertificationTagsDtoList.add(this.OsiCertificationTagToOsiCertificationTagsDto(osiCertificationTags));
		}
		return osiCertificationTagsDtoList;
	}

}
