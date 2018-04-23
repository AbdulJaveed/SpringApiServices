package com.osi.ems.dao;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiSkilsVO;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiSkilsDao {
	public List<ListOsiSkilsVO> getAllOsiSkilss(Map<String, String> searchFieldsMap) throws DataAccessException;
}
