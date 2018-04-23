/**
 * 
 */
package com.osi.ems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.IListOsiJobCodesDao;
import com.osi.ems.service.IListOsiJobCodesService;
import com.osi.ems.service.dto.ListOsiJobCodesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiJobCodesServiceImpl implements IListOsiJobCodesService {

	@Autowired
	IListOsiJobCodesDao osijobcodesDao;
	
	private final Logger LOGGER = Logger.getLogger(ListOsiJobCodesServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiJobCodesService#getOsiJobCodesList()
	 */
	@Override
	public List<ListOsiJobCodesVO> getOsiJobCodesList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		List<ListOsiJobCodesVO> listOsiJobCodesVO = new ArrayList<ListOsiJobCodesVO>();
		LOGGER.info("getOsiJobCodesList : Begin");
		try{
			listOsiJobCodesVO =  osijobcodesDao.getAllOsiJobCodess(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi jobs");
		}
		LOGGER.info("getOsiJobCodesList : End");
		return listOsiJobCodesVO;
	}

}
