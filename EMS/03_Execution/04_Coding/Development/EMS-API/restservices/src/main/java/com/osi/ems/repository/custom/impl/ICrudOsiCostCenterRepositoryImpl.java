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

import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.repository.custom.ICrudOsiCostCenterRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class ICrudOsiCostCenterRepositoryImpl implements ICrudOsiCostCenterRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public ICrudOsiCostCenterRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(ICrudOsiCostCenterRepositoryImpl.class);
	
	@Override
	public OsiCostCenter findOneWithLock(Integer id) throws DataAccessException {
		LOGGER.info("findOneWithLock :: Begin ");
		OsiCostCenter crudOsiCostCenterEntity = null;
		try {
			crudOsiCostCenterEntity = entityManager.find(OsiCostCenter.class, id, LockModeType.PESSIMISTIC_WRITE);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("findOneWithLock :: End ");
		return crudOsiCostCenterEntity;
	}

}
