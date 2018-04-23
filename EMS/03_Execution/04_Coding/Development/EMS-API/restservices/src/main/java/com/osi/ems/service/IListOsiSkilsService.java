package com.osi.ems.service;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiSkilsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface IListOsiSkilsService {
	public List<ListOsiSkilsVO> getOsiSkilsList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException;
}
