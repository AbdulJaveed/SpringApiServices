package com.osi.ems.dao;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiBusinessUnitsVO;
import com.osi.ems.service.dto.OsiBuGradesDTO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiBusinessUnitsDao {
	public CrudOsiBusinessUnitsVO getOsiBusinessUnits(Integer id) throws DataAccessException;
	public CrudOsiBusinessUnitsVO saveOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO, Integer userId) throws DataAccessException;
	public CrudOsiBusinessUnitsVO updateOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO, Integer userId) throws DataAccessException;
	public void deleteOsiBusinessUnits(Integer id) throws DataAccessException;
	
	List<OsiBuGradesDTO> getOsiBUGradesByBUID(Integer ccId) throws DataAccessException;
	void deleteOsiBUGradesByBUID(Integer buId) throws DataAccessException;
	OsiBuGradesDTO saveOsiBUGrades(OsiBuGradesDTO osiBuGradesDTO, Integer userId) throws DataAccessException;
	
	boolean compareWithExisting(OsiBuGradesDTO buGrade);
	void deleteOsiBUGrades(Integer id) throws DataAccessException;
	List<OsiBuGradesDTO> getOsiBUGradesHistory(Integer buId, Integer orgId, Integer gradeId) throws DataAccessException;
}
