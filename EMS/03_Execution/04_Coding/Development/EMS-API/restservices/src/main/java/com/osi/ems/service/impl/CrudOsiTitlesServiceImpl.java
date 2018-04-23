/**
 * 
 */
package com.osi.ems.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.ICrudOsiTitlesDao;
import com.osi.ems.service.ICrudOsiTitlesService;
import com.osi.ems.service.dto.CrudOsiTitlesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiTitlesServiceImpl implements ICrudOsiTitlesService {

	@Autowired
	ICrudOsiTitlesDao crudOsiTitlesDao;
	
	private final Logger LOGGER = Logger.getLogger(CrudOsiTitlesServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiTitlesService#getOsiTitles(java.lang.Integer)
	 */
	@Override
	public CrudOsiTitlesVO getOsiTitles(Integer id) throws BusinessException, DataAccessException {
		LOGGER.info("getOsiTitles : Begin");
		CrudOsiTitlesVO crudOsiTitles = new CrudOsiTitlesVO();
		try{
			crudOsiTitles =  crudOsiTitlesDao.getOsiTitles(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi titles");
		}
		LOGGER.info("getOsiTitles : End");
		return crudOsiTitles;
	}

	@Override
	public CrudOsiTitlesVO createOsiTitles(CrudOsiTitlesVO crudOsiTitlesVO,Integer userId) throws BusinessException, DataAccessException{
		// TODO Auto-generated method stub
		LOGGER.info("createOsiTitles : Begin");
		CrudOsiTitlesVO crudOsiTitles = new CrudOsiTitlesVO();
		try{
			crudOsiTitles =  crudOsiTitlesDao.saveOsiTitles(crudOsiTitlesVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the osi titles");
		}
		LOGGER.info("createOsiTitles : End");
		return crudOsiTitles;
		
	}
	
	@Override
	public CrudOsiTitlesVO updateOsiTitles(CrudOsiTitlesVO crudOsiTitlesVO,Integer userId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("updateOsiTitles : Begin");
		CrudOsiTitlesVO crudOsiTitles = new CrudOsiTitlesVO();
		try{
			crudOsiTitles =  crudOsiTitlesDao.updateOsiTitles(crudOsiTitlesVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the osi titles");
		}
		LOGGER.info("updateOsiTitles : End");
		return crudOsiTitles;
	}
	
	@Override
	public void deleteOsiTitles(Integer id) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("deleteOsiTitles : Begin");
		try{
			crudOsiTitlesDao.deleteOsiTitles(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osi titles");
		}
		LOGGER.info("deleteOsiTitles : End");
	}

}
