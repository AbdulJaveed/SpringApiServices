package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.domain.OsiCategories;
import com.osi.ems.mapper.OsiFlexiFieldMapper;
import com.osi.ems.repository.custom.OsiCategoriesRepositoryCustom;
import com.osi.ems.service.OsiFlexiFieldService;
import com.osi.ems.service.dto.OsiFlexiFieldDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


@Service
@Transactional
public class OsiFlexiFieldServiceImpl implements OsiFlexiFieldService  {

	@Autowired
	private OsiCategoriesRepositoryCustom osiCategoriesRepositoryCustom;
	
	@Autowired
	private OsiFlexiFieldMapper mapper;
	
	private final Logger log = LoggerFactory.getLogger(OsiFlexiFieldServiceImpl.class);

	@Override
	public List<OsiFlexiFieldDTO> getFlexiFields(String categoryName, int orgId) throws BusinessException {
		List<OsiFlexiFieldDTO> flexiFieldList = new ArrayList<OsiFlexiFieldDTO>();
		log.info("getFlexiFields : Begin");
		
		List<OsiCategories> categoryList;
		try {
			categoryList = osiCategoriesRepositoryCustom.getCategoryFlexFields(categoryName,orgId);
			flexiFieldList = mapper.toFlexiFieldsVOList(categoryList);
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the flex fields");
		}
		log.info("getFlexiFields : End");
		
		return flexiFieldList;
	}
	
	@Override
	public OsiCategories getFlexiFieldsName(String functionUrl, int orgId) throws BusinessException {
		
		OsiCategories category = null;
		log.info("getFlexiFieldsName : Begin");
		try {
			
			category = osiCategoriesRepositoryCustom.findCategoryNameByFunctionIdAndOrg(functionUrl, orgId);
			
		} catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while getting the flex fields name");
		}
		
		log.info("getFlexiFieldsName : End");
		return category;
	}

}
