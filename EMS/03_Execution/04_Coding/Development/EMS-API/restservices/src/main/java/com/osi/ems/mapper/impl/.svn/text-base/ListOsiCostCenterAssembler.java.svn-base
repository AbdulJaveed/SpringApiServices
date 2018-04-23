package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.service.dto.ListOsiCostCenterVO;

@Component
public class ListOsiCostCenterAssembler {

	public OsiCostCenter toOsiCostCenterEntity(ListOsiCostCenterVO listOsiCostCenterVO) {
		OsiCostCenter osicostcenterEntity = new OsiCostCenter();
		osicostcenterEntity.setCcId(listOsiCostCenterVO.getCcId());
		osicostcenterEntity.setCcShortName(listOsiCostCenterVO.getCcShortName());
		osicostcenterEntity.setCcLongName(listOsiCostCenterVO.getCcLongName());
		osicostcenterEntity.setActive(listOsiCostCenterVO.getActive());
		return osicostcenterEntity;
	}

	public ListOsiCostCenterVO toListOsiCostCenterVO(OsiCostCenter osicostcenterEntity) {;
		ListOsiCostCenterVO listOsiCostCenterVO = new ListOsiCostCenterVO();
		listOsiCostCenterVO.setCcId(osicostcenterEntity.getCcId());
		listOsiCostCenterVO.setCcShortName(osicostcenterEntity.getCcShortName());
		listOsiCostCenterVO.setCcLongName(osicostcenterEntity.getCcLongName());
		listOsiCostCenterVO.setActive(osicostcenterEntity.getActive());
		return listOsiCostCenterVO;
	}

	public List<OsiCostCenter> toOsiCostCenterEntityList(List<ListOsiCostCenterVO> listOsiCostCenterVOList){
		List<OsiCostCenter> osicostcenterEntityList = new ArrayList<OsiCostCenter>();
		for (ListOsiCostCenterVO listOsiCostCenterVO : listOsiCostCenterVOList) {
			osicostcenterEntityList.add(toOsiCostCenterEntity(listOsiCostCenterVO));
			}
		return osicostcenterEntityList;
	}

	public List<ListOsiCostCenterVO> toListOsiCostCenterVOList(List<OsiCostCenter> osicostcenterEntityList){
		List<ListOsiCostCenterVO> listOsiCostCenterVOList = new ArrayList<ListOsiCostCenterVO>();
		for (OsiCostCenter osicostcenterEntity : osicostcenterEntityList) {
			listOsiCostCenterVOList.add(toListOsiCostCenterVO(osicostcenterEntity));
			}
		return listOsiCostCenterVOList;
	}

}