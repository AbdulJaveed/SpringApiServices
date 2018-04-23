package com.osi.ems.dao;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiCostCenterVO;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiCostCenterDao {
	public List<ListOsiCostCenterVO> getAllOsiCostCenters(Map<String, String> searchFieldsMap) throws DataAccessException;
}
