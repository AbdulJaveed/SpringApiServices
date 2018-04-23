package com.osi.ems.dao;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiGradesVO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiGradesDao {
	public CrudOsiGradesVO getOsiGrades(Integer id) throws DataAccessException;
	public CrudOsiGradesVO saveOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws DataAccessException;
	public CrudOsiGradesVO updateOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws DataAccessException;
	public void deleteOsiGrades(Integer id) throws DataAccessException;
	public CrudOsiGradesVO getGrades(Integer gradeId) throws DataAccessException;
	List<CrudOsiGradesVO> getOsiGradesHistory(Integer id, Integer orgId) throws DataAccessException;
}
