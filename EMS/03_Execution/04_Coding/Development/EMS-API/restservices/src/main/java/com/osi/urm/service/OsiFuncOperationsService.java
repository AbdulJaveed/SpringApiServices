package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiFuncOperationsDTO;

public interface OsiFuncOperationsService {

	OsiFuncOperationsDTO save(OsiFuncOperationsDTO osiFuncOperationsDTO, Integer busineesGroupId) throws BusinessException;
	List<OsiFuncOperationsDTO> findAll();
	List<OsiFuncOperationsDTO>findByFunctionId(Integer id);
}
