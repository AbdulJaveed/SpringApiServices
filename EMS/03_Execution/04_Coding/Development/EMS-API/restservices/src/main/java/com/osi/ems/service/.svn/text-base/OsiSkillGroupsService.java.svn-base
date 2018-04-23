package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiSkillGroupsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiSkillGroupsService {

	/**
	 * Method for saving the osiSkillgroups.
	 * 
	 * @param osiSkillGroups
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSkillGroupsDTO saveOsiSkillGroups(OsiSkillGroupsDTO osiSkillGroupsDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for updating the skill groups.
	 * 
	 * @param osiSkillGroups
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSkillGroupsDTO updateOsiSkillGroups(OsiSkillGroupsDTO osiSkillGroupsDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting the skillGroup by id.
	 * 
	 * @param osiSkillGroupId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiSkillGroupsDTO getOsiSkillGroupsById(Integer osiSkillGroupId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting the all skill groups.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public List<OsiSkillGroupsDTO> getAllOsiSkillGroups(OsiSkillGroupsDTO dto) throws BusinessException, DataAccessException;

	public OsiSkillGroupsDTO deleteOsiSkillGroups(Integer osiSkillGroupId, Integer userId)
			throws BusinessException, DataAccessException;

	public List<OsiSkillGroupsDTO> getAllActiveOsiSkillGroups() throws BusinessException, DataAccessException;

}
