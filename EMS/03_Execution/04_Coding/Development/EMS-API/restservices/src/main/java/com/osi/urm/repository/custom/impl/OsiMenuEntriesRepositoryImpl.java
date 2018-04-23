package com.osi.urm.repository.custom.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.osi.urm.domain.OsiMenuEntries;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiMenuEntriesRepositoryCustom;

public class OsiMenuEntriesRepositoryImpl implements OsiMenuEntriesRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;
	
	

	@Override
	public Integer deleteMenuEntry(Integer id, Integer businessGroupId, Integer userId) throws DataAccessException {
	Integer count = 0;
	try {
		String query = "update OsiMenuEntries set active = 0, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and id IN (:id)";
		count = this.entityManager.createQuery(query)
						  .setParameter("updatedBy", userId)
						  .setParameter("updatedDate", new Date())
						  .setParameter("businessGroupId", businessGroupId)
						  .setParameter("id", id)
				          .executeUpdate();
		if(count==0){
			throw new DataAccessException("ERR_1002", null);
		}
	}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			throw new DataAccessException("ERR_1005", e.getMessage());
			// e.printStackTrace();
		}
		return count;
		
		
	}



	@Override
	public List<OsiMenuEntries> searchEntries(Integer businessGroupId,Integer sequence, String prompt, String headerMenuName,
			String subMenuName, String funcName) throws DataAccessException 
	{
		List<OsiMenuEntries> osiMenuEntriesList=null;
		try 
		{
			if(businessGroupId > 0 && businessGroupId !=null)
			{
				StringBuilder queryString= new StringBuilder("SELECT ome FROM OsiMenuEntries ome WHERE businessGroupId= :businessGroupId AND  ome.osiMenusByMenuId IN(select id from OsiMenus) AND (ome.osiMenusBySubMenuId IN(select id from OsiMenus) OR ome.osiFunctions in(select id from OsiFunctions)) AND  (");
				
				String whereClause="";

				if( sequence!=null){
					whereClause=" ome.seq=:sequence";
				}
				if( prompt!=null && prompt!="" ){
					if(whereClause.length()>0){
						//whereClause+=" OR ome.prompt=:prompt";
						whereClause+=" AND ome.prompt LIKE CONCAT('%',:prompt,'%')";
					}
					else{
						//whereClause+=" ome.prompt=:prompt";
						whereClause+=" ome.prompt LIKE CONCAT('%',:prompt,'%')";
					}
				}

				if( headerMenuName!=null ){
					if(whereClause.length()>0){
						//whereClause+=" OR ome.osiMenusByMenuId=(select id from OsiMenus om  where om.menuName=:headerMenuName)";
						whereClause+=" AND ome.osiMenusByMenuId IN (select id from OsiMenus om  where om.menuName LIKE CONCAT('%',:headerMenuName,'%'))";
					}
					else{
						//whereClause+=" ome.osiMenusByMenuId=(select id from OsiMenus om where om.menuName=:headerMenuName)";
						whereClause+=" ome.osiMenusByMenuId IN (select id from OsiMenus om  where om.menuName LIKE CONCAT('%',:headerMenuName,'%'))";
					}
				}

				if( subMenuName!=null ){
					if(whereClause.length()>0){
						whereClause+=" OR ome.osiMenusBySubMenuId=(select id from OsiMenus om1 where om1.menuName=:subMenuName)";
					}
					else{
						whereClause+=" ome.osiMenusBySubMenuId=(select id from OsiMenus om1 where om1.menuName=:subMenuName)";
					}
				}

				if( funcName!=null ){
					if(whereClause.length()>0){
						whereClause+=" OR  ome.osiFunctions=(select id from OsiFunctions ff where ff.funcName=:funcName)";
					}
					else{
						whereClause+="  ome.osiFunctions=(select id from OsiFunctions ff where ff.funcName=:funcName)";
					}
				}

				queryString.append(whereClause+")  ORDER BY updatedDate desc");
				System.out.println("QUERY=============================:"+queryString.toString());
				Query query=this.entityManager.createQuery(queryString.toString());
				query.setParameter("businessGroupId", businessGroupId);
				if( sequence!=null){
					query.setParameter("sequence", sequence);
				}
				if( prompt!=null && prompt !="" ){
					query.setParameter("prompt", prompt);
				}

				if( headerMenuName!=null ){
					query.setParameter("headerMenuName", headerMenuName);
				}

				if( subMenuName!=null ){
					query.setParameter("subMenuName", subMenuName);
				}

				if( funcName!=null ){
					query.setParameter("funcName", funcName);
				}
				
					osiMenuEntriesList=query.getResultList();
			}

		}
		catch (Exception e) {
			System.out.println("Excepton ===========:"+e.getMessage());
			// TODO: handle exception
		} 
		
		return osiMenuEntriesList;
	}	
		
}