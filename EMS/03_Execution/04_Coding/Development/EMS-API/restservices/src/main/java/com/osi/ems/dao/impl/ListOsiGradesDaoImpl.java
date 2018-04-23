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

import com.osi.ems.dao.IListOsiGradesDao;
import com.osi.ems.domain.OsiGrades;
import com.osi.ems.domain.OsiTitles;
import com.osi.ems.mapper.impl.ListOsiGradesAssembler;
import com.osi.ems.mapper.impl.ListOsiTitlesAssembler;
import com.osi.ems.repository.IListOsiGradesRepository;
import com.osi.ems.repository.IListOsiTitlesRepository;
import com.osi.ems.service.dto.ListOsiGradesVO;
import com.osi.ems.service.dto.ListOsiTitlesVO;
import com.osi.ems.service.dto.OsiTitlesDTO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiGradesDaoImpl implements IListOsiGradesDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiGradesRepository osigradesRepository;
	
	@Autowired
	ListOsiGradesAssembler osigradesAssembler;
	
	@Autowired
	IListOsiTitlesRepository osititlesRepository;
	
	@Autowired
	ListOsiTitlesAssembler osititlesAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiGradesDao#getAllOsiGradess()
	 */
	@Override
	public List<ListOsiGradesVO> getAllOsiGradess(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiGradess : Begin");
		List<ListOsiGradesVO> osigradesVOs = new  ArrayList<ListOsiGradesVO>(0);
		try{
			osigradesVOs=osigradesAssembler.toListOsiGradesVOList((List<OsiGrades>)osigradesRepository.findAllOsiGradesEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiGradess : End");
		return osigradesVOs;
	}

	@Override
	public List<ListOsiTitlesVO> getAllOsiTitles() throws com.osi.urm.exception.DataAccessException {
		LOGGER.info("getAllOsiTitles : Begin");
		List<ListOsiTitlesVO> osititlesVOs = new  ArrayList<ListOsiTitlesVO>(0);
		try{
			osititlesVOs=osititlesAssembler.toListOsiTitlesVOList((List<OsiTitles>)osititlesRepository.findAll());
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiTitles : End");
		return osititlesVOs;
	}
	@Override
	public List<OsiTitlesDTO> getAllOsiTitleGrades() throws DataAccessException {
		LOGGER.info("getAllOsiTitles : Begin");
		List<OsiTitlesDTO> osititlesVOs = new  ArrayList<OsiTitlesDTO>(0);
		try{
			osititlesVOs = osititlesRepository.findAllTitleGrades();
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiTitles : End");
		return osititlesVOs;
	}
}
