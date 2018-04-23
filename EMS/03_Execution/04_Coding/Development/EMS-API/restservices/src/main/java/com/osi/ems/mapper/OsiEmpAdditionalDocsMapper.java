package com.osi.ems.mapper;

import java.util.List;

import com.osi.ems.domain.OsiEmpAdditionalDocs;
import com.osi.ems.service.dto.OsiEmpAdditionalDocsDTO;
import com.osi.urm.exception.BusinessException;

public interface OsiEmpAdditionalDocsMapper {

	OsiEmpAdditionalDocs toOsiEmpAdditionalDocs(OsiEmpAdditionalDocsDTO empAdditionalDocsDTO) throws BusinessException;

	OsiEmpAdditionalDocsDTO toOsiEmpAdditionalDocsDTO(OsiEmpAdditionalDocs empAdditionalDocs) throws BusinessException;

	List<OsiEmpAdditionalDocs> toOsiEmpAdditionalDocsList(List<OsiEmpAdditionalDocsDTO> empAdditionalDocsDTOList)
			throws BusinessException;

	List<OsiEmpAdditionalDocsDTO> toOsiEmpAdditionalDocsDTOList(List<OsiEmpAdditionalDocs> empAdditionalDocsList)
			throws BusinessException;

}
