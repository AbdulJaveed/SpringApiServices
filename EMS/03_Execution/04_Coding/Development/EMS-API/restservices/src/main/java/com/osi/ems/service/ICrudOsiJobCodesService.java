package com.osi.ems.service;

import com.osi.ems.service.dto.CrudOsiJobCodesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;


public interface ICrudOsiJobCodesService {
	public CrudOsiJobCodesVO getOsiJobCodes(Integer id) throws BusinessException, DataAccessException;
	public CrudOsiJobCodesVO createOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws BusinessException, DataAccessException;
	public CrudOsiJobCodesVO updateOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws BusinessException, DataAccessException;
	public void deleteOsiJobCodes(Integer id) throws Exception;
}
