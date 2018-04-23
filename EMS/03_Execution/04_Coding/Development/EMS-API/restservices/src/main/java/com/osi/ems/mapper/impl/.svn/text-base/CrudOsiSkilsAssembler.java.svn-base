package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiSkils;
import com.osi.ems.service.dto.CrudOsiSkilsVO;
import com.osi.urm.domain.SkillDetails;

@Component
public class CrudOsiSkilsAssembler {

	public OsiSkils toOsiSkilsEntity(CrudOsiSkilsVO crudOsiSkilsVO) {
		;
		OsiSkils osiskilsEntity = new OsiSkils();
		osiskilsEntity.setSkillId(crudOsiSkilsVO.getSkillId());
		osiskilsEntity.setSkillName(crudOsiSkilsVO.getSkillName());
		osiskilsEntity.setSkillDisplayName(crudOsiSkilsVO.getSkillDisplayName());
		osiskilsEntity.setSkillDescription(crudOsiSkilsVO.getSkillDescription());
		osiskilsEntity.setSkillVersion(crudOsiSkilsVO.getSkillVersion());
		osiskilsEntity.setActive(crudOsiSkilsVO.getActive());
		osiskilsEntity.setCreatedBy(crudOsiSkilsVO.getCreatedBy());
		osiskilsEntity.setCreatedDate(crudOsiSkilsVO.getCreatedDate());
		osiskilsEntity.setUpdatedBy(crudOsiSkilsVO.getUpdatedBy());
		osiskilsEntity.setLastUpdateDate(crudOsiSkilsVO.getLastUpdateDate());
		osiskilsEntity.setOsiSkillGroupId(crudOsiSkilsVO.getOsiSkillGroupId());
		osiskilsEntity.setOsiSkillTags(crudOsiSkilsVO.getOsiSkillTags());

		return osiskilsEntity;
	}

	public CrudOsiSkilsVO toCrudOsiSkilsVO(OsiSkils osiskilsEntity) {
		CrudOsiSkilsVO crudOsiSkilsVO = new CrudOsiSkilsVO();
		crudOsiSkilsVO.setSkillId(osiskilsEntity.getSkillId());
		crudOsiSkilsVO.setSkillName(osiskilsEntity.getSkillName());
		crudOsiSkilsVO.setSkillDisplayName(osiskilsEntity.getSkillDisplayName());
		crudOsiSkilsVO.setSkillDescription(osiskilsEntity.getSkillDescription());
		crudOsiSkilsVO.setSkillVersion(osiskilsEntity.getSkillVersion());
		crudOsiSkilsVO.setActive(osiskilsEntity.getActive());
		crudOsiSkilsVO.setCreatedBy(osiskilsEntity.getCreatedBy());
		crudOsiSkilsVO.setCreatedDate(osiskilsEntity.getCreatedDate());
		crudOsiSkilsVO.setUpdatedBy(osiskilsEntity.getUpdatedBy());
		crudOsiSkilsVO.setLastUpdateDate(osiskilsEntity.getLastUpdateDate());
		crudOsiSkilsVO.setOsiSkillGroupId(osiskilsEntity.getOsiSkillGroupId());
		crudOsiSkilsVO.setOsiSkillTags(osiskilsEntity.getOsiSkillTags());

		return crudOsiSkilsVO;
	}

	public SkillDetails toSkillDetails(OsiSkils osiSkills) {
		SkillDetails skillDetails = new SkillDetails();
		if (osiSkills.getSkillId() != null) {
			skillDetails.setSkillId(String.valueOf(osiSkills.getSkillId()));
		}
		skillDetails.setSkillName(osiSkills.getSkillName());
		skillDetails.setSkillDisplayName(osiSkills.getSkillDisplayName());
		skillDetails.setSkillDescription(osiSkills.getSkillDescription());
		if (osiSkills.getOsiSkillGroupId() != null) {
			skillDetails.setSkillGroupId(String.valueOf(osiSkills.getOsiSkillGroupId()));
		}
		skillDetails.setOsiSkillTagsList(osiSkills.getOsiSkillTags());
		boolean active = osiSkills.getActive() == 1 ? true : false;
		skillDetails.setActive(active);
		if (osiSkills.getSkillVersion() != null) {
			skillDetails.setSkillVersion(String.valueOf(osiSkills.getSkillVersion()));
		}
		skillDetails.setGroupId(osiSkills.getOsiSkillGroupId());
		skillDetails.setOsiSkillGroups(osiSkills.getOsiSkillGroup());
		return skillDetails;
	}

	public List<SkillDetails> toOsiSkillDetailsList(List<OsiSkils> osiSkilsList) {
		List<SkillDetails> osiSkillDetilsList = new ArrayList<>();
		for (OsiSkils osiSkill : osiSkilsList) {
			osiSkillDetilsList.add(this.toSkillDetails(osiSkill));
		}
		return osiSkillDetilsList;
	}

	public List<OsiSkils> toOsiSkilsEntityList(List<CrudOsiSkilsVO> crudOsiSkilsVOList) {
		List<OsiSkils> osiskilsEntityList = new ArrayList<OsiSkils>();
		;
		for (CrudOsiSkilsVO crudOsiSkilsVO : crudOsiSkilsVOList) {
			;
			osiskilsEntityList.add(toOsiSkilsEntity(crudOsiSkilsVO));
			;
		}
		return osiskilsEntityList;
	}

	public List<CrudOsiSkilsVO> toCrudOsiSkilsVOList(List<OsiSkils> osiskilsEntityList) {
		List<CrudOsiSkilsVO> crudOsiSkilsVOList = new ArrayList<CrudOsiSkilsVO>();
		;
		for (OsiSkils osiskilsEntity : osiskilsEntityList) {
			;
			crudOsiSkilsVOList.add(toCrudOsiSkilsVO(osiskilsEntity));
			;
		}
		return crudOsiSkilsVOList;
	}

}