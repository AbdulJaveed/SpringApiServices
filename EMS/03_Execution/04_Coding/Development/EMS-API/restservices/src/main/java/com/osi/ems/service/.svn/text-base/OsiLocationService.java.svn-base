package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRegionsDTO;
import com.osi.ems.service.dto.OsiTimezonesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiLocationService {
	OsiLocationsDTO createLocation(OsiLocationsDTO osiLocationsDTO,Integer userId)throws BusinessException, DataAccessException ;
	List<OsiLocationsDTO> locationbyOrgId(Integer orgId) throws BusinessException, DataAccessException ;
	OsiLocationsDTO locationbyLocId(Integer locId) throws BusinessException, DataAccessException ;
	List<OsiRegionsDTO> getRegionInfo() throws BusinessException, DataAccessException ;
	List<OsiTimezonesDTO> getTimezoneInfo() throws BusinessException, DataAccessException;
}
