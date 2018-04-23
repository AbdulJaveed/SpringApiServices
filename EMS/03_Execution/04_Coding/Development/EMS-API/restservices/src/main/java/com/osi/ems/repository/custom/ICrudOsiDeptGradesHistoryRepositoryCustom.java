package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.domain.OsiDeptGradesHistoryDTO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiDeptGradesHistoryRepositoryCustom {

	void save(OsiDeptGradesHistoryDTO osiDeptGradesHistoryDTO) throws DataAccessException;

	List<OsiDeptGradesHistoryDTO> getDeptGradeHistory(Integer deptId, Integer orgId, Integer gradeId) throws DataAccessException;

	public Integer getCountOfDepartmentGradeHistory(OsiDeptGradesHistoryDTO osiDeptGradesHistoryDTO)
			throws DataAccessException;

}
