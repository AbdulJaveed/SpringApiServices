package com.osi.ems.dao;

import java.util.List;

import com.osi.ems.service.dto.CrudOsiCostCenterVO;
import com.osi.ems.service.dto.OsiCcGradesDTO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiCostCenterDao {
	public CrudOsiCostCenterVO getOsiCostCenter(Integer id) throws DataAccessException;
	public CrudOsiCostCenterVO saveOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO, Integer userId) throws DataAccessException;
	public CrudOsiCostCenterVO updateOsiCostCenter(CrudOsiCostCenterVO crudOsiCostCenterVO, Integer userId) throws DataAccessException;
	public void deleteOsiCostCenter(Integer id) throws DataAccessException;
	
	// Cost Center Grdes Repo
	public OsiCcGradesDTO getOsiCCGrades(Integer id) throws DataAccessException;
	OsiCcGradesDTO saveOsiCCGrades(OsiCcGradesDTO crudOsiCCGradesDTO, Integer userId) throws DataAccessException;
	OsiCcGradesDTO updateOsiCostCenter(OsiCcGradesDTO osiCcGradesDTO, Integer userId) throws DataAccessException;
	void deleteOsiCCGrades(Integer id) throws DataAccessException;
	List<OsiCcGradesDTO> getOsiCCGradesByCCID(Integer ccId) throws DataAccessException;
	void deleteOsiCCGradesByCCID(Integer id) throws DataAccessException;
	List<OsiCcGradesDTO> getOsiCCGradesHistory(Integer ccId, Integer orgId, Integer gradeId) throws DataAccessException;
	boolean compareWithExisting(OsiCcGradesDTO ccGrade);
}
