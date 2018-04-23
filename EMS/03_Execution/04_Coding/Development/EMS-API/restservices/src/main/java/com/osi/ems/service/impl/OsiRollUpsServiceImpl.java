package com.osi.ems.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.OsiRollUpsDao;
import com.osi.ems.service.OsiRollUpsService;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiRollUpsServiceImpl implements OsiRollUpsService {
	
	@Autowired
	OsiRollUpsDao osiRollUpsDao;

	private final Logger log = LoggerFactory.getLogger(OsiRollUpsServiceImpl.class);
	
	@Override
	public String checkActiveStatus(String param) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		log.info("checkActiveStatus : Begin");
		String result = null;
		try{
			 result=osiRollUpsDao.checkActiveStatus(param);
		}catch (DataAccessException e) {
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			log.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while checking the active status");
		}
		log.info("checkActiveStatus : End");
		
		return result;
	}

}
