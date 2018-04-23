package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiResponsibilitiesDTO;

/**
 * Service Interface for managing OsiResponsibility.
 */
public interface OsiResponsibilityService {

    /**
     * Save a osiResponsibility.
     *
     * @param osiResponsibilityDTO the entity to save
     * @return the persisted entity
     * @throws DataAccessException 
     */
    OsiResponsibilitiesDTO save(OsiResponsibilitiesDTO osiResponsibilityDTO,Integer businessGroupId) throws BusinessException, DataAccessException;

    /**
     *  Get all the osiResponsibilities.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    List<OsiResponsibilitiesDTO> findAll(Integer businessGroupId)throws BusinessException;
    List<OsiResponsibilitiesDTO> findInitialResponsibilities(Integer businessGroupId)throws BusinessException;
    List<OsiResponsibilitiesDTO> findAllOsiResponsibilitiesList(Integer businessGroupId)throws BusinessException;
    
    /**
     *  Get the "id" osiResponsibility.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OsiResponsibilitiesDTO findOne(Integer id,Integer businessGroupId)throws BusinessException;
    /**
     *  Delete the "id" osiResponsibility.
     *
     *  @param id the id of the entity
     */
    OsiResponsibilitiesDTO delete(Integer id,Integer userId)throws BusinessException;

	List<OsiResponsibilitiesDTO> findOsiResponsibilitiesByNameOrDescription(String respName, String respDescription) throws BusinessException;

	Integer findResponsibilityByNameAndActive(String respName, Integer businessGroupId, Integer Active) throws DataAccessException, BusinessException;

	Integer findResponsibilityByNameIdAndActive(Integer id, String respName, Integer businessGroupId, Integer avtive) throws DataAccessException,BusinessException;
}
