package com.osi.ems.service;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiTitlesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface IListOsiTitlesService {
	public List<ListOsiTitlesVO> getOsiTitlesList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException;;
}
