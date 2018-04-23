package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.domain.OsiCCGradesHistoryDTO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiCCGradesHistoryRepositoryCustom {

	void save(OsiCCGradesHistoryDTO osiCCGradesHistoryDTO) throws DataAccessException;

	List<OsiCCGradesHistoryDTO> getCCGradeHistory(Integer ccGradeId, Integer orgId, Integer gradeId)
			throws DataAccessException;

	public Integer getCCGradeHistoryCount(OsiCCGradesHistoryDTO costHistory) throws DataAccessException;

}
