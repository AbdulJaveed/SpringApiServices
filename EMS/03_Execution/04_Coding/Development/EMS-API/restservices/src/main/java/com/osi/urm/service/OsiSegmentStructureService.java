package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiKeyFlexDTO;
import com.osi.urm.service.dto.OsiSegmentStructureDetailsDTO;
import com.osi.urm.service.dto.OsiSegmentStructureHdrDTO;
import com.osi.urm.service.dto.OsiUserDTO;

/**
 * Service Interface for managing OsiSegmentStructureHdr.
 */
public interface OsiSegmentStructureService {

	 /**
     * Save a osiSegmentStructureHdr.
     *
     * @param osiSegmentStructureHdrDTO the entity to save
     * @return the persisted entity
     * @throws BusinessException 
     */
    OsiSegmentStructureHdrDTO save(OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO, Integer userId) throws BusinessException;

    /**
     *  Get all the osiSegmentStructureHdrs.
     *  
     *  @param busines the pagination information
     *  @return the list of entities
     * @throws BusinessException 
     */
    List<OsiSegmentStructureHdrDTO> findAll(Integer businessGroupId) throws BusinessException;

    /**
     *  Get the "id" osiSegmentStructureHdr.
     *
     *  @param id the id of the entity
     *  @return the entity
     * @throws BusinessException 
     */
    
    Integer findOne(String segmentStructureHdrDesc)throws BusinessException;
    
    OsiSegmentStructureHdrDTO findOne(Integer segmentStructureHdrId, Integer businessGroupId) throws BusinessException;

    /**
     *  Delete the "id" osiSegmentStructureHdr.
     *
     *  @param id the id of the entity
     * @throws BusinessException 
     */
    int deleteAll(List<Integer> segmentStructureHdrIds,OsiUserDTO user) throws BusinessException;
    List<OsiSegmentStructureDetailsDTO> getOsiSegmentStructureValues(String structureName, Integer businessGroupId) throws BusinessException;
    List<OsiKeyFlexDTO> getOsiKeyFlexFields(Integer segmentStructureHdrId, Integer businessGroupId) throws BusinessException;

	List<OsiSegmentStructureHdrDTO> searchSegment(OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO,Integer businessGroupId)  throws BusinessException;
	public OsiSegmentStructureHdrDTO isSegmentUsedinCategoryOrCoa(Integer segmentHdrId,Integer businessGroupId,String segmentHdrName) throws BusinessException;
	
	OsiSegmentStructureHdrDTO getOsiSegmentStructureValues(Integer businessGroupID) throws BusinessException;
}
