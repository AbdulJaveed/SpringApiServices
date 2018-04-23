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

import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.repository.custom.IListOsiCostCenterRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiCostCenterRepositoryImpl implements IListOsiCostCenterRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public IListOsiCostCenterRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(IListOsiCostCenterRepositoryImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiCostCenterRepositoryCustom#findAllOsiCostCenterEnitiesWithSearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiCostCenter> findAllOsiCostCenterEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		// TODO Auto-generated method stub
		int count = 1;
		LOGGER.info("findAllOsiCostCenterEnitiesWithSearchCriteria :: Begin ");
		String regex="(.)([A-Z])";
		String replacement = "$1_$2";
		OsiCostCenter osicostcenterEntity = null;
		List<OsiCostCenter> osicostcenterEntities = new ArrayList<OsiCostCenter>(0);
		try{
			String sql ="select ccId, ccShortName, ccLongName, active from osiCostCenter where 1=1";
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
				osicostcenterEntity = new OsiCostCenter();
				osicostcenterEntity.setCcId(Integer.parseInt(result[0].toString()));
					osicostcenterEntity.setCcShortName((String)result[1]);
		osicostcenterEntity.setCcLongName((String)result[2]);
		osicostcenterEntity.setActive((Integer)result[3]);
	
				osicostcenterEntities.add(osicostcenterEntity);
			}
		}
			catch (NoResultException e) {
				LOGGER.error("Exception occured "+e.getMessage());
				throw new DataAccessException("ERR_1002", " No Records Found");
			}catch(Exception e){
				LOGGER.error("Exception occured "+e.getMessage());
				throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
			}
			LOGGER.info("findAllOsiCostCenterEnitiesWithSearchCriteria :: End ");
        return osicostcenterEntities;
	}

}
