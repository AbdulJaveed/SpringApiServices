package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.osi.ems.repository.custom.impl.MySQL;
import com.osi.ems.service.OrgHierarchyService;
import com.osi.ems.service.dto.OrgHierarchy;
import com.osi.ems.service.dto.OrgHierarchyDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Component
public class OrgHierarchyServiceImpl implements OrgHierarchyService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private MySQL employeeDao;
	
	
	@Autowired
	private Environment env;
	
	public static final String max_level = "MAX_LEVEL";
	
	public OrgHierarchyDTO getOrgHierarchyEmployees(JSONObject jsonObject) throws BusinessException {
		LOGGER.info("getOrgHierarchyEmployees : Begin");
		String employeeName = jsonObject.has("employeeName") ? jsonObject.getString("employeeName") : null;
		Integer employeeIdString = jsonObject.has("employeeId") ? jsonObject.getInt("employeeId") : null;
		final int HIGHEST_RANKING_EMPLOYEE = 0;
		Integer employeeId =HIGHEST_RANKING_EMPLOYEE;
		if(employeeIdString!=null){
			 employeeId = employeeIdString.intValue();
		}
		Boolean isTreeView = Boolean.valueOf(jsonObject.getString("isTreeView"));
		Boolean displayAll = false;
		String displayAllParameter = jsonObject.has("displayAll") ? jsonObject.getString("displayAll") : null;
		Boolean isDisplayAll = false;
		//MySQL employeeDao = new MySQL();
		
		List<OrgHierarchy> allEmployees;
		
		OrgHierarchy mainEmployee = null;
		OrgHierarchy parentEmployee = null;
		OrgHierarchy grandParentEmployee = null;
		OrgHierarchyDTO employeeDTO = new OrgHierarchyDTO();
		try {
			Integer maxDisplayParam = Integer.parseInt(env.getProperty("TREE_MAX_DEPTH_LEVEL"));
			//Integer maxDisplayParam = Integer.parseInt(employeeDao.getConfigurationByName(max_level));
			
			allEmployees = new ArrayList<OrgHierarchy>();
			if(displayAllParameter!=null && !displayAllParameter.trim().isEmpty() && displayAllParameter.trim()!=""){
				
				isDisplayAll = Boolean.valueOf(jsonObject.getString("displayAll"));
				allEmployees = employeeDao.getAllEmployees();
				
			} else if(employeeName!=null && !employeeName.trim().isEmpty() && employeeName.trim()!=""){

				Integer mainLevel = (int) Math.ceil((double) maxDisplayParam / 2 );
				Integer parentLevels = mainLevel - 1;
				Integer childLevels = mainLevel + 1;
				
				mainEmployee = employeeDao.getEmployee(null, employeeName);
				mainEmployee.setLevel(mainLevel);
				mainEmployee = this.getChildren(mainEmployee, childLevels, maxDisplayParam, employeeDao);
									
				
				while(null!=mainEmployee.getSupervisorId() && mainEmployee.getId()!=mainEmployee.getSupervisorId() && parentLevels > 0){
					OrgHierarchy parentEmp = employeeDao.getEmployee(mainEmployee.getSupervisorId(), null);
					List<OrgHierarchy> parentSubs = new ArrayList<OrgHierarchy>();
					parentSubs.add(mainEmployee);
					parentEmp.setSubordinate(parentSubs);
					parentEmp.setLevel(parentLevels);
					mainEmployee = parentEmp;
					parentLevels--;
				}
			
				//loop children and get their grand children here.
			} else if(employeeId!=HIGHEST_RANKING_EMPLOYEE){
			
				Integer mainLevel = (int) Math.ceil((double) maxDisplayParam / 2 );
				Integer parentLevels = mainLevel - 1;
				Integer childLevels = mainLevel + 1;
				
				mainEmployee = employeeDao.getEmployee(employeeId, null);
				mainEmployee.setLevel(mainLevel);
				
				if(mainEmployee.getId().equals(mainEmployee.getSupervisorId())){
					Integer rootLevel = 1;
					mainEmployee = this.getRootChildren(mainEmployee,rootLevel, maxDisplayParam , employeeDao);
				} else{
					mainEmployee = this.getChildren(mainEmployee, childLevels, maxDisplayParam, employeeDao);
				}
					
				while(null!=mainEmployee.getSupervisorId()  && !mainEmployee.getId().equals(mainEmployee.getSupervisorId()) && parentLevels > 0) {
					OrgHierarchy parentEmp = employeeDao.getEmployee(mainEmployee.getSupervisorId(), null);
					List<OrgHierarchy> parentSubs = new ArrayList<OrgHierarchy>();
					parentSubs.add(mainEmployee);
					parentEmp.setSubordinate(parentSubs);
					parentEmp.setLevel(parentLevels);
					mainEmployee = parentEmp;
					parentLevels--;
					
				}	
			}			
			
			mainEmployee = checkIfSubordinateIsSupervisor(mainEmployee);
			
			allEmployees.add(mainEmployee);
			
			//Place this to EmployeeDTO where first index of the table should be the highest ranking employee
			OrgHierarchy employee = allEmployees.get(HIGHEST_RANKING_EMPLOYEE);
			employeeDTO.setId(employee.getId());
			employeeDTO.setName(employee.getName());
			employeeDTO.setPosition(employee.getPosition());
			employeeDTO.setDepartment(employee.getDepartment());
			employeeDTO.setIsSupervisor(employee.getIsSupervisor());
			employeeDTO.setSupervisorId(employee.getSupervisorId());
			employeeDTO.setDateHired(employee.getDateHired());
			employeeDTO.setImageLocation(employee.getImageLocation());
			employeeDTO.setSupervisorName(employee.getSupervisorName());
			employeeDTO.setChildren(employee.getSubordinate());
			employeeDTO.setSex(employee.getSex());
			employeeDTO.setEmployeeNumber(employee.getEmployeeNumber());
			employeeDTO.setDeptStructure(employee.getDeptStructure());
				
		} catch(DataAccessException de) {
			LOGGER.error("Exception : "+de.getMessage());;
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());;
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getOrgHierarchyEmployees : End");
		return employeeDTO;

	}
	
	//checks if subordinate is a supervisor
	private OrgHierarchy checkIfSubordinateIsSupervisor(OrgHierarchy mainEmployee) throws BusinessException {
		LOGGER.info("checkIfSubordinateIsSupervisor : Begin");
		try{
			for(OrgHierarchy subordinateEmployee: mainEmployee.getSubordinate()){
				Integer empId = subordinateEmployee.getId();
						if(empId.equals(mainEmployee.getId())){
							mainEmployee = checkIfSubordinateIsSupervisor(subordinateEmployee);
						}	
			}
		}catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());;
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("checkIfSubordinateIsSupervisor : End");
		return mainEmployee;
	}

	public OrgHierarchy getChildren(OrgHierarchy mainEmployee, Integer level, Integer maxParm, MySQL employeeDaos) throws BusinessException {
		LOGGER.info("getChildren : Begin");
		try{
			List<OrgHierarchy> subordinateList = new ArrayList<OrgHierarchy>();
			mainEmployee = employeeDaos.getEmployeeChildrenBySupervisorId(mainEmployee, level);
			
			Integer currentLevel = level;
			
			if(null!=mainEmployee.getSubordinate() && mainEmployee.getSubordinate().size() > 0 && currentLevel < maxParm){
				for(int x = 0; x < mainEmployee.getSubordinate().size(); x++){
					subordinateList.add(this.getChildren(mainEmployee.getSubordinate().get(x), currentLevel + 1, maxParm, employeeDaos));
				}
			}
			
		} catch(DataAccessException de) {
			LOGGER.error("Exception : "+de.getMessage());;
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());;
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getChildren : End");
		return mainEmployee;
	}
	
	
	public OrgHierarchy getRootChildren(OrgHierarchy mainEmployee, Integer level, Integer maxParm, MySQL employeeDaos) throws BusinessException{
		LOGGER.info("getRootChildren : Begin");
		try{
			List<OrgHierarchy> subordinateList = new ArrayList<OrgHierarchy>();
			mainEmployee = employeeDaos.getRootChildrenBySupervisorId(mainEmployee, level);
			Integer currentLevel = level;
//			if(maxParm != 0){
//				maxParm--;
//				level++;
				/*if(null!=mainEmployee.getSubordinate() && mainEmployee.getSubordinate().size() > 0 && currentLevel < maxParm){
					for(int x = 0; x < mainEmployee.getSubordinate().size(); x++){
						subordinateList.add(this.getRootChildren(mainEmployee.getSubordinate().get(x), currentLevel + 1, maxParm, employeeDao));
					}
				}*/

//				mainEmployee = this.getRootChildren(mainEmployee, level, maxParm, employeeDao);
//			}
		} catch(DataAccessException de) {
			LOGGER.error("Exception : "+de.getMessage());;
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());;
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getRootChildren : End");
		return mainEmployee;
	}	
	
	public OrgHierarchyDTO retrieveFromGrandSuperiorWithNoSiblings(JSONObject jsonObject, int supervisorId) throws BusinessException {
		LOGGER.info("retrieveFromGrandSuperiorWithNoSiblings : Begin");
		List<OrgHierarchy> allEmployees;
		final int HIGHEST_RANKING_EMPLOYEE = 0;
		//MySQL employeeDao = new MySQL();
		OrgHierarchyDTO employeeDTO = new OrgHierarchyDTO();
		
		try {
			
			allEmployees = employeeDao.getAllEmployeesById(supervisorId);
			
			//Place this to EmployeeDTO where first index of the table should be the highest ranking employee
			OrgHierarchy employee = allEmployees.get(HIGHEST_RANKING_EMPLOYEE);
			//int superorId
			//if the immediate superior also had a superior, use the grand superior as basis of retrieval
			if(employee.getSupervisorId()!=0){
				allEmployees = employeeDao.getAllEmployeesById(employee.getSupervisorId());
				employee = allEmployees.get(HIGHEST_RANKING_EMPLOYEE);
			}
			
			employeeDTO.setId(employee.getId());
			employeeDTO.setName(employee.getName());
			employeeDTO.setPosition(employee.getPosition());
			employeeDTO.setDepartment(employee.getDepartment());
			employeeDTO.setIsSupervisor(employee.getIsSupervisor());
			employeeDTO.setSupervisorId(employee.getSupervisorId());

			employeeDTO.setSupervisorName(employee.getSupervisorName());

			//obtian supervisor
			List<OrgHierarchy>listOfSupervisors = employeeDao.getAllEmployeesById(employee.getSupervisorId());
			if(listOfSupervisors.size()>0){
				final OrgHierarchy spervisor = listOfSupervisors.get(HIGHEST_RANKING_EMPLOYEE);
				employeeDTO.setSupervisorName(spervisor.getName());
			}

			employeeDTO.setDateHired(employee.getDateHired());
			List<OrgHierarchy> subordinates = employee.getSubordinate();
			
			for(OrgHierarchy subordinate:subordinates){
				if(subordinate.getId()==supervisorId){
					subordinates=new ArrayList<OrgHierarchy>();
					subordinates.add(subordinate);
					break;
				}
			}
			employeeDTO.setChildren(subordinates);
			
		} catch(DataAccessException de) {
			LOGGER.error("Exception : "+de.getMessage());;
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());;
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("retrieveFromGrandSuperiorWithNoSiblings : End");
		return employeeDTO;
	}

	@Override
	public String getEmployeeOrgList(Integer employeeId) throws BusinessException {
		String result = null;
		LOGGER.info("getEmployeeOrgList : Begin");
		try {
			if(null != employeeId)
				result = employeeDao.getEmployeeOrgList(employeeId.intValue());
			else
				throw new BusinessException("ERR_1009", "Invalid Employee Id");
		} catch(DataAccessException de) {
			LOGGER.error("Exception : "+de.getMessage());;
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());;
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getEmployeeOrgList : End");
		return result;
	}

	@Override
	public String getLocationList() throws BusinessException {
		String result = null;
		LOGGER.info("getLocationList : Begin");
		try {
			result = employeeDao.getLocationList();
		} catch(DataAccessException de) {
			LOGGER.error("Exception : "+de.getMessage());;
			throw new BusinessException(de.getErrorCode(), de.getSystemMessage());
		} catch(Exception e) {
			LOGGER.error("Exception : "+e.getMessage());;
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getLocationList : End");
		return result;
	}

}
