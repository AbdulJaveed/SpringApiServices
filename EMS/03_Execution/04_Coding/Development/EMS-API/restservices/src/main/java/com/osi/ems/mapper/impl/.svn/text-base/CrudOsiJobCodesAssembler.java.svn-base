package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.service.dto.CrudOsiJobCodesVO;

@Component
public class CrudOsiJobCodesAssembler {

	public OsiJobCodes toOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO) {;
		OsiJobCodes OsiJobCodes = new OsiJobCodes();
		OsiJobCodes.setJobCodeId(crudOsiJobCodesVO.getJobCodeId());
	//	OsiJobCodes.setVersion(crudOsiJobCodesVO.getVersion());
		OsiJobCodes.setJobCodeName(crudOsiJobCodesVO.getJobCodeName());
		OsiJobCodes.setJobCodeDescription(crudOsiJobCodesVO.getJobCodeDescription());
		OsiJobCodes.setActive(crudOsiJobCodesVO.getActive());
		OsiJobCodes.setOrgId(crudOsiJobCodesVO.getOrgId());
		OsiJobCodes.setCreatedBy(crudOsiJobCodesVO.getCreatedBy());
		OsiJobCodes.setCreatedDate(crudOsiJobCodesVO.getCreatedDate());
		OsiJobCodes.setUpdatedBy(crudOsiJobCodesVO.getUpdatedBy());
		OsiJobCodes.setLastUpdateDate(crudOsiJobCodesVO.getLastUpdateDate());
		
		return OsiJobCodes;
	}

	public CrudOsiJobCodesVO toCrudOsiJobCodesVO(OsiJobCodes OsiJobCodes) {;
		CrudOsiJobCodesVO crudOsiJobCodesVO = new CrudOsiJobCodesVO();
		crudOsiJobCodesVO.setJobCodeId(OsiJobCodes.getJobCodeId());
		//crudOsiJobCodesVO.setVersion(OsiJobCodes.getVersion());
		crudOsiJobCodesVO.setJobCodeName(OsiJobCodes.getJobCodeName());
		crudOsiJobCodesVO.setJobCodeDescription(OsiJobCodes.getJobCodeDescription());
		crudOsiJobCodesVO.setActive(OsiJobCodes.getActive());
		crudOsiJobCodesVO.setOrgId(OsiJobCodes.getOrgId());
		crudOsiJobCodesVO.setCreatedBy(OsiJobCodes.getCreatedBy());
		crudOsiJobCodesVO.setCreatedDate(OsiJobCodes.getCreatedDate());
		crudOsiJobCodesVO.setUpdatedBy(OsiJobCodes.getUpdatedBy());
		crudOsiJobCodesVO.setLastUpdateDate(OsiJobCodes.getLastUpdateDate());

		return crudOsiJobCodesVO;
	}

	public List<OsiJobCodes> toOsiJobCodesList(List<CrudOsiJobCodesVO> crudOsiJobCodesVOList){
		List<OsiJobCodes> OsiJobCodesList = new ArrayList<OsiJobCodes>();;
		for (CrudOsiJobCodesVO crudOsiJobCodesVO : crudOsiJobCodesVOList) {;
			OsiJobCodesList.add(toOsiJobCodes(crudOsiJobCodesVO));;
			}
		return OsiJobCodesList;
	}

	public List<CrudOsiJobCodesVO> toCrudOsiJobCodesVOList(List<OsiJobCodes> OsiJobCodesList){
		List<CrudOsiJobCodesVO> crudOsiJobCodesVOList = new ArrayList<CrudOsiJobCodesVO>();;
		for (OsiJobCodes OsiJobCodes : OsiJobCodesList) {;
			crudOsiJobCodesVOList.add(toCrudOsiJobCodesVO(OsiJobCodes));;
			}
		return crudOsiJobCodesVOList;
	}

}