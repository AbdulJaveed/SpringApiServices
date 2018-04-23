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

import com.osi.ems.domain.OsiGrades;
import com.osi.ems.repository.custom.IListOsiGradesRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiGradesRepositoryImpl implements IListOsiGradesRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;
	private final Logger LOGGER = Logger.getLogger(IListOsiGradesRepositoryImpl.class);
	/**
	 * 
	 */
	public IListOsiGradesRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiGradesRepositoryCustom#findAllOsiGradesEnitiesWithSearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiGrades> findAllOsiGradesEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		// TODO Auto-generated method stub
		int count = 1;
		LOGGER.info("findAllOsiGradesEnitiesWithSearchCriteria :: Begin ");
		String regex="(.)([A-Z])";
		String replacement = "$1_$2";
		OsiGrades OsiGrades = null;
		List<OsiGrades> osigradesEntities = new ArrayList<OsiGrades>(0);
		try{
				String sql ="select gradeId, gradeName, gradeDescription, costPerHour, costPerMonth, revPerHour, revPerMonth,orgId, seq from osiGrades where 1=1";
				
				//String sql ="select gradeId, gradeName, gradeDescription from osiGrades where 1=1";
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
					OsiGrades = new OsiGrades();
					OsiGrades.setGradeId(Integer.parseInt(result[0].toString()));
						OsiGrades.setGradeName((String)result[1]);
						OsiGrades.setGradeDescription((String)result[2]);
						OsiGrades.setCostPerHour((BigDecimal)result[3]);
						OsiGrades.setCostPerMonth((BigDecimal)result[4]);
						OsiGrades.setRevPerHour((BigDecimal)result[5]);
						OsiGrades.setRevPerMonth((BigDecimal)result[6]);
			OsiGrades.setOrgId((Integer)result[7]);
			OsiGrades.setSeq((Integer)result[8]);
					osigradesEntities.add(OsiGrades);
				}
			}
	catch (NoResultException e) {
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1002", " No Records Found");
	}catch(Exception e){
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
	}
	LOGGER.info("findAllOsiGradesEnitiesWithSearchCriteria :: End ");
        return osigradesEntities;
	}

}
