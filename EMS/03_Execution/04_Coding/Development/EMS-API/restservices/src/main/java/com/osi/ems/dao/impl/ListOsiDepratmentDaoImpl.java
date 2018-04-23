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

import com.osi.ems.dao.IListOsiDepratmentDao;
import com.osi.ems.domain.OsiDepartment;
import com.osi.ems.mapper.impl.ListOsiDepratmentAssembler;
import com.osi.ems.repository.IListOsiDepratmentRepository;
import com.osi.ems.service.dto.ListOsiDepartmentVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiDepratmentDaoImpl implements IListOsiDepratmentDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiDepratmentRepository osidepratmentRepository;
	
	@Autowired
	ListOsiDepratmentAssembler osidepratmentAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiDepratmentDao#getAllOsiDepratments()
	 */
	@Override
	public List<ListOsiDepartmentVO> getAllOsiDepratments(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiDepratments : Begin");
		List<ListOsiDepartmentVO> osidepratmentVOs = new  ArrayList<ListOsiDepartmentVO>(0);
		try{
			osidepratmentVOs=osidepratmentAssembler.toListOsiDepratmentVOList((List<OsiDepartment>)osidepratmentRepository.findAllOsiDepratmentEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiDepratments : End");
		return osidepratmentVOs;
	}
}
