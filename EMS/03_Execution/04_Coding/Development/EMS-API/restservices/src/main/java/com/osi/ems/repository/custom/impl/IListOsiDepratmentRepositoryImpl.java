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

import com.osi.ems.domain.OsiDepartment;
import com.osi.ems.repository.custom.IListOsiDepratmentRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiDepratmentRepositoryImpl implements IListOsiDepratmentRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public IListOsiDepratmentRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiDepratmentRepositoryCustom#findAllOsiDepratmentEnitiesWithSearchCriteria(java.lang.String)
	 */
	private final Logger LOGGER = Logger.getLogger(IListOsiDepratmentRepositoryImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiDepartment> findAllOsiDepratmentEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		// TODO Auto-generated method stub
		LOGGER.info("findAllOsiDepratmentEnitiesWithSearchCriteria :: Begin ");
		int count = 1;
		String regex="(.)([A-Z])";
		String replacement = "$1_$2";
		OsiDepartment osidepratmentEntity = null;
		List<OsiDepartment> osidepratmentEntities = new ArrayList<OsiDepartment>(0);
		try{
			String sql ="select deptId, deptShortName, deptLongName, active from osiDepartment where 1=1";
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
				osidepratmentEntity = new OsiDepartment();
				osidepratmentEntity.setDeptId((Integer)result[0]);
					osidepratmentEntity.setDeptShortName((String)result[1]);
		osidepratmentEntity.setDeptLongName((String)result[2]);
		osidepratmentEntity.setActive((Integer)result[3]);
	
				osidepratmentEntities.add(osidepratmentEntity);
		}
	}
		catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("findAllOsiDepratmentEnitiesWithSearchCriteria :: End ");
        return osidepratmentEntities;
	}

}
