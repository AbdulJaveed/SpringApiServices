package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiCostCenterVO;
import com.osi.ems.service.dto.OsiCcGradesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface ICrudOsiCostCenterService {
	public CrudOsiCostCenterVO getOsiCostCenter(Integer id) throws BusinessException, DataAccessException;
	public CrudOsiCostCenterVO createOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO, Integer userId) throws BusinessException, DataAccessException;
	public CrudOsiCostCenterVO updateOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO, Integer userId) throws BusinessException, DataAccessException;
	public void deleteOsiCostCenter(Integer id) throws Exception;
	
	//OSI CC Graddes
	OsiCcGradesDTO getOsiCCGrades(Integer id) throws BusinessException, DataAccessException;
	OsiCcGradesDTO createOsiCCGrades(OsiCcGradesDTO crudOsiCCGradesDTO, Integer userId) throws BusinessException;
	OsiCcGradesDTO updateOsiCCGrades(OsiCcGradesDTO osiCCGradesDTO, Integer userId) throws BusinessException;
	void deleteOsiCCGrades(Integer id) throws BusinessException;
	List<OsiCcGradesDTO> getOsiCCGradesByCCID(Integer ccId) throws BusinessException;
	List<OsiGradesDTO> getAllGradesByOrganization(Integer orgId) throws BusinessException;
	void deleteOsiCCGradesByCCID(Integer id) throws BusinessException;
	List<OsiCcGradesDTO> getOsiCCGradesHistory(Integer garedId, Integer orgId, Integer gradeId) throws BusinessException, DataAccessException;
	public OsiCcGradesDTO createOsiCCGradesHistoryDuplicate(OsiCcGradesDTO osiCCGradesDTO, Integer userId)
			throws BusinessException;
}
