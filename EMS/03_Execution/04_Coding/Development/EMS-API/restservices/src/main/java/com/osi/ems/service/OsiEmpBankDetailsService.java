package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiEmpBankDetailsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface OsiEmpBankDetailsService {


	public List<OsiEmpBankDetailsDTO> getallBankDetails() throws DataAccessException, BusinessException;
//	public List<OrganisationDTO> getAllOrganization();
	
	public List<OsiEmpBankDetailsDTO> getAllBankDetailsByEmpIdAndSearchDate(String inputObject) throws DataAccessException, BusinessException;

	public OsiEmpBankDetailsDTO saveEmpBankDetails(OsiEmpBankDetailsDTO osiEmpBankDetailsDTO, Integer id) throws BusinessException;

	public OsiEmpBankDetailsDTO getEmpBankDetailById(Integer id) throws DataAccessException, BusinessException;
	
}
