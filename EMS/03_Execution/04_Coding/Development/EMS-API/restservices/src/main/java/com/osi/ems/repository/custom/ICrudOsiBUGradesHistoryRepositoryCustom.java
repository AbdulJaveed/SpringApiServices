package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.domain.OsiBUGradesHistoryDTO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiBUGradesHistoryRepositoryCustom {

	void save(OsiBUGradesHistoryDTO osiBUGradesHistoryDTO) throws DataAccessException;

	List<OsiBUGradesHistoryDTO> getBUGradeHistory(Integer buId, Integer orgId, Integer gradeId)
			throws DataAccessException;

	Integer getBUGradeHistoryCount(OsiBUGradesHistoryDTO history) throws DataAccessException;

}
