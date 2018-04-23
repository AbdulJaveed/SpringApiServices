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

import com.osi.ems.dao.IListOsiBusinessUnitsDao;
import com.osi.ems.service.IListOsiBusinessUnitsService;
import com.osi.ems.service.dto.ListOsiBusinessUnitsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiBusinessUnitsServiceImpl implements IListOsiBusinessUnitsService {

	@Autowired
	IListOsiBusinessUnitsDao osibusinessunitsDao;
	
	private final Logger LOGGER = Logger.getLogger(ListOsiBusinessUnitsServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiBusinessUnitsService#getOsiBusinessUnitsList()
	 */
	@Override
	public List<ListOsiBusinessUnitsVO> getOsiBusinessUnitsList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		List<ListOsiBusinessUnitsVO> listOsiBusinessUnitsVO = new ArrayList<ListOsiBusinessUnitsVO>();
		LOGGER.info("getOsiBusinessUnitsList : Begin");
		try{
			listOsiBusinessUnitsVO =  osibusinessunitsDao.getAllOsiBusinessUnitss(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi business units list");
		}
		LOGGER.info("getOsiBusinessUnitsList : End");
		return listOsiBusinessUnitsVO;
		
	}

}
