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

import com.osi.ems.dao.IListOsiJobCodesDao;
import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.mapper.impl.ListOsiJobCodesAssembler;
import com.osi.ems.repository.IListOsiJobCodesRepository;
import com.osi.ems.service.dto.ListOsiJobCodesVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiJobCodesDaoImpl implements IListOsiJobCodesDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiJobCodesRepository osijobcodesRepository;
	
	@Autowired
	ListOsiJobCodesAssembler osijobcodesAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiJobCodesDao#getAllOsiJobCodess()
	 */
	@Override
	public List<ListOsiJobCodesVO> getAllOsiJobCodess(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiJobCodess : Begin");
		List<ListOsiJobCodesVO> osijobcodesVOs = new  ArrayList<ListOsiJobCodesVO>(0);
		try{
			osijobcodesVOs=osijobcodesAssembler.toListOsiJobCodesVOList((List<OsiJobCodes>)osijobcodesRepository.findAllOsiJobCodesEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiJobCodess : End");
		return osijobcodesVOs;
	}
}
