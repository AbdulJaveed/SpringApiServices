package com.osi.urm.service;

import org.springframework.web.multipart.MultipartFile;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiAttachmentsDTO;

public interface OsiAttachmentsService {
	OsiAttachmentsDTO save(MultipartFile uploadfile,Integer userId,Integer businessGroupId,Integer id) throws BusinessException;
}