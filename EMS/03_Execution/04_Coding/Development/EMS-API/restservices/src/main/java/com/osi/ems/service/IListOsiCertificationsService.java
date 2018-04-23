package com.osi.ems.service;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiCertificationsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface IListOsiCertificationsService {
	public List<ListOsiCertificationsVO> getOsiCertificationsList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException;
}
