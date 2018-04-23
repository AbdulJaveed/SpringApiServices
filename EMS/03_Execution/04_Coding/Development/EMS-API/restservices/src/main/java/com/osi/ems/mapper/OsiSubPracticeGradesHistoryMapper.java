package com.osi.ems.mapper;

import java.util.List;

import com.osi.ems.domain.OsiSubPracticeGradesHistory;
import com.osi.ems.service.dto.OsiSubPracticeGradesHistoryDto;

public interface OsiSubPracticeGradesHistoryMapper {

	public OsiSubPracticeGradesHistoryDto getOsiSubPracticeGradesHistoryDto(
			OsiSubPracticeGradesHistory OsiSubPracticeGradesHistory);

	public OsiSubPracticeGradesHistory getOsiSubPracticeGradesHistory(
			OsiSubPracticeGradesHistoryDto OsiSubPracticeGradesHistory);

	public List<OsiSubPracticeGradesHistory> getOsiSubPracticeGradesHistoryList(
			List<OsiSubPracticeGradesHistoryDto> OsiSubPracticeGradesHistoryList);

	public List<OsiSubPracticeGradesHistoryDto> getOsiSubPracticeGradesHistoryDtoList(
			List<OsiSubPracticeGradesHistory> OsiSubPracticeGradesHistoryList);
	

}
