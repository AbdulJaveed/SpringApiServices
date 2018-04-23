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

import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.repository.custom.IListOsiJobCodesRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiJobCodesRepositoryImpl implements IListOsiJobCodesRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public IListOsiJobCodesRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(IListOsiJobCodesRepositoryImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiJobCodesRepositoryCustom#findAllOsiJobCodesEnitiesWithSearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiJobCodes> findAllOsiJobCodesEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		// TODO Auto-generated method stub
		int count = 1;
		String regex="(.)([A-Z])";
		String replacement = "$1_$2";
		OsiJobCodes OsiJobCodes = null;
		LOGGER.info("findAllOsiJobCodesEnitiesWithSearchCriteria :: Begin ");
		List<OsiJobCodes> osijobcodesEntities = new ArrayList<OsiJobCodes>(0);
		try{
				String sql =" select jobCodeId, jobCodeName, jobCodeDescription, active,orgId from osiJobCodes where 1=1";
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
					OsiJobCodes = new OsiJobCodes();
					OsiJobCodes.setJobCodeId(Integer.parseInt(result[0].toString()));
						OsiJobCodes.setJobCodeName((String)result[1]);
			OsiJobCodes.setJobCodeDescription((String)result[2]);
			OsiJobCodes.setActive(Integer.parseInt(result[3].toString()));
			OsiJobCodes.setOrgId(Integer.parseInt(result[4].toString()));
					osijobcodesEntities.add(OsiJobCodes);
				}
		}
		catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
		}
		LOGGER.info("findAllOsiJobCodesEnitiesWithSearchCriteria :: End ");
        return osijobcodesEntities;
	}

}
