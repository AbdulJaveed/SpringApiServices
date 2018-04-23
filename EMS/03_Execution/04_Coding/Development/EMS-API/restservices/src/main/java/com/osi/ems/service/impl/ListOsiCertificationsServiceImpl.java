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

import com.osi.ems.dao.IListOsiCertificationsDao;
import com.osi.ems.service.IListOsiCertificationsService;
import com.osi.ems.service.dto.ListOsiCertificationsVO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiCertificationsServiceImpl implements IListOsiCertificationsService {

	@Autowired
	IListOsiCertificationsDao osicertificationsDao;
	
	private final Logger LOGGER = Logger.getLogger(ListOsiCertificationsServiceImpl.class);
	

	@Override
	public List<ListOsiCertificationsVO> getOsiCertificationsList(Map<String, String> searchFieldsMap) throws DataAccessException, BusinessException {
		// TODO Auto-generated method stub
		List<ListOsiCertificationsVO> listOsiCertificationsVO = new ArrayList<ListOsiCertificationsVO>();
		LOGGER.info("getOsiCertificationsList : Begin");
		try{
			listOsiCertificationsVO =  osicertificationsDao.getAllOsiCertificationss(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi certifications list");
		}
		LOGGER.info("getOsiCertificationsList : End");
		return listOsiCertificationsVO;
	}

}
