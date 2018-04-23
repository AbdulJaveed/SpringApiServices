/**
 * 
 */
package com.osi.ems.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.IListOsiCertificationsDao;
import com.osi.ems.domain.OsiCertifications;
import com.osi.ems.mapper.impl.ListOsiCertificationsAssembler;
import com.osi.ems.repository.IListOsiCertificationsRepository;
import com.osi.ems.service.dto.ListOsiCertificationsVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiCertificationsDaoImpl implements IListOsiCertificationsDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiCertificationsRepository osicertificationsRepository;
	
	@Autowired
	ListOsiCertificationsAssembler osicertificationsAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCertificationsDao#getAllOsiCertificationss()
	 */
	@Override
	public List<ListOsiCertificationsVO> getAllOsiCertificationss(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiCertificationss : Begin");
		List<ListOsiCertificationsVO> osicertificationsVOs = new  ArrayList<ListOsiCertificationsVO>(0);
		try{
			osicertificationsVOs=osicertificationsAssembler.toListOsiCertificationsVOList((List<OsiCertifications>)osicertificationsRepository.findAllOsiCertificationsEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiCertificationss : End");
		return osicertificationsVOs;
	}
}
