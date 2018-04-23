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

import com.osi.ems.dao.IListOsiGradesDao;
import com.osi.ems.service.IListOsiGradesService;
import com.osi.ems.service.dto.ListOsiGradesVO;
import com.osi.ems.service.dto.ListOsiTitlesVO;
import com.osi.ems.service.dto.OsiTitlesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiGradesServiceImpl implements IListOsiGradesService {

	@Autowired
	IListOsiGradesDao osigradesDao;
	
	
	private final Logger LOGGER = Logger.getLogger(ListOsiGradesServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.osi.ems.service.IOsiGradesService#getOsiGradesList()
	 */
	@Override
	public List<ListOsiGradesVO> getOsiGradesList(Map<String, String> searchFieldsMap) throws BusinessException, DataAccessException {
		// TODO Auto-generated method stub
		
		List<ListOsiGradesVO> listOsiGradesVO = new ArrayList<ListOsiGradesVO>();
		LOGGER.info("getOsiGradesList : Begin");
		try{
			listOsiGradesVO =  osigradesDao.getAllOsiGradess(searchFieldsMap);
		}catch (DataAccessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
		}catch(Exception e){
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new BusinessException("ERR_1000", "Error occured while retrieving the osi grades list");
		}
		LOGGER.info("getOsiGradesList : End");
		return listOsiGradesVO;
	}


	@Override
	public List<ListOsiTitlesVO> getOsiTitlesList() throws BusinessException, DataAccessException {	
		List<ListOsiTitlesVO> listOsiTitlesVO = new ArrayList<ListOsiTitlesVO>();
		LOGGER.info("getOsiTitlesList : Begin");
		try{
			listOsiTitlesVO =  osigradesDao.getAllOsiTitles();
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
	
	@Override
	public List<OsiTitlesDTO> getOsiTitleGradesList() throws BusinessException, DataAccessException {	
		List<OsiTitlesDTO> listOsiTitlesVO = new ArrayList<OsiTitlesDTO>();
		LOGGER.info("getOsiTitlesList : Begin");
		try{
			listOsiTitlesVO =  osigradesDao.getAllOsiTitleGrades();
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
