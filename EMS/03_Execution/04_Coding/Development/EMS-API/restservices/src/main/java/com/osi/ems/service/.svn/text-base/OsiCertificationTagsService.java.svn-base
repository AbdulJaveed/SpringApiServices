package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiCertificationTagsDto;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

public interface OsiCertificationTagsService {

	/**
	 * Method for saving the osiCertificationTags
	 * 
	 * @param osiCertificationTags
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiCertificationTagsDto saveOsiCertificationTags(OsiCertificationTagsDto osiCertificationTagsDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for updating the osiCertification tags.
	 * 
	 * @param osiCertificationTags
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiCertificationTagsDto updateOsiCertificationTags(OsiCertificationTagsDto osiCertificationTagsDto, Integer userId)
			throws BusinessException, DataAccessException;

	/**
	 * Method for getting osiCertificationtag by id.
	 * 
	 * @param osiCertificationTagId
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public OsiCertificationTagsDto getOsiCertificationTagsById(Integer osiCertificationTagId) throws BusinessException, DataAccessException;

	/**
	 * Method for getting all osiCertificationtags.
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws DataAccessException
	 */
	public List<OsiCertificationTagsDto> getAllOsiCertificationTags(OsiCertificationTagsDto dto) throws BusinessException, DataAccessException;

	public OsiCertificationTagsDto deleteOsiCertificationTags(Integer CertificationTagId, Integer userId)
			throws BusinessException, DataAccessException;
	
	public List<OsiCertificationTagsDto> getAllActiveOsiCertificationTags() throws BusinessException, DataAccessException;


}
