/**
 * 
 */
package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.ICrudOsiGradesDao;
import com.osi.ems.service.ICrudOsiGradesService;
import com.osi.ems.service.dto.CrudOsiGradesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class CrudOsiGradesServiceImpl implements ICrudOsiGradesService {

	@Autowired
	ICrudOsiGradesDao crudOsiGradesDao;
	
	private final Logger LOGGER = Logger.getLogger(CrudOsiGradesServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiGradesService#getOsiGrades(java.lang.Integer)
	 */
	@Override
	public CrudOsiGradesVO getOsiGrades(Integer id) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiGrades : Begin");
		CrudOsiGradesVO osiGradesVo=new CrudOsiGradesVO();
		try {
			osiGradesVo=crudOsiGradesDao.getOsiGrades(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the osi grades");
		}
		LOGGER.info("getOsiGrades : End");
		return osiGradesVo;
	}

	@Override
	public CrudOsiGradesVO createOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("createOsiGrades : Begin");
		CrudOsiGradesVO osiGradesVo=new CrudOsiGradesVO();
		try {
			osiGradesVo=crudOsiGradesDao.saveOsiGrades(crudOsiGradesVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while creating the osi grades");
		}
		LOGGER.info("createOsiGrades : End");
		return osiGradesVo;
	}
	
	@Override
	public CrudOsiGradesVO updateOsiGrades(CrudOsiGradesVO crudOsiGradesVO,Integer userId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("updateOsiGrades : Begin");
		CrudOsiGradesVO osiGradesVo=new CrudOsiGradesVO();
		try {
			osiGradesVo=crudOsiGradesDao.updateOsiGrades(crudOsiGradesVO,userId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while updating the osi grades");
		}
		LOGGER.info("updateOsiGrades : End");
		return osiGradesVo;
	}
	
	@Override
	public void deleteOsiGrades(Integer id) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("deleteOsiGrades : Begin");
		try{
			crudOsiGradesDao.deleteOsiGrades(id);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while deleting the osi grades");
		}
		LOGGER.info("deleteOsiGrades : End");
	}
	
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiGradesService#getOsiGradesHistory(java.lang.Integer)
	 */
	@Override
	public List<CrudOsiGradesVO> getOsiGradesHistory(Integer garedId, Integer orgId) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("getOsiGrades : Begin");
		List<CrudOsiGradesVO> osiGradesVoList=new ArrayList<>();
		try {
			osiGradesVoList=crudOsiGradesDao.getOsiGradesHistory(garedId, orgId);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retreiving the osi grades");
		}
		LOGGER.info("getOsiGrades : End");
		return osiGradesVoList;
	}

}
