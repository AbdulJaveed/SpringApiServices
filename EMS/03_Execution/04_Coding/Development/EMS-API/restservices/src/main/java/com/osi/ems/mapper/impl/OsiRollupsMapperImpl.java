package com.osi.ems.mapper.impl;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiRollUps;
import com.osi.ems.mapper.OsiRollupsMapper;
import com.osi.ems.service.dto.OsiRollUpsDTO;

@Component
public class OsiRollupsMapperImpl implements OsiRollupsMapper {

	@Override
	public OsiRollUpsDTO osiRollupsToOsiRollupsDTO(OsiRollUps osiRollups) {
		OsiRollUpsDTO osiRollupsDto = null;
		if(null != osiRollups) {
			osiRollupsDto = new OsiRollUpsDTO();
			
			osiRollupsDto.setRollupId(osiRollups.getRollupId());
			osiRollupsDto.setSegment1(osiRollups.getSegment1());
			osiRollupsDto.setSegment2(osiRollups.getSegment2());
			osiRollupsDto.setSegment3(osiRollups.getSegment3());
			osiRollupsDto.setSegment4(osiRollups.getSegment4());
			osiRollupsDto.setSegment5(osiRollups.getSegment5());
			osiRollupsDto.setSegment6(osiRollups.getSegment6());
			osiRollupsDto.setSegment7(osiRollups.getSegment7());
			osiRollupsDto.setSegment8(osiRollups.getSegment8());
			osiRollupsDto.setSegment9(osiRollups.getSegment9());
			osiRollupsDto.setSegment10(osiRollups.getSegment10());
			osiRollupsDto.setActive(osiRollups.getActive());
			osiRollupsDto.setOrgId(osiRollups.getOrgId());
			osiRollupsDto.setCreatedBy(osiRollups.getCreatedBy());
			osiRollupsDto.setCreatedDate(osiRollups.getCreatedDate());
			osiRollupsDto.setUpdatedBy(osiRollups.getUpdatedBy());
			osiRollupsDto.setUpdatedDate(osiRollups.getUpdatedDate());
		}
		return osiRollupsDto;
	}

	@Override
	public OsiRollUps osiRollupsDtoToOsiRollups(OsiRollUpsDTO osiRollupsDTO) {
		OsiRollUps osiRollups = null;
		if(null != osiRollupsDTO) {
			osiRollups = new OsiRollUps();
			
			osiRollups.setRollupId(osiRollupsDTO.getRollupId());
			osiRollups.setSegment1(osiRollupsDTO.getSegment1());
			osiRollups.setSegment2(osiRollupsDTO.getSegment2());
			osiRollups.setSegment3(osiRollupsDTO.getSegment3());
			osiRollups.setSegment4(osiRollupsDTO.getSegment4());
			osiRollups.setSegment5(osiRollupsDTO.getSegment5());
			osiRollups.setSegment6(osiRollupsDTO.getSegment6());
			osiRollups.setSegment7(osiRollupsDTO.getSegment7());
			osiRollups.setSegment8(osiRollupsDTO.getSegment8());
			osiRollups.setSegment9(osiRollupsDTO.getSegment9());
			osiRollups.setSegment10(osiRollupsDTO.getSegment10());
			osiRollups.setActive(osiRollupsDTO.getActive());
			osiRollups.setOrgId(osiRollupsDTO.getOrgId());
			osiRollups.setCreatedBy(osiRollupsDTO.getCreatedBy());
			osiRollups.setCreatedDate(osiRollupsDTO.getCreatedDate());
			osiRollups.setUpdatedBy(osiRollupsDTO.getUpdatedBy());
			osiRollups.setUpdatedDate(osiRollupsDTO.getUpdatedDate());
		}
		return osiRollups;
	}

}
