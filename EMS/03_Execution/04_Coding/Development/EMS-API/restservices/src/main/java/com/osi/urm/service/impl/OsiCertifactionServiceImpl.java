package com.osi.urm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.osi.ems.common.CommonService;
import com.osi.ems.domain.OsiAttachments;
import com.osi.ems.domain.OsiCertifications;
import com.osi.ems.mapper.OsiAttachmentsesMapper;
import com.osi.ems.mapper.OsiEmployeesMapper;
import com.osi.ems.mapper.impl.ListOsiCertificationsAssembler;
import com.osi.ems.repository.ICrudOsiCertificationsRepository;
import com.osi.ems.repository.OsiAttachmentsesRepository;
import com.osi.ems.service.dto.OsiAttachmentsDTO;
import com.osi.urm.domain.OsiCertificationDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiCerticationRepositryCustom;
import com.osi.urm.service.OsiCertifactionService;

public class OsiCertifactionServiceImpl implements OsiCertifactionService {

	@Autowired
	private OsiCerticationRepositryCustom osiCerticationRepositryCustom;

	@Autowired
	private CommonService commonService;

	@Autowired
	private OsiEmployeesMapper osiEmployeesMapper;

	@Autowired
	private ListOsiCertificationsAssembler assembler;

	@Autowired
	private ICrudOsiCertificationsRepository certificationsRepository;

	@Autowired
	private OsiAttachmentsesRepository osiAttachmentsesRepository;

	@Autowired
	private OsiAttachmentsesMapper osiAttachmentsMapper;

	@Override
	public List<OsiCertificationDetails> getCertificationDetails() {
		List<OsiCertificationDetails> osiCertificationList = null;
		List<OsiCertifications> certificationsList = null;
		certificationsList = this.certificationsRepository.findByActive(1);
		osiCertificationList = this.assembler.osiCertificationsListToOsiCertificationDetailsList(certificationsList);
		
		return osiCertificationList;
	}

	@Override
	public List<OsiCertificationDetails> searchCertificationDetails(OsiCertificationDetails osiCertificationDetails)
			throws BusinessException {

		List<OsiCertificationDetails> empCertificationsList = osiCerticationRepositryCustom
				.searchCertificationDetails(osiCertificationDetails);
		for (OsiCertificationDetails certificate : empCertificationsList) {
			List<OsiAttachments> osiAttachmentsList = new ArrayList<OsiAttachments>();
			if (certificate.getAttachmentId() != null) {
				OsiAttachments osiAttachments = osiAttachmentsesRepository.findOne(certificate.getAttachmentId());
				osiAttachments.setAttachmentType("CERTIFICATIONS");
				osiAttachmentsList.add(osiAttachments);
			}
			List<OsiAttachmentsDTO> osiAttachmentsDtoList = osiAttachmentsMapper
					.osiAttachmentsToAttachmentsDTOList(osiAttachmentsList);
			certificate.setOsiEmpAttachments(osiAttachmentsDtoList);
		}
		return empCertificationsList;
	}

	@Override
	public int saveCertifications(OsiCertificationDetails osiCertificationDetails, Integer orgId)
			throws BusinessException {
		// TODO Auto-generated method stub
		boolean result;
		try {
			osiCertificationDetails.setLastUpdatedDate(commonService.getCurrentDateStringinUTC());
			osiCertificationDetails.setCreatedDate(commonService.getCurrentDateStringinUTC());
			/*
			 * if(osiCertificationDetails.getEmpCertificationId() != null &&
			 * !"".equalsIgnoreCase(osiCertificationDetails.getEmpCertificationId())){
			 * result =false; }else{ result
			 * =osiCerticationRepositryCustom.findByCetificationIdAndEmployeeId(
			 * osiCertificationDetails.getCertificationId(),
			 * osiCertificationDetails.getEmployeeId()); }
			 * 
			 * if( result == false) {
			 */
			List<OsiAttachmentsDTO> certificationAttachmentsDto = osiCertificationDetails.getOsiEmpAttachments();
			OsiAttachments osiAttachment = null;
			if (certificationAttachmentsDto != null && !certificationAttachmentsDto.isEmpty()) {
				for (OsiAttachmentsDTO attachment : certificationAttachmentsDto) {
					if (osiCertificationDetails.getEmployeeId() != null) {
						attachment.setEmployeeId(osiCertificationDetails.getEmployeeId());
					} else {
						attachment.setCreatedBy(osiCertificationDetails.getCreatedBy());
						attachment.setCreationDate(commonService.getCurrentDateinUTC());
					}
					attachment.setLastUpdatedBy(osiCertificationDetails.getCreatedBy());
					attachment.setLastUpdateDate(commonService.getCurrentDateinUTC());
					attachment.setAttachmentType("CERTIFICATIONS"); // TODO: Floder name
					attachment.setObjectType("osi_emp_certifications");// TODO: Table name
					attachment.setObjectId(osiCertificationDetails.getEmployeeId());
					osiAttachment = osiEmployeesMapper.mapToAttachments(attachment);
				}
			}
			if (osiAttachment != null) {
				osiCertificationDetails.setAttachmentId(osiAttachment.getAttachmentId());
			}
			return osiCerticationRepositryCustom.saveCertifications(osiCertificationDetails, orgId);

			/*
			 * } else throw new BusinessException("ERR_1000",
			 * "Entered certification is alreay exist");
			 */
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("ERR_1000", "Unable to save/update requested certification");
		} catch (DuplicateKeyException e) {
			throw new BusinessException("ERR_1000", "Entered certification is alreay exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException("ERR_1000", "Unable to save/update requested certification");
		}


	}

	@Override
	public List<OsiCertificationDetails> getCertificationDetailsById(int certificateId) {

		return osiCerticationRepositryCustom.getCertificationDetailsById(certificateId);
	}

	@Override
	public void updateVerifiedSkills(List<OsiCertificationDetails> certDetailsList, Integer updatedBy)
			throws BusinessException {
		try {
			for (OsiCertificationDetails certificate : certDetailsList) {
				certificate.setUpdatedBy(updatedBy);
				certificate.setLastUpdatedDate(commonService.getCurrentDateStringinUTC());
				osiCerticationRepositryCustom.updateVerifiedSkills(certificate);
			}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}
	}

}
