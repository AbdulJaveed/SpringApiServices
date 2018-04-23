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

import com.osi.ems.dao.IListOsiCostCenterDao;
import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.mapper.impl.ListOsiCostCenterAssembler;
import com.osi.ems.repository.IListOsiCostCenterRepository;
import com.osi.ems.service.dto.ListOsiCostCenterVO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */
@Component
public class ListOsiCostCenterDaoImpl implements IListOsiCostCenterDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IListOsiCostCenterRepository osicostcenterRepository;
	
	@Autowired
	ListOsiCostCenterAssembler osicostcenterAssembler;

	/* (non-Javadoc)
	 * @see com.osi.ems.dao.IOsiCostCenterDao#getAllOsiCostCenters()
	 */
	@Override
	public List<ListOsiCostCenterVO> getAllOsiCostCenters(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("getAllOsiCostCenters : Begin");
		List<ListOsiCostCenterVO> osicostcenterVOs = new  ArrayList<ListOsiCostCenterVO>(0);
		try{
			osicostcenterVOs=osicostcenterAssembler.toListOsiCostCenterVOList((List<OsiCostCenter>)osicostcenterRepository.findAllOsiCostCenterEnitiesWithSearchCriteria(searchFieldsMap));
		} catch (NoResultException ne) {
			LOGGER.error("Exception : " + ne.getMessage());			
			throw new DataAccessException("ERR_1002", "No Records Found");
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getAllOsiCostCenters : End");
		return osicostcenterVOs;
	}
}
