package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.domain.OsiEmpBankDetails;
import com.osi.urm.exception.DataAccessException;

public interface OsiEmpBankDetailsRepositoryCustom {


	List<OsiEmpBankDetails> getAllEmpBankInfoDetails() throws DataAccessException;
	
	public List<OsiEmpBankDetails> getAllEmpBankInfoDetailsByEmployeeId(Integer employeeId) throws DataAccessException;

	Integer saveEmpBankDetails(OsiEmpBankDetails osiEmpBankDetails) throws DataAccessException;

	OsiEmpBankDetails getEmpBankDetailById(Integer id) throws DataAccessException;

	Integer updateEmpBankDetails(OsiEmpBankDetails osiEmpBankDetails) throws DataAccessException;
	
	public Integer updateEmpBankDetailsStatus(Integer employeeId, String acctNo, String ifscCode) throws DataAccessException;

}
