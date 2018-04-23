package com.osi.urm.service;

import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;

public interface OsiUserOperationExclService {

	OsiUserOperationExclDTO save(OsiUserOperationExclDTO osiUserOperationExcl, Integer businessGroupId)  throws BusinessException;

	List<OsiUserOperationExclDTO> findOne(Integer funcId, Integer userId);

	void delete(Integer id);

	void deleteByUserId(Integer userId, Integer functionId, Integer businessGroupId);

	List<OsiUserOperationExclDTO> findByEmployeeId(Integer userId);

	void deleteByEmployeeId(int employeeId);

	void deleteAll();

}
