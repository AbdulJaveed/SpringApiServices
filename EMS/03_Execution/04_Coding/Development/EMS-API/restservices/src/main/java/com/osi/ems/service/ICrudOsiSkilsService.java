package com.osi.ems.service;

import com.osi.ems.service.dto.CrudOsiSkilsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface ICrudOsiSkilsService {
	public CrudOsiSkilsVO getOsiSkils(Integer id) throws BusinessException, DataAccessException;
	public CrudOsiSkilsVO createOsiSkils(CrudOsiSkilsVO crudOsiSkilsVO, Integer userId) throws BusinessException, DataAccessException;
	public CrudOsiSkilsVO updateOsiSkils(CrudOsiSkilsVO crudOsiSkilsVO, Integer userId) throws BusinessException, DataAccessException;
	public void deleteOsiSkils(Integer id) throws Exception;
}
