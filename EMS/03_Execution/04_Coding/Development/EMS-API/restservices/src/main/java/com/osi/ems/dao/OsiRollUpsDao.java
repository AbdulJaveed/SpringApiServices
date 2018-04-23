package com.osi.ems.dao;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiRollUpsDao {
	public String checkActiveStatus(String param) throws BusinessException, DataAccessException ;
}
