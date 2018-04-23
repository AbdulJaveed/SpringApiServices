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

import com.osi.ems.domain.OsiGrades;
import com.osi.ems.repository.custom.ICrudOsiGradesRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class ICrudOsiGradesRepositoryImpl implements ICrudOsiGradesRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public ICrudOsiGradesRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(ICrudOsiGradesRepositoryImpl.class);
	
	@Override
	public OsiGrades findOneWithLock(Integer id) throws DataAccessException {
		LOGGER.info("findOneWithLock :: Begin ");
		OsiGrades crudOsiGrades = null;
		try {
			//entityManager.createNativeQuery("set local lock_timeout to '5s';").executeUpdate();
			crudOsiGrades = entityManager.find(OsiGrades.class, id, LockModeType.PESSIMISTIC_WRITE);
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("findOneWithLock :: End ");
		return crudOsiGrades;
	}


}
