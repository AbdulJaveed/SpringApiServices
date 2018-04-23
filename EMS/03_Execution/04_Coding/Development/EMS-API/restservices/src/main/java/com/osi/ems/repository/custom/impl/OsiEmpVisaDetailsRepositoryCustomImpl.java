package com.osi.ems.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiEmpVisaDetails;
import com.osi.ems.repository.custom.OsiEmpVisaDetailsRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiEmpVisaDetailsRepositoryCustomImpl implements OsiEmpVisaDetailsRepositoryCustom {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<OsiEmpVisaDetails> getVisaInfoByEmployeeId(Integer employeeId) throws DataAccessException {
		LOGGER.info("getVisaInfoByEmployeeId : Begin");
		List<OsiEmpVisaDetails> visaDetails = new ArrayList<OsiEmpVisaDetails>();
		try {
			String queryStr = "select vd from OsiEmpVisaDetails vd where vd.employeeId = :employeeId order by vd.dateOfIssue";
			TypedQuery<OsiEmpVisaDetails> query = entityManager.createQuery(queryStr, OsiEmpVisaDetails.class)
															   .setParameter("employeeId", employeeId);
			visaDetails = query.getResultList();
		} catch(NoResultException ne) {
			LOGGER.info("Exception : "+ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Records found in employee visa details");
		}catch (Exception e) {
			LOGGER.info("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getVisaInfoByEmployeeId : End");
		return visaDetails;		
	}
	
	@Override
	public void removeVisaInfoByVisaId(List<Integer> visaIds) throws DataAccessException {
		LOGGER.info("removeVisaInfoByVisaId : Begin");
		try {
			String queryStr = "delete from OsiEmpVisaDetails where visaId in (:visaIds)";
			Query query = this.entityManager.createQuery(queryStr);
			if(null != visaIds && visaIds.size() > 0) {
				query.setParameter("visaIds", visaIds);
			}
			query.executeUpdate();
		} catch (Exception e) {
			LOGGER.info("Exception : "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} 
		LOGGER.info("removeVisaInfoByVisaId : End");
	}

}
