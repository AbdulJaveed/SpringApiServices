package com.osi.urm.mapper;

import java.util.List;

import com.osi.urm.domain.OsiUserFuncExcl;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;

public interface OsiUserFuncExclMapper {

	OsiUserFuncExclDTO osiUserFuncExclToOsiUserFuncExclDTO(OsiUserFuncExcl osiUserFuncExcl);

	List<OsiUserFuncExclDTO> osiUserFuncExclListToOsiUserFuncExclDTOList(List<OsiUserFuncExcl> osiUsersFuncExcl);

	OsiUserFuncExcl osiUserFuncExclDTOToOsiUserFuncExcl(OsiUserFuncExclDTO osiUserFunctionExclDTO) throws BusinessException;

	List<OsiUserFuncExcl> osiUserFuncExclDTOListToOsiUserFuncExclList(List<OsiUserFuncExclDTO> osiUserFuncExclDTO) throws BusinessException;

}