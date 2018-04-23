package com.osi.ems.service.impl;




import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiCategories;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.mapper.OsiCategoryMapper;
import com.osi.ems.repository.OsiCategoriesRepository;
import com.osi.ems.repository.OsiCategoryFieldsRepository;
import com.osi.ems.repository.OsiOrganizationsRepository;
import com.osi.ems.repository.custom.OsiCategoriesRepositoryCustom;
import com.osi.ems.service.OsiCategoryService;
import com.osi.ems.service.dto.OsiCategoryDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.ems.service.dto.OsiTablesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;



/**
 * Service Implementation for managing OsiCategories.
 */
@Service
@Transactional
public class OsiCategoryServiceImpl implements OsiCategoryService {

	private final Logger LOGGER = LoggerFactory.getLogger(OsiCategoryServiceImpl.class);

	@Autowired
	private OsiCategoriesRepository osiCategoryRepository;
	@Autowired
	private OsiCategoryFieldsRepository osiCategoryFieldsRepository;
	
	@Autowired
	private OsiCategoriesRepositoryCustom osiCategoryRepositoryCustom;
	@Autowired
	private OsiOrganizationsRepository osiOrganizationsRepository;
	
	
	
	@Autowired
	private OsiCategoryMapper osiCategorysMapper;
	
	
	@PersistenceContext
    private EntityManager entityManager;

	/**
	 * Save a osiCategories.
	 *
	 * @param osiCategoryDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	public OsiCategoryDTO save(OsiCategoryDTO osiCategoryDTO, Integer userId) throws BusinessException {
		OsiCategories osiCategorys = new OsiCategories();
		CommonService cs = new CommonService();
		osiCategoryDTO.setUpdatedBy(userId);
		osiCategoryDTO.setCreatedBy(userId);
		LOGGER.info("save : Begin");
		try {
			
			if (osiCategoryDTO.getId() != null) {
				
				
				osiCategoryDTO.setUpdatedDate(cs.getCurrentDateinUTC());
				
				
				osiCategorys = osiCategorysMapper.osiCategoryDTOToOsiCategory(osiCategoryDTO);
				osiCategoryFieldsRepository.removeByOsiCategoryId(osiCategorys.getId());
				
				osiCategorys = osiCategoryRepository.save(osiCategorys);
				osiCategoryDTO= osiCategorysMapper.osiCategorysToosiCategoryDTO(osiCategorys);
				
			} else {
				osiCategoryDTO.setCreatedDate(cs.getCurrentDateinUTC());
				osiCategoryDTO.setUpdatedDate(cs.getCurrentDateinUTC());
				osiCategorys = osiCategorysMapper.osiCategoryDTOToOsiCategory(osiCategoryDTO);
				
				osiCategorys = osiCategoryRepository.save(osiCategorys);
				osiCategoryDTO= osiCategorysMapper.osiCategorysToosiCategoryDTO(osiCategorys);
					
				
				
			}
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while saving the categories");
		}
		LOGGER.info("save : End");
		return osiCategoryDTO;
	}
	 /**
     *  Get all the osiCategories.
     *  
     *  @return the list of osiCategories
     */
	 @Transactional(readOnly = true) 
	public List<OsiCategoryDTO> findAll() throws BusinessException {
		 LOGGER.info("findAll : Begin");
		 List<OsiCategoryDTO> categoryDTOList=null;
	        //PageRequest pgrObj=new PageRequest(0, 8);
		 try {
			 List<OsiCategories> result=osiCategoryRepository.findAll();
		        
		      categoryDTOList=osiCategorysMapper.osiCategoryListToOsiCategoryDTOList(result);
		     System.out.println(categoryDTOList); 
		  
		 }catch (NoResultException e) {
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while getting the categories");
		}
		 LOGGER.info("findAll : End");
	     return categoryDTOList;
	}
	 @Transactional(readOnly = true) 
	public List<OsiOrganizationsDTO> findAllOrganizations() throws BusinessException {
		 LOGGER.info("findAllOrganizations : Begin");
		 List<OsiOrganizationsDTO> categoryDTOList = new ArrayList<OsiOrganizationsDTO>();
	      try{
	        List<OsiOrganizations> result=osiOrganizationsRepository.findAll(); 
	        categoryDTOList=osiCategorysMapper.osiOrganizationsListToOsiOrganizationDTOList(result);
	      }catch (NoResultException e) {
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1002", "No Records Found");
		}catch(Exception e){
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while retreiving the categories");
		}
	      LOGGER.info("findAllOrganizations : End");
	        return categoryDTOList;
	}
	 
	@Override
	@Transactional(readOnly = true)
	public List<OsiCategoryDTO> findCategories(OsiCategoryDTO osiCategoryDTO) throws BusinessException {
		 List<OsiCategoryDTO> osiCategoryDTOList = null;
			List<OsiCategories> domainList = null;
			
			String categoryName=null;
			String tableName =null;
			Integer orgId =null;
			
			 LOGGER.info("findCategories : Begin");
			if(osiCategoryDTO.getCatName()==null || osiCategoryDTO.getCatName()=="")
			{
				
			}
			else
			{
				categoryName=osiCategoryDTO.getCatName();
			}
			if(osiCategoryDTO.getTblName()==null || osiCategoryDTO.getTblName()=="")
			{
				
			}
			else
			{
				tableName=osiCategoryDTO.getTblName();
			}
			if(osiCategoryDTO.getOrgId()!=null)
			{
				 orgId=osiCategoryDTO.getOrgId();
			}
					
			
			try 
			{
				 domainList=new ArrayList<>();
				 domainList=osiCategoryRepositoryCustom.searchEntries(categoryName,tableName,orgId);			
				 osiCategoryDTOList=osiCategorysMapper.osiCategoryListToOsiCategoryDTOList(domainList);
			}catch(DataAccessException e){
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while getting the categories");
			}
			 LOGGER.info("findCategories : End");
			 return osiCategoryDTOList;
	}
	
	 @Override
		@Transactional(readOnly = true)
	public List<OsiTablesDTO> findColumnsByTableName(String tableName) throws BusinessException {
		 List<OsiTablesDTO> osiTablesDTOList = null;
			List<String> domainList = null;
			 LOGGER.info("findColumnsByTableName : Begin");
			try 
			{
				 domainList=new ArrayList<>();
				 domainList=osiCategoryRepositoryCustom.findColumnsByTableName(tableName);			
				osiTablesDTOList=osiCategorysMapper.tablecolumnListToTableColumnDTOList(domainList);
			}catch(DataAccessException e){
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch(Exception e){
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new BusinessException("ERR_1000", "Error occured while getting the columns by table name");
			}
			 LOGGER.info("findColumnsByTableName : End");
		return osiTablesDTOList;
	}
	
	
}
