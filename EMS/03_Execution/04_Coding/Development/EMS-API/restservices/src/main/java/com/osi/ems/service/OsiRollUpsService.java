package com.osi.ems.service;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiRollUpsService {
	public String checkActiveStatus(String param) throws BusinessException, DataAccessException ;
}
