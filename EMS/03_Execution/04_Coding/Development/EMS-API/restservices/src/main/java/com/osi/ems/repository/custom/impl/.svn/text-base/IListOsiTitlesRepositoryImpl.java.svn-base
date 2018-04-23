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

import com.osi.ems.domain.OsiTitles;
import com.osi.ems.repository.custom.IListOsiTitlesRepositoryCustom;
import com.osi.ems.service.dto.OsiTitlesDTO;
import com.osi.urm.exception.DataAccessException;

/**
 * @author osi
 *
 */

@Repository
@Transactional(readOnly = true)
public class IListOsiTitlesRepositoryImpl implements IListOsiTitlesRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	/**
	 * 
	 */
	public IListOsiTitlesRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger LOGGER = Logger.getLogger(IListOsiTitlesRepositoryImpl.class);
	/* (non-Javadoc)
	 * @see com.osi.ems.repository.custom.IOsiTitlesRepositoryCustom#findAllOsiTitlesEnitiesWithSearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiTitles> findAllOsiTitlesEnitiesWithSearchCriteria(Map<String, String> searchFieldsMap) throws DataAccessException {
		// TODO Auto-generated method stub
		int count = 1;
		String regex="(.)([A-Z])";
		String replacement = "$1_$2";
		OsiTitles osititlesEntity = null;
		List<OsiTitles> osititlesEntities = new ArrayList<OsiTitles>(0);
		LOGGER.info("findAllOsiTitlesEnitiesWithSearchCriteria :: Begin ");
		try{
			String sql ="select titleId, titleCode, titleShortName, titleLongName from osiTitles where 1=1";
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
				osititlesEntity = new OsiTitles();
				osititlesEntity.setTitleId(Integer.parseInt(result[0].toString()));
					osititlesEntity.setTitleCode((String)result[1]);
		osititlesEntity.setTitleShortName((String)result[2]);
		osititlesEntity.setTitleLongName((String)result[3]);
	
				osititlesEntities.add(osititlesEntity);
			}
	}
	catch (NoResultException e) {
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1002", " No Records Found");
	}catch(Exception e){
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1000", "Exception occured while retreiving");
	}
	LOGGER.info("findAllOsiTitlesEnitiesWithSearchCriteria :: End ");
        return osititlesEntities;
	}
	@Override
	public List<OsiTitlesDTO> findAllTitleGrades() throws DataAccessException {
		
		OsiTitlesDTO osititlesEntity = null;
		List<OsiTitlesDTO> osititlesEntities = new ArrayList<OsiTitlesDTO>(0);
		LOGGER.info("findAllTitleGrades :: Begin ");
		try{
			String sql ="select t.TITLE_SHORT_NAME,tg.GRADE_ID from osi_title_grades tg join osi_titles t on t.TITLE_ID = tg.TITLE_ID";
			
			Query query = entityManager.createNativeQuery(sql);
			
			List<Object[]> results = query.getResultList();
			
			for (Object[] result : results) {
				osititlesEntity = new OsiTitlesDTO();
				osititlesEntity.setTitleShortName((String)result[0]);
				osititlesEntity.setGradeId(Integer.parseInt(result[1].toString()));
				
				osititlesEntities.add(osititlesEntity);
			}
	}
	catch (NoResultException e) {
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1002", " No Records Found");
	}catch(Exception e){
		LOGGER.error("Exception occured "+e.getMessage());
		throw new DataAccessException("ERR_1000", "Exception occured while retreiving title grdes");
	}
	LOGGER.info("findAllTitleGrades :: End ");
        return osititlesEntities;
	}

}
