package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiSubPracticeDto;
import com.osi.ems.service.dto.OsiSubPracticeGradesDto;
import com.osi.ems.service.dto.OsiSubPracticeGradesHistoryDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiSubPracticeService {

	/**
	 * Method for saving the osiSkillTags
	 * 
	 * @param osiSkillTags
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSubPracticeDto saveOsiSubPractice(OsiSubPracticeDto OsiSubPracticeDto, Integer userId)
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
	public OsiSubPracticeDto updateOsiSubPractice(OsiSubPracticeDto OsiSubPracticeDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting osiSkilltag by id.
	 * 
	 * @param osiSkillTagId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSubPracticeDto getOsiSubPracticeById(Integer osiSkillTagId) throws BusinessException, DataAccessException;

	/**
	 * Method for getting all osiSkilltags.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public List<OsiSubPracticeDto> getAllOsiSubPractice(OsiSubPracticeDto osiSubPracticeDto)
			throws BusinessException, DataAccessException;

	public OsiSubPracticeDto deleteOsiSubPractice(Integer osiSubPracticeId, Integer userId)
			throws BusinessException, DataAccessException;

	public List<OsiSubPracticeDto> getAllActiveOsiSubPractice() throws BusinessException, DataAccessException;

	public OsiSubPracticeGradesDto createOsiSubPracticesGrades(OsiSubPracticeGradesDto osiBuGradesDto, Integer userId)
			throws BusinessException, DataAccessException;

	public void deleteOsiSubPracticeGradesBysubPracticeId(Integer id) throws BusinessException, DataAccessException;

	List<OsiSubPracticeGradesDto> getOsiSubPracticeGradesBysubPracticeId(Integer subPracticeId)
			throws BusinessException, DataAccessException;

	public List<OsiSubPracticeGradesHistoryDto> getOsiSubPracticeGradesHistory(Integer subPracticeId, Integer orgId,
			Integer gradeId) throws BusinessException, DataAccessException;

	public OsiSubPracticeGradesDto createOsiSubPracticesGradesWithHistoryDuplicate(
			OsiSubPracticeGradesDto osiSubPracticeGradesDto, Integer userId)
			throws BusinessException, DataAccessException;

}
