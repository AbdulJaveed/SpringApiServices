package com.osi.ems.mapper;

import com.osi.ems.domain.OsiAttachments;
import com.osi.ems.domain.OsiContacts;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiEmployeesMapper {
	
	public OsiEmployees mapToBasicInfo(OsiEmployees fromOsiEmployee, OsiEmployees toOsiEmployee)  throws BusinessException;
	OsiAttachments mapToAttachments(com.osi.ems.service.dto.OsiAttachmentsDTO osiAttachmentsDto)  throws BusinessException;
	OsiEmployees mapToPersonalInfo(OsiEmployees fromOsiEmployee, OsiEmployees toOsiEmployee) throws BusinessException;
	OsiEmployees osiEmployeesDTOToOsiEmployees(OsiEmployeesDTO osiFunctionsDTO)throws BusinessException;

	OsiEmployees osiEmployeesMedicalInfoToOsiEmployeesDTO(OsiEmployeesDTO osiEmployeesDTO)throws BusinessException,DataAccessException;

	OsiEmployees osiEmployeesOfficeInfoToOsiEmployeesDTO(OsiEmployeesDTO osiEmployeesDTO) throws BusinessException,DataAccessException;

	OsiContacts osiEmployeesOfficeInfoToOsiEmployeesContactsDTO(OsiEmployeesDTO osiEmployeesDTO, Integer employee_id,String employee_name)throws BusinessException;
	OsiEmployees osiEmployeesMedicalInfoToOsiEmployeesDTOUpdate(OsiEmployeesDTO osiEmployeesDTO)
			throws BusinessException, DataAccessException;
	public OsiEmployees osiEmployeesOfficeInfoToOsiEmployeesDTOUpdate(OsiEmployeesDTO osiEmployeesDTO) throws BusinessException, DataAccessException;
}
