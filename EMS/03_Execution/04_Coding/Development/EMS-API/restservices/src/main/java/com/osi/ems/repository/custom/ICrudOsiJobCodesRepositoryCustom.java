package com.osi.ems.repository.custom;

import com.osi.ems.domain.OsiJobCodes;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiJobCodesRepositoryCustom {

	public OsiJobCodes findOneWithLock(Integer id) throws DataAccessException;
	
}
