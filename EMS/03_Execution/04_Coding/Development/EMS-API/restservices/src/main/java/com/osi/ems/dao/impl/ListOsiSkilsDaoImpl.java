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

import com.osi.ems.dao.IListOsiSkilsDao;
import com.osi.ems.domain.OsiSkils;
import com.osi.ems.mapper.impl.ListOsiSkilsAssembler;
import com.osi.ems.repository.IListOsiSkilsRepository;
import com.osi.ems.service.dto.ListOsiSkilsVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiSkilsDaoImpl implements IListOsiSkilsDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiSkilsRepository osiskilsRepository;
	
	@Autowired
	ListOsiSkilsAssembler osiskilsAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiSkilsDao#getAllOsiSkilss()
	 */
	@Override
	public List<ListOsiSkilsVO> getAllOsiSkilss(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiSkilss : Begin");
		List<ListOsiSkilsVO> osiskilsVOs = new  ArrayList<ListOsiSkilsVO>(0);
		try{
			osiskilsVOs=osiskilsAssembler.toListOsiSkilsVOList((List<OsiSkils>)osiskilsRepository.findAllOsiSkilsEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiSkilss : End");
		return osiskilsVOs;
	}
}
