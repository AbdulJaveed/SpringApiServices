package com.osi.ems.dao;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiDepartmentVO;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiDepratmentDao {
	public List<ListOsiDepartmentVO> getAllOsiDepratments(Map<String, String> searchFieldsMap) throws DataAccessException;
}
