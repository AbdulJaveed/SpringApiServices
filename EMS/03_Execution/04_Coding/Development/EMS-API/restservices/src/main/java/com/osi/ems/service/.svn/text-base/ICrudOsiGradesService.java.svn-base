package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiGradesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiGradesService {
	public CrudOsiGradesVO getOsiGrades(Integer id) throws BusinessException, DataAccessException;
	public CrudOsiGradesVO createOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws BusinessException, DataAccessException;
	public CrudOsiGradesVO updateOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws BusinessException, DataAccessException;
	public void deleteOsiGrades(Integer id) throws Exception;
	List<CrudOsiGradesVO> getOsiGradesHistory(Integer garedId, Integer orgId) throws BusinessException, DataAccessException;
}
