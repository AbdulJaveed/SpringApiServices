package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiDepartment;
import com.osi.ems.service.dto.ListOsiDepartmentVO;

@Component
public class ListOsiDepratmentAssembler {

	public OsiDepartment toOsiDepratmentEntity(ListOsiDepartmentVO listOsiDepratmentVO) {
		OsiDepartment osidepratmentEntity = new OsiDepartment();
		osidepratmentEntity.setDeptId(listOsiDepratmentVO.getDeptId());
		osidepratmentEntity.setDeptShortName(listOsiDepratmentVO.getDeptShortName());
		osidepratmentEntity.setDeptLongName(listOsiDepratmentVO.getDeptLongName());
		osidepratmentEntity.setActive(listOsiDepratmentVO.getActive());
		return osidepratmentEntity;
	}

	public ListOsiDepartmentVO toListOsiDepratmentVO(OsiDepartment osidepratmentEntity) {;
		ListOsiDepartmentVO listOsiDepratmentVO = new ListOsiDepartmentVO();
		listOsiDepratmentVO.setDeptId(osidepratmentEntity.getDeptId());
		listOsiDepratmentVO.setDeptShortName(osidepratmentEntity.getDeptShortName());
		listOsiDepratmentVO.setDeptLongName(osidepratmentEntity.getDeptLongName());
		listOsiDepratmentVO.setActive(osidepratmentEntity.getActive());
		return listOsiDepratmentVO;
	}

	public List<OsiDepartment> toOsiDepratmentEntityList(List<ListOsiDepartmentVO> listOsiDepratmentVOList){
		List<OsiDepartment> osidepratmentEntityList = new ArrayList<OsiDepartment>();
		for (ListOsiDepartmentVO listOsiDepratmentVO : listOsiDepratmentVOList) {
			osidepratmentEntityList.add(toOsiDepratmentEntity(listOsiDepratmentVO));
			}
		return osidepratmentEntityList;
	}

	public List<ListOsiDepartmentVO> toListOsiDepratmentVOList(List<OsiDepartment> osidepratmentEntityList){
		List<ListOsiDepartmentVO> listOsiDepratmentVOList = new ArrayList<ListOsiDepartmentVO>();
		for (OsiDepartment osidepratmentEntity : osidepratmentEntityList) {
			listOsiDepratmentVOList.add(toListOsiDepratmentVO(osidepratmentEntity));
			}
		return listOsiDepratmentVOList;
	}

}