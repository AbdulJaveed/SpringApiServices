package com.osi.ems.repository.custom;

import java.util.List;
import java.util.Map;

import com.osi.ems.domain.OsiCostCenter;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiCostCenterRepositoryCustom {

	public List<OsiCostCenter> findAllOsiCostCenterEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException;
}
