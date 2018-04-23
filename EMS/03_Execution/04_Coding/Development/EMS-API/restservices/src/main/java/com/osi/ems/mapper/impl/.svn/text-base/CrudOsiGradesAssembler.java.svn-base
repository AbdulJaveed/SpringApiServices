package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiGradesHistory;
import com.osi.ems.service.dto.CrudOsiGradesVO;

@Component
public class CrudOsiGradesAssembler {

	public OsiGrades toOsiGrades(CrudOsiGradesVO crudOsiGradesVO) {
		OsiGrades OsiGrades = new OsiGrades();
		OsiGrades.setGradeId(crudOsiGradesVO.getGradeId());
		OsiGrades.setGradeName(crudOsiGradesVO.getGradeName());
		OsiGrades.setGradeDescription(crudOsiGradesVO.getGradeDescription());
		OsiGrades.setSeq(crudOsiGradesVO.getSeq());
		OsiGrades.setCostPerHour(crudOsiGradesVO.getCostPerHour());
		OsiGrades.setCostPerMonth(crudOsiGradesVO.getCostPerMonth());
		OsiGrades.setRevPerHour(crudOsiGradesVO.getRevPerHour());
		OsiGrades.setRevPerMonth(crudOsiGradesVO.getRevPerMonth());
		OsiGrades.setActive(crudOsiGradesVO.getActive());
		OsiGrades.setOrgId(crudOsiGradesVO.getOrgId());
		return OsiGrades;
	}

	public CrudOsiGradesVO toCrudOsiGradesVO(OsiGrades OsiGrades) {
		CrudOsiGradesVO crudOsiGradesVO = new CrudOsiGradesVO();
		crudOsiGradesVO.setGradeId(OsiGrades.getGradeId());
		crudOsiGradesVO.setGradeName(OsiGrades.getGradeName());
		crudOsiGradesVO.setGradeDescription(OsiGrades.getGradeDescription());
		crudOsiGradesVO.setSeq(OsiGrades.getSeq());
		crudOsiGradesVO.setCostPerHour(OsiGrades.getCostPerHour());
		crudOsiGradesVO.setCostPerMonth(OsiGrades.getCostPerMonth());
		crudOsiGradesVO.setRevPerHour(OsiGrades.getRevPerHour());
		crudOsiGradesVO.setRevPerMonth(OsiGrades.getRevPerMonth());
		crudOsiGradesVO.setActive(OsiGrades.getActive());
		crudOsiGradesVO.setOrgId(OsiGrades.getOrgId());

		return crudOsiGradesVO;
	}

	public List<OsiGrades> toOsiGradesList(List<CrudOsiGradesVO> crudOsiGradesVOList){
		List<OsiGrades> OsiGradesList = new ArrayList<OsiGrades>();
		for (CrudOsiGradesVO crudOsiGradesVO : crudOsiGradesVOList) {
			OsiGradesList.add(toOsiGrades(crudOsiGradesVO));
			}
		return OsiGradesList;
	}

	public List<CrudOsiGradesVO> toCrudOsiGradesVOList(List<OsiGrades> OsiGradesList){
		List<CrudOsiGradesVO> crudOsiGradesVOList = new ArrayList<CrudOsiGradesVO>();
		for (OsiGrades OsiGrades : OsiGradesList) {
			crudOsiGradesVOList.add(toCrudOsiGradesVO(OsiGrades));
			}
		return crudOsiGradesVOList;
	}
	
	public CrudOsiGradesVO toCrudOsiGradesVOHist(OsiGradesHistory OsiGradesHist) {
		CrudOsiGradesVO crudOsiGradesVO = new CrudOsiGradesVO();
		crudOsiGradesVO.setGradeId(OsiGradesHist.getGradeId());
		crudOsiGradesVO.setGradeName(OsiGradesHist.getGradeName());
		crudOsiGradesVO.setGradeDescription(OsiGradesHist.getGradeDescription());
		crudOsiGradesVO.setSeq(OsiGradesHist.getSeq());
		crudOsiGradesVO.setCostPerHour(OsiGradesHist.getCostPerHour());
		crudOsiGradesVO.setCostPerMonth(OsiGradesHist.getCostPerMonth());
		crudOsiGradesVO.setRevPerHour(OsiGradesHist.getRevPerHour());
		crudOsiGradesVO.setRevPerMonth(OsiGradesHist.getRevPerMonth());
		crudOsiGradesVO.setActive(OsiGradesHist.getActive());
		crudOsiGradesVO.setOrgId(OsiGradesHist.getOrgId());
		crudOsiGradesVO.setCreationDate(OsiGradesHist.getCreatedDate());

		return crudOsiGradesVO;
	}
	public List<CrudOsiGradesVO> toCrudOsiGradesHistVOList(List<OsiGradesHistory> OsiGradesHistList){
		List<CrudOsiGradesVO> crudOsiGradesVOList = new ArrayList<CrudOsiGradesVO>();
		for (OsiGradesHistory OsiGradesHist : OsiGradesHistList) {
			crudOsiGradesVOList.add(toCrudOsiGradesVOHist(OsiGradesHist));
			}
		return crudOsiGradesVOList;
	}
}