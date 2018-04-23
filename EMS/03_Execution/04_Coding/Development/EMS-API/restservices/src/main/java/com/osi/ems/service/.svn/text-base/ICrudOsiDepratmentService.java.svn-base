package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiDepartmentVO;
import com.osi.ems.service.dto.OsiDeptGradesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface ICrudOsiDepratmentService {
	public CrudOsiDepartmentVO getOsiDepratment(Integer id) throws BusinessException, DataAccessException;
	public CrudOsiDepartmentVO createOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO, Integer userId) throws BusinessException, DataAccessException;
	public CrudOsiDepartmentVO updateOsiDepratment(CrudOsiDepartmentVO crudOsiDepratmentVO, Integer userId) throws BusinessException, DataAccessException;
	public void deleteOsiDepratment(Integer id) throws Exception;
	OsiDeptGradesDTO createOsiDeptGrades(OsiDeptGradesDTO osiDeptGradesDTO, Integer userId) throws BusinessException, DataAccessException;
	void deleteOsiBUGradesByDeptID(Integer deptId) throws BusinessException;
	List<OsiDeptGradesDTO> getOsiDeptGradesByDeptID(Integer deptId) throws BusinessException, DataAccessException;
	List<OsiGradesDTO> getAllGradesByOrganization(Integer orgId) throws BusinessException;
	List<OsiDeptGradesDTO> getOsiDeptGradesHistory(Integer deptId, Integer orgId, Integer gradeId) throws BusinessException, DataAccessException;
	public OsiDeptGradesDTO createOsiDeptGradesHistoryDuplicate(OsiDeptGradesDTO osiDeptGradesDTO, Integer userId)
			throws BusinessException;
}
