package com.osi.urm.mapper;

import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.service.dto.OsiLookupTypesDTO;

public interface OsiLookupMapper {

	/*Set<OsiLookupValuesDTO> osiLookupValuesSetToOsiLookupValuesDTOSet(Set<OsiLookupValues> osiLookupValues);

	Set<OsiLookupValues> osiLookupValuesDTOSetToOsiLookupValuesSet(Set<OsiLookupValuesDTO> osiLookupValuesDTO,OsiLookupTypes osiLookupTypes);*/
	
	OsiLookupTypesDTO osiLookupToOsiLookupDTO(OsiLookupTypes osiLookupTypes);
	
	OsiLookupTypes osiLookupDTOToOsiLookup(OsiLookupTypesDTO osiLookupTypesDTO);
	
	/*OsiLookupValuesDTO osiLookupValueToOsiLookupValueDTO(OsiLookupValues osiLookupValues, Integer businessGroupId);
	
	OsiLookupValues osiLookupValueDTOToOsiLookupValue(OsiLookupValuesDTO osiLookupValuesDTO,Integer businessGroupId);*/

}
