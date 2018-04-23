package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiDepartment;
import com.osi.ems.domain.OsiDeptGradesHistoryDTO;
import com.osi.ems.service.dto.CrudOsiDepartmentVO;
import com.osi.ems.service.dto.OsiDeptGradesDTO;

@Component
public class CrudOsiDepratmentAssembler {

	public OsiDepartment toOsiDepratmentEntity(CrudOsiDepartmentVO crudOsiDepratmentVO) {
		OsiDepartment osidepratmentEntity = new OsiDepartment();
		osidepratmentEntity.setDeptId(crudOsiDepratmentVO.getDeptId());
		osidepratmentEntity.setDeptShortName(crudOsiDepratmentVO.getDeptShortName());
		osidepratmentEntity.setDeptLongName(crudOsiDepratmentVO.getDeptLongName());
		osidepratmentEntity.setActive(crudOsiDepratmentVO.getActive());
		osidepratmentEntity.setCreatedBy(crudOsiDepratmentVO.getCreatedBy());
		osidepratmentEntity.setCreationDate(crudOsiDepratmentVO.getCreationDate());
		osidepratmentEntity.setLastUpdateDate(crudOsiDepratmentVO.getLastUpdateDate());
		osidepratmentEntity.setLastUpdatedBy(crudOsiDepratmentVO.getLastUpdatedBy());
		
		return osidepratmentEntity;
	}

	public CrudOsiDepartmentVO toCrudOsiDepratmentVO(OsiDepartment osidepratmentEntity) {;
		CrudOsiDepartmentVO crudOsiDepratmentVO = new CrudOsiDepartmentVO();
		crudOsiDepratmentVO.setDeptId(osidepratmentEntity.getDeptId());
		crudOsiDepratmentVO.setDeptShortName(osidepratmentEntity.getDeptShortName());
		crudOsiDepratmentVO.setDeptLongName(osidepratmentEntity.getDeptLongName());
		crudOsiDepratmentVO.setActive(osidepratmentEntity.getActive());
		crudOsiDepratmentVO.setCreatedBy(osidepratmentEntity.getCreatedBy());
		crudOsiDepratmentVO.setCreationDate(osidepratmentEntity.getCreationDate());
		crudOsiDepratmentVO.setLastUpdateDate(osidepratmentEntity.getLastUpdateDate());
		crudOsiDepratmentVO.setLastUpdatedBy(osidepratmentEntity.getLastUpdatedBy());

		return crudOsiDepratmentVO;
	}

	public List<OsiDepartment> toOsiDepratmentEntityList(List<CrudOsiDepartmentVO> crudOsiDepratmentVOList){
		List<OsiDepartment> osidepratmentEntityList = new ArrayList<OsiDepartment>();;
		for (CrudOsiDepartmentVO crudOsiDepratmentVO : crudOsiDepratmentVOList) {;
			osidepratmentEntityList.add(toOsiDepratmentEntity(crudOsiDepratmentVO));;
			}
		return osidepratmentEntityList;
	}

	public List<CrudOsiDepartmentVO> toCrudOsiDepratmentVOList(List<OsiDepartment> osidepratmentEntityList){
		List<CrudOsiDepartmentVO> crudOsiDepratmentVOList = new ArrayList<CrudOsiDepartmentVO>();;
		for (OsiDepartment osidepratmentEntity : osidepratmentEntityList) {;
			crudOsiDepratmentVOList.add(toCrudOsiDepratmentVO(osidepratmentEntity));;
			}
		return crudOsiDepratmentVOList;
	}
	
	public OsiDeptGradesDTO toCrudOsiDeptGradesVOHist(OsiDeptGradesHistoryDTO osiBuGradesHistDto) {
		OsiDeptGradesDTO deptGradesDTO = new OsiDeptGradesDTO();
		deptGradesDTO.setGradeId(osiBuGradesHistDto.getGradeId());
		deptGradesDTO.setDeptGradeId(osiBuGradesHistDto.getDeptGradeId());
		deptGradesDTO.setInternalCostOverheadPct(osiBuGradesHistDto.getInternalCostOverheadPct());
		deptGradesDTO.setCostPerHour(osiBuGradesHistDto.getCostPerHour());
		deptGradesDTO.setCostPerMonth(osiBuGradesHistDto.getCostPerMonth());
		deptGradesDTO.setOrgId(osiBuGradesHistDto.getOrgId());
		deptGradesDTO.setCreationDate(osiBuGradesHistDto.getCreationDate());
		deptGradesDTO.setCreatedBy(osiBuGradesHistDto.getCreatedBy());
		deptGradesDTO.setLastUpdateDate(osiBuGradesHistDto.getLastUpdateDate());
		deptGradesDTO.setLastUpdatedBy(osiBuGradesHistDto.getLastUpdatedBy());

		return deptGradesDTO;
	}
	public List<OsiDeptGradesDTO> toCrudOsiGradesHistDTOList(List<OsiDeptGradesHistoryDTO> osiDeptGradesHistList){
		List<OsiDeptGradesDTO> crudOsiDeptGradesVOList = new ArrayList<OsiDeptGradesDTO>();
		for (OsiDeptGradesHistoryDTO osiDeptGradesHist : osiDeptGradesHistList) {
			crudOsiDeptGradesVOList.add(toCrudOsiDeptGradesVOHist(osiDeptGradesHist));
			}
		return crudOsiDeptGradesVOList;
	}

}