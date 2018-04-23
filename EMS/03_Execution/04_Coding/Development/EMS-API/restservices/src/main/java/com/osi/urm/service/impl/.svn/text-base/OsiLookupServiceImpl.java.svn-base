package com.osi.urm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiLookupMapper;
import com.osi.urm.repository.OsiLookupRepository;
import com.osi.urm.service.OsiLookupTypesService;
import com.osi.urm.service.dto.OsiLookupTypesDTO;
import com.osi.urm.service.dto.OsiLookupValuesDTO;
import com.osi.urm.service.dto.OsiUserDTO;

/**
 * Service Implementation for managing OsiLookupTypes.
 */
@Service
@Transactional
public class OsiLookupServiceImpl implements OsiLookupTypesService{

    private final Logger log = LoggerFactory.getLogger(OsiLookupServiceImpl.class);
    
    @Autowired
    private OsiLookupRepository osiLookupTypesRepository;
    
    @Autowired
    OsiLookupMapper mapper;
    
    @Autowired
	AppConfig appConfig;
    
    @Autowired
	private CommonService commonService;

    /**
     * Save a osiLookupTypes.
     *
     * @param osiLookupTypesDTO the entity to save
     * @return the persisted entity
     */
    public OsiLookupTypesDTO save(OsiLookupTypesDTO osiLookupTypesDTO, Integer userId)
    		throws BusinessException, DataIntegrityViolationException {
     	log.debug("Request to save OsiMenu : {}", osiLookupTypesDTO);
		String errorCode = "";
		boolean isValidationSuccess = false;
		boolean isLookupNameUnique = false;
		try {
			//Lookup Code Uniqueness Validation			
			if(osiLookupTypesDTO.getLookupCode()!=null && !osiLookupTypesDTO.getLookupCode().equals("")){
				List<Long> existingLookupId = osiLookupTypesRepository.validateUniqueLookupCode(osiLookupTypesDTO.getLookupCode().toUpperCase());
				if(existingLookupId.size()<=1){
					if (osiLookupTypesDTO.getId() != null) {
						if(!existingLookupId.isEmpty()){
							if(existingLookupId.get(0).equals(osiLookupTypesDTO.getId())){
								isValidationSuccess=true;
							}
						}else{
							isValidationSuccess=true;
						}
					}else {
						if(!existingLookupId.isEmpty()){
							isValidationSuccess = false;
						}else{
							isValidationSuccess=true;
						}
					}
				}else{
					/*errorCode = "ERR_1000";
					throw new Exception();*/
					isValidationSuccess = false;
				}
			}
			//Lookup Name uniqueness validation
			if(osiLookupTypesDTO.getLookupName()!=null && !osiLookupTypesDTO.getLookupName().equals("")){
				List<Long> existingLookupId = osiLookupTypesRepository.validateUniqueLookupName(osiLookupTypesDTO.getLookupName().toUpperCase());
				if(existingLookupId.size()<=1){
					if (osiLookupTypesDTO.getId() != null) {
						if(!existingLookupId.isEmpty()){
							if(existingLookupId.get(0).equals(osiLookupTypesDTO.getId())){
								isLookupNameUnique=true;
							}
						}else{
							isLookupNameUnique=true;
						}
					}else {
						if(!existingLookupId.isEmpty()){
							isLookupNameUnique = false;
						}else{
							isLookupNameUnique=true;
						}
					}
				}else{
					/*errorCode = "ERR_1000";
					throw new Exception();*/
					isLookupNameUnique = false;
				}
			}
			
			//This is for checking if lookup ZONE is already referenced.
			/*if(osiLookupTypesDTO.getLookupCode().equals(appConfig.getZone()) && osiLookupTypesDTO.getActive() != 1) {
				List<OsiInventoryOrg> invOrgList = osiInventoryOrgRepository.findInventoryOrgByBusinessGroup(osiLookupTypesDTO.getBusinessGroupId());
				if(invOrgList.size() > 0) {
					errorCode = "ERR_1064";
					throw new Exception();
				}
			}
			*/
			if(isValidationSuccess && isLookupNameUnique && osiLookupTypesDTO.getLookupCode()!=null && !osiLookupTypesDTO.getLookupCode().equals("") 
					&& osiLookupTypesDTO.getLookupName()!=null && !osiLookupTypesDTO.getLookupName().equals("") 
					&& osiLookupTypesDTO.getOsiLookupValueses()!=null && !osiLookupTypesDTO.getOsiLookupValueses().isEmpty()){
				
				Set<String> validateDuplicateLookupValues = new HashSet<String>();	
				for(OsiLookupValuesDTO lookupValuesDTO : osiLookupTypesDTO.getOsiLookupValueses() ){
					//Lookup Value Mandatory Validation
					if(lookupValuesDTO.getLookupValue()!=null && !lookupValuesDTO.getLookupValue().equals("") ){
						validateDuplicateLookupValues.add(lookupValuesDTO.getLookupValue().trim().toUpperCase());						
					}else{
						errorCode = "ERR_10111";
						throw new Exception();
					}
				}
				if(validateDuplicateLookupValues.size()!=osiLookupTypesDTO.getOsiLookupValueses().size()){
					errorCode = "ERR_1004";
					throw new Exception();
				}
				osiLookupTypesDTO.setCreatedBy(userId);
				osiLookupTypesDTO.setUpdatedBy(userId);
				OsiLookupTypes osiLookupTypes = mapper.osiLookupDTOToOsiLookup(osiLookupTypesDTO);
				//osiLookupTypes.setActive(1);
				
				if (osiLookupTypesDTO.getId() != null) {
					errorCode = "ERR_1009";
					osiLookupTypes.setId(osiLookupTypesDTO.getId());
					osiLookupTypes.setUpdatedBy(userId);
					//osiLookupTypes.setUpdatedDate(new Date());
					osiLookupTypes.setUpdatedDate(commonService.getCurrentDateinUTC());
					osiLookupTypes.setActive(osiLookupTypesDTO.getActive());
					osiLookupTypes = osiLookupTypesRepository.updateLookup(osiLookupTypes);
				} else {
					errorCode = "ERR_1008";
					osiLookupTypes.setCreatedBy(userId);
					//osiLookupTypes.setCreatedDate(new Date());
					osiLookupTypes.setCreatedDate(commonService.getCurrentDateinUTC());
					osiLookupTypes.setUpdatedBy(userId);
					//osiLookupTypes.setUpdatedDate(new Date());
					osiLookupTypes.setUpdatedDate(commonService.getCurrentDateinUTC());
					osiLookupTypes = osiLookupTypesRepository.save(osiLookupTypes);
				}
				osiLookupTypesDTO.setId(osiLookupTypes.getId());
			}else{
				errorCode = "ERR_10111";
				if(!isValidationSuccess)
					errorCode = "ERR_1051";
				if(!isLookupNameUnique)
					errorCode = "ERR_1052";
				throw new Exception();
			}
		} catch (DataAccessException e) {
			throw new BusinessException(errorCode, e.getSystemMessage());
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("ERR_1004", e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(errorCode, e.getMessage());
		}
		return osiLookupTypesDTO;
    }

    /**
     *  Get all the osiLookupTypes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     * @throws BusinessException 
     */
    @Transactional(readOnly = true) 
    public List<OsiLookupTypesDTO> findAll(Integer businessGroupId) throws BusinessException {
        log.debug("Request to get all OsiLookupTypes");
        List<OsiLookupTypesDTO> lookupTypesDTOList = new ArrayList<OsiLookupTypesDTO>();
        try{
        	PageRequest pgrObj=new PageRequest(0, 8);
	        List<OsiLookupTypes> result = osiLookupTypesRepository.findAll();
	        for(OsiLookupTypes osiLookupTypes:result){
	        	OsiLookupTypesDTO osiLookupTypesDTO = mapper.osiLookupToOsiLookupDTO(osiLookupTypes);
				lookupTypesDTOList.add(osiLookupTypesDTO);
	        }
        }catch (Exception e) {
			throw new BusinessException("ERR_1002", e.getMessage());
		}
        return lookupTypesDTOList;
    }
    
    @Transactional(readOnly = true) 
    public List<OsiLookupTypesDTO> findAllActiveLookups(Integer businessGroupId) throws BusinessException {
        log.debug("Request to get all OsiLookupTypes");
        List<OsiLookupTypesDTO> lookupTypesDTOList = new ArrayList<OsiLookupTypesDTO>();
        try{
	        List<OsiLookupTypes> result = osiLookupTypesRepository.findAllActiveLookups();
	        for(OsiLookupTypes osiLookupTypes:result){
	        	OsiLookupTypesDTO osiLookupTypesDTO = mapper.osiLookupToOsiLookupDTO(osiLookupTypes);
				lookupTypesDTOList.add(osiLookupTypesDTO);
	        }
        }catch (Exception e) {
			throw new BusinessException("ERR_1002", e.getMessage());
		}
        return lookupTypesDTOList;
    }

    /**
     *  Get one osiLookupTypes by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public OsiLookupTypesDTO findOne(Long id, Integer businessGroupId) throws BusinessException {
        log.debug("Request to get OsiLookupTypes : {}", id);
        OsiLookupTypes osiLookupTypes = osiLookupTypesRepository.findSingleActiveLookup(id);
        OsiLookupTypesDTO osiLookupTypesDTO = null;
    	
        try{
        	if(osiLookupTypes!=null){
		    	osiLookupTypesDTO = mapper.osiLookupToOsiLookupDTO(osiLookupTypes);
        	}else{
        		throw new Exception();
        	}
        }catch(Exception e){
        	throw new BusinessException("ERR_1002", e.getMessage());
        }
        return osiLookupTypesDTO;
    }

    /**
     *  Delete the  osiLookupTypes by id.
     *
     *  @param id the id of the entity
     * @throws BusinessException 
     */
    public int delete(List<Long> id,OsiUserDTO user) throws BusinessException {
        log.debug("Request to delete OsiLookupTypes : {}", id);
        int count = 0;
        try {
			 count = osiLookupTypesRepository.deleteLookup(id,user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}
        return count;
        //osiLookupTypesRepository.delete(id);
    }

	@Override
	public OsiLookupTypesDTO findOsiLookupValuesesByLookupName(String lookupName) throws BusinessException {
		OsiLookupTypes osiLookupTypes=osiLookupTypesRepository.findOsiLookupValuesesByLookupName(lookupName);
		OsiLookupTypesDTO OsiLookupTypesDTO=mapper.osiLookupToOsiLookupDTO(osiLookupTypes);
		return OsiLookupTypesDTO;
	}

	@Transactional(readOnly = true)
	public List<OsiLookupTypesDTO> searchLookup(
			OsiLookupTypesDTO osiLookupTypesDTO, Integer businessGroupId)
			throws BusinessException {
		log.debug("Request to get Searched OsiLookupTypes");
		StringBuffer queryParam = new StringBuffer("");
		   if(osiLookupTypesDTO.getLookupName()!=null && !osiLookupTypesDTO.getLookupName().equals("")){
			   queryParam.append(" AND UPPER(l.lookupName) LIKE '%"+osiLookupTypesDTO.getLookupName().toUpperCase()+"%'");
		   }
		   if(osiLookupTypesDTO.getLookupCode()!=null && !osiLookupTypesDTO.getLookupCode().equals("")){
			   queryParam.append(" AND UPPER(l.lookupCode) LIKE '%"+osiLookupTypesDTO.getLookupCode().toUpperCase()+"%'");
		   }
        List<OsiLookupTypesDTO> lookupTypesDTOList = new ArrayList<OsiLookupTypesDTO>();
        try{
	        List<OsiLookupTypes> result = osiLookupTypesRepository.searchLookup(queryParam.toString(),businessGroupId);
	        for(OsiLookupTypes osiLookupTypes:result){
	        	OsiLookupTypesDTO lookupDTO = mapper.osiLookupToOsiLookupDTO(osiLookupTypes);
				lookupTypesDTOList.add(lookupDTO);
	        }
        }catch (Exception e) {
			throw new BusinessException("ERR_1002", e.getMessage());
		}
        return lookupTypesDTOList;
	}
	
	public OsiLookupTypesDTO isLookupUsedinCategory(Long lookupId,Integer businessGroupId) throws BusinessException{
		OsiLookupTypesDTO osiLookupTypesDTO=new OsiLookupTypesDTO();		
		try{
			Long catCount=osiLookupTypesRepository.isLookupUsedinCategory(lookupId, businessGroupId);
			Long coaCount=osiLookupTypesRepository.isLookupUsedinCOa(lookupId, businessGroupId);
			Long userCatCount=osiLookupTypesRepository.isLookupUsedinUserCategory(lookupId, businessGroupId);
			Long prHdrCount=osiLookupTypesRepository.isLookupUsedinPrHeader(lookupId, businessGroupId);
			Long prLineCount=osiLookupTypesRepository.isLookupUsedinPrReqLines(lookupId, businessGroupId);
			Long poHdrCount=osiLookupTypesRepository.isLookupUsedinPoHeader(lookupId, businessGroupId);
			Long poLineCount=osiLookupTypesRepository.isLookupUsedinPoReqLines(lookupId, businessGroupId);
			
			if(catCount>0 || coaCount>0 || userCatCount>0 || prHdrCount>0 || prLineCount>0 || poHdrCount>0 || poLineCount>0){
				osiLookupTypesDTO.setInactivable(0);
			}else{
				osiLookupTypesDTO.setInactivable(1);
			}
			
		}catch (Exception e) {
			throw new BusinessException("ERR_1065", e.getMessage());
		}
		return osiLookupTypesDTO;
		
	}
}
