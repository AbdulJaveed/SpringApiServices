package com.osi.urm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.osi.urm.service.dto.OsiLookupValuesDTO;

/**
 * Service Interface for managing OsiLookupValues.
 */
public interface OsiLookupValuesService {

    /**
     * Save a osiLookupValues.
     *
     * @param osiLookupValuesDTO the entity to save
     * @return the persisted entity
     */
    OsiLookupValuesDTO save(OsiLookupValuesDTO osiLookupValuesDTO);

    /**
     *  Get all the osiLookupValues.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OsiLookupValuesDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" osiLookupValues.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OsiLookupValuesDTO findOne(Integer id);

    /**
     *  Delete the "id" osiLookupValues.
     *
     *  @param id the id of the entity
     */
    void delete(Integer id);
}
