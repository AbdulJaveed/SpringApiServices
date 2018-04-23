package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiSubPracticeGradesHistoryDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiSubPracticeGradesHistoryService {

	/**
	 * Method for saving the osiSkillTags
	 * 
	 * @param osiSkillTags
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSubPracticeGradesHistoryDto saveOsiSubPracticeGradesHistory(
			OsiSubPracticeGradesHistoryDto OsiSubPracticeGradesHistoryDto, Integer userId)
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
	public OsiSubPracticeGradesHistoryDto updateOsiSubPracticeGradesHistory(
			OsiSubPracticeGradesHistoryDto OsiSubPracticeGradesHistoryDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting osiSkilltag by id.
	 * 
	 * @param osiSkillTagId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSubPracticeGradesHistoryDto getOsiSubPracticeGradesHistoryById(Integer osiSubPracticeGradeHistoryId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting all osiSkilltags.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public List<OsiSubPracticeGradesHistoryDto> getAllOsiSubPracticeGradesHistory(
			OsiSubPracticeGradesHistoryDto osiSubPracticeGradesHistoryDto)
			throws BusinessException, DataAccessException;

	public OsiSubPracticeGradesHistoryDto deleteOsiSubPracticeGradesHistory(Integer osiSubPracticeGradesHistoryId,
			Integer userId) throws BusinessException, DataAccessException;

	public List<OsiSubPracticeGradesHistoryDto> getAllActiveOsiSubPracticeGradesHistory()
			throws BusinessException, DataAccessException;

}
