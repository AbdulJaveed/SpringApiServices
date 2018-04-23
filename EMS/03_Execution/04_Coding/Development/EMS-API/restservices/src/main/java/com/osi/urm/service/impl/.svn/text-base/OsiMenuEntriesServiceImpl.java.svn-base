package com.osi.urm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiMenuEntries;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiMenuEntriesMapper;
import com.osi.urm.repository.OsiMenuEntriesRepository;
import com.osi.urm.service.OsiMenuEntriesService;
import com.osi.urm.service.dto.OsiMenuEntriesDTO;

/**
 * Service Implementation for managing OsiMenuEntries.
 */
@Service
@Transactional
public class OsiMenuEntriesServiceImpl implements OsiMenuEntriesService{

    private final Logger log = LoggerFactory.getLogger(OsiMenuEntriesServiceImpl.class);
    
    @Autowired
    private OsiMenuEntriesRepository osiMenuEntriesRepository;
    
    @Autowired
    private OsiMenuEntriesMapper osiMenuEntriesMapper;
    
    @Autowired
	private CommonService commonService;

    /**
     * Save a osiMenuEntries.
     *
     * @param osiMenuEntriesDTO the entity to save
     * @return the persisted entity
     */

    
    
    public OsiMenuEntriesDTO save(OsiMenuEntriesDTO osiMenuEntriesDTO,int userId) throws BusinessException {
    	
    	OsiMenuEntries osiMenuEntries=null;
        log.debug("Request to save OsiMenuEntries : {}", osiMenuEntriesDTO);
        if(osiMenuEntriesDTO.getId() != null){
        	osiMenuEntriesDTO.setUpdatedBy(userId);
        	//osiMenuEntriesDTO.setUpdatedDate(new Date());
        	osiMenuEntriesDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
        	osiMenuEntries = osiMenuEntriesMapper.osiMenuEntriesDTOToOsiMenuEntries(osiMenuEntriesDTO);
        	osiMenuEntries = osiMenuEntriesRepository.save(osiMenuEntries);
        }else {
        	osiMenuEntriesDTO.setCreatedBy(userId);
        	//osiMenuEntriesDTO.setCreatedDate(new Date());
        	//osiMenuEntriesDTO.setUpdatedDate(new Date());
        	osiMenuEntriesDTO.setCreatedDate(commonService.getCurrentDateinUTC());
        	osiMenuEntriesDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
        	osiMenuEntriesDTO.setUpdatedBy(userId);
        	osiMenuEntries = osiMenuEntriesMapper.osiMenuEntriesDTOToOsiMenuEntries(osiMenuEntriesDTO);
        	osiMenuEntries = osiMenuEntriesRepository.save(osiMenuEntries);
        }
        
        OsiMenuEntriesDTO result = osiMenuEntriesMapper.OsiMenuEntriesToOsiMenuEntriesDTO(osiMenuEntries);
        return result;
        
    }
    /**
     *  Get all the osiMenuEntries.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<OsiMenuEntriesDTO> findMenuEntriesInitially(int businessGroupId)throws BusinessException {
        log.debug("Request to get all OsiMenuEntries");
        PageRequest pgrObj=new PageRequest(0, 8);
        List<OsiMenuEntries> result=osiMenuEntriesRepository.findOsiMenuEntriesByBusinessGroupId(businessGroupId, pgrObj);
        
        List<OsiMenuEntriesDTO>menuEntriesDTOList=osiMenuEntriesMapper.osiMenuEntriesListToOsiMenuEntriesDTOList(result);
        return menuEntriesDTOList;
    }

    /**
     *  Get all the osiMenuEntries.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<OsiMenuEntriesDTO> findAll(int businessGroupId)throws BusinessException {
        log.debug("Request to get all OsiMenuEntries");
        
        List<OsiMenuEntries> result=osiMenuEntriesRepository.findOsiMenuEntriesByBusinessGroupId(businessGroupId);
        
        List<OsiMenuEntriesDTO>menuEntriesDTOList=osiMenuEntriesMapper.osiMenuEntriesListToOsiMenuEntriesDTOList(result);
        return menuEntriesDTOList;
    }
   

    /**
     *  Get one osiMenuEntries by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public OsiMenuEntriesDTO findOne(Integer id,int businessGroupId)throws BusinessException
    {
        log.debug("Request to get OsiMenuEntries : {}", id);
        OsiMenuEntries osiMenuEntries = osiMenuEntriesRepository.findOsiMenusEntryByBusinessGroupIdAndId(id,businessGroupId);
        OsiMenuEntriesDTO osiMenuEntriesDTO = osiMenuEntriesMapper.OsiMenuEntriesToOsiMenuEntriesDTO(osiMenuEntries);
        return osiMenuEntriesDTO;
    }

    /**
     *  Delete the  osiMenuEntries by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Integer id)throws BusinessException {
        log.debug("Request to delete OsiMenuEntries : {}", id);
        osiMenuEntriesRepository.delete(id);
    }
    
    @Override
	@Transactional(readOnly = true)
	public List<OsiMenuEntriesDTO> findMenuEntries(OsiMenuEntriesDTO osiMenuEntriesDTO,Integer businessGroupId) throws BusinessException {
		List<OsiMenuEntriesDTO> osiMenuEntriesDTOList = null;
		List<OsiMenuEntries> domainList = null;
		Integer sequence=null;
		String menuPrompt=null;
		String headerMenuName =null;
		String subMenuName =null;
		String funcName =null;
		
		if(osiMenuEntriesDTO.getSeq()!=null)
		{
		 sequence=osiMenuEntriesDTO.getSeq();
		}
		if(osiMenuEntriesDTO.getPrompt()!=null)
		{
			 menuPrompt=osiMenuEntriesDTO.getPrompt();
		}
		if(osiMenuEntriesDTO.getOsiMenusByMenuId()!=null)
		{
			 headerMenuName=osiMenuEntriesDTO.getOsiMenusByMenuId().getMenuName();
		}
		if(osiMenuEntriesDTO.getOsiMenusBySubMenuId()!=null)
		{
		  subMenuName=osiMenuEntriesDTO.getOsiMenusBySubMenuId().getMenuName();
		}
		if(osiMenuEntriesDTO.getOsiFunctions()!=null)
		{
			 funcName=osiMenuEntriesDTO.getOsiFunctions().getFuncName();
		}		
		
		try 
		{
			 domainList=new ArrayList<>();
			 domainList=osiMenuEntriesRepository.searchEntries(businessGroupId,sequence,menuPrompt,headerMenuName,subMenuName,funcName);			
			 osiMenuEntriesDTOList=osiMenuEntriesMapper.osiMenuEntriesListToOsiMenuEntriesDTOList(domainList);
		} 
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		 return osiMenuEntriesDTOList;
	}
    
    
	public Integer deleteMenuEntry(Integer OsiMenuId, Integer businessGroupId, Integer userId) throws BusinessException {
		Integer count = 0;
		try {
			count = osiMenuEntriesRepository.deleteMenuEntry(OsiMenuId, businessGroupId, userId);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return count;
	}
	
}
