package com.osi.ems.mapper;

import java.util.List;

import com.osi.ems.domain.OsiSubPractice;
import com.osi.ems.service.dto.OsiSubPracticeDto;

public interface OsiSubPracticeMapper {
	
	public OsiSubPractice getOsiSubPractice(OsiSubPracticeDto osiSubPracticeDto) ;
	
	public OsiSubPracticeDto getOsiSubPracticeDto(OsiSubPractice osiSubPractice);
	
	public List<OsiSubPractice> getOsiSubPracticeList(List<OsiSubPracticeDto> osiSubPracticeDtoList);
	
	public List<OsiSubPracticeDto> getOsiSubPracticeDtoList(List<OsiSubPractice> osiSubPracticeList);
	
}
