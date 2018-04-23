package com.osi.ems.service;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiBusinessUnitsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface IListOsiBusinessUnitsService {
	public List<ListOsiBusinessUnitsVO> getOsiBusinessUnitsList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException;
}
