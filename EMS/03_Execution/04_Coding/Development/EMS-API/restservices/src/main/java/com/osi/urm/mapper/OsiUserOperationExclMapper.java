package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiUserOperationExcl;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;

public interface OsiUserOperationExclMapper {
	
	OsiUserOperationExclDTO osiUserToOsiUserDTO(OsiUserOperationExcl osiUser);
	
	List<OsiUserOperationExclDTO> osiUserListToOsiUserDTOList(List<OsiUserOperationExcl> osiUsers);
	
	OsiUserOperationExcl osiUserDTOToOsiUser(OsiUserOperationExclDTO osiUserDTO);
	
	List<OsiUserOperationExcl> osiUserDTOListToOsiUserList(List<OsiUserOperationExclDTO> osiUserDTO);

}
