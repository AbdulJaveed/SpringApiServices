package com.osi.urm.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiAttachments;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.mapper.OsiAttachmentsMapper;
import com.osi.urm.repository.OsiAttachmentsRepository;
import com.osi.urm.repository.OsiUserRepository;
import com.osi.urm.service.OsiAttachmentsService;
import com.osi.urm.service.OsiUserService;
import com.osi.urm.service.dto.OsiAttachmentsDTO;
import com.osi.urm.service.dto.OsiUserDTO;

@Service
@Transactional
public class OsiAttachmentsServiceImpl implements OsiAttachmentsService {

		
	@Value("${spring.imagePath}")
	String imageDirectory;
	
	@Autowired
	private OsiAttachmentsRepository osiAttachmentsRepository;
	
	@Autowired
	private OsiUserRepository osiUserRepository;

	@Autowired
	private OsiUserService osiUserService;

	@Autowired
	OsiAttachmentsMapper osiAttachmentsMapper;

	
	@Autowired
	private AppConfig appConfig;
	
	public OsiAttachmentsDTO save(MultipartFile uploadfile, Integer userId, Integer businessGroupId, Integer id)
			throws BusinessException {
		Date date = new Date();
		
		File prDir = new File(appConfig.getImagePath()+File.separator+"USERS");
		if(!prDir.isDirectory()){
			prDir.mkdir();
		}
		
		String filename = id + date.getTime() + ".jpg";
		String attachmentNameWithUploadDirectory=appConfig.getImagePath()+File.separator+"USERS"+File.separator+Paths.get(filename).toString();
		
		
		BufferedOutputStream stream;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(new File(attachmentNameWithUploadDirectory)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		OsiUserDTO osiUserDTO = osiUserService.findOne(id,businessGroupId);
		List<OsiAttachments> attachments = null;
		
		try {
			attachments = osiUserRepository.getUserAttachments(id, businessGroupId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		OsiUser osiUser = new OsiUser();
		//osiUser.setUserId(osiUserDTO.getId());
		OsiAttachments osiAttachment = new OsiAttachments();
		osiAttachment.setAttachment(attachmentNameWithUploadDirectory);
		osiAttachment.setBusinessGroupId(businessGroupId);
		osiAttachment.setOsiUser(osiUser);
		if (attachments != null && !attachments.isEmpty()) {
			osiAttachment.setId(attachments.get(0).getId());
			osiAttachment.setUpdatedBy(id);
			osiAttachment.setUpdatedDate(date);
			osiAttachment.setCreatedBy(attachments.get(0).getCreatedBy());
			osiAttachment.setCreatedDate(attachments.get(0).getCreatedDate());
		}else{
			osiAttachment.setCreatedBy(userId);
			osiAttachment.setUpdatedDate(date);
			osiAttachment.setCreatedDate(date);
		}
		osiAttachment=osiAttachmentsRepository.save(osiAttachment);
		OsiAttachmentsDTO OsiAttachmentsDTO=osiAttachmentsMapper.osiUserToOsiUserDTO(osiAttachment);
		return OsiAttachmentsDTO;
	}
}