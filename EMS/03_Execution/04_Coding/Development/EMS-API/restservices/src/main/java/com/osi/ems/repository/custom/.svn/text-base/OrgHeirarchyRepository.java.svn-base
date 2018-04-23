package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.service.dto.OrgHierarchy;
import com.osi.urm.exception.DataAccessException;

public interface OrgHeirarchyRepository {

	 OrgHierarchy getEmployee(Integer employeeId, String fullEmployeeName) throws DataAccessException;
	 OrgHierarchy getEmployeeChildrenBySupervisorId(OrgHierarchy parentEmployee, Integer level)	throws DataAccessException;
	 List<OrgHierarchy> getAllEmployees() throws DataAccessException;
	 List<OrgHierarchy> getSubordinateEmployee(OrgHierarchy supervisingEmployee, int level) throws DataAccessException;
	 List<OrgHierarchy> getSubordinateEmployeeNoLimit(OrgHierarchy supervisingEmployee)	throws DataAccessException;
	 List<OrgHierarchy> getAllEmployeesByName(String employeeName) throws DataAccessException;
	 List<OrgHierarchy> getAllEmployeesById(Integer employeeId) throws DataAccessException;
	 OrgHierarchy getRootChildrenBySupervisorId(OrgHierarchy parentEmployee, Integer level) throws DataAccessException;
	 
	 
	 /*
	 * for Employee List - Grid view
	 */
	String getEmployeeOrgList(int employeeId) throws DataAccessException;
	String getLocationList() throws DataAccessException;
	 
}
