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

import com.osi.ems.dao.IListOsiTitlesDao;
import com.osi.ems.service.IListOsiTitlesService;
import com.osi.ems.service.dto.ListOsiTitlesVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiTitlesServiceImpl implements IListOsiTitlesService {

	@Autowired
	IListOsiTitlesDao osititlesDao;
	
	private final Logger LOGGER = Logger.getLogger(ListOsiTitlesServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiTitlesService#getOsiTitlesList()
	 */
	@Override
	public List<ListOsiTitlesVO> getOsiTitlesList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException {

		// TODO Auto-generated method stub
		List<ListOsiTitlesVO> listOsiTitlesVO = new ArrayList<ListOsiTitlesVO>();
		LOGGER.info("getOsiTitlesList : Begin");
		try{
			listOsiTitlesVO = osititlesDao.getAllOsiTitless(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi titles");
		}
		LOGGER.info("getOsiTitlesList : End");
		return listOsiTitlesVO;
	}

}
