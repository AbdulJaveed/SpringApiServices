package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCertifications;
import com.osi.ems.service.dto.CrudOsiCertificationsVO;

@Component
public class CrudOsiCertificationsAssembler {

	public OsiCertifications toOsiCertificationsEntity(CrudOsiCertificationsVO crudOsiCertificationsVO) {;
		OsiCertifications osicertificationsEntity = new OsiCertifications();
		osicertificationsEntity.setCertificationId(crudOsiCertificationsVO.getCertificationId());
		osicertificationsEntity.setCertificationName(crudOsiCertificationsVO.getCertificationName());
		osicertificationsEntity.setCertificationCode(crudOsiCertificationsVO.getCertificationCode());
		osicertificationsEntity.setCertificationAddInfo(crudOsiCertificationsVO.getCertificationAddInfo());
		osicertificationsEntity.setIssuedBy(crudOsiCertificationsVO.getIssuedBy());
		osicertificationsEntity.setActive(crudOsiCertificationsVO.getActive());
		osicertificationsEntity.setCreatedBy(crudOsiCertificationsVO.getCreatedBy());
		osicertificationsEntity.setCreatedDate(crudOsiCertificationsVO.getCreatedDate());
		osicertificationsEntity.setUpdatedBy(crudOsiCertificationsVO.getUpdatedBy());
		osicertificationsEntity.setLastUpdateDate(crudOsiCertificationsVO.getLastUpdateDate());
		osicertificationsEntity.setOsiCertificationGroupId(crudOsiCertificationsVO.getOsiCertificationGroupId());
		osicertificationsEntity.setOsiCertificationTags(crudOsiCertificationsVO.getOsiCertificationTags());
		return osicertificationsEntity;
	}

	public CrudOsiCertificationsVO toCrudOsiCertificationsVO(OsiCertifications osicertificationsEntity) {;
		CrudOsiCertificationsVO crudOsiCertificationsVO = new CrudOsiCertificationsVO();
		crudOsiCertificationsVO.setCertificationId(osicertificationsEntity.getCertificationId());
		crudOsiCertificationsVO.setCertificationName(osicertificationsEntity.getCertificationName());
		crudOsiCertificationsVO.setCertificationCode(osicertificationsEntity.getCertificationCode());
		crudOsiCertificationsVO.setCertificationAddInfo(osicertificationsEntity.getCertificationAddInfo());
		crudOsiCertificationsVO.setIssuedBy(osicertificationsEntity.getIssuedBy());
		crudOsiCertificationsVO.setActive(osicertificationsEntity.getActive());
		crudOsiCertificationsVO.setCreatedBy(osicertificationsEntity.getCreatedBy());
		crudOsiCertificationsVO.setCreatedDate(osicertificationsEntity.getCreatedDate());
		crudOsiCertificationsVO.setUpdatedBy(osicertificationsEntity.getUpdatedBy());
		crudOsiCertificationsVO.setLastUpdateDate(osicertificationsEntity.getLastUpdateDate());
		crudOsiCertificationsVO.setOsiCertificationGroupId(osicertificationsEntity.getOsiCertificationGroupId());
		crudOsiCertificationsVO.setOsiCertificationTags(osicertificationsEntity.getOsiCertificationTags());
		
		return crudOsiCertificationsVO;
	}

	public List<OsiCertifications> toOsiCertificationsEntityList(List<CrudOsiCertificationsVO> crudOsiCertificationsVOList){
		List<OsiCertifications> osicertificationsEntityList = new ArrayList<OsiCertifications>();;
		for (CrudOsiCertificationsVO crudOsiCertificationsVO : crudOsiCertificationsVOList) {;
			osicertificationsEntityList.add(toOsiCertificationsEntity(crudOsiCertificationsVO));;
			}
		return osicertificationsEntityList;
	}

	public List<CrudOsiCertificationsVO> toCrudOsiCertificationsVOList(List<OsiCertifications> osicertificationsEntityList){
		List<CrudOsiCertificationsVO> crudOsiCertificationsVOList = new ArrayList<CrudOsiCertificationsVO>();;
		for (OsiCertifications osicertificationsEntity : osicertificationsEntityList) {;
			crudOsiCertificationsVOList.add(toCrudOsiCertificationsVO(osicertificationsEntity));;
			}
		return crudOsiCertificationsVOList;
	}

}