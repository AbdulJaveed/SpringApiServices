package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.service.dto.ListOsiJobCodesVO;

@Component
public class ListOsiJobCodesAssembler {

	public OsiJobCodes toOsiJobCodes(ListOsiJobCodesVO listOsiJobCodesVO) {
		OsiJobCodes OsiJobCodes = new OsiJobCodes();
		OsiJobCodes.setJobCodeId(listOsiJobCodesVO.getJobCodeId());
		OsiJobCodes.setJobCodeName(listOsiJobCodesVO.getJobCodeName());
		OsiJobCodes.setJobCodeDescription(listOsiJobCodesVO.getJobCodeDescription());
		OsiJobCodes.setActive(listOsiJobCodesVO.getActive());
		OsiJobCodes.setOrgId(listOsiJobCodesVO.getOrgId());
		return OsiJobCodes;
	}

	public ListOsiJobCodesVO toListOsiJobCodesVO(OsiJobCodes OsiJobCodes) {;
		ListOsiJobCodesVO listOsiJobCodesVO = new ListOsiJobCodesVO();
		listOsiJobCodesVO.setJobCodeId(OsiJobCodes.getJobCodeId());
		listOsiJobCodesVO.setJobCodeName(OsiJobCodes.getJobCodeName());
		listOsiJobCodesVO.setJobCodeDescription(OsiJobCodes.getJobCodeDescription());
		listOsiJobCodesVO.setActive(OsiJobCodes.getActive());
		listOsiJobCodesVO.setOrgId(OsiJobCodes.getOrgId());
		return listOsiJobCodesVO;
	}

	public List<OsiJobCodes> toOsiJobCodesList(List<ListOsiJobCodesVO> listOsiJobCodesVOList){
		List<OsiJobCodes> OsiJobCodesList = new ArrayList<OsiJobCodes>();
		for (ListOsiJobCodesVO listOsiJobCodesVO : listOsiJobCodesVOList) {
			OsiJobCodesList.add(toOsiJobCodes(listOsiJobCodesVO));
			}
		return OsiJobCodesList;
	}

	public List<ListOsiJobCodesVO> toListOsiJobCodesVOList(List<OsiJobCodes> OsiJobCodesList){
		List<ListOsiJobCodesVO> listOsiJobCodesVOList = new ArrayList<ListOsiJobCodesVO>();
		for (OsiJobCodes OsiJobCodes : OsiJobCodesList) {
			listOsiJobCodesVOList.add(toListOsiJobCodesVO(OsiJobCodes));
			}
		return listOsiJobCodesVOList;
	}

}