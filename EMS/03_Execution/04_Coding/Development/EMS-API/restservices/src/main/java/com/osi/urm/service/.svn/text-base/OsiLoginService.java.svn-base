package com.osi.urm.service;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiUserDTO;

/**
 * Service Interface for managing OsiUser.
 */
public interface OsiLoginService {
	public OsiUserDTO validateLogin(OsiUserDTO osiUserDTO)  throws BusinessException;
	public void logout(Integer userId, String token) throws BusinessException;
}
