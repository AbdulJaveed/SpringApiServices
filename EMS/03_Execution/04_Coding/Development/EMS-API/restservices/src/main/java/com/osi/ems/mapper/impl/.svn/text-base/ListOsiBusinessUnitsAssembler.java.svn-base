package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.ems.service.dto.ListOsiBusinessUnitsVO;

@Component
public class ListOsiBusinessUnitsAssembler {

	public OsiBusinessUnits toOsiBusinessUnitsEntity(ListOsiBusinessUnitsVO listOsiBusinessUnitsVO) {
		OsiBusinessUnits osibusinessunitsEntity = new OsiBusinessUnits();
		osibusinessunitsEntity.setBuId(listOsiBusinessUnitsVO.getBuId());
		osibusinessunitsEntity.setBuShortName(listOsiBusinessUnitsVO.getBuShortName());
		osibusinessunitsEntity.setBuLongName(listOsiBusinessUnitsVO.getBuLongName());
		osibusinessunitsEntity.setActive(listOsiBusinessUnitsVO.getActive());
		return osibusinessunitsEntity;
	}

	public ListOsiBusinessUnitsVO toListOsiBusinessUnitsVO(OsiBusinessUnits osibusinessunitsEntity) {;
		ListOsiBusinessUnitsVO listOsiBusinessUnitsVO = new ListOsiBusinessUnitsVO();
		listOsiBusinessUnitsVO.setBuId(osibusinessunitsEntity.getBuId());
		listOsiBusinessUnitsVO.setBuShortName(osibusinessunitsEntity.getBuShortName());
		listOsiBusinessUnitsVO.setBuLongName(osibusinessunitsEntity.getBuLongName());
		listOsiBusinessUnitsVO.setActive(osibusinessunitsEntity.getActive());
		return listOsiBusinessUnitsVO;
	}

	public List<OsiBusinessUnits> toOsiBusinessUnitsEntityList(List<ListOsiBusinessUnitsVO> listOsiBusinessUnitsVOList){
		List<OsiBusinessUnits> osibusinessunitsEntityList = new ArrayList<OsiBusinessUnits>();
		for (ListOsiBusinessUnitsVO listOsiBusinessUnitsVO : listOsiBusinessUnitsVOList) {
			osibusinessunitsEntityList.add(toOsiBusinessUnitsEntity(listOsiBusinessUnitsVO));
			}
		return osibusinessunitsEntityList;
	}

	public List<ListOsiBusinessUnitsVO> toListOsiBusinessUnitsVOList(List<OsiBusinessUnits> osibusinessunitsEntityList){
		List<ListOsiBusinessUnitsVO> listOsiBusinessUnitsVOList = new ArrayList<ListOsiBusinessUnitsVO>();
		for (OsiBusinessUnits osibusinessunitsEntity : osibusinessunitsEntityList) {
			listOsiBusinessUnitsVOList.add(toListOsiBusinessUnitsVO(osibusinessunitsEntity));
			}
		return listOsiBusinessUnitsVOList;
	}

}