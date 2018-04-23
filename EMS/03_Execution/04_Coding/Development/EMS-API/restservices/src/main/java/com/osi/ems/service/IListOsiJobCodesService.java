package com.osi.ems.service;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiJobCodesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface IListOsiJobCodesService {
	public List<ListOsiJobCodesVO> getOsiJobCodesList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException;
}
