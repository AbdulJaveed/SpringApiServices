/**
 * 
 */
package com.osi.ems.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.ICrudOsiSkilsDao;
import com.osi.ems.service.ICrudOsiSkilsService;
import com.osi.ems.service.dto.CrudOsiSkilsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiSkilsServiceImpl implements ICrudOsiSkilsService {

	@Autowired
	ICrudOsiSkilsDao crudOsiSkilsDao;
	
	private final Logger LOGGER = Logger.getLogger(CrudOsiSkilsServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiSkilsService#getOsiSkils(java.lang.Integer)
	 */
	
	@Override
	public CrudOsiSkilsVO getOsiSkils(Integer id) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiSkils : Begin");
		CrudOsiSkilsVO crudOsiSkilsVO = new CrudOsiSkilsVO();
		try{
			crudOsiSkilsVO =  crudOsiSkilsDao.getOsiSkils(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skills");
		}
		LOGGER.info("getOsiSkils : End");
		return crudOsiSkilsVO;
		
		
	}

	@Override
	public CrudOsiSkilsVO createOsiSkils(CrudOsiSkilsVO crudOsiSkilsVO, Integer userId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("createOsiSkils : Begin");
		CrudOsiSkilsVO crudOsiSkils = new CrudOsiSkilsVO();
		try{
			crudOsiSkils = crudOsiSkilsDao.saveOsiSkils(crudOsiSkilsVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the osi skills");
		}
		LOGGER.info("createOsiSkils : End");
		return crudOsiSkils;
		
	}
	
	@Override
	public CrudOsiSkilsVO updateOsiSkils(CrudOsiSkilsVO crudOsiSkilsVO, Integer userId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("updateOsiSkils : Begin");
		CrudOsiSkilsVO crudOsiSkils = new CrudOsiSkilsVO();
		try{
			crudOsiSkils = crudOsiSkilsDao.updateOsiSkils(crudOsiSkilsVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the osi skills");
		}
		LOGGER.info("updateOsiSkils : End");
		return crudOsiSkils;
	}
	
	@Override
	public void deleteOsiSkils(Integer id) throws Exception {
		LOGGER.info("deleteOsiSkils : Begin");
		try{
			crudOsiSkilsDao.deleteOsiSkils(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osi skills");
		}
		LOGGER.info("deleteOsiSkils : End");
	}

}
