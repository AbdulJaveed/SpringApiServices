package com.osi.ems.dao;

import com.osi.ems.service.dto.CrudOsiCertificationsVO;
import com.osi.urm.exception.DataAccessException;

public interface ICrudOsiCertificationsDao {
	public CrudOsiCertificationsVO getOsiCertifications(Integer id) throws DataAccessException;
	public CrudOsiCertificationsVO saveOsiCertifications(CrudOsiCertificationsVO crudOsiCertificationsVO, Integer userId) throws DataAccessException;
	public CrudOsiCertificationsVO updateOsiCertifications(CrudOsiCertificationsVO crudOsiCertificationsVO, Integer userId) throws DataAccessException;
	public void deleteOsiCertifications(Integer id) throws DataAccessException;
}
