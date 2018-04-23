package com.osi.ems.service;

import org.json.JSONObject;

import com.osi.ems.repository.custom.impl.MySQL;
import com.osi.ems.service.dto.OrgHierarchy;
import com.osi.ems.service.dto.OrgHierarchyDTO;
import com.osi.urm.exception.BusinessException;

public interface OrgHierarchyService {
	
	OrgHierarchyDTO getOrgHierarchyEmployees(JSONObject jsonObject) throws BusinessException;
	OrgHierarchy getChildren(OrgHierarchy mainEmployee, Integer level, Integer maxParm, MySQL employeeDao) throws BusinessException;
	OrgHierarchyDTO retrieveFromGrandSuperiorWithNoSiblings(JSONObject jsonObject, int supervisorId) throws BusinessException;
	
	/*
	 * for Employee List - Grid view
	 */
	String getEmployeeOrgList(Integer employeeId) throws BusinessException;
	String getLocationList() throws BusinessException;
	
}
