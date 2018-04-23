/**
 * 
 */
package com.osi.ems.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.ICrudOsiCertificationsDao;
import com.osi.ems.service.ICrudOsiCertificationsService;
import com.osi.ems.service.dto.CrudOsiCertificationsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiCertificationsServiceImpl implements ICrudOsiCertificationsService {

	@Autowired
	ICrudOsiCertificationsDao crudOsiCertificationsDao;
	
	private final Logger LOGGER = Logger.getLogger(CrudOsiCertificationsServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiCertificationsService#getOsiCertifications(java.lang.Integer)
	 */
	
	@Override
	public CrudOsiCertificationsVO getOsiCertifications(Integer id) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiCertifications : Begin");
		CrudOsiCertificationsVO crudOsiCertificationsVO = new CrudOsiCertificationsVO();
		try{
			crudOsiCertificationsVO =  crudOsiCertificationsDao.getOsiCertifications(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the data");
		}
		
		LOGGER.info("getOsiCertifications : End");
		return crudOsiCertificationsVO;
	}

	@Override
	public CrudOsiCertificationsVO createOsiCertifications(CrudOsiCertificationsVO crudOsiCertificationsVO,Integer userId) throws BusinessException, DataAccessException {
		LOGGER.info("getOsiCertifications : Begin");
		
		CrudOsiCertificationsVO crudOsiCertifications = new CrudOsiCertificationsVO();
		try{
			crudOsiCertifications =  crudOsiCertificationsDao.saveOsiCertifications(crudOsiCertificationsVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the data");
		}
		LOGGER.info("getOsiCertifications : End");
		return crudOsiCertifications;
	}
	
	@Override
	public CrudOsiCertificationsVO updateOsiCertifications(CrudOsiCertificationsVO crudOsiCertificationsVO,Integer userId) throws BusinessException, DataAccessException {
		LOGGER.info("updateOsiCertifications : Begin");
		CrudOsiCertificationsVO crudOsiCertifications = new CrudOsiCertificationsVO();
		try{
			crudOsiCertifications =  crudOsiCertificationsDao.updateOsiCertifications(crudOsiCertificationsVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the data");
		}
		LOGGER.info("updateOsiCertifications : End");
		return crudOsiCertifications;
		
	}
	
	@Override
	public void deleteOsiCertifications(Integer id) throws Exception {
		LOGGER.info("deleteOsiCertifications : Begin");
	
		try{
			crudOsiCertificationsDao.deleteOsiCertifications(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the data");
		}
		LOGGER.info("deleteOsiCertifications : End");
	}

}
