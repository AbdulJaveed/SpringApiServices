/**
 * 
 */
package com.osi.ems.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.ICrudOsiJobCodesDao;
import com.osi.ems.service.ICrudOsiJobCodesService;
import com.osi.ems.service.dto.CrudOsiJobCodesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiJobCodesServiceImpl implements ICrudOsiJobCodesService {

	@Autowired
	ICrudOsiJobCodesDao crudOsiJobCodesDao;
	

	private final Logger LOGGER = Logger.getLogger(CrudOsiJobCodesServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiJobCodesService#getOsiJobCodes(java.lang.Integer)
	 */
	@Override
	public CrudOsiJobCodesVO getOsiJobCodes(Integer id) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiJobCodes : Begin");
		CrudOsiJobCodesVO crudOsiJobCodesVO = new CrudOsiJobCodesVO();
		try{
			crudOsiJobCodesVO =  crudOsiJobCodesDao.getOsiJobCodes(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi Job codes");
		}
		LOGGER.info("getOsiJobCodes : End");
		return crudOsiJobCodesVO;
		
	}

	@Override
	public CrudOsiJobCodesVO createOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
				LOGGER.info("createOsiJobCodes : Begin");
				CrudOsiJobCodesVO crudOsiJobCodes = new CrudOsiJobCodesVO();
				try{
					crudOsiJobCodes =  crudOsiJobCodesDao.saveOsiJobCodes(crudOsiJobCodesVO,userId);
				}catch (DataAccessException e) {
					LOGGER.error("Error Occured : "+e.getSystemMessage());
					throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
				}catch(Exception e){
					LOGGER.error("Error Occured : "+e.getMessage());
					throw new BusinessException("ERR_1000", "Error occured while creating the osi job codes");
				}
				LOGGER.info("createOsiJobCodes : End");
				return crudOsiJobCodes;
	}
	
	@Override
	public CrudOsiJobCodesVO updateOsiJobCodes(CrudOsiJobCodesVO crudOsiJobCodesVO,Integer userId) throws BusinessException, DataAccessException {
		LOGGER.info("updateOsiJobCodes : Begin");
		CrudOsiJobCodesVO crudOsiJobCodes = new CrudOsiJobCodesVO();
		try{
			crudOsiJobCodes =  crudOsiJobCodesDao.updateOsiJobCodes(crudOsiJobCodesVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the osi job codes");
		}
		LOGGER.info("updateOsiJobCodes : End");
		return crudOsiJobCodes;
		
	}
	
	@Override
	public void deleteOsiJobCodes(Integer id) throws Exception {
		LOGGER.info("deleteOsiJobCodes : Begin");
		
		try{
			crudOsiJobCodesDao.deleteOsiJobCodes(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osi job codes");
		}
		LOGGER.info("deleteOsiJobCodes : End");
	}

}
