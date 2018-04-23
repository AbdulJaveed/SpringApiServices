package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiRespUserDTO;

public interface OsiRespUserMapper {

	OsiRespUserDTO osiUserToOsiUserDTO(OsiRespUser osiUser) throws BusinessException;

	List<OsiRespUserDTO> osiUserListToOsiUserDTOList(List<OsiRespUser> osiUsers) throws BusinessException;

	OsiRespUser osiUserDTOToOsiUser(OsiRespUserDTO osiUserDTO);

	List<OsiRespUser> osiUserDTOListToOsiUserList(List<OsiRespUserDTO> osiUserDTO);

}
