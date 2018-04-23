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

import com.osi.ems.dao.IListOsiBusinessUnitsDao;
import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.ems.mapper.impl.ListOsiBusinessUnitsAssembler;
import com.osi.ems.repository.IListOsiBusinessUnitsRepository;
import com.osi.ems.service.dto.ListOsiBusinessUnitsVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiBusinessUnitsDaoImpl implements IListOsiBusinessUnitsDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiBusinessUnitsRepository osibusinessunitsRepository;
	
	@Autowired
	ListOsiBusinessUnitsAssembler osibusinessunitsAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiBusinessUnitsDao#getAllOsiBusinessUnitss()
	 */
	@Override
	public List<ListOsiBusinessUnitsVO> getAllOsiBusinessUnitss(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiBusinessUnitss : Begin");
		List<ListOsiBusinessUnitsVO> osibusinessunitsVOs = new  ArrayList<ListOsiBusinessUnitsVO>(0);
		try{
			osibusinessunitsVOs=osibusinessunitsAssembler.toListOsiBusinessUnitsVOList((List<OsiBusinessUnits>)osibusinessunitsRepository.findAllOsiBusinessUnitsEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiBusinessUnitss : End");
		return osibusinessunitsVOs;
	}
}
