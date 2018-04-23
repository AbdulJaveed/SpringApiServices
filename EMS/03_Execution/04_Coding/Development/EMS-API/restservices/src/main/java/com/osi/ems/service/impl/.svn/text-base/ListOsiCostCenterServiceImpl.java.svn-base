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

import com.osi.ems.dao.IListOsiCostCenterDao;
import com.osi.ems.service.IListOsiCostCenterService;
import com.osi.ems.service.dto.ListOsiCostCenterVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiCostCenterServiceImpl implements IListOsiCostCenterService {

	@Autowired
	IListOsiCostCenterDao osicostcenterDao;
	
	private final Logger LOGGER = Logger.getLogger(ListOsiCostCenterServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiCostCenterService#getOsiCostCenterList()
	 */
	@Override
	public List<ListOsiCostCenterVO> getOsiCostCenterList(Map<String, String> searchFieldsMap) throws DataAccessException, BusinessException {
		List<ListOsiCostCenterVO> listOsiCostCenterVO = new ArrayList<ListOsiCostCenterVO>();
		LOGGER.info("getOsiCostCenterList : Begin");
		try{
			listOsiCostCenterVO =  osicostcenterDao.getAllOsiCostCenters(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi cost center list");
		}
		LOGGER.info("getOsiCostCenterList : End");
		return listOsiCostCenterVO;
	}

}
