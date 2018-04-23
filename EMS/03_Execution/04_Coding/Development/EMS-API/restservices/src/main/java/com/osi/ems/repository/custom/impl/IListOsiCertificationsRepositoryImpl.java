/**
 * 
 */
package com.osi.ems.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.domain.OsiCertifications;
import com.osi.ems.repository.custom.IListOsiCertificationsRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiCertificationsRepositoryImpl implements IListOsiCertificationsRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public IListOsiCertificationsRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(IListOsiCertificationsRepositoryImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiCertificationsRepositoryCustom#findAllOsiCertificationsEnitiesWithSearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiCertifications> findAllOsiCertificationsEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		LOGGER.info("findOneWithLock :: Begin ");
		int count = 1;
		String regex="(.)([A-Z])";
		String replacement = "$1_$2";
		OsiCertifications osicertificationsEntity = null;
		List<OsiCertifications> osicertificationsEntities = new ArrayList<OsiCertifications>(0);
		try{
			String sql ="select certificationId, certificationName, certificationCode, issuedBy, active from osiCertifications where 1=1";
			Set<String> keySet = searchFieldsMap.keySet();
			for (Object searchField : keySet) {
			if(searchFieldsMap.get(searchField)!=null && !searchFieldsMap.get(searchField).equals("")){
				searchField = searchField.toString().replaceAll(regex, replacement).toLowerCase();
				sql = sql + " and "+searchField+" like concat('%',?,'%')";
				}
			}
			Query query = entityManager.createNativeQuery(sql.replaceAll(regex, replacement).toLowerCase());
			for (Object searchField : keySet) {
				if(searchFieldsMap.get(searchField)!=null && !searchFieldsMap.get(searchField).equals("")){
				query.setParameter(count, searchFieldsMap.get(searchField));
				count++;
				}
			}
			
			List<Object[]> results = query.getResultList();
			
			for (Object[] result : results) {
				osicertificationsEntity = new OsiCertifications();
				osicertificationsEntity.setCertificationId(Integer.parseInt(result[0].toString()));
					osicertificationsEntity.setCertificationName((String)result[1]);
		osicertificationsEntity.setCertificationCode((String)result[2]);
		osicertificationsEntity.setIssuedBy((String)result[3]);
		osicertificationsEntity.setActive((Integer)result[4]);
	
				osicertificationsEntities.add(osicertificationsEntity);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("findOneWithLock :: End ");
        return osicertificationsEntities;
	}

}
