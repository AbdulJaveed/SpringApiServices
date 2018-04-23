package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiRegions;
import com.osi.ems.domain.OsiTimezones;
import com.osi.urm.exception.DataAccessException;

public interface OsiLocationRepositoryCustom {
	public List<OsiLocations> getLocationsByOrgId(Integer orgId) throws DataAccessException;
	public OsiLocations getLocationsByLocId(Integer locId) throws DataAccessException;
	public List<OsiRegions> getRegionsInfo() throws DataAccessException;
	public List<OsiTimezones> getTimezonesInfo() throws DataAccessException;
}
