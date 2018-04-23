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

import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.ems.repository.custom.IListOsiBusinessUnitsRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiBusinessUnitsRepositoryImpl implements IListOsiBusinessUnitsRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public IListOsiBusinessUnitsRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(IListOsiBusinessUnitsRepositoryImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiBusinessUnitsRepositoryCustom#findAllOsiBusinessUnitsEnitiesWithSearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiBusinessUnits> findAllOsiBusinessUnitsEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("findAllOsiBusinessUnitsEnitiesWithSearchCriteria :: Begin ");
		OsiBusinessUnits osibusinessunitsEntity = null;
		List<OsiBusinessUnits> osibusinessunitsEntities = new ArrayList<OsiBusinessUnits>(0);
	try{
			int count = 1;
			String regex="(.)([A-Z])";
			String replacement = "$1_$2";
			
		
			String sql ="select buId, buShortName, buLongName, active from osiBusinessUnits where 1=1";
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
				osibusinessunitsEntity = new OsiBusinessUnits();
				osibusinessunitsEntity.setBuId((Integer)result[0]);
					osibusinessunitsEntity.setBuShortName((String)result[1]);
		osibusinessunitsEntity.setBuLongName((String)result[2]);
		osibusinessunitsEntity.setActive((Integer)result[3]);
	
				osibusinessunitsEntities.add(osibusinessunitsEntity);
			}
	}catch (NoResultException e) {
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1002", " No Records Found");
	}catch(Exception e){
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
	}
	LOGGER.info("findOneWithLock :: End ");
        return osibusinessunitsEntities;
	}

}
