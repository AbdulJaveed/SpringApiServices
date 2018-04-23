package com.osi.ems.service;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiDepartmentVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface IListOsiDepratmentService {
	public List<ListOsiDepartmentVO> getOsiDepratmentList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException;
}
