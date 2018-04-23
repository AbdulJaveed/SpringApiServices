package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiUserLogins;
import com.osi.urm.service.dto.OsiUserLoginsDTO;

public interface OsiUserLoginsMapper {

	OsiUserLoginsDTO OsiUserLoginsToOsiUserLoginsDTO(OsiUserLogins osiUser);

	List<OsiUserLoginsDTO> osiUserLoginsListToOsiUserLoginsDTOList(List<OsiUserLogins> osiUsers);

	OsiUserLogins osiUserLoginsDTOToOsiUserLogins(OsiUserLoginsDTO osiUserDTO);

	List<OsiUserLogins> osiUserLoginsDTOListToOsiUserLoginsList(List<OsiUserLoginsDTO> osiUserDTO);

}
