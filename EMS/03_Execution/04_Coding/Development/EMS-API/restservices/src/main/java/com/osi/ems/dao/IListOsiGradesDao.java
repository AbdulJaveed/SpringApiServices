package com.osi.ems.dao;

import java.util.List;
import java.util.Map;

import com.osi.ems.service.dto.ListOsiGradesVO;
import com.osi.ems.service.dto.ListOsiTitlesVO;
import com.osi.ems.service.dto.OsiTitlesDTO;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiGradesDao {
	public List<ListOsiGradesVO> getAllOsiGradess(Map<String, String> searchFieldsMap) throws DataAccessException;

	public List<ListOsiTitlesVO> getAllOsiTitles() throws DataAccessException;

	List<OsiTitlesDTO> getAllOsiTitleGrades() throws DataAccessException;
}
