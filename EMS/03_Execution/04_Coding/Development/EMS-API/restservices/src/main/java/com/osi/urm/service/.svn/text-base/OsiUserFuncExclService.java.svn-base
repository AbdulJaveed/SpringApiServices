package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiFunctionsDTO;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;

/**
 * Service Interface for managing OsiFunction.
 */
public interface OsiUserFuncExclService {

	/**
	 * Save a osiFunction.
	 * @param businessGroupId 
	 *
	 * @param osiFunctionsDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	OsiUserFuncExclDTO save(OsiUserFuncExclDTO osiFunctionsExclDTO, Integer businessGroupId) throws BusinessException;

	/**
	 * Get all the osiFunctions.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	//Page<OsiUserFuncExclDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" osiFunction.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	OsiFunctionsDTO findOne(Integer id);

	/**
	 * Delete the "id" osiFunction.
	 *
	 * @param id
	 *            the id of the entity
	 */

	void delete(Integer id);

	List<OsiUserFuncExclDTO> findOsiUserFuncExclByUserId(Integer businessGroupId, Integer userId) throws BusinessException;

	void deleteByEmployeeId(Integer id);

	void deleteAll();	
}
