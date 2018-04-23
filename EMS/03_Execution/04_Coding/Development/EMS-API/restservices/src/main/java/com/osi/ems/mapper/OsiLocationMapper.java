package com.osi.ems.mapper;

import java.util.List;

import com.osi.ems.domain.OsiLocations;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.urm.exception.BusinessException;

public interface OsiLocationMapper {
	OsiLocationsDTO osiLocationToOsiLocationDTO(OsiLocations osiLocation);
	
	List<OsiLocationsDTO> osiLocationToOsiLocationDTOList(List<OsiLocations> osiLocation);
	
	OsiLocations osiLocationDTOToOsiLocation(OsiLocationsDTO osiLocationDTO) throws BusinessException;
	
	List<OsiLocations> osiLocationDTOToOsiLocation(List<OsiLocationsDTO> osiLocationDTO) throws BusinessException;
}
