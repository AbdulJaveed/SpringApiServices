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

import com.osi.ems.dao.IListOsiSkilsDao;
import com.osi.ems.service.IListOsiSkilsService;
import com.osi.ems.service.dto.ListOsiSkilsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiSkilsServiceImpl implements IListOsiSkilsService {

	@Autowired
	IListOsiSkilsDao osiskilsDao;
	
	private final Logger LOGGER = Logger.getLogger(ListOsiSkilsServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiSkilsService#getOsiSkilsList()
	 */
	@Override
	public List<ListOsiSkilsVO> getOsiSkilsList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException {
		
		// TODO Auto-generated method stub
		List<ListOsiSkilsVO> listOsiSkilsVO = new ArrayList<ListOsiSkilsVO>();
		LOGGER.info("getOsiSkilsList : Begin");
		try{
			listOsiSkilsVO =  osiskilsDao.getAllOsiSkilss(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi skills");
		}
		LOGGER.info("getOsiSkilsList : End");
		return listOsiSkilsVO;
	
	}

}
