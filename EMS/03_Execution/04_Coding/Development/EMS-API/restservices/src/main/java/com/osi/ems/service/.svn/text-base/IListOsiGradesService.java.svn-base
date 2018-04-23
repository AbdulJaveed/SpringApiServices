package com.osi.ems.service;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiGradesVO;
import com.osi.ems.service.dto.ListOsiTitlesVO;
import com.osi.ems.service.dto.OsiTitlesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface IListOsiGradesService {
	public List<ListOsiGradesVO> getOsiGradesList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException;
	public List<ListOsiTitlesVO> getOsiTitlesList() throws BusinessException, DataAccessException;
	List<OsiTitlesDTO> getOsiTitleGradesList() throws BusinessException, DataAccessException;
}
