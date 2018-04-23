package com.osi.urm.service;

import java.util.List;

import com.osi.urm.domain.OsiCertificationDetails;
import com.osi.urm.exception.BusinessException;

public interface OsiCertifactionService {	
	
	public int saveCertifications( OsiCertificationDetails osiCertificationDetails, Integer orgId )throws BusinessException;
	public List<OsiCertificationDetails> getCertificationDetails();
	public List<OsiCertificationDetails> searchCertificationDetails( OsiCertificationDetails osiCertificationDetails ) throws BusinessException;
	List<OsiCertificationDetails> getCertificationDetailsById(int certificateId);
	public void updateVerifiedSkills(List<OsiCertificationDetails> certDetailsList, Integer id) throws BusinessException;
}
