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

import com.osi.ems.dao.IListOsiDepratmentDao;
import com.osi.ems.service.IListOsiDepratmentService;
import com.osi.ems.service.dto.ListOsiDepartmentVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiDepratmentServiceImpl implements IListOsiDepratmentService {

	@Autowired
	IListOsiDepratmentDao osidepratmentDao;
	
	private final Logger LOGGER = Logger.getLogger(ListOsiDepratmentServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiDepratmentService#getOsiDepratmentList()
	 */
	@Override
	public List<ListOsiDepartmentVO> getOsiDepratmentList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
	
		List<ListOsiDepartmentVO> listOsiDepartmentVO = new ArrayList<ListOsiDepartmentVO>();
		LOGGER.info("getOsiCostCenterList : Begin");
		try{
			listOsiDepartmentVO =  osidepratmentDao.getAllOsiDepratments(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi departments list");
		}
		LOGGER.info("getOsiCostCenterList : End");
		return listOsiDepartmentVO;
	}

}
