package com.osi.ems.dao;

import java.util.List;

import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiRegions;
import com.osi.ems.domain.OsiTimezones;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiLocationDao {
	
	List<OsiLocations> locationbyOrgId(Integer orgId) throws BusinessException, DataAccessException ;
	OsiLocations createLocation(OsiLocations osiLocations,Integer userId)throws BusinessException, DataAccessException ;
	OsiLocations updateLocation(OsiLocations osiLocations,Integer userId)throws BusinessException, DataAccessException ;
	OsiLocations locationbyLocId(Integer locId) throws BusinessException, DataAccessException ;
	List<OsiRegions> getRegionsInfo() throws BusinessException, DataAccessException ;
	List<OsiTimezones> getTimezonesInfo() throws BusinessException, DataAccessException;
}
