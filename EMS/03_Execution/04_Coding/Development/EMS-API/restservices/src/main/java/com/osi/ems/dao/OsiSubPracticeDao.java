package com.osi.ems.dao;

import java.util.List;

import com.osi.ems.service.dto.OsiSubPracticeGradesDto;
import com.osi.ems.service.dto.OsiSubPracticeGradesHistoryDto;
import com.osi.urm.exception.DataAccessException;

public interface OsiSubPracticeDao {
	
	List<OsiSubPracticeGradesDto> getOsiSubPracticeGradesBysubPracticeId(Integer ccId) throws DataAccessException;
	void deleteOsiSubPracticeGradesBysubPracticeId(Integer buId) throws DataAccessException;
	OsiSubPracticeGradesDto saveOsiSubPracticeGrades(OsiSubPracticeGradesDto osiSubPracticeGradesDTO, Integer userId) throws DataAccessException;
	
	boolean compareWithExisting(OsiSubPracticeGradesDto buGrade);
	void deleteOsiSubPracticeGrades(Integer id) throws DataAccessException;
	List<OsiSubPracticeGradesHistoryDto> getOsiSubPracticeGradesHistory(Integer subPracticeId, Integer orgId, Integer gradeId) throws DataAccessException;
}
