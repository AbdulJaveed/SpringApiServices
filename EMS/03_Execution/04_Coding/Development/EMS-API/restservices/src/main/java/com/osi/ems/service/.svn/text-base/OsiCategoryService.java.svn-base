package com.osi.ems.service;

import java.util.List;

import com.osi.ems.service.dto.OsiCategoryDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.ems.service.dto.OsiTablesDTO;
import com.osi.urm.exception.BusinessException;


/**
 * Service Interface for managing OsiCategories.
 */
public interface OsiCategoryService {

	/**
	 * Save a osiCategory.
	 *
	 * @param osiCategoryDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	OsiCategoryDTO save(OsiCategoryDTO osiCategoryDTO, Integer userId)throws BusinessException;

	/**
     *  Get all the osiCategories.
     *  
     *  @return the list of categories
     * @throws BusinessException 
     */
    List<OsiCategoryDTO> findAll() throws BusinessException;

	List<OsiOrganizationsDTO> findAllOrganizations() throws BusinessException;

	List<OsiCategoryDTO> findCategories(OsiCategoryDTO osiCategoryDTO)throws BusinessException;

	
	List<OsiTablesDTO> findColumnsByTableName(String tableName)throws BusinessException;

}
