package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCertifications;
import com.osi.ems.mapper.OsiCertificationTagsMapper;
import com.osi.ems.service.dto.ListOsiCertificationsVO;
import com.osi.ems.service.dto.OsiCertificationTagsDto;
import com.osi.urm.domain.OsiCertificationDetails;

@Component
public class ListOsiCertificationsAssembler {

	@Autowired
	private OsiCertificationTagsMapper tagsMapper;

	public OsiCertifications toOsiCertificationsEntity(ListOsiCertificationsVO listOsiCertificationsVO) {
		OsiCertifications osicertificationsEntity = new OsiCertifications();
		osicertificationsEntity.setCertificationId(listOsiCertificationsVO.getCertificationId());
		osicertificationsEntity.setCertificationName(listOsiCertificationsVO.getCertificationName());
		osicertificationsEntity.setCertificationCode(listOsiCertificationsVO.getCertificationCode());
		osicertificationsEntity.setIssuedBy(listOsiCertificationsVO.getIssuedBy());
		osicertificationsEntity.setActive(listOsiCertificationsVO.getActive());
		osicertificationsEntity.setCreatedBy(listOsiCertificationsVO.getCreatedBy());
		osicertificationsEntity.setCreatedDate(listOsiCertificationsVO.getCreatedDate());
		osicertificationsEntity.setUpdatedBy(listOsiCertificationsVO.getUpdatedBy());
		osicertificationsEntity.setLastUpdateDate(listOsiCertificationsVO.getLastUpdateDate());
		osicertificationsEntity.setOsiCertificationGroupId(listOsiCertificationsVO.getOsiCertificationGroupId());
		osicertificationsEntity.setOsiCertificationTags(listOsiCertificationsVO.getOsiCertificationTags());
		return osicertificationsEntity;
	}

	public ListOsiCertificationsVO toListOsiCertificationsVO(OsiCertifications osicertificationsEntity) {
		;
		ListOsiCertificationsVO listOsiCertificationsVO = new ListOsiCertificationsVO();
		listOsiCertificationsVO.setCertificationId(osicertificationsEntity.getCertificationId());
		listOsiCertificationsVO.setCertificationName(osicertificationsEntity.getCertificationName());
		listOsiCertificationsVO.setCertificationCode(osicertificationsEntity.getCertificationCode());
		listOsiCertificationsVO.setIssuedBy(osicertificationsEntity.getIssuedBy());
		listOsiCertificationsVO.setActive(osicertificationsEntity.getActive());
		listOsiCertificationsVO.setCreatedBy(osicertificationsEntity.getCreatedBy());
		listOsiCertificationsVO.setCreatedDate(osicertificationsEntity.getCreatedDate());
		listOsiCertificationsVO.setUpdatedBy(osicertificationsEntity.getUpdatedBy());
		listOsiCertificationsVO.setLastUpdateDate(osicertificationsEntity.getLastUpdateDate());
		listOsiCertificationsVO.setOsiCertificationGroupId(osicertificationsEntity.getOsiCertificationGroupId());
		listOsiCertificationsVO.setOsiCertificationTags(osicertificationsEntity.getOsiCertificationTags());

		return listOsiCertificationsVO;
	}

	public List<OsiCertifications> toOsiCertificationsEntityList(
			List<ListOsiCertificationsVO> listOsiCertificationsVOList) {
		List<OsiCertifications> osicertificationsEntityList = new ArrayList<OsiCertifications>();
		for (ListOsiCertificationsVO listOsiCertificationsVO : listOsiCertificationsVOList) {
			osicertificationsEntityList.add(toOsiCertificationsEntity(listOsiCertificationsVO));
		}
		return osicertificationsEntityList;
	}

	public List<ListOsiCertificationsVO> toListOsiCertificationsVOList(
			List<OsiCertifications> osicertificationsEntityList) {
		List<ListOsiCertificationsVO> listOsiCertificationsVOList = new ArrayList<ListOsiCertificationsVO>();
		for (OsiCertifications osicertificationsEntity : osicertificationsEntityList) {
			listOsiCertificationsVOList.add(toListOsiCertificationsVO(osicertificationsEntity));
		}
		return listOsiCertificationsVOList;
	}

	public OsiCertificationDetails osiCertificationsToOsiCertificationDetails(OsiCertifications osiCertifications) {
		OsiCertificationDetails certificationDetails = new OsiCertificationDetails();
		certificationDetails.setCertificationId(String.valueOf(osiCertifications.getCertificationId()));
		certificationDetails.setCertificationName(osiCertifications.getCertificationName());
		certificationDetails.setCertificationCode(osiCertifications.getCertificationCode());
		certificationDetails.setCertificationAddInfo(osiCertifications.getCertificationAddInfo());
		certificationDetails.setIssuedBy(osiCertifications.getIssuedBy());
		boolean active = osiCertifications.getActive() == 1 ? true : false;
		certificationDetails.setActive(active);
		certificationDetails.setCreatedBy(osiCertifications.getCreatedBy());
		certificationDetails.setCreatedDate(osiCertifications.getCreatedDate().toString());
		certificationDetails.setUpdatedBy(osiCertifications.getUpdatedBy());
		certificationDetails.setLastUpdatedDate(osiCertifications.getLastUpdateDate().toString());
		if (osiCertifications.getOsiCertificationGroupId() != null) {
			certificationDetails.setGroupId(String.valueOf(osiCertifications.getOsiCertificationGroupId()));
			certificationDetails.setGroupName(osiCertifications.getCertificationGroup().getGroupName());
		}
		if (osiCertifications.getOsiCertificationTags() != null) {
			Set<OsiCertificationTagsDto> tagsDto = tagsMapper
					.osiCertificationTagsListToOsiCertificationTagsDtoSet(osiCertifications.getOsiCertificationTags());
			certificationDetails.setOsiCertificationTagsList(tagsDto);
		}

		return certificationDetails;
	}

	public List<OsiCertificationDetails> osiCertificationsListToOsiCertificationDetailsList(
			List<OsiCertifications> osiCertificationsList) {
		List<OsiCertificationDetails> osiCertificationDetailsList = new ArrayList<>();
		for (OsiCertifications osicertificationsEntity : osiCertificationsList) {
			osiCertificationDetailsList.add(osiCertificationsToOsiCertificationDetails(osicertificationsEntity));
		}
		/*osiCertificationsList.forEach(osiCertification ->{
			OsiCertificationDetails details = this.osiCertificationsToOsiCertificationDetails(osiCertification);
			osiCertificationDetailsList.add(details);
		});*/
		
		return osiCertificationDetailsList;
	}

}