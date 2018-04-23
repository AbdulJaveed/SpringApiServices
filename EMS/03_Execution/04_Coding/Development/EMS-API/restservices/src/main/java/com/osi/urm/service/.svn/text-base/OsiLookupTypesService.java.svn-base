package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiLookupTypesDTO;
import com.osi.urm.service.dto.OsiUserDTO;

/**
 * Service Interface for managing OsiLookupTypes.
 */
public interface OsiLookupTypesService {

    /**
     * Save a osiLookupTypes.
     *
     * @param osiLookupTypesDTO the entity to save
     * @return the persisted entity
     */
    OsiLookupTypesDTO save(OsiLookupTypesDTO osiLookupTypesDTO, Integer userId) throws BusinessException;

    /**
     *  Get all the osiLookupTypes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    List<OsiLookupTypesDTO> findAll(Integer businessGroupId) throws BusinessException;

    /**
     *  Get the "id" osiLookupTypes.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OsiLookupTypesDTO findOne(Long id,Integer businessGroupId) throws BusinessException;

    /**
     *  Delete the "id" osiLookupTypes.
     *
     *  @param id the id of the entity
     */
    int delete(List<Long> id,OsiUserDTO user)  throws BusinessException;
    
    //Search lookup using LookupName and LookupCode
    List<OsiLookupTypesDTO> searchLookup(OsiLookupTypesDTO osiLookupTypesDTO,Integer businessGroupId) throws BusinessException;
    
    public OsiLookupTypesDTO findOsiLookupValuesesByLookupName(String lookupName) throws BusinessException;
    
    public OsiLookupTypesDTO isLookupUsedinCategory(Long lookupId,Integer businessGroupId) throws BusinessException;
    
    public List<OsiLookupTypesDTO> findAllActiveLookups(Integer businessGroupId) throws BusinessException;
}
