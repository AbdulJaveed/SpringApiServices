package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiSkillTagsDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiSkillTagsService {

	/**
	 * Method for saving the osiSkillTags
	 * 
	 * @param osiSkillTags
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSkillTagsDto saveOsiSkillTags(OsiSkillTagsDto osiSkillTagsDto, Integer userId)
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
	public OsiSkillTagsDto updateOsiSkillTags(OsiSkillTagsDto osiSkillTagsDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting osiSkilltag by id.
	 * 
	 * @param osiSkillTagId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSkillTagsDto getOsiSkillTagsById(Integer osiSkillTagId) throws BusinessException, DataAccessException;

	/**
	 * Method for getting all osiSkilltags.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public List<OsiSkillTagsDto> getAllOsiSkillTags(OsiSkillTagsDto dto) throws BusinessException, DataAccessException;

	public OsiSkillTagsDto deleteOsiSkillTags(Integer skillTagId, Integer userId)
			throws BusinessException, DataAccessException;

	public List<OsiSkillTagsDto> getAllActiveOsiSkillTags() throws BusinessException, DataAccessException;

}
