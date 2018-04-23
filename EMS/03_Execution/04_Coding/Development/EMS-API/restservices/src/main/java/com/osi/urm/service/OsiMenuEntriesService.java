package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiMenuEntriesDTO;

/**
 * Service Interface for managing OsiMenuEntries.
 */
public interface OsiMenuEntriesService {

	
	public List<OsiMenuEntriesDTO> findMenuEntries(OsiMenuEntriesDTO osiMenuEntriesDTO, Integer businessGroupId) throws BusinessException;
    /**
     * Save a osiMenuEntries.
     *
     * @param osiMenuEntriesDTO the entity to save
     * @return the persisted entity
     */
    OsiMenuEntriesDTO save(OsiMenuEntriesDTO osiMenuEntriesDTO,int userId)throws BusinessException;

    /**
     *  Get 10 MenuEntries.
     *  
     *  @return the list of entities
     * @throws BusinessException 
     */
    List<OsiMenuEntriesDTO> findMenuEntriesInitially(int businessGroupId) throws BusinessException;
    
    /**
     *  Get all the osiMenuEntries.
     *  
     *  @return the list of entities
     * @throws BusinessException 
     */
    List<OsiMenuEntriesDTO> findAll(int businessGroupId) throws BusinessException;

    /**
     *  Get the "id" osiMenuEntries.
     *
     *  @param id the id of the entity
     *  @return the entity
     * @throws BusinessException 
     */
    OsiMenuEntriesDTO findOne(Integer id,int businessdGroupId) throws BusinessException;

    /**
     *  Delete the "id" osiMenuEntries.
     *
     *  @param id the id of the entity
     */
    void delete(Integer id)throws BusinessException;

    Integer deleteMenuEntry(Integer id, Integer businessGroupId, Integer userId)throws BusinessException;
}
