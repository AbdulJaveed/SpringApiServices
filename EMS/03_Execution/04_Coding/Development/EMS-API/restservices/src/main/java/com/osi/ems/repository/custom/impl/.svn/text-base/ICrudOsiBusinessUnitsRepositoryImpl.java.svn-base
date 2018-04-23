/**
 * 
 */
package com.osi.ems.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.ems.repository.custom.ICrudOsiBusinessUnitsRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class ICrudOsiBusinessUnitsRepositoryImpl implements ICrudOsiBusinessUnitsRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	private final Logger LOGGER = Logger.getLogger(ICrudOsiBusinessUnitsRepositoryImpl.class);
	public ICrudOsiBusinessUnitsRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public OsiBusinessUnits findOneWithLock(Integer id) throws DataAccessException {
		LOGGER.info("findOneWithLock :: Begin ");
		OsiBusinessUnits crudOsiBusinessUnitsEntity = null;
		try {
			
			crudOsiBusinessUnitsEntity = entityManager.find(OsiBusinessUnits.class, id, LockModeType.PESSIMISTIC_WRITE);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("findOneWithLock :: End ");
		return crudOsiBusinessUnitsEntity;
	}

}
