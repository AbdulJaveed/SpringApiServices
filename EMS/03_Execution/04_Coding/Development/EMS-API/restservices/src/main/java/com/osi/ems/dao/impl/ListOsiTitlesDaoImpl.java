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

import com.osi.ems.dao.IListOsiTitlesDao;
import com.osi.ems.domain.OsiTitles;
import com.osi.ems.mapper.impl.ListOsiTitlesAssembler;
import com.osi.ems.repository.IListOsiTitlesRepository;
import com.osi.ems.service.dto.ListOsiTitlesVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiTitlesDaoImpl implements IListOsiTitlesDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiTitlesRepository osititlesRepository;
	
	@Autowired
	ListOsiTitlesAssembler osititlesAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiTitlesDao#getAllOsiTitless()
	 */
	@Override
	public List<ListOsiTitlesVO> getAllOsiTitless(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiTitless : Begin");
		List<ListOsiTitlesVO> osititlesVOs = new  ArrayList<ListOsiTitlesVO>(0);
		try{
			osititlesVOs=osititlesAssembler.toListOsiTitlesVOList((List<OsiTitles>)osititlesRepository.findAllOsiTitlesEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} 
		
		LOGGER.info("getAllOsiTitless : End");
		return osititlesVOs;
	}
}
