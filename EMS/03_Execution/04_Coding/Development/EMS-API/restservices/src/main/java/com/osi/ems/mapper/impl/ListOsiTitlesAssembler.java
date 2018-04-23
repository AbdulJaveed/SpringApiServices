package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiTitles;
import com.osi.ems.service.dto.ListOsiTitlesVO;

@Component
public class ListOsiTitlesAssembler {

	public OsiTitles toOsiTitlesEntity(ListOsiTitlesVO listOsiTitlesVO) {
		OsiTitles osititlesEntity = new OsiTitles();
		osititlesEntity.setTitleId(listOsiTitlesVO.getTitleId());
		osititlesEntity.setTitleCode(listOsiTitlesVO.getTitleCode());
		osititlesEntity.setTitleShortName(listOsiTitlesVO.getTitleShortName());
		osititlesEntity.setTitleLongName(listOsiTitlesVO.getTitleLongName());
		return osititlesEntity;
	}

	public ListOsiTitlesVO toListOsiTitlesVO(OsiTitles osititlesEntity) {;
		ListOsiTitlesVO listOsiTitlesVO = new ListOsiTitlesVO();
		listOsiTitlesVO.setTitleId(osititlesEntity.getTitleId());
		listOsiTitlesVO.setTitleCode(osititlesEntity.getTitleCode());
		listOsiTitlesVO.setTitleShortName(osititlesEntity.getTitleShortName());
		listOsiTitlesVO.setTitleLongName(osititlesEntity.getTitleLongName());
		return listOsiTitlesVO;
	}

	public List<OsiTitles> toOsiTitlesEntityList(List<ListOsiTitlesVO> listOsiTitlesVOList){
		List<OsiTitles> osititlesEntityList = new ArrayList<OsiTitles>();
		for (ListOsiTitlesVO listOsiTitlesVO : listOsiTitlesVOList) {
			osititlesEntityList.add(toOsiTitlesEntity(listOsiTitlesVO));
			}
		return osititlesEntityList;
	}

	public List<ListOsiTitlesVO> toListOsiTitlesVOList(List<OsiTitles> osititlesEntityList){
		List<ListOsiTitlesVO> listOsiTitlesVOList = new ArrayList<ListOsiTitlesVO>();
		for (OsiTitles osititlesEntity : osititlesEntityList) {
			listOsiTitlesVOList.add(toListOsiTitlesVO(osititlesEntity));
			}
		return listOsiTitlesVOList;
	}

}