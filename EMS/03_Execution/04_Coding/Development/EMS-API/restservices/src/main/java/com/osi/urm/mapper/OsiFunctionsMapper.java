package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiFunctions;
import com.osi.urm.service.dto.OsiFunctionsDTO;

public interface OsiFunctionsMapper {

	OsiFunctionsDTO osiUserToOsiFunctionsDTO(OsiFunctions osiFunctions);

	List<OsiFunctionsDTO> osiFunctionsListToOsiFunctionsDTOList(List<OsiFunctions> osiFunctions);

	OsiFunctions osiFunctionsDTOToOsiFunctions(OsiFunctionsDTO osiFunctionsDTO);

	List<OsiFunctions> osiFunctionsDTOListToOsiFunctionsList(List<OsiFunctionsDTO> osiFunctionsDTO);

}
