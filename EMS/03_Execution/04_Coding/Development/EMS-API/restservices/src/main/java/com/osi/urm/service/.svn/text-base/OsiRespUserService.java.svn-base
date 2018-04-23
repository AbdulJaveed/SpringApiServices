package com.osi.urm.service;

import java.util.List;

import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiRespUserDTO;

/**
 * Service Interface for managing OsiUser.
 */
public interface OsiRespUserService {

    /**
     * Save a osiUser.
     *
     * @param OsiUser the entity to save
     * @return the persisted entity
     * @throws BusinessException 
     */
    OsiRespUserDTO save(OsiRespUser osiRespUser);
    //List<OsiRespUserDTO> getById(OsiUser osiUser);
    void deleteRespUser(Integer id) throws BusinessException;
	List<OsiRespUserDTO> getById(Integer employeeId, Integer businessId) throws BusinessException;
	void deleteOperationsExclusionByUserId(Integer userId) throws BusinessException;
	void deleteFuncExclusionByUserId(Integer userId) throws BusinessException;
   }
