package com.osi.urm.repository.custom.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.osi.urm.domain.OsiMenus;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiMenuRepositoryCustom;

public class OsiMenuRepositoryImpl implements OsiMenuRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Integer updateMenu(OsiMenus osiMenus)  throws DataAccessException{
		Integer count = 0;
		try {
			String query = "update OsiMenus m set m.menuName = :menuName, m.description = :description, m.active= :active ,m.updatedBy = :updatedBy, m.updatedDate = :updatedDate, m.reportGrpId = :reportGrpId where m.id = :id";
			 count = this.entityManager.createQuery(query)
							  .setParameter("menuName", osiMenus.getMenuName())
							  .setParameter("description", osiMenus.getDescription())
							  .setParameter("updatedBy", osiMenus.getUpdatedBy())
							  .setParameter("active", osiMenus.getActive())
							  .setParameter("updatedDate", osiMenus.getUpdatedDate())
							  .setParameter("id", osiMenus.getId())
							  .setParameter("reportGrpId", osiMenus.getReportGrpId())
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1000",  null); 
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode() , e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1005", e.getMessage()); 
			//e.printStackTrace();
		}

		return count;
	}
	
	@Override
	public Integer findMenusByNameId(Integer id, String menuName, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiMenus WHERE businessGroupId = :businessGroupId and upper(menuName) =:menuName and id !=:id";
			List list = this.entityManager.createQuery(query)
							  .setParameter("id", id)
							  .setParameter("menuName", menuName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							  .getResultList();
			if(list!=null && list.get(0)!=null && Integer.parseInt(list.get(0).toString())>0)
				count = 1;
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public Integer findMenusByName(String menuName, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiMenus WHERE businessGroupId = :businessGroupId and upper(menuName) =:menuName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("menuName", menuName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							  .getResultList();
			if(list!=null && list.get(0)!=null && Integer.parseInt(list.get(0).toString())>0)
				count = 1;
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}

	@Override
	public Integer deleteMenu(Integer id, Integer businessGroupId, Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteMenus(List<Integer> listIds, Integer businessGroupId, Integer userId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiMenus set active = 0, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and Id IN (:listIds)";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", userId)
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("listIds", listIds)
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1012", null);
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getSystemMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}


	
		
}