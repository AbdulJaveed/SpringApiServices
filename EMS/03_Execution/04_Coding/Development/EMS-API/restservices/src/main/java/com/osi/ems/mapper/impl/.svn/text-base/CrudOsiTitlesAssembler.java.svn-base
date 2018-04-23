package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiTitles;
import com.osi.ems.service.dto.CrudOsiTitlesVO;

@Component
public class CrudOsiTitlesAssembler {

	public OsiTitles toOsiTitlesEntity(CrudOsiTitlesVO crudOsiTitlesVO) {
		OsiTitles osititlesEntity = new OsiTitles();
		osititlesEntity.setTitleId(crudOsiTitlesVO.getTitleId());
		osititlesEntity.setTitleShortName(crudOsiTitlesVO.getTitleShortName());
		osititlesEntity.setTitleLongName(crudOsiTitlesVO.getTitleLongName());
		osititlesEntity.setTitleCode(crudOsiTitlesVO.getTitleCode());


		return osititlesEntity;
	}

	public CrudOsiTitlesVO toCrudOsiTitlesVO(OsiTitles osititlesEntity) {
		CrudOsiTitlesVO crudOsiTitlesVO = new CrudOsiTitlesVO();
		crudOsiTitlesVO.setTitleId(osititlesEntity.getTitleId());
		crudOsiTitlesVO.setTitleShortName(osititlesEntity.getTitleShortName());
		crudOsiTitlesVO.setTitleLongName(osititlesEntity.getTitleLongName());
		crudOsiTitlesVO.setTitleCode(osititlesEntity.getTitleCode());
		

		return crudOsiTitlesVO;
	}

	public List<OsiTitles> toOsiTitlesEntityList(List<CrudOsiTitlesVO> crudOsiTitlesVOList){
		List<OsiTitles> osititlesEntityList = new ArrayList<OsiTitles>();
		for (CrudOsiTitlesVO crudOsiTitlesVO : crudOsiTitlesVOList) {
			osititlesEntityList.add(toOsiTitlesEntity(crudOsiTitlesVO));
			}
		return osititlesEntityList;
	}

	public List<CrudOsiTitlesVO> toCrudOsiTitlesVOList(List<OsiTitles> osititlesEntityList){
		List<CrudOsiTitlesVO> crudOsiTitlesVOList = new ArrayList<CrudOsiTitlesVO>();
		for (OsiTitles osititlesEntity : osititlesEntityList) {
			crudOsiTitlesVOList.add(toCrudOsiTitlesVO(osititlesEntity));
			}
		return crudOsiTitlesVOList;
	}

}