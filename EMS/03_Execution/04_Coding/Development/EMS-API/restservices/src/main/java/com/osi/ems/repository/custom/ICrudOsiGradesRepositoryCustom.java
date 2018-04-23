package com.osi.ems.repository.custom;

import com.osi.ems.domain.OsiGrades;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiGradesRepositoryCustom {

	public OsiGrades findOneWithLock(Integer id) throws DataAccessException;
	
	
	
}
