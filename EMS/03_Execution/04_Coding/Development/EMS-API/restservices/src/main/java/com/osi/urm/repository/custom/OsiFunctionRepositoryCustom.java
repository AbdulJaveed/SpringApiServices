package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.OsiFunctions;
import com.osi.urm.exception.DataAccessException;


public interface OsiFunctionRepositoryCustom {
	
	public List<OsiFunctions> getFunctionsName(Integer bussinessGroupId,List<Integer> userRespIds)throws DataAccessException;
	
	public List<OsiFunctions> getUserFunctions(Integer userId)throws DataAccessException;
}
