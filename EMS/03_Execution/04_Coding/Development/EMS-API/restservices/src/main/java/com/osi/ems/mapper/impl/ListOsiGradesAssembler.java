package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiGrades;
import com.osi.ems.service.dto.ListOsiGradesVO;

@Component
public class ListOsiGradesAssembler {

	public OsiGrades toOsiGrades(ListOsiGradesVO listOsiGradesVO) {
		OsiGrades OsiGrades = new OsiGrades();
		OsiGrades.setGradeId(listOsiGradesVO.getGradeId());
		OsiGrades.setGradeName(listOsiGradesVO.getGradeName());
		OsiGrades.setGradeDescription(listOsiGradesVO.getGradeDescription());
		/*OsiGrades.setCostPerHour(listOsiGradesVO.getCostPerHour());
		OsiGrades.setCostPerMonth(listOsiGradesVO.getCostPerMonth());
		OsiGrades.setRevPerHour(listOsiGradesVO.getRevPerHour());
		OsiGrades.setRevPerMonth(listOsiGradesVO.getRevPerMonth());*/
		OsiGrades.setOrgId(listOsiGradesVO.getOrgId());
		OsiGrades.setSeq((listOsiGradesVO.getSeq()));
		return OsiGrades;
	}

	public ListOsiGradesVO toListOsiGradesVO(OsiGrades OsiGrades) {;
		ListOsiGradesVO listOsiGradesVO = new ListOsiGradesVO();
		listOsiGradesVO.setGradeId(OsiGrades.getGradeId());
		listOsiGradesVO.setGradeName(OsiGrades.getGradeName());
		listOsiGradesVO.setGradeDescription(OsiGrades.getGradeDescription());
		/*listOsiGradesVO.setCostPerHour(OsiGrades.getCostPerHour());
		listOsiGradesVO.setCostPerMonth(OsiGrades.getCostPerMonth());
		listOsiGradesVO.setRevPerHour(OsiGrades.getRevPerHour());
		listOsiGradesVO.setRevPerMonth(OsiGrades.getRevPerMonth());*/
		listOsiGradesVO.setOrgId(OsiGrades.getOrgId());
		listOsiGradesVO.setSeq(OsiGrades.getSeq());
		return listOsiGradesVO;
	}

	public List<OsiGrades> toOsiGradesList(List<ListOsiGradesVO> listOsiGradesVOList){
		List<OsiGrades> OsiGradesList = new ArrayList<OsiGrades>();
		for (ListOsiGradesVO listOsiGradesVO : listOsiGradesVOList) {
			OsiGradesList.add(toOsiGrades(listOsiGradesVO));
			}
		return OsiGradesList;
	}

	public List<ListOsiGradesVO> toListOsiGradesVOList(List<OsiGrades> OsiGradesList){
		List<ListOsiGradesVO> listOsiGradesVOList = new ArrayList<ListOsiGradesVO>();
		for (OsiGrades OsiGrades : OsiGradesList) {
			listOsiGradesVOList.add(toListOsiGradesVO(OsiGrades));
			}
		return listOsiGradesVOList;
	}

}