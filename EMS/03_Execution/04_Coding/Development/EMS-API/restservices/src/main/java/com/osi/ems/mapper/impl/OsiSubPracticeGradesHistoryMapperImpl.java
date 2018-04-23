package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiSubPracticeGradesHistory;
import com.osi.ems.mapper.OsiSubPracticeGradesHistoryMapper;
import com.osi.ems.service.dto.OsiSubPracticeGradesHistoryDto;

@Component
public class OsiSubPracticeGradesHistoryMapperImpl implements OsiSubPracticeGradesHistoryMapper {

	@Override
	public OsiSubPracticeGradesHistory getOsiSubPracticeGradesHistory(
			OsiSubPracticeGradesHistoryDto osiSubPracticeGradesHistoryDto) {
		OsiSubPracticeGradesHistory osiSubPracticeGradesHistory = null;
		osiSubPracticeGradesHistory = new OsiSubPracticeGradesHistory();

		osiSubPracticeGradesHistory.setCreatedBy(osiSubPracticeGradesHistoryDto.getCreatedBy());
		osiSubPracticeGradesHistory.setCreatedDate(osiSubPracticeGradesHistoryDto.getCreatedDate());
		osiSubPracticeGradesHistory.setLastUpdateDate(osiSubPracticeGradesHistoryDto.getLastUpdateDate());
		osiSubPracticeGradesHistory.setUpdatedBy(osiSubPracticeGradesHistoryDto.getUpdatedBy());
		osiSubPracticeGradesHistory.setCostPerHour(osiSubPracticeGradesHistoryDto.getCostPerHour());
		osiSubPracticeGradesHistory.setCostPerMonth(osiSubPracticeGradesHistoryDto.getCostPerMonth());
		osiSubPracticeGradesHistory.setGradeId(osiSubPracticeGradesHistoryDto.getGradeId());
		osiSubPracticeGradesHistory
				.setInternalCostOverheadPercentage(osiSubPracticeGradesHistoryDto.getInternalCostOverheadPct());
		osiSubPracticeGradesHistory.setOrgId(osiSubPracticeGradesHistoryDto.getOrgId());
		osiSubPracticeGradesHistory.setSubPracticeId(osiSubPracticeGradesHistoryDto.getSubPracticeId());
		osiSubPracticeGradesHistory.setSubPracticeGradeId(osiSubPracticeGradesHistoryDto.getSubPracticeGradeId());

		return osiSubPracticeGradesHistory;
	}

	@Override
	public OsiSubPracticeGradesHistoryDto getOsiSubPracticeGradesHistoryDto(
			OsiSubPracticeGradesHistory osiSubPracticeGradesHistory) {

		OsiSubPracticeGradesHistoryDto osiSubPracticeGradesHistoryDto = null;
		osiSubPracticeGradesHistoryDto = new OsiSubPracticeGradesHistoryDto();

		osiSubPracticeGradesHistoryDto.setCreatedBy(osiSubPracticeGradesHistory.getCreatedBy());
		osiSubPracticeGradesHistoryDto.setCreatedDate(osiSubPracticeGradesHistory.getCreatedDate());
		osiSubPracticeGradesHistoryDto.setLastUpdateDate(osiSubPracticeGradesHistory.getLastUpdateDate());
		osiSubPracticeGradesHistoryDto.setUpdatedBy(osiSubPracticeGradesHistory.getUpdatedBy());
		osiSubPracticeGradesHistoryDto.setCostPerHour(osiSubPracticeGradesHistory.getCostPerHour());
		osiSubPracticeGradesHistoryDto.setCostPerMonth(osiSubPracticeGradesHistory.getCostPerMonth());
		osiSubPracticeGradesHistoryDto.setGradeId(osiSubPracticeGradesHistory.getGradeId());
		osiSubPracticeGradesHistoryDto
				.setInternalCostOverheadPct(osiSubPracticeGradesHistory.getInternalCostOverheadPercentage());
		osiSubPracticeGradesHistoryDto.setOrgId(osiSubPracticeGradesHistory.getOrgId());
		osiSubPracticeGradesHistoryDto.setSubPracticeId(osiSubPracticeGradesHistory.getSubPracticeId());
		osiSubPracticeGradesHistoryDto.setSubPracticeGradeId(osiSubPracticeGradesHistory.getSubPracticeGradeId());

		return osiSubPracticeGradesHistoryDto;
	}

	@Override
	public List<OsiSubPracticeGradesHistory> getOsiSubPracticeGradesHistoryList(
			List<OsiSubPracticeGradesHistoryDto> osiSubPracticeGradesHistoryDtoList) {
		List<OsiSubPracticeGradesHistory> osiSubPracticeGradesHistoryList = new ArrayList<>();
		/*osiSubPracticeGradesHistoryDtoList.stream().forEach(OsiSubPracticeGradesHistoryDto -> {
			osiSubPracticeGradesHistoryList.add(this.getOsiSubPracticeGradesHistory(OsiSubPracticeGradesHistoryDto));
		});*/
		for (OsiSubPracticeGradesHistoryDto osiSubPracticeGradesDto : osiSubPracticeGradesHistoryDtoList) {
			osiSubPracticeGradesHistoryList.add(this.getOsiSubPracticeGradesHistory(osiSubPracticeGradesDto));
		}
		return osiSubPracticeGradesHistoryList;
	}

	@Override
	public List<OsiSubPracticeGradesHistoryDto> getOsiSubPracticeGradesHistoryDtoList(
			List<OsiSubPracticeGradesHistory> osiSubPracticeGradesHistoryList) {
		List<OsiSubPracticeGradesHistoryDto> osiSubPracticeGradesHistoryDtoList = new ArrayList<>();
		
		/*osiSubPracticeGradesHistoryList.forEach(OsiSubPracticeGradesHistory -> {
			osiSubPracticeGradesHistoryDtoList.add(this.getOsiSubPracticeGradesHistoryDto(OsiSubPracticeGradesHistory));
		});*/
		for (OsiSubPracticeGradesHistory osiSubPracticeGrades : osiSubPracticeGradesHistoryList) {
			osiSubPracticeGradesHistoryDtoList.add(this.getOsiSubPracticeGradesHistoryDto(osiSubPracticeGrades));
		}
		return osiSubPracticeGradesHistoryDtoList;
	}

}
