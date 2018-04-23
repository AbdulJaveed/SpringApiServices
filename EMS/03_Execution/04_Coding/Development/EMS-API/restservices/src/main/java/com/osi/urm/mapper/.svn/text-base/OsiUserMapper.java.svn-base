package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiUserDTO;

public interface OsiUserMapper {
	
	OsiUserDTO osiUserToOsiUserDTO(OsiUser osiUser) throws BusinessException;
	
	List<OsiUserDTO> osiUserListToOsiUserDTOList(List<OsiUser> osiUsers) throws BusinessException;
	
	OsiUser osiUserDTOToOsiUser(OsiUserDTO osiUserDTO );
	
	List<OsiUser> osiUserDTOListToOsiUserList(List<OsiUserDTO> osiUserDTO);

}
