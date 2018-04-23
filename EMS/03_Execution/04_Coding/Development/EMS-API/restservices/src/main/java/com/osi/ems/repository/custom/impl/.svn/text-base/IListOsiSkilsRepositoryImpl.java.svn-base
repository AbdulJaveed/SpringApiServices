/**
 * 
 */
package com.osi.ems.repository.custom.impl;

import java.math.BigDecimal;
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

import com.osi.ems.domain.OsiSkils;
import com.osi.ems.repository.custom.IListOsiSkilsRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiSkilsRepositoryImpl implements IListOsiSkilsRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public IListOsiSkilsRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(IListOsiSkilsRepositoryImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiSkilsRepositoryCustom#findAllOsiSkilsEnitiesWithSearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiSkils> findAllOsiSkilsEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		// TODO Auto-generated method stub
		int count = 1;
		LOGGER.info("findAllOsiSkilsEnitiesWithSearchCriteria :: Begin ");
		String regex="(.)([A-Z])";
		String replacement = "$1_$2";
		OsiSkils osiskilsEntity = null;
		List<OsiSkils> osiskilsEntities = new ArrayList<OsiSkils>(0);
		try{
			String sql ="select skillId, skillName, skillDescription, skillDisplayName, skillVersion, active from osiSkils where 1=1";
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
				osiskilsEntity = new OsiSkils();
				osiskilsEntity.setSkillId(Integer.parseInt(result[0].toString()));
					osiskilsEntity.setSkillName((String)result[1]);
		osiskilsEntity.setSkillDescription((String)result[2]);
		osiskilsEntity.setSkillDisplayName((String)result[3]);
		osiskilsEntity.setSkillVersion((BigDecimal)result[4]);
		osiskilsEntity.setActive((Integer)result[5]);
	
				osiskilsEntities.add(osiskilsEntity);
			}
	}
	catch (NoResultException e) {
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1002", " No Records Found");
	}catch(Exception e){
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
	}
	LOGGER.info("findAllOsiSkilsEnitiesWithSearchCriteria :: End ");
        return osiskilsEntities;
	}

}
