package com.osi.ems.dao;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiDepartmentVO;
import com.osi.ems.service.dto.OsiDeptGradesDTO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiDepratmentDao {
	public CrudOsiDepartmentVO getOsiDepratment(Integer id) throws DataAccessException;
	public CrudOsiDepartmentVO saveOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO, Integer userId) throws DataAccessException;
	public CrudOsiDepartmentVO updateOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO, Integer userId) throws DataAccessException;
	public void deleteOsiDepratment(Integer id) throws DataAccessException;
	
	List<OsiDeptGradesDTO> getOsiDeptGradesByDeptID(Integer deptId) throws DataAccessException;
	void deleteOsiDeptGradesByDeptID(Integer deptId) throws DataAccessException;
	OsiDeptGradesDTO saveOsiDeptGrades(OsiDeptGradesDTO osiDeptGradesDTO, Integer userId) throws DataAccessException;
	List<OsiDeptGradesDTO> getOsiDeptGradesHistory(Integer deptId, Integer orgId, Integer gradeId) throws DataAccessException;
	boolean compareWithExisting(OsiDeptGradesDTO buGrade);
	void deleteOsiDeptGrades(Integer deptGraeId) throws DataAccessException;
}
