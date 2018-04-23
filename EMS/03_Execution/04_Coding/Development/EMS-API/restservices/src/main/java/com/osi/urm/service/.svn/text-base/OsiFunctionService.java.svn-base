package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiFunctionsDTO;

/**
 * Service Interface for managing OsiFunction.
 */
public interface OsiFunctionService {

	/**
	 * Save a osiFunction.
	 *
	 * @param osiFunctionsDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	OsiFunctionsDTO save(OsiFunctionsDTO osiFunctionsDTO, Integer userId, Integer businessGroupId)throws BusinessException;

	/**
	 * Get all the osiFunctions.
	 * @param integer 
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	List<OsiFunctionsDTO> findAll(Integer integer) throws BusinessException;
	
	List<OsiFunctionsDTO> findFunctionsInitially(Integer integer) throws BusinessException;
	
	List<OsiFunctionsDTO> findAllList(Integer integer) throws BusinessException;


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
	Integer deleteFunction(Integer id, Integer businessGroupId, Integer userId)throws BusinessException;
	List<OsiFunctionsDTO> search(String funcName, String FuncValue)throws BusinessException, DataAccessException;

	Integer findFunctionsByNameId(Integer id, String funcName, Integer businessGroupId) throws DataAccessException, BusinessException;

	Integer findFunctionsByName(String funcName, Integer businessGroupId) throws DataAccessException, BusinessException;

	List<OsiFunctionsDTO> findAllByRespIds(Integer bussinessGroupId,List<Integer> userRespIds);

	List<OsiFunctionsDTO> findUserFunctionsList(Integer userId) throws BusinessException;
}
