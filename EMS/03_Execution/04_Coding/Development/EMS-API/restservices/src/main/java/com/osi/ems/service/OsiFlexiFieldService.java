package com.osi.ems.service;

import java.util.List;

import com.osi.ems.domain.OsiCategories;
import com.osi.ems.service.dto.OsiFlexiFieldDTO;
import com.osi.urm.exception.BusinessException;

public interface OsiFlexiFieldService {
	
	public List<OsiFlexiFieldDTO> getFlexiFields(String categoryName, int orgId) throws BusinessException;

	OsiCategories getFlexiFieldsName(String functionUrl, int orgId) throws BusinessException;
}
