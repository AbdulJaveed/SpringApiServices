package com.osi.ems.service;

import java.util.List;

import com.osi.ems.domain.OsiContacts;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.service.dto.OsiCountriesDTO;
import com.osi.ems.service.dto.OsiCountryVisasDTO;
import com.osi.ems.service.dto.OsiEmpAdditionalDocsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.urm.exception.BusinessException;

public interface OsiEmployeesService {

	List<OsiEmployees> findAll() throws BusinessException;
	
	OsiEmployees findByEmployeeId(Integer employeeId) throws BusinessException;
	
	OsiEmployees saveEmployee(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException;
	
	Integer updateEmployeeEffectiveEndDate(OsiEmployees osiEmployees, String effectiveEndDate) throws BusinessException;

	OsiEmployees saveEmployeePersonalInfo(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException;
	
	List<OsiEmployees> searchEmployees(String searchData) throws BusinessException;
	
	public OsiContacts saveContacts(OsiEmployeesDTO osiEmployeesDTO, Integer employeeId, String employee_name)throws BusinessException;

	public OsiContacts findOsiContacts(int employee_id) throws BusinessException;
	
	public List<OsiCountriesDTO> getAllCountries() throws BusinessException;
	
	public List<OsiCountryVisasDTO> getAllCountryVisas(Integer countryId) throws BusinessException;
	
	public OsiEmployees getPassportInfoByEmployeeId(Integer employeeId) throws BusinessException;
	
	public OsiEmployees getPassportInfoByEmployeeIdAndSearchDate(String inputObject) throws BusinessException;
	
	public Integer savePassportInfo(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException;
	
	String getPersonalInfoByEmployeeId(Integer employeeId) throws BusinessException;

	OsiEmployees findByEmployeeIdAndSerachDate(String inputObject) throws BusinessException;
	
	String getPersonalInfoByEmployeeIdAndDate(String inputObject) throws BusinessException;
	
	OsiEmployees updateEmployee(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException;

	OsiEmployees updateEmployeePersonalInfo(OsiEmployees osiEmployees, Integer loggedInEmployeeId) throws BusinessException;
	
	boolean isManager(Integer employeeId) throws BusinessException;
	
	/**
	 * Search Employees List for the manger/employees
	 */
	List<OsiEmployees> searchEmployeesSelf(String searchData) throws BusinessException;

	boolean findByOfficeEmail(String mailId, Integer integer) throws BusinessException;

	List<OsiEmpAdditionalDocsDTO> findAdditionalDocumentsByEmployeeId(Integer employeeId) throws BusinessException;

	List<OsiEmpAdditionalDocsDTO> saveAdditionalDocs(OsiEmpAdditionalDocsDTO empDocs) throws BusinessException;

	List<OsiEmployees> overallSearch(String searchData) throws BusinessException;

	List<OsiEmployees> getAllEmployees(String inputData) throws BusinessException;

	List<OsiEmployees> updateEmployees(List<OsiEmployees> inputData) throws BusinessException;

}
