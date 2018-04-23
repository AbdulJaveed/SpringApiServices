package com.osi.ems.repository.custom;

import java.util.List;
import java.util.Map;

import com.osi.ems.domain.OsiJobCodes;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiJobCodesRepositoryCustom {

	public List<OsiJobCodes> findAllOsiJobCodesEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException;
}
