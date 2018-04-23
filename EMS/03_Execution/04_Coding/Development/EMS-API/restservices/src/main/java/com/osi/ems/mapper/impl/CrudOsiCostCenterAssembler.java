package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiBuGrades;
import com.osi.ems.domain.OsiCCGradesHistoryDTO;
import com.osi.ems.domain.OsiCcGrades;
import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.domain.OsiDeptGrades;
import com.osi.ems.service.dto.CrudOsiCostCenterVO;
import com.osi.ems.service.dto.OsiBuGradesDTO;
import com.osi.ems.service.dto.OsiCcGradesDTO;
import com.osi.ems.service.dto.OsiDeptGradesDTO;

@Component
public class CrudOsiCostCenterAssembler {

	public OsiCostCenter toOsiCostCenterEntity(CrudOsiCostCenterVO crudOsiCostCenterVO) {;
		OsiCostCenter osicostcenterEntity = new OsiCostCenter();
		osicostcenterEntity.setCcId(crudOsiCostCenterVO.getCcId());
		osicostcenterEntity.setCcShortName(crudOsiCostCenterVO.getCcShortName());
		osicostcenterEntity.setCcLongName(crudOsiCostCenterVO.getCcLongName());
		osicostcenterEntity.setActive(crudOsiCostCenterVO.getActive());
		osicostcenterEntity.setCreatedBy(crudOsiCostCenterVO.getCreatedBy());
		osicostcenterEntity.setCreationDate(crudOsiCostCenterVO.getCreationDate());
		osicostcenterEntity.setLastUpdateDate(crudOsiCostCenterVO.getLastUpdateDate());
		osicostcenterEntity.setLastUpdatedBy(crudOsiCostCenterVO.getLastUpdatedBy());
		

		return osicostcenterEntity;
	}

	public CrudOsiCostCenterVO toCrudOsiCostCenterVO(OsiCostCenter osicostcenterEntity) {;
		CrudOsiCostCenterVO crudOsiCostCenterVO = new CrudOsiCostCenterVO();
		crudOsiCostCenterVO.setCcId(osicostcenterEntity.getCcId());
		crudOsiCostCenterVO.setCcShortName(osicostcenterEntity.getCcShortName());
		crudOsiCostCenterVO.setCcLongName(osicostcenterEntity.getCcLongName());
		crudOsiCostCenterVO.setActive(osicostcenterEntity.getActive());
		crudOsiCostCenterVO.setCreatedBy(osicostcenterEntity.getCreatedBy());
		crudOsiCostCenterVO.setCreationDate(osicostcenterEntity.getCreationDate());
		crudOsiCostCenterVO.setLastUpdateDate(osicostcenterEntity.getLastUpdateDate());
		crudOsiCostCenterVO.setLastUpdatedBy(osicostcenterEntity.getLastUpdatedBy());

		return crudOsiCostCenterVO;
	}

	public List<OsiCostCenter> toOsiCostCenterEntityList(List<CrudOsiCostCenterVO> crudOsiCostCenterVOList){
		List<OsiCostCenter> osicostcenterEntityList = new ArrayList<OsiCostCenter>();;
		for (CrudOsiCostCenterVO crudOsiCostCenterVO : crudOsiCostCenterVOList) {;
			osicostcenterEntityList.add(toOsiCostCenterEntity(crudOsiCostCenterVO));;
			}
		return osicostcenterEntityList;
	}

	public List<CrudOsiCostCenterVO> toCrudOsiCostCenterVOList(List<OsiCostCenter> osicostcenterEntityList){
		List<CrudOsiCostCenterVO> crudOsiCostCenterVOList = new ArrayList<CrudOsiCostCenterVO>();;
		for (OsiCostCenter osicostcenterEntity : osicostcenterEntityList) {;
			crudOsiCostCenterVOList.add(toCrudOsiCostCenterVO(osicostcenterEntity));;
			}
		return crudOsiCostCenterVOList;
	}

	
	// OsiCCGrades
	public OsiCcGradesDTO toCrudOsiCCGradesDTO(OsiCcGrades osiccGradesEntity) {;
		OsiCcGradesDTO crudOsiCCGradesVO = new OsiCcGradesDTO();
		crudOsiCCGradesVO.setCcGradeId(osiccGradesEntity.getCcGradeId());
		crudOsiCCGradesVO.setCcId(osiccGradesEntity.getCcId());
		crudOsiCCGradesVO.setOrgId(osiccGradesEntity.getOrgId());
		crudOsiCCGradesVO.setGradeId(osiccGradesEntity.getGradeId());
		crudOsiCCGradesVO.setCostPerHour(osiccGradesEntity.getCostPerHour());
		crudOsiCCGradesVO.setCostPerMonth(osiccGradesEntity.getCostPerMonth());
		crudOsiCCGradesVO.setCreatedBy(osiccGradesEntity.getCreatedBy());
		crudOsiCCGradesVO.setCreationDate(osiccGradesEntity.getCreationDate());
		crudOsiCCGradesVO.setLastUpdateDate(osiccGradesEntity.getLastUpdateDate());
		crudOsiCCGradesVO.setLastUpdatedBy(osiccGradesEntity.getLastUpdatedBy());
		crudOsiCCGradesVO.setInternalCostOverheadPct(osiccGradesEntity.getInternalCostOverheadPct());
	
		return crudOsiCCGradesVO;
	}
	
	public OsiCcGrades toOsiCCGradesEntity(OsiCcGradesDTO crudOsiCCGradesVO) {;
		OsiCcGrades osiccGradesEntity = new OsiCcGrades();
		osiccGradesEntity.setCcGradeId(crudOsiCCGradesVO.getCcGradeId());
		osiccGradesEntity.setCcId(crudOsiCCGradesVO.getCcId());
		osiccGradesEntity.setOrgId(crudOsiCCGradesVO.getOrgId());
		osiccGradesEntity.setGradeId(crudOsiCCGradesVO.getGradeId());
		osiccGradesEntity.setCostPerHour(crudOsiCCGradesVO.getCostPerHour());
		osiccGradesEntity.setCostPerMonth(crudOsiCCGradesVO.getCostPerMonth());
		osiccGradesEntity.setCreatedBy(crudOsiCCGradesVO.getCreatedBy());
		osiccGradesEntity.setCreationDate(crudOsiCCGradesVO.getCreationDate());
		osiccGradesEntity.setLastUpdateDate(crudOsiCCGradesVO.getLastUpdateDate());
		osiccGradesEntity.setLastUpdatedBy(crudOsiCCGradesVO.getLastUpdatedBy());
		osiccGradesEntity.setInternalCostOverheadPct(crudOsiCCGradesVO.getInternalCostOverheadPct());
	
		return osiccGradesEntity;
	}
	
	public List<OsiCcGrades> toOsiCCGradesEntityList(List<OsiCcGradesDTO> crudOsiCCGradesDTOList){
		List<OsiCcGrades> osiccGradesEntityList = new ArrayList<OsiCcGrades>();;
		for (OsiCcGradesDTO crudOsiCCGradesDTO : crudOsiCCGradesDTOList) {;
		osiccGradesEntityList.add(toOsiCCGradesEntity(crudOsiCCGradesDTO));;
			}
		return osiccGradesEntityList;
	}

	public List<OsiCcGradesDTO> toCrudOsiCCGradesDTOList(List<OsiCcGrades> osiccGradesEntityList){
		List<OsiCcGradesDTO> crudOsiCCGradesDTOList = new ArrayList<OsiCcGradesDTO>();;
		for (OsiCcGrades osiccGradesEntity : osiccGradesEntityList) {;
		crudOsiCCGradesDTOList.add(toCrudOsiCCGradesDTO(osiccGradesEntity));;
			}
		return crudOsiCCGradesDTOList;
	}
	
		// OsiBUGrades
		public OsiBuGradesDTO toCrudOsiBuGradesDTO(OsiBuGrades buGradesEntity) {;
		OsiBuGradesDTO buGradesDTO = new OsiBuGradesDTO();
			buGradesDTO.setBuGradeId(buGradesEntity.getBuGradeId());
			buGradesDTO.setBuId(buGradesEntity.getBuId());
			buGradesDTO.setOrgId(buGradesEntity.getOrgId());
			buGradesDTO.setGradeId(buGradesEntity.getGradeId());
			buGradesDTO.setCostPerHour(buGradesEntity.getCostPerHour());
			buGradesDTO.setCostPerMonth(buGradesEntity.getCostPerMonth());
			buGradesDTO.setCreatedBy(buGradesEntity.getCreatedBy());
			buGradesDTO.setCreationDate(buGradesEntity.getCreationDate());
			buGradesDTO.setLastUpdateDate(buGradesEntity.getLastUpdateDate());
			buGradesDTO.setLastUpdatedBy(buGradesEntity.getLastUpdatedBy());
			buGradesDTO.setInternalCostOverheadPct(buGradesEntity.getInternalCostOverheadPct());
			
			return buGradesDTO;
		}
		
		public OsiBuGrades toOsiBuGradesEntity(OsiBuGradesDTO buGradesDTO) {;
		OsiBuGrades buGradesEntity = new OsiBuGrades();
			buGradesEntity.setBuGradeId(buGradesDTO.getBuGradeId());
			buGradesEntity.setBuId(buGradesDTO.getBuId());
			buGradesEntity.setOrgId(buGradesDTO.getOrgId());
			buGradesEntity.setGradeId(buGradesDTO.getGradeId());
			buGradesEntity.setCostPerHour(buGradesDTO.getCostPerHour());
			buGradesEntity.setCostPerMonth(buGradesDTO.getCostPerMonth());
			buGradesEntity.setCreatedBy(buGradesDTO.getCreatedBy());
			buGradesEntity.setCreationDate(buGradesDTO.getCreationDate());
			buGradesEntity.setLastUpdateDate(buGradesDTO.getLastUpdateDate());
			buGradesEntity.setLastUpdatedBy(buGradesDTO.getLastUpdatedBy());
			buGradesEntity.setInternalCostOverheadPct(buGradesDTO.getInternalCostOverheadPct());
		
			return buGradesEntity;
		}
		
		public List<OsiBuGrades> toOsiBuGradesEntityList(List<OsiBuGradesDTO> osiBuGradesDTOList){
			List<OsiBuGrades> osiBuGradesEntityList = new ArrayList<OsiBuGrades>();;
			for (OsiBuGradesDTO osiBuGradesDTO : osiBuGradesDTOList) {;
				osiBuGradesEntityList.add(toOsiBuGradesEntity(osiBuGradesDTO));;
			}
			return osiBuGradesEntityList;
		}

		public List<OsiBuGradesDTO> toCrudOsiBuGradesDTOList(List<OsiBuGrades> osiccGradesEntityList){
			List<OsiBuGradesDTO> osiBuGradesDTOList = new ArrayList<OsiBuGradesDTO>();;
			for (OsiBuGrades osiBuGradesEntity : osiccGradesEntityList) {;
				osiBuGradesDTOList.add(toCrudOsiBuGradesDTO(osiBuGradesEntity));;
			}
			return osiBuGradesDTOList;
		}
		
		// OsiDeptGrades
		public OsiDeptGradesDTO toCrudOsiDeptGradesDTO(OsiDeptGrades deptGradesEntity) {;
			OsiDeptGradesDTO deptGradesDTO = new OsiDeptGradesDTO();
			deptGradesDTO.setDeptGradeId(deptGradesEntity.getDeptGradeId());
			deptGradesDTO.setDeptId(deptGradesEntity.getDeptId());
			deptGradesDTO.setOrgId(deptGradesEntity.getOrgId());
			deptGradesDTO.setGradeId(deptGradesEntity.getGradeId());
			deptGradesDTO.setCostPerHour(deptGradesEntity.getCostPerHour());
			deptGradesDTO.setCostPerMonth(deptGradesEntity.getCostPerMonth());
			deptGradesDTO.setCreatedBy(deptGradesEntity.getCreatedBy());
			deptGradesDTO.setCreationDate(deptGradesEntity.getCreationDate());
			deptGradesDTO.setLastUpdateDate(deptGradesEntity.getLastUpdateDate());
			deptGradesDTO.setLastUpdatedBy(deptGradesEntity.getLastUpdatedBy());
			deptGradesDTO.setInternalCostOverheadPct(deptGradesEntity.getInternalCostOverheadPct());
		
			return deptGradesDTO;
		}
		
		public OsiDeptGrades toOsiDeptGradesEntity(OsiDeptGradesDTO deptGradesDTO) {;
			OsiDeptGrades deptGradesEntity = new OsiDeptGrades();
			deptGradesEntity.setDeptGradeId(deptGradesDTO.getDeptGradeId());
			deptGradesEntity.setDeptId(deptGradesDTO.getDeptId());
			deptGradesEntity.setOrgId(deptGradesDTO.getOrgId());
			deptGradesEntity.setGradeId(deptGradesDTO.getGradeId());
			deptGradesEntity.setCostPerHour(deptGradesDTO.getCostPerHour());
			deptGradesEntity.setCostPerMonth(deptGradesDTO.getCostPerMonth());
			deptGradesEntity.setCreatedBy(deptGradesDTO.getCreatedBy());
			deptGradesEntity.setCreationDate(deptGradesDTO.getCreationDate());
			deptGradesEntity.setLastUpdateDate(deptGradesDTO.getLastUpdateDate());
			deptGradesEntity.setLastUpdatedBy(deptGradesDTO.getLastUpdatedBy());		
			deptGradesEntity.setInternalCostOverheadPct(deptGradesDTO.getInternalCostOverheadPct());
			
			return deptGradesEntity;
		}
		
		public List<OsiDeptGrades> toOsiDeptGradesEntityList(List<OsiDeptGradesDTO> deptGradesDTOList){
			List<OsiDeptGrades> deptGradesEntityList = new ArrayList<OsiDeptGrades>();;
			for (OsiDeptGradesDTO deptGradesDTO : deptGradesDTOList) {;
				deptGradesEntityList.add(toOsiDeptGradesEntity(deptGradesDTO));;
			}
			return deptGradesEntityList;
		}

		public List<OsiDeptGradesDTO> toCrudOsiDeptGradesDTOList(List<OsiDeptGrades> deptGradesEntityList){
			List<OsiDeptGradesDTO> deptGradesDTOList = new ArrayList<OsiDeptGradesDTO>();;
			for (OsiDeptGrades osiBuGradesEntity : deptGradesEntityList) {;
				deptGradesDTOList.add(toCrudOsiDeptGradesDTO(osiBuGradesEntity));;
			}
			return deptGradesDTOList;
		}
		
		
		public OsiCcGradesDTO toCrudOsiCcGradesVOHist(OsiCCGradesHistoryDTO osiCcGradesHistDto) {
			OsiCcGradesDTO ccGradesDTO = new OsiCcGradesDTO();
			ccGradesDTO.setGradeId(osiCcGradesHistDto.getGradeId());
			ccGradesDTO.setCcGradeId(osiCcGradesHistDto.getCcGradeId());
			ccGradesDTO.setInternalCostOverheadPct(osiCcGradesHistDto.getInternalCostOverheadPct());
			ccGradesDTO.setCostPerHour(osiCcGradesHistDto.getCostPerHour());
			ccGradesDTO.setCostPerMonth(osiCcGradesHistDto.getCostPerMonth());
			ccGradesDTO.setOrgId(osiCcGradesHistDto.getOrgId());
			ccGradesDTO.setCreationDate(osiCcGradesHistDto.getCreationDate());
			ccGradesDTO.setCreatedBy(osiCcGradesHistDto.getCreatedBy());
			ccGradesDTO.setLastUpdateDate(osiCcGradesHistDto.getLastUpdateDate());
			ccGradesDTO.setLastUpdatedBy(osiCcGradesHistDto.getLastUpdatedBy());

			return ccGradesDTO;
		}
		public List<OsiCcGradesDTO> toCrudOsiGradesHistDTOList(List<OsiCCGradesHistoryDTO> osiCcGradesHistList){
			List<OsiCcGradesDTO> crudOsiCcGradesVOList = new ArrayList<OsiCcGradesDTO>();
			for (OsiCCGradesHistoryDTO osiCcGradesHist : osiCcGradesHistList) {
				crudOsiCcGradesVOList.add(toCrudOsiCcGradesVOHist(osiCcGradesHist));
				}
			return crudOsiCcGradesVOList;
		}
}