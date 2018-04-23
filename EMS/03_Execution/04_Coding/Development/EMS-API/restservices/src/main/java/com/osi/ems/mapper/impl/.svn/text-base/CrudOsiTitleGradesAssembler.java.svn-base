package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiTitleGrades;
import com.osi.ems.service.dto.OsiTitleGradesDTO;

@Component
public class CrudOsiTitleGradesAssembler {

	public OsiTitleGrades toOsiTitleGrades(OsiTitleGradesDTO osiTitleGradesDTO) {
		OsiTitleGrades titleGrades = new OsiTitleGrades();

		titleGrades.setTitleGradeId(osiTitleGradesDTO.getTitleGradeId());
		titleGrades.setTitleId(osiTitleGradesDTO.getTitleId());
		titleGrades.setCreatedBy(osiTitleGradesDTO.getCreatedBy());
		titleGrades.setCreationDate(osiTitleGradesDTO.getCreationDate());
		titleGrades.setLastUpdateDate(osiTitleGradesDTO.getLastUpdateDate());
		titleGrades.setLastUpdatedBy(osiTitleGradesDTO.getLastUpdatedBy());
		titleGrades.setGrades(osiTitleGradesDTO.getGradeId());
		return titleGrades;
	}

	public OsiTitleGradesDTO toOsiTitleGradesDTO(OsiTitleGrades titleGrades) {
		OsiTitleGradesDTO osiTitleGradesDTO = new OsiTitleGradesDTO();

		osiTitleGradesDTO.setTitleGradeId(titleGrades.getTitleGradeId());
		osiTitleGradesDTO.setTitleId(titleGrades.getTitleId());
		osiTitleGradesDTO.setGradeId(titleGrades.getGrades());
		osiTitleGradesDTO.setCreatedBy(titleGrades.getCreatedBy());
		osiTitleGradesDTO.setCreationDate(titleGrades.getCreationDate());
		osiTitleGradesDTO.setLastUpdateDate(titleGrades.getLastUpdateDate());
		osiTitleGradesDTO.setLastUpdatedBy(titleGrades.getLastUpdatedBy());

		return osiTitleGradesDTO;
	}

	public List<OsiTitleGrades> toOsiTitleGradesList(List<OsiTitleGradesDTO> osiTitleGradesDTOList) {
		List<OsiTitleGrades> titleGradesList = new ArrayList<OsiTitleGrades>();

		for (OsiTitleGradesDTO osiTitleGradesDTO : osiTitleGradesDTOList) {
			titleGradesList.add(toOsiTitleGrades(osiTitleGradesDTO));
		}
		return titleGradesList;
	}

	public List<OsiTitleGradesDTO> toOsiTitleGradesDTOList(List<OsiTitleGrades> osiTitleGradesList) {
		List<OsiTitleGradesDTO> titleGradesDTOList = new ArrayList<OsiTitleGradesDTO>();

		for (OsiTitleGrades osiTitleGrades : osiTitleGradesList) {
			titleGradesDTOList.add(toOsiTitleGradesDTO(osiTitleGrades));
		}
		return titleGradesDTOList;
	}

}
