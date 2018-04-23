package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiSubPracticeGradesDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiSubPracticeGradesService {

	/**
	 * Method for saving the osiSkillTags
	 * 
	 * @param osiSkillTags
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSubPracticeGradesDto saveOsiSubPracticeGrades(OsiSubPracticeGradesDto OsiSubPracticeGradesDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for updating the osiskill tags.
	 * 
	 * @param osiSkillTags
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSubPracticeGradesDto updateOsiSubPracticeGrades(OsiSubPracticeGradesDto OsiSubPracticeGradesDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting osiSkilltag by id.
	 * 
	 * @param osiSkillTagId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSubPracticeGradesDto getOsiSubPracticeGradesById(Integer osiSkillTagId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting all osiSkilltags.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public List<OsiSubPracticeGradesDto> getAllOsiSubPracticeGrades(OsiSubPracticeGradesDto osiSubPracticeDto)
			throws BusinessException, DataAccessException;

	public OsiSubPracticeGradesDto deleteOsiSubPracticeGrades(Integer osiSubPracticeGradeId, Integer userId)
			throws BusinessException, DataAccessException;

	public List<OsiSubPracticeGradesDto> getAllActiveOsiSubPracticeGrades()
			throws BusinessException, DataAccessException;

}
