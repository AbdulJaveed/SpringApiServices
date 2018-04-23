package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiSubPractice;
import com.osi.ems.mapper.OsiSubPracticeMapper;
import com.osi.ems.service.dto.OsiSubPracticeDto;

@Component
public class OsiSubPracticeMapperImpl implements OsiSubPracticeMapper {

	@Override
	public OsiSubPractice getOsiSubPractice(OsiSubPracticeDto osiSubPracticeDto) {
		OsiSubPractice osiSubPractice = null;
		osiSubPractice = new OsiSubPractice();

		osiSubPractice.setSubPracticeId(osiSubPracticeDto.getSubPracticeId());
		osiSubPractice.setActive(osiSubPracticeDto.getActive());
		osiSubPractice.setCreatedBy(osiSubPracticeDto.getCreatedBy());
		osiSubPractice.setCreatedDate(osiSubPracticeDto.getCreatedDate());
		osiSubPractice.setLastUpdateDate(osiSubPracticeDto.getLastUpdateDate());
		osiSubPractice.setSubPractceLongName(osiSubPracticeDto.getSubPractceLongName());
		osiSubPractice.setSubPracticeShortName(osiSubPracticeDto.getSubPracticeShortName());
		osiSubPractice.setUpdatedBy(osiSubPracticeDto.getUpdatedBy());

		return osiSubPractice;
	}

	@Override
	public OsiSubPracticeDto getOsiSubPracticeDto(OsiSubPractice osiSubPractice) {
		OsiSubPracticeDto osiSubPracticeDto = null;
		osiSubPracticeDto = new OsiSubPracticeDto();
		osiSubPracticeDto.setActive(osiSubPractice.getActive());
		osiSubPracticeDto.setCreatedBy(osiSubPractice.getCreatedBy());
		osiSubPracticeDto.setCreatedDate(osiSubPractice.getCreatedDate());
		osiSubPracticeDto.setLastUpdateDate(osiSubPractice.getLastUpdateDate());
		osiSubPracticeDto.setSubPractceLongName(osiSubPractice.getSubPractceLongName());
		osiSubPracticeDto.setSubPracticeId(osiSubPractice.getSubPracticeId());
		osiSubPracticeDto.setSubPracticeShortName(osiSubPractice.getSubPracticeShortName());
		osiSubPracticeDto.setUpdatedBy(osiSubPractice.getUpdatedBy());

		return osiSubPracticeDto;
	}

	@Override
	public List<OsiSubPractice> getOsiSubPracticeList(List<OsiSubPracticeDto> osiSubPracticeDtoList) {
		List<OsiSubPractice> osiSubPracticeList = new ArrayList<>();
		
		/*osiSubPracticeDtoList.stream().forEach(osiSubPracticeDto->{
			osiSubPracticeList.add(this.getOsiSubPractice(osiSubPracticeDto));
		});*/
		for (OsiSubPracticeDto osiSubPractice : osiSubPracticeDtoList) {
			osiSubPracticeList.add(this.getOsiSubPractice(osiSubPractice));
		}
		return osiSubPracticeList;
	}

	@Override
	public List<OsiSubPracticeDto> getOsiSubPracticeDtoList(List<OsiSubPractice> osiSubPracticeList) {
		List<OsiSubPracticeDto> osiSubPracticeDtoList = new ArrayList<>();
		/*osiSubPracticeList.forEach(osiSubPractice->{
			osiSubPracticeDtoList.add(this.getOsiSubPracticeDto(osiSubPractice));
		});*/
		for (OsiSubPractice osiSubPractice : osiSubPracticeList) {
			osiSubPracticeDtoList.add(this.getOsiSubPracticeDto(osiSubPractice));
		}
		return osiSubPracticeDtoList;
	}
	
	

}
