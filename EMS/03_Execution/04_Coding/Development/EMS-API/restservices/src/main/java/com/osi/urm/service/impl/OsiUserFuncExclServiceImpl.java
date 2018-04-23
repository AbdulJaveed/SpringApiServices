package com.osi.urm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.urm.domain.OsiFunctions;
import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.mapper.OsiFunctionsMapper;
import com.osi.urm.mapper.OsiUserFuncExclMapper;
import com.osi.urm.repository.OsiFunctionExcluRepository;
import com.osi.urm.repository.OsiFunctionRepository;
import com.osi.urm.service.OsiUserFuncExclService;
import com.osi.urm.service.dto.OsiFunctionsDTO;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;

/**
 * Service Implementation for managing OsiFunction.
 */
@Service
@Transactional
public class OsiUserFuncExclServiceImpl implements OsiUserFuncExclService {

	private final Logger log = LoggerFactory.getLogger(OsiUserFuncExclServiceImpl.class);

	@Autowired
	private OsiFunctionRepository osiFunctionRepository;

	@Autowired
	private OsiFunctionExcluRepository osiFunctionExcluRepository;
	
	@Autowired
	private OsiFunctionsMapper osiFunctionsMapper;
	
	@Autowired
	private OsiUserFuncExclMapper osiUserFuncExclMapper;
	 
	/**
	 * Save a osiFunction.
	 * 
	 * @param <osiFunctionExclDTO>
	 *
	 * @param osiFunctionDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws BusinessException 
	 */
	public OsiUserFuncExclDTO save(OsiUserFuncExclDTO osiUserFuncExclDTO, Integer businessGroupId) throws BusinessException {

		log.debug("Request to save OsiFunctionExclusion : {}", osiUserFuncExclDTO);
		osiUserFuncExclDTO.setBusinessGroupId(businessGroupId);
		OsiUserFuncExcl osiUserFuncExcl = osiUserFuncExclMapper.osiUserFuncExclDTOToOsiUserFuncExcl(osiUserFuncExclDTO);
		
		osiUserFuncExcl = osiFunctionExcluRepository.save(osiUserFuncExcl);
		OsiUserFuncExclDTO result = osiUserFuncExclMapper.osiUserFuncExclToOsiUserFuncExclDTO(osiUserFuncExcl);

		return result;

	}

	/**
	 * Get all the osiFunctions.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */

	public List<OsiFunctionsDTO> findAll() {
		log.debug("Request to get all OsiMenus");
		List<OsiFunctions> osiFunctions = osiFunctionRepository.findAll();
		List<OsiFunctionsDTO> osiFunctionsDTOs = osiFunctionsMapper.osiFunctionsListToOsiFunctionsDTOList(osiFunctions);
		return osiFunctionsDTOs;
	}

	/**
	 * Get one osiFunction by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public OsiFunctionsDTO findOne(Integer id) {
		log.debug("Request to get OsiFunction : {}", id);
		OsiFunctions osiFunctions = osiFunctionRepository.findOne(id);
		
		OsiFunctionsDTO osiFunctionDTO = new OsiFunctionsDTO();
		osiFunctionDTO.setId(osiFunctions.getId());
		osiFunctionDTO.setFuncType(osiFunctions.getFuncType());
		osiFunctionDTO.setFuncValue(osiFunctions.getFuncValue());
		
		osiFunctions.getOsiUserOperationExcls().iterator();
		return osiFunctionDTO;
	}

	/**
	 * Delete the osiFunction by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Integer id) {
		log.debug("Request to delete OsiFunction : {}", id);
		osiFunctionExcluRepository.delete(id);
	}
	
	@Override
	public void deleteByEmployeeId(Integer id) {
		log.debug("Request to delete OsiFunction : {}");
		osiFunctionExcluRepository.removeByEmployeeId(id);
	}
	
	@Override
	public void deleteAll() {
		log.debug("Request to delete OsiFunction : {}");
		osiFunctionExcluRepository.deleteAllInBatch();
	}
	@Override
	public List<OsiUserFuncExclDTO> findOsiUserFuncExclByUserId(Integer businessGroupId, Integer userId) {
		List<OsiUserFuncExcl> osiUserFuncExclList = osiFunctionExcluRepository.findByEmployeeIdAndBusinessGroupId(userId, businessGroupId);
		List<OsiUserFuncExclDTO> osiUserFuncExclDTOList = osiUserFuncExclMapper.osiUserFuncExclListToOsiUserFuncExclDTOList(osiUserFuncExclList);
		return osiUserFuncExclDTOList;
	}


}
