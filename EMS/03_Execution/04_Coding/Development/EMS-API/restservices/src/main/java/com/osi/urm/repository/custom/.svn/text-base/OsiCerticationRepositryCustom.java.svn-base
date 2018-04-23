package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.OsiCertificationDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiCerticationRepositryCustom {
	

	public List<OsiCertificationDetails> getCertificationDetails();
	
	public List<OsiCertificationDetails> searchCertificationDetails( OsiCertificationDetails osiCertificationDetails );
	
	public int saveCertifications( OsiCertificationDetails osiCertificationDetails, Integer orgId ) throws DataAccessException;
	
	public List<OsiCertificationDetails> getCertificationDetailsById(int certificateId);

	public boolean findByCetificationIdAndEmployeeId(String certificationId, Integer employeeId)throws BusinessException;

	public int updateVerifiedSkills(OsiCertificationDetails certificate) throws DataAccessException;

}
