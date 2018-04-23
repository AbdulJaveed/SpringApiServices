package com.osi.ems.mapper;

import com.osi.ems.domain.OsiAssignments;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.domain.OsiLocations;
import com.osi.ems.service.dto.OsiAssignmentsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.ems.service.dto.OsiJobCodesDTO;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.urm.exception.BusinessException;

public interface OsiAssignmentsMapper {

	OsiAssignmentsDTO convertOsiAssignmentToDTO(OsiAssignments assignment) throws BusinessException;

	OsiAssignments convertDTOToOsiAssignment(OsiAssignmentsDTO osiAssignmentsDTO) throws BusinessException;

	OsiJobCodesDTO convertOsiJobCodesToDTO(OsiJobCodes jobs) throws BusinessException;

	OsiGradesDTO convertOsiGradesToDTO(OsiGrades grade) throws BusinessException;

	OsiLocationsDTO convertOsiLocationsToDTO(OsiLocations location) throws BusinessException;

	OsiEmployeesDTO convertOsiEmployeeToDTO(OsiEmployees employee);

}
