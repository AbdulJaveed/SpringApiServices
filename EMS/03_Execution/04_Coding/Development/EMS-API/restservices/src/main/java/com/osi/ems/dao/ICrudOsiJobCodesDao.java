package com.osi.ems.dao;

import com.osi.ems.service.dto.CrudOsiJobCodesVO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiJobCodesDao {
	public CrudOsiJobCodesVO getOsiJobCodes(Integer id) throws DataAccessException;
	public CrudOsiJobCodesVO saveOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws DataAccessException;
	public CrudOsiJobCodesVO updateOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws DataAccessException;
	public void deleteOsiJobCodes(Integer id) throws DataAccessException;
}
