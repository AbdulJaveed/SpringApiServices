package com.osi.ems.mapper.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiAttachments;
import com.osi.ems.mapper.OsiAttachmentsesMapper;
import com.osi.ems.service.dto.OsiAttachmentsDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;

@Component
public class OsiAttachmentsesMapperImpl implements OsiAttachmentsesMapper {

	@Autowired
	private AppConfig appConfig;
	
	@Value( "${image.rendering.path}" )
	private String imagePath;
	
	@Override
	public List<OsiAttachmentsDTO> osiAttachmentsToAttachmentsDTOList(List<OsiAttachments> osiAttachmentsList) throws BusinessException {
		List<OsiAttachmentsDTO> attachmentsDtoList = new ArrayList<OsiAttachmentsDTO>();
		if(osiAttachmentsList != null) {
			for(OsiAttachments attachment : osiAttachmentsList) {
				attachmentsDtoList.add(this.osiAttachmentsToAttachmentsDTO(attachment));
			}
		}
		return attachmentsDtoList;
	}

	@Override
	public OsiAttachmentsDTO osiAttachmentsToAttachmentsDTO(OsiAttachments osiAttachments) throws BusinessException {
		OsiAttachmentsDTO osiAttachmentsDTO = null;
		if(osiAttachments != null) {
			osiAttachmentsDTO = new OsiAttachmentsDTO();
			osiAttachmentsDTO.setAttachmentId(osiAttachments.getAttachmentId());
			osiAttachmentsDTO.setAttachmentType(osiAttachments.getAttachmentType());
			osiAttachmentsDTO.setEmployeeId(osiAttachments.getEmployeeId());
			osiAttachmentsDTO.setDuplicateFileName(osiAttachments.getDuplicateFileName());
			osiAttachmentsDTO.setFileType(osiAttachments.getFileType());
			osiAttachmentsDTO.setOriginalFileName(osiAttachments.getOriginalFileName());
			osiAttachmentsDTO.setObjectId(osiAttachments.getObjectId());
			osiAttachmentsDTO.setObjectType(osiAttachments.getObjectType());
			osiAttachmentsDTO.setCreatedBy(osiAttachments.getCreatedBy());
			osiAttachmentsDTO.setCreationDate(osiAttachments.getCreationDate());
			osiAttachmentsDTO.setLastUpdatedBy(osiAttachments.getLastUpdatedBy());
			osiAttachmentsDTO.setLastUpdateDate(osiAttachments.getLastUpdateDate());
			
		//	String filePath =appConfig.getImagePath()+File.separator+osiAttachments.getAttachmentType()+File.separator+osiAttachments.getDuplicateFileName();
			osiAttachmentsDTO.setFileContent(imagePath+File.separator+osiAttachments.getAttachmentType()+File.separator+osiAttachments.getDuplicateFileName());
		}
		return osiAttachmentsDTO;
	}

}
