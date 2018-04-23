package com.osi.ems.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCategories;
import com.osi.ems.repository.custom.OsiCategoriesRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiCategoriesRepositoryCustomImpl implements OsiCategoriesRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private Environment env;

	private final Logger LOGGER = Logger.getLogger(OsiCategoriesRepositoryCustomImpl.class);
	@Override
	public List<OsiCategories> getCategoryFlexFields(String categoryName, int orgId) throws DataAccessException{
		
		LOGGER.info("getCategoryFlexFields :: Begin ");
		List<OsiCategories> categoryFieldList = new ArrayList<OsiCategories>();
		String queryStr = "select Distinct oc "
				+ " from OsiCategories oc, OsiCategoryFields ocf"
				+ " where oc.id = ocf.osiCategory.id "
				+ " and oc.orgId = :orgId and oc.catName = :categoryName";
		try {
			TypedQuery<OsiCategories> query = entityManager.createQuery(queryStr, OsiCategories.class).setParameter("orgId", orgId).setParameter("categoryName", categoryName);
		
			categoryFieldList = query.getResultList();
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getCategoryFlexFields :: End ");
		return categoryFieldList;		
	}
	@Override
	public List<OsiCategories> searchEntries(String categoryName, String tableName, Integer orgId) throws DataAccessException {
		List<OsiCategories> osiCategoryList=null;
	
		LOGGER.info("searchEntries :: Begin ");
		osiCategoryList = new ArrayList<>();
		List<OsiCategories> queryList =  new ArrayList<>();
		try 
		{
			String catquery = "select c from OsiCategories c where 1=1 ";
			
			if(categoryName != null )
			{
				catquery=catquery+" AND c.catName =:catName";
			}
			if(tableName !=null)
			{
				catquery=catquery+" AND c.tblName = :tblName";
			}
			if(orgId != null)
			{
				catquery=catquery+" AND c.orgId = :orgId";
			}
			Query query =this.entityManager.createQuery(catquery);
			if(categoryName != null )
			{
				query.setParameter("catName", categoryName);
			}
			if(tableName !=null)
			{
				query.setParameter("tblName", tableName);
			}
			if(orgId != null)
			{
				query.setParameter("orgId", orgId);
			}
			queryList=query.getResultList();
				
				
				for (OsiCategories c : queryList) {				
					
						osiCategoryList.add(c);
					
				}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getCategoryFlexFields :: End ");
		return osiCategoryList;
	}

	@Override
	public void removeCategoryFieldsByCategoryId(Integer id) throws DataAccessException {
		LOGGER.info("removeCategoryFieldsByCategoryId :: Begin ");
		try 
		{
			String catquery = " delete from OsiCategoryFields where categoryId =:catId";
			Query query =this.entityManager.createQuery(catquery);
			if(id != null )
			{
				query.setParameter("catId", id);
			}
			query.getResultList();
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getCategoryFlexFields :: End ");
	}

	@Override
	public List<String> findColumnsByTableName(String tableName) throws DataAccessException {
	List<String> columnNames=null;
	columnNames = new ArrayList<>();
	LOGGER.info("findColumnsByTableName :: Begin ");
	Query columnsQuery = entityManager.createNativeQuery("select column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"+tableName+"' AND column_name REGEXP '[[:digit:]]$' AND column_name LIKE 'Attribute%'");
	List<String> columnsResult = columnsQuery.getResultList();
	String columns = "";
	int count = 1;
	for(String columnName :columnsResult){
		if(count == columnsResult.size())
			columns+="'"+columnName+"'";
		else
			columns+="'"+columnName+"',";
		
		count++;
	}
	try 
		{
			Query query1 = entityManager.createNativeQuery("show columns from "+tableName+" where Field in ("+columns+")");
			List<Object[]> results = query1.getResultList();
			for (Object[] obj : results) {
				columnNames.add((String) obj[0]);
			   
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("findColumnsByTableName :: End ");
		
		return columnNames;
	}
	@Override
	public OsiCategories findCategoryNameByFunctionIdAndOrg(String functionUrl, int orgId) throws DataAccessException {
		OsiCategories categoryInfo = null;
		LOGGER.info("findCategoryNameByFunctionIdAndOrg :: Begin ");
		String queryStr = "select c.category_name, c.inline_popup from osi_functions f, osi_categories c  "
				+ " where f.FUNC_NAME = c.category_name " 
				+ " and f.func_value = :functionUrl and c.org_id = :orgId";
		try {
			functionUrl = "/"+functionUrl;
			Object[] object = (Object[]) entityManager.createNativeQuery(queryStr).setParameter("functionUrl", functionUrl).setParameter("orgId", orgId).getSingleResult();
			System.out.println(object);
			if(null != object) {
				categoryInfo = new OsiCategories();
				categoryInfo.setCatName((String) object[0]);
				categoryInfo.setInlineOrPopup((String) object[1]);
			}
		}catch (NoResultException e) {
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1002", " No Records Found");
		}catch(Exception e){
			LOGGER.error("Exception occured "+e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("findCategoryNameByFunctionIdAndOrg :: End ");
		return categoryInfo;
	}

}
