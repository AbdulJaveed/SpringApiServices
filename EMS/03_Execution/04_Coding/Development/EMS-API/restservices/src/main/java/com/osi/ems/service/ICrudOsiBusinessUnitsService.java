package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiBusinessUnitsVO;
import com.osi.ems.service.dto.OsiBuGradesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface ICrudOsiBusinessUnitsService {
	public CrudOsiBusinessUnitsVO getOsiBusinessUnits(Integer id) throws BusinessException, DataAccessException;
	public CrudOsiBusinessUnitsVO createOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO, Integer userId) throws BusinessException, DataAccessException;
	public CrudOsiBusinessUnitsVO updateOsiBusinessUnits(CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO, Integer userId) throws BusinessException, DataAccessException;
	public void deleteOsiBusinessUnits(Integer id) throws Exception;
	
	List<OsiBuGradesDTO> getOsiBUGradesByBUID(Integer buId) throws BusinessException, DataAccessException;
	List<OsiGradesDTO> getAllGradesByOrganization(Integer orgId) throws BusinessException, DataAccessException;
	void deleteOsiBUGradesByBUID(Integer id) throws BusinessException, DataAccessException;
	OsiBuGradesDTO createOsiBUGrades(OsiBuGradesDTO osiBuGradesDTO, Integer userId) throws BusinessException, DataAccessException;
	List<OsiBuGradesDTO> getOsiBUGradesHistory(Integer buId, Integer orgId, Integer gradeId) throws BusinessException, DataAccessException;
	public OsiBuGradesDTO createOsiBUGradesWithHistoryDuplicate(OsiBuGradesDTO osiBuGradesDTO, Integer userId)
			throws BusinessException, DataAccessException;
}
