package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiAttachments;
import com.osi.ems.domain.OsiEmpAdditionalDocs;
import com.osi.ems.mapper.OsiAttachmentsesMapper;
import com.osi.ems.mapper.OsiEmpAdditionalDocsMapper;
import com.osi.ems.mapper.OsiEmployeesMapper;
import com.osi.ems.repository.OsiAttachmentsesRepository;
import com.osi.ems.service.dto.OsiAttachmentsDTO;
import com.osi.ems.service.dto.OsiEmpAdditionalDocsDTO;
import com.osi.urm.exception.BusinessException;

@Component
@Transactional
public class OsiEmpAdditionalDocsMapperImpl implements OsiEmpAdditionalDocsMapper {
	
	@Autowired
	private OsiAttachmentsesRepository osiAttachmentsesRepository;
	
	@Autowired
	private OsiAttachmentsesMapper osiAttachmentsMapper;
	
	@Autowired
	private OsiEmployeesMapper osiEmployeesMapper;
	
	@Override
	public OsiEmpAdditionalDocs toOsiEmpAdditionalDocs(OsiEmpAdditionalDocsDTO empAdditionalDocsDTO) throws BusinessException  {
		OsiEmpAdditionalDocs osiEmpAdditionalDocs = new OsiEmpAdditionalDocs();
		
		osiEmpAdditionalDocs.setDescription(empAdditionalDocsDTO.getDescription());
		osiEmpAdditionalDocs.setDocId(empAdditionalDocsDTO.getDocId());
		osiEmpAdditionalDocs.setEmployeeId(empAdditionalDocsDTO.getEmployeeId());
		if(null != empAdditionalDocsDTO.getAttachments()) {
			OsiAttachmentsDTO attachementsDto = empAdditionalDocsDTO.getAttachments();
			OsiAttachments osiAttachment = null;
			if(null != attachementsDto) {
					if( empAdditionalDocsDTO.getEmployeeId() != null) {
						attachementsDto.setEmployeeId(empAdditionalDocsDTO.getEmployeeId());
					}
					attachementsDto.setAttachmentType("ADDITIONAL DOCUMENTS"); // TODO: Floder name
					attachementsDto.setObjectType("osi_employees");// TODO: Table name
					attachementsDto.setObjectId(attachementsDto.getEmployeeId());
					attachementsDto.setDuplicateFileName(attachementsDto.getEmployeeId() +"_"+ empAdditionalDocsDTO.getDescription());
					osiAttachment = osiEmployeesMapper.mapToAttachments(attachementsDto);
			}
			if(osiAttachment != null) 
				osiEmpAdditionalDocs.setAttachmentId(osiAttachment.getAttachmentId());
		}
		//osiEmpAdditionalDocs.setAttachmentId(empAdditionalDocsDTO.getAttachmentId());
		return osiEmpAdditionalDocs;
	}
	@Override
	public OsiEmpAdditionalDocsDTO toOsiEmpAdditionalDocsDTO(OsiEmpAdditionalDocs empAdditionalDocs) throws BusinessException {
		
		OsiEmpAdditionalDocsDTO osiEmpAdditionalDocsDTO = new OsiEmpAdditionalDocsDTO();
		osiEmpAdditionalDocsDTO.setAttachmentId(empAdditionalDocs.getAttachmentId());
		if(null != empAdditionalDocs.getAttachmentId()) {
			OsiAttachments osiAttachments = osiAttachmentsesRepository.findOne(empAdditionalDocs.getAttachmentId());
			OsiAttachmentsDTO osiAttachmentsDto = osiAttachmentsMapper.osiAttachmentsToAttachmentsDTO(osiAttachments);
			osiEmpAdditionalDocsDTO.setAttachments(osiAttachmentsDto);
		}
		osiEmpAdditionalDocsDTO.setDescription(empAdditionalDocs.getDescription());
		osiEmpAdditionalDocsDTO.setEmployeeId(empAdditionalDocs.getEmployeeId());
		osiEmpAdditionalDocsDTO.setDocId(empAdditionalDocs.getDocId());
		return osiEmpAdditionalDocsDTO;
	}
	
	@Override
	public List<OsiEmpAdditionalDocs> toOsiEmpAdditionalDocsList(List<OsiEmpAdditionalDocsDTO> empAdditionalDocsDTOList)  throws BusinessException {
		List<OsiEmpAdditionalDocs> osiEmpAdditionalDocsList = new ArrayList<OsiEmpAdditionalDocs>();
		for (OsiEmpAdditionalDocsDTO empAdditionalDocsDTO : empAdditionalDocsDTOList) {;
			osiEmpAdditionalDocsList.add(toOsiEmpAdditionalDocs(empAdditionalDocsDTO));;
		}
		return osiEmpAdditionalDocsList;
	}
	
	@Override
	public List<OsiEmpAdditionalDocsDTO> toOsiEmpAdditionalDocsDTOList(List<OsiEmpAdditionalDocs> empAdditionalDocsList)  throws BusinessException {
		List<OsiEmpAdditionalDocsDTO> osiEmpAdditionalDocsDTOList = new ArrayList<OsiEmpAdditionalDocsDTO>();
		for (OsiEmpAdditionalDocs osiEmpAdditionalDocs : empAdditionalDocsList) {;
		osiEmpAdditionalDocsDTOList.add(toOsiEmpAdditionalDocsDTO(osiEmpAdditionalDocs));;
			}
		return osiEmpAdditionalDocsDTOList;
	}
	
}
