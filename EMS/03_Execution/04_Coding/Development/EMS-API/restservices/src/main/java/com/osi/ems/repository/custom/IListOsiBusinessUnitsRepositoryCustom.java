package com.osi.ems.repository.custom;

import java.util.List;
import java.util.Map;

import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiBusinessUnitsRepositoryCustom {

	public List<OsiBusinessUnits> findAllOsiBusinessUnitsEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException;
}
