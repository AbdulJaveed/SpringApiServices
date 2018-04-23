package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiMenuEntries;
import com.osi.urm.service.dto.OsiMenuEntriesDTO;

public interface OsiMenuEntriesMapper {

	OsiMenuEntriesDTO OsiMenuEntriesToOsiMenuEntriesDTO(OsiMenuEntries osiMenuEntries);

	List<OsiMenuEntriesDTO> osiMenuEntriesListToOsiMenuEntriesDTOList(List<OsiMenuEntries> osiMenuEntries);

	OsiMenuEntries osiMenuEntriesDTOToOsiMenuEntries(OsiMenuEntriesDTO osiMenuEntriesDTO);

	List<OsiMenuEntries> osiMenuEntriesDTOListToOsMenuEntriesList(List<OsiMenuEntriesDTO> osiMenuEntriesDTO);

}
