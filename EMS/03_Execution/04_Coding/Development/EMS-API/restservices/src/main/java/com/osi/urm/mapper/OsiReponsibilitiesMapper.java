package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiResponsibilities;
import com.osi.urm.service.dto.OsiResponsibilitiesDTO;

public interface OsiReponsibilitiesMapper {

	OsiResponsibilitiesDTO osiResponsibilitiesToOsiResponsibilitiesDTO(OsiResponsibilities osiResponsibilities);

	List<OsiResponsibilitiesDTO> osiResponsibilitiesListToOsiResponsibilitiesDTOList(List<OsiResponsibilities> osiResponsibilities);

	OsiResponsibilities osiResponsibilitiesDTOToOsiResponsibilities(OsiResponsibilitiesDTO osiResponsibilitiesDTO);

	List<OsiResponsibilities> OsiResponsibilitiesDTOListToOsiResponsibilitiesList(List<OsiResponsibilitiesDTO> osiResponsibilitiesDTO);
}
