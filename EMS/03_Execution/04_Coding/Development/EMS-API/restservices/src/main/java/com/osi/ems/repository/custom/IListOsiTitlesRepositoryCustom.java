package com.osi.ems.repository.custom;

import java.util.List;
import java.util.Map;

import com.osi.ems.domain.OsiTitles;
import com.osi.ems.service.dto.OsiTitlesDTO;
import com.osi.urm.exception.DataAccessException;

public interface IListOsiTitlesRepositoryCustom {

	public List<OsiTitles> findAllOsiTitlesEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException;
	
	public List<OsiTitlesDTO> findAllTitleGrades() throws DataAccessException;
}
