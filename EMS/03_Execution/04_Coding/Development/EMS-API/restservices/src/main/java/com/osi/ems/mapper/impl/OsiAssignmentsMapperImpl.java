package com.osi.ems.mapper.impl;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiAssignments;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.domain.OsiLocations;
import com.osi.ems.mapper.OsiAssignmentsMapper;
import com.osi.ems.service.dto.OsiAssignmentsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.ems.service.dto.OsiJobCodesDTO;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRegionsDTO;
import com.osi.urm.exception.BusinessException;

@Component
public class OsiAssignmentsMapperImpl implements OsiAssignmentsMapper {

	@Override
	public OsiAssignmentsDTO convertOsiAssignmentToDTO(OsiAssignments assignment) throws BusinessException{
		OsiAssignmentsDTO dto = new OsiAssignmentsDTO();
		
		dto.setVersion(assignment.getVersion());
		dto.setAssignmentId(assignment.getAssignmentId());
		dto.setAssignmentType(assignment.getAssignmentType());
		dto.setEmployeeType(assignment.getAssignmentType());
		dto.setDeptId(assignment.getDeptId());
		dto.setIsManager(assignment.getIsManager());
		dto.setOnProbation(assignment.getOnProbation());
		dto.setProbationUnit(assignment.getProbationUnit());
		dto.setProbationUnitValue(assignment.getProbationUnitValue());
		dto.setChangeReason(assignment.getChangeReason());
		dto.setPayGradeId(assignment.getPayGradeId());
		dto.setSupervisorId(assignment.getSupervisorId());
		dto.setReferredById(assignment.getReferredById());
		dto.setGradeId(assignment.getGradeId());
		dto.setJobId(assignment.getJobId());
		dto.setLocationId(assignment.getLocationId());
		dto.setEmployeeId(assignment.getEmployeeId());
		
		dto.setEffectiveStartDate(assignment.getEffectiveStartDate());
		dto.setEffectiveEndDate(assignment.getEffectiveEndDate());
		dto.setContractStartDate(assignment.getContractStartDate());
		dto.setContractEndDate(assignment.getContractEndDate());
		
		dto.setProbationEndDate(assignment.getProbationEndDate());
		dto.setCreatedBy(assignment.getCreatedBy());
		dto.setCreatedDate(assignment.getCreatedDate());
		dto.setUpdatedBy(assignment.getUpdatedBy());
		dto.setLastUpdateDate(assignment.getLastUpdateDate());

		
		return dto;
	}

	@Override
	public OsiAssignments convertDTOToOsiAssignment(OsiAssignmentsDTO dto) throws BusinessException {
		OsiAssignments assignment = new OsiAssignments();
	
		
		assignment.setVersion(dto.getVersion());
		assignment.setAssignmentId(dto.getAssignmentId());
		assignment.setAssignmentType(dto.getAssignmentType());
		assignment.setDeptId(dto.getDeptId());
		assignment.setIsManager(dto.getIsManager());
		assignment.setOnProbation(dto.getOnProbation());
		assignment.setProbationUnit(dto.getProbationUnit());
		assignment.setProbationUnitValue(dto.getProbationUnitValue());
		assignment.setChangeReason(dto.getChangeReason());
		assignment.setPayGradeId(dto.getPayGradeId());
		assignment.setSupervisorId(dto.getSupervisorId());
		assignment.setReferredById(dto.getReferredById());
		assignment.setGradeId(dto.getGradeId());
		assignment.setJobId(dto.getJobId());
		assignment.setLocationId(dto.getLocationId());
		assignment.setEmployeeId(dto.getEmployeeId());
		
		
		assignment.setEffectiveStartDate(dto.getEffectiveStartDate());
		assignment.setEffectiveEndDate(dto.getEffectiveEndDate());
		assignment.setContractStartDate(dto.getContractStartDate());
		assignment.setContractEndDate(dto.getContractEndDate());
		
		assignment.setProbationEndDate(dto.getProbationEndDate());
		assignment.setCreatedBy(dto.getCreatedBy());
		assignment.setCreatedDate(dto.getCreatedDate());
		assignment.setUpdatedBy(dto.getUpdatedBy());
		assignment.setLastUpdateDate(dto.getLastUpdateDate());
		
		
		return assignment;
	}

	@Override
	public OsiJobCodesDTO convertOsiJobCodesToDTO(OsiJobCodes jobs) {
		OsiJobCodesDTO dto = new OsiJobCodesDTO();
		
		dto.setActive(jobs.getActive());
		dto.setJobCodeDescription(jobs.getJobCodeDescription());
		dto.setJobCodeId(jobs.getJobCodeId());
		dto.setJobCodeName(jobs.getJobCodeName());
		dto.setOrgId(jobs.getOrgId());
		dto.setCreatedBy(jobs.getCreatedBy());
		dto.setCreatedDate(jobs.getCreatedDate());
		dto.setUpdatedBy(jobs.getUpdatedBy());
		dto.setLastUpdateDate(jobs.getLastUpdateDate());
	
		return dto;
	}

	@Override
	public OsiGradesDTO convertOsiGradesToDTO(OsiGrades grade) throws BusinessException {
		OsiGradesDTO dto = new OsiGradesDTO();
		
		dto.setActive(grade.getActive());
		dto.setGradeDescription(grade.getGradeDescription());
		dto.setGradeId(grade.getGradeId());
		dto.setGradeName(grade.getGradeName());
		dto.setOrgId(grade.getOrgId());
		dto.setSeq(grade.getSeq());
		dto.setCostPerHour(grade.getCostPerHour());
		dto.setCostPerMonth(grade.getCostPerMonth());
		dto.setCreatedBy(grade.getCreatedBy());
		dto.setCreatedDate(grade.getCreatedDate());
		dto.setUpdatedBy(grade.getUpdatedBy());
		dto.setLastUpdateDate(grade.getLastUpdateDate());
		
		return dto;
	}

	@Override
	public OsiLocationsDTO convertOsiLocationsToDTO(OsiLocations location) throws BusinessException {
		OsiLocationsDTO dto = new OsiLocationsDTO();
		
		dto.setActive(location.getActive());
		dto.setIsPrimary(location.getIsPrimary());
		dto.setLocationId(location.getLocationId());
		dto.setLocationName(location.getLocationName());
		OsiRegionsDTO dto2 = new OsiRegionsDTO();
		dto2.setRegionId(location.getOsiRegionsId().getRegionId());
		dto2.setRegionShortName(location.getOsiRegionsId().getRegionShortName());
		dto.setOsiRegionsId(dto2);
		//dto.setOrgId(location.getOrgId());
		dto.setCreatedBy(location.getCreatedBy());
		dto.setCreationDate(location.getCreationDate());
		dto.setLastUpdatedBy(location.getLastUpdatedBy());
		dto.setLastUpdateDate(location.getLastUpdateDate());
		
		return dto;
	}

	@Override
	public OsiEmployeesDTO convertOsiEmployeeToDTO(OsiEmployees employee) {
		OsiEmployeesDTO dto = new OsiEmployeesDTO();
		
		dto.setEmployeeId(employee.getEmployeeId());
		dto.setEmployeeNumber(employee.getEmployeeNumber());
		dto.setEmployeeType(employee.getEmployeeType());
		dto.setFullName(employee.getFullName());
		dto.setIsManager(employee.getIsManager());
		
		return dto;
	}


	

}
