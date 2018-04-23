package com.osi.ems.service;

import java.util.List;

import org.json.JSONArray;

import com.osi.ems.service.dto.OsiAssignmentsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.ems.service.dto.OsiJobCodesDTO;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRollUpsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiLookupTypesDTO;

public interface OsiAssignmentsService {

	List<OsiAssignmentsDTO> getInitialAssignmentsList() throws BusinessException;

	OsiAssignmentsDTO findByAssignmentId(Integer id) throws BusinessException, DataAccessException;

	OsiAssignmentsDTO saveAssignments(OsiAssignmentsDTO osiAssignmentsDTO) throws BusinessException;
	
	OsiAssignmentsDTO updateAssignments(OsiAssignmentsDTO osiAssignmentsDTO) throws BusinessException;

	List<OsiJobCodesDTO> getAllJobCodes(Integer empId) throws BusinessException;

	List<OsiGradesDTO> getAllGrades(Integer empId) throws BusinessException;

	List<OsiLookupTypesDTO> getAllDepartment() throws BusinessException;

	List<OsiEmployeesDTO> searchEmployeeByNumber(String empNumber) throws BusinessException;

	OsiEmployeesDTO searchSupervisorByNumber(String empNumber) throws BusinessException;

	OsiEmployeesDTO searchEmployeeById(Integer id) throws BusinessException;

	List<OsiLocationsDTO> getAllLocations(Integer orgId) throws BusinessException;

	OsiJobCodesDTO getJobCodesById(Integer id)throws BusinessException;

	OsiGradesDTO getGradesById(Integer id)throws BusinessException;

	OsiLocationsDTO getOsiLocatonById(Integer id)throws BusinessException;
	
	OsiAssignmentsDTO findByAssignmentIdAndSerachDate(String inputObject) throws BusinessException, DataAccessException;

	OsiRollUpsDTO saveEmployeeRollups(OsiRollUpsDTO employeeRollupDto) throws BusinessException;

	OsiRollUpsDTO findRollUpsById(Integer rollUpId) throws BusinessException;
	
	JSONArray loadRollupsPopup(String inputObject) throws BusinessException, DataAccessException;

	Integer findSuperviosrId(Integer id)  throws BusinessException;

}
