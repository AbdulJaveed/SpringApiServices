package com.osi.ems.dao;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiCertificationsVO;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiCertificationsDao {
	public List<ListOsiCertificationsVO> getAllOsiCertificationss(Map<String, String> searchFieldsMap) throws DataAccessException;
}
