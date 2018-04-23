package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiSubPracticeGrades;
import com.osi.ems.mapper.OsiSubPracticeGradesMapper;
import com.osi.ems.service.dto.OsiSubPracticeGradesDto;

@Component
public class OsiSubPracticeGradesMapperImpl implements OsiSubPracticeGradesMapper {

	@Override
	public OsiSubPracticeGrades getOsiSubPracticeGrades(OsiSubPracticeGradesDto osiSubPracticeGradesDto) {
		OsiSubPracticeGrades osiSubPracticeGrades = null;
		osiSubPracticeGrades = new OsiSubPracticeGrades();

		osiSubPracticeGrades.setCreatedBy(osiSubPracticeGradesDto.getCreatedBy());
		osiSubPracticeGrades.setCreatedDate(osiSubPracticeGradesDto.getCreatedDate());
		osiSubPracticeGrades.setLastUpdateDate(osiSubPracticeGradesDto.getLastUpdateDate());
		osiSubPracticeGrades.setUpdatedBy(osiSubPracticeGradesDto.getUpdatedBy());
		osiSubPracticeGrades.setCostPerHour(osiSubPracticeGradesDto.getCostPerHour());
		osiSubPracticeGrades.setCostPerMonth(osiSubPracticeGradesDto.getCostPerMonth());
		osiSubPracticeGrades.setGradeId(osiSubPracticeGradesDto.getGradeId());
		osiSubPracticeGrades.setInternalCostOverheadPercentage(osiSubPracticeGradesDto.getInternalCostOverheadPct());
		osiSubPracticeGrades.setOrgId(osiSubPracticeGradesDto.getOrgId());
		osiSubPracticeGrades.setSubPracticeId(osiSubPracticeGradesDto.getSubPracticeId());
		osiSubPracticeGrades.setSubPracticeGradeId(osiSubPracticeGradesDto.getSubPracticeGradeId());

		return osiSubPracticeGrades;
	}

	@Override
	public OsiSubPracticeGradesDto getOsiSubPracticeGradesDto(OsiSubPracticeGrades osiSubPracticeGrades) {

		OsiSubPracticeGradesDto osiSubPracticeGradesDto = null;
		osiSubPracticeGradesDto = new OsiSubPracticeGradesDto();

		osiSubPracticeGradesDto.setCreatedBy(osiSubPracticeGrades.getCreatedBy());
		osiSubPracticeGradesDto.setCreatedDate(osiSubPracticeGrades.getCreatedDate());
		osiSubPracticeGradesDto.setLastUpdateDate(osiSubPracticeGrades.getLastUpdateDate());
		osiSubPracticeGradesDto.setUpdatedBy(osiSubPracticeGrades.getUpdatedBy());
		osiSubPracticeGradesDto.setCostPerHour(osiSubPracticeGrades.getCostPerHour());
		osiSubPracticeGradesDto.setCostPerMonth(osiSubPracticeGrades.getCostPerMonth());
		osiSubPracticeGradesDto.setGradeId(osiSubPracticeGrades.getGradeId());
		osiSubPracticeGradesDto.setInternalCostOverheadPct(osiSubPracticeGrades.getInternalCostOverheadPercentage());
		osiSubPracticeGradesDto.setOrgId(osiSubPracticeGrades.getOrgId());
		osiSubPracticeGradesDto.setSubPracticeId(osiSubPracticeGrades.getSubPracticeId());
		osiSubPracticeGradesDto.setSubPracticeGradeId(osiSubPracticeGrades.getSubPracticeGradeId());

		return osiSubPracticeGradesDto;
	}

	@Override
	public List<OsiSubPracticeGrades> getOsiSubPracticeGradesList(
			List<OsiSubPracticeGradesDto> osiSubPracticeGradesDtoList) {
		List<OsiSubPracticeGrades> osiSubPracticeGradesList = new ArrayList<>();
		/*osiSubPracticeGradesDtoList.stream().forEach(OsiSubPracticeGradesDto -> {
			osiSubPracticeGradesList.add(this.getOsiSubPracticeGrades(OsiSubPracticeGradesDto));
		});*/
		for (OsiSubPracticeGradesDto osiSubPracticeGradesDto : osiSubPracticeGradesDtoList) {
			osiSubPracticeGradesList.add(this.getOsiSubPracticeGrades(osiSubPracticeGradesDto));
		}
		return osiSubPracticeGradesList;
	}

	@Override
	public List<OsiSubPracticeGradesDto> getOsiSubPracticeGradesDtoList(
			List<OsiSubPracticeGrades> osiSubPracticeGradesList) {
		List<OsiSubPracticeGradesDto> osiSubPracticeGradesDtoList = new ArrayList<>();
		/*osiSubPracticeGradesList.forEach(OsiSubPracticeGrades -> {
			osiSubPracticeGradesDtoList.add(this.getOsiSubPracticeGradesDto(OsiSubPracticeGrades));
		});*/
		for (OsiSubPracticeGrades osiSubPracticeGrades : osiSubPracticeGradesList) {
			osiSubPracticeGradesDtoList.add(this.getOsiSubPracticeGradesDto(osiSubPracticeGrades));
		}
		return osiSubPracticeGradesDtoList;
	}

}
