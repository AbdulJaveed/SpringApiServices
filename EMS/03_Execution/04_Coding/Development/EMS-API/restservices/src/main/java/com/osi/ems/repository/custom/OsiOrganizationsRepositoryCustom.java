package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.domain.OsiCurrencies;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.DataAccessException;

public interface OsiOrganizationsRepositoryCustom {
	public List<OsiOrganizations> getOsiOrganizations() throws DataAccessException;
	public List<OsiOrganizationsDTO> orgSeacrh(String orgName,String country,String location) throws DataAccessException;	
	public List<OsiCurrencies> getOsiCurrencyInfo() throws DataAccessException;
	public OsiOrganizationsDTO getOrganizationsByOrgId(Integer orgId) throws DataAccessException;
	public List<OsiEmployeesDTO> getOsiEmployeeInfo(String empName,Integer orgId) throws DataAccessException;
}
