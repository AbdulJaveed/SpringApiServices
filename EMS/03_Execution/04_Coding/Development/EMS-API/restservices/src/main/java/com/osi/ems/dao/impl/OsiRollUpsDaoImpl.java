package com.osi.ems.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.osi.ems.dao.OsiRollUpsDao;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiRollUpsDaoImpl implements OsiRollUpsDao{
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String checkActiveStatus(String param) throws BusinessException, DataAccessException {
		LOGGER.info("checkActiveStatus : Begin");
		StringBuilder queryString = new StringBuilder("SELECT active FROM osi_rollups WHERE ");
		queryString.append("SEGMENT1 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT2 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT3 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT4 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT5 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT6 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT7 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT8 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT9 LIKE UPPER(CONCAT('%',:param,'%')) OR ");
		queryString.append("SEGMENT10 LIKE UPPER(CONCAT('%',:param,'%')) ");
		queryString.append("and active=1");

		String result="0";
		try {
			Query query = entityManager
					.createNativeQuery(queryString.toString())
					.setParameter("param", param);
			
			result = query.getSingleResult().toString();
		} catch (NoResultException ne) {
			/*LOGGER.error("Exception : "+ ne.getMessage());
			throw new BusinessException("ERR_1002", "No record found");*/
			result = "0";
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new BusinessException("ERR_1000", "Exception occured while executing");
		} 
		LOGGER.info("checkActiveStatus : End");
		return result;
	}

}
