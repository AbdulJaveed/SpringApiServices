package com.osi.ems.dao;

import java.util.List;

import com.osi.ems.domain.OsiCurrencies;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiOrganizationDao {
	public List<OsiOrganizations> getOsiOrganizations() throws BusinessException, DataAccessException;
	public List<OsiOrganizationsDTO> orgSeacrh(String orgName,String city,String location)throws BusinessException, DataAccessException;
	public List<OsiCurrencies> getOsiCurrencyInfo() throws BusinessException, DataAccessException;
	public OsiOrganizations updateOrganization(OsiOrganizations organizations, Integer userId) throws BusinessException, DataAccessException ;
	public OsiOrganizations createOrganization(OsiOrganizations organizations, Integer userId) throws BusinessException, DataAccessException; ;
	public OsiOrganizationsDTO getOrganizationsByOrgId(Integer orgId) throws BusinessException, DataAccessException ;
	public List<OsiEmployeesDTO> getOsiEmployeeInfo(String empName,Integer orgId) throws BusinessException, DataAccessException;
	
}
