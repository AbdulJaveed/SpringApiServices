package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiCertificationGroupsDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiCertificationGroupsService {

	/**
	 * Method for saving the osiCertificationgroups.
	 * 
	 * @param osiCertificationGroups
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiCertificationGroupsDTO saveOsiCertificationGroups(OsiCertificationGroupsDTO osiCertificationGroupsDto,
			Integer userId) throws BusinessException, DataAccessException;

	/**
	 * Method for updating the Certification groups.
	 * 
	 * @param osiCertificationGroups
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiCertificationGroupsDTO updateOsiCertificationGroups(OsiCertificationGroupsDTO osiCertificationGroupsDto,
			Integer userId) throws BusinessException, DataAccessException;

	/**
	 * Method for getting the CertificationGroup by id.
	 * 
	 * @param osiCertificationGroupId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiCertificationGroupsDTO getOsiCertificationGroupsById(Integer osiCertificationGroupId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting the all Certification groups.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public List<OsiCertificationGroupsDTO> getAllOsiCertificationGroups(OsiCertificationGroupsDTO dto)
			throws BusinessException, DataAccessException;

	public OsiCertificationGroupsDTO deleteOsiCertificationGroups(Integer osiCertificationGroupId, Integer userId)
			throws BusinessException, DataAccessException;

	public List<OsiCertificationGroupsDTO> getAllActiveOsiCertificationGroups()
			throws BusinessException, DataAccessException;

}
