package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiBUGradesHistoryDTO;
import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.ems.service.dto.CrudOsiBusinessUnitsVO;
import com.osi.ems.service.dto.OsiBuGradesDTO;

@Component
public class CrudOsiBusinessUnitsAssembler {

	public OsiBusinessUnits toOsiBusinessUnitsEntity(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO) {;
		OsiBusinessUnits osibusinessunitsEntity = new OsiBusinessUnits();
		osibusinessunitsEntity.setBuId(crudOsiBusinessUnitsVO.getBuId());
		osibusinessunitsEntity.setBuShortName(crudOsiBusinessUnitsVO.getBuShortName());
		osibusinessunitsEntity.setBuLongName(crudOsiBusinessUnitsVO.getBuLongName());
		osibusinessunitsEntity.setActive(crudOsiBusinessUnitsVO.getActive());
		osibusinessunitsEntity.setCreatedBy(crudOsiBusinessUnitsVO.getCreatedBy());
		osibusinessunitsEntity.setCreationDate(crudOsiBusinessUnitsVO.getCreationDate());
		osibusinessunitsEntity.setLastUpdateDate(crudOsiBusinessUnitsVO.getLastUpdateDate());
		osibusinessunitsEntity.setLastUpdatedBy(crudOsiBusinessUnitsVO.getLastUpdatedBy());

		return osibusinessunitsEntity;
	}

	public CrudOsiBusinessUnitsVO toCrudOsiBusinessUnitsVO(OsiBusinessUnits osibusinessunitsEntity) {;
		CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO = new CrudOsiBusinessUnitsVO();
		crudOsiBusinessUnitsVO.setBuId(osibusinessunitsEntity.getBuId());
		crudOsiBusinessUnitsVO.setBuShortName(osibusinessunitsEntity.getBuShortName());
		crudOsiBusinessUnitsVO.setBuLongName(osibusinessunitsEntity.getBuLongName());
		crudOsiBusinessUnitsVO.setActive(osibusinessunitsEntity.getActive());
		crudOsiBusinessUnitsVO.setCreatedBy(osibusinessunitsEntity.getCreatedBy());
		crudOsiBusinessUnitsVO.setCreationDate(osibusinessunitsEntity.getCreationDate());
		crudOsiBusinessUnitsVO.setLastUpdateDate(osibusinessunitsEntity.getLastUpdateDate());
		crudOsiBusinessUnitsVO.setLastUpdatedBy(osibusinessunitsEntity.getLastUpdatedBy());

		return crudOsiBusinessUnitsVO;
	}

	public List<OsiBusinessUnits> toOsiBusinessUnitsEntityList(List<CrudOsiBusinessUnitsVO> crudOsiBusinessUnitsVOList){
		List<OsiBusinessUnits> osibusinessunitsEntityList = new ArrayList<OsiBusinessUnits>();;
		for (CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO : crudOsiBusinessUnitsVOList) {;
			osibusinessunitsEntityList.add(toOsiBusinessUnitsEntity(crudOsiBusinessUnitsVO));;
			}
		return osibusinessunitsEntityList;
	}

	public List<CrudOsiBusinessUnitsVO> toCrudOsiBusinessUnitsVOList(List<OsiBusinessUnits> osibusinessunitsEntityList){
		List<CrudOsiBusinessUnitsVO> crudOsiBusinessUnitsVOList = new ArrayList<CrudOsiBusinessUnitsVO>();;
		for (OsiBusinessUnits osibusinessunitsEntity : osibusinessunitsEntityList) {;
			crudOsiBusinessUnitsVOList.add(toCrudOsiBusinessUnitsVO(osibusinessunitsEntity));;
			}
		return crudOsiBusinessUnitsVOList;
	}
	
	
	public OsiBuGradesDTO toCrudOsiBuGradesVOHist(OsiBUGradesHistoryDTO osiBuGradesHistDto) {
		OsiBuGradesDTO buGradesDTO = new OsiBuGradesDTO();
		buGradesDTO.setGradeId(osiBuGradesHistDto.getGradeId());
		buGradesDTO.setBuGradeId(osiBuGradesHistDto.getBuGradeId());
		buGradesDTO.setInternalCostOverheadPct(osiBuGradesHistDto.getInternalCostOverheadPct());
		buGradesDTO.setCostPerHour(osiBuGradesHistDto.getCostPerHour());
		buGradesDTO.setCostPerMonth(osiBuGradesHistDto.getCostPerMonth());
		buGradesDTO.setOrgId(osiBuGradesHistDto.getOrgId());
		buGradesDTO.setCreationDate(osiBuGradesHistDto.getCreationDate());
		buGradesDTO.setCreatedBy(osiBuGradesHistDto.getCreatedBy());
		buGradesDTO.setLastUpdateDate(osiBuGradesHistDto.getLastUpdateDate());
		buGradesDTO.setLastUpdatedBy(osiBuGradesHistDto.getLastUpdatedBy());

		return buGradesDTO;
	}
	public List<OsiBuGradesDTO> toCrudOsiGradesHistDTOList(List<OsiBUGradesHistoryDTO> osiBuGradesHistList){
		List<OsiBuGradesDTO> crudOsiBuGradesVOList = new ArrayList<OsiBuGradesDTO>();
		for (OsiBUGradesHistoryDTO osiBuGradesHist : osiBuGradesHistList) {
			crudOsiBuGradesVOList.add(toCrudOsiBuGradesVOHist(osiBuGradesHist));
			}
		return crudOsiBuGradesVOList;
	}

}