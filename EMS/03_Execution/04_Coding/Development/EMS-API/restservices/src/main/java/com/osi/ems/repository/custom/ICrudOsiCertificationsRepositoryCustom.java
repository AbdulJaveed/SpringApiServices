package com.osi.ems.repository.custom;

import com.osi.ems.domain.OsiCertifications;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiCertificationsRepositoryCustom {

	public OsiCertifications findOneWithLock(Integer id) throws DataAccessException;
	
}
