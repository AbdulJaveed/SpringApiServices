package com.osi.ems.dao;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiJobCodesVO;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiJobCodesDao {
	public List<ListOsiJobCodesVO> getAllOsiJobCodess(Map<String, String> searchFieldsMap) throws DataAccessException;
}
