package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiSkils;
import com.osi.ems.service.dto.ListOsiSkilsVO;

@Component
public class ListOsiSkilsAssembler {

	public OsiSkils toOsiSkilsEntity(ListOsiSkilsVO listOsiSkilsVO) {
		OsiSkils osiskilsEntity = new OsiSkils();
		osiskilsEntity.setSkillId(listOsiSkilsVO.getSkillId());
		osiskilsEntity.setSkillName(listOsiSkilsVO.getSkillName());
		osiskilsEntity.setSkillDescription(listOsiSkilsVO.getSkillDescription());
		osiskilsEntity.setSkillDisplayName(listOsiSkilsVO.getSkillDisplayName());
		osiskilsEntity.setSkillVersion(listOsiSkilsVO.getSkillVersion());
		osiskilsEntity.setActive(listOsiSkilsVO.getActive());
		osiskilsEntity.setOsiSkillGroupId(listOsiSkilsVO.getOsiSkillGroupId());
		osiskilsEntity.setOsiSkillTags(listOsiSkilsVO.getOsiSkillTags());
		return osiskilsEntity;
	}

	public ListOsiSkilsVO toListOsiSkilsVO(OsiSkils osiskilsEntity) {;
		ListOsiSkilsVO listOsiSkilsVO = new ListOsiSkilsVO();
		listOsiSkilsVO.setSkillId(osiskilsEntity.getSkillId());
		listOsiSkilsVO.setSkillName(osiskilsEntity.getSkillName());
		listOsiSkilsVO.setSkillDescription(osiskilsEntity.getSkillDescription());
		listOsiSkilsVO.setSkillDisplayName(osiskilsEntity.getSkillDisplayName());
		listOsiSkilsVO.setSkillVersion(osiskilsEntity.getSkillVersion());
		listOsiSkilsVO.setActive(osiskilsEntity.getActive());
		listOsiSkilsVO.setOsiSkillGroupId(osiskilsEntity.getOsiSkillGroupId());
		listOsiSkilsVO.setOsiSkillTags(osiskilsEntity.getOsiSkillTags());
		return listOsiSkilsVO;
	}

	public List<OsiSkils> toOsiSkilsEntityList(List<ListOsiSkilsVO> listOsiSkilsVOList){
		List<OsiSkils> osiskilsEntityList = new ArrayList<OsiSkils>();
		for (ListOsiSkilsVO listOsiSkilsVO : listOsiSkilsVOList) {
			osiskilsEntityList.add(toOsiSkilsEntity(listOsiSkilsVO));
			}
		return osiskilsEntityList;
	}

	public List<ListOsiSkilsVO> toListOsiSkilsVOList(List<OsiSkils> osiskilsEntityList){
		List<ListOsiSkilsVO> listOsiSkilsVOList = new ArrayList<ListOsiSkilsVO>();
		for (OsiSkils osiskilsEntity : osiskilsEntityList) {
			listOsiSkilsVOList.add(toListOsiSkilsVO(osiskilsEntity));
			}
		return listOsiSkilsVOList;
	}

}