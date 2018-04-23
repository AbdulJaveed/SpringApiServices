package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiMenusDTO;

/**
 * Service Interface for managing OsiMenu.
 */
public interface OsiMenuService {

    /**
     * Save a osiMenu.
     *
     * @param osiMenusDTO the entity to save
     * @param integer 
     * @return the persisted entity
     * @throws BusinessException 
     */
    Integer save(OsiMenusDTO osiMenusDTO, Integer userId, Integer businessGroupId) throws BusinessException;
    
    /**
     *  Get 10 Menus.
     * @param businessGroupId 
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     * @throws BusinessException 
     */
    List<OsiMenusDTO> findMenusInitially(Integer businessGroupId) throws BusinessException;

    /**
     *  Get all the osiMenus.
     * @param businessGroupId 
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     * @throws BusinessException 
     */
    List<OsiMenusDTO> findAll(Integer businessGroupId) throws BusinessException;
    
    
    List<OsiMenusDTO> findAllForList(Integer businessGroupId) throws BusinessException;

    /**
     *  Get the "id" osiMenu.
     *
     *  @param id the id of the entity
     * @param businessGroupId 
     *  @return the entity
     *  @throws BusinessException 
     */
    OsiMenusDTO findOne(Integer id, Integer businessGroupId) throws BusinessException;

    /**
     *  Delete the "id" osiMenu.
     *
     *  @param id the id of the entity
     */
   /* void delete(Integer id);*/

	Integer deleteMenu(Integer id, Integer businessGroupId, Integer userId) throws BusinessException;
	
	Integer deleteMenus(List<Integer> id, Integer businessGroupId, Integer userId) throws BusinessException;
	
	List<OsiMenusDTO> findOsiMenusByMenuNameOrDescription(String menuName, String menuDescription) throws BusinessException;

}
