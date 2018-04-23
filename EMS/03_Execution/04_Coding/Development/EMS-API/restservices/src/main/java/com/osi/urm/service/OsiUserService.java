package com.osi.urm.service;

import java.util.List;

import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiAttachmentsDTO;
import com.osi.urm.service.dto.OsiRespUserDTO;
import com.osi.urm.service.dto.OsiUserDTO;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;

/**
 * Service Interface for managing OsiUser.
 */
public interface OsiUserService {

    /**
     * Save a osiUser.
     *
     * @param osiUserDTO the entity to save
     * @param businessGroupId 
     * @return the persisted entity
     */
    OsiUser save(OsiUserDTO osiUserDTO,Integer userId,Integer businessGroupId) throws BusinessException, DataAccessException ;
    
    /**
     *  Get 10 Users.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    List<OsiUserDTO> findUserInitially(Integer businessGroupId)throws BusinessException;
    
    /**
     *  Get all the osiUsers.
     *  
     *  @return the list of entities
     */
    List<OsiUserDTO> findAll(Integer businessGroupId)throws BusinessException;

    /**
     *  Get the "id" osiUser.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OsiUserDTO findOne(Integer id,Integer businessGroupId)throws BusinessException;

    /**
     *  Delete the "id" osiUser.
     *
     *  @param id the id of the entity
     */
    Integer delete(List<Integer> id,Integer businessGroupId,Integer userId)throws BusinessException;

	OsiUserDTO findUserById(Integer userId, Integer businessGroupId) throws BusinessException;
	 
	List<OsiRespUserDTO> findUserResponsibilities(Integer userId, Integer businessGroupId) throws BusinessException;
	List<OsiUserFuncExclDTO> getUserFunctionExclusions(Integer userId, Integer businessGroupId) throws BusinessException;
	List<OsiUserOperationExclDTO> getUserOperationExclusions(Integer userId, Integer businessGroupId) throws BusinessException;
	List<OsiAttachmentsDTO> getUserAttachments(Integer userId, Integer businessGroupId) throws BusinessException;
	List<OsiUserDTO> findAllActiveUsers(Integer businessGroupId)throws BusinessException;
	OsiUser updateUserProfile(OsiUserDTO osiUserDTO,Integer businessGroupId) throws BusinessException;
	public List<OsiUserDTO> findUser(OsiUserDTO osiUserDTO, Integer businessGroupId) throws BusinessException;
	Integer  updatePassword(Integer userId, Integer businessGroupId,String password) throws BusinessException;	
	OsiUser updateUserPassword(Integer businessGroupId, Integer id, Integer id2, String encodedPassword) throws BusinessException;
}
