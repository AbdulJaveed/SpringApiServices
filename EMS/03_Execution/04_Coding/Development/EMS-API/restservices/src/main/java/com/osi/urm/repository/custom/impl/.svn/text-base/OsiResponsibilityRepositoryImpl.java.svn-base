package com.osi.urm.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.osi.urm.domain.OsiMenuEntries;
import com.osi.urm.domain.OsiMenuResp;
import com.osi.urm.domain.OsiResponsibilities;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiResponsibilityRepositoryCustom;

public class OsiResponsibilityRepositoryImpl implements OsiResponsibilityRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public OsiResponsibilities saveResponsibility(OsiResponsibilities osiResponsibilities) throws DataAccessException {
		try {
			osiResponsibilities = this.entityManager.merge(osiResponsibilities);
			Object[] object  = (Object[])this.entityManager.createQuery("SELECT COUNT(id), (SELECT MAX(seq)+1 FROM 	OsiMenuEntries WHERE osiMenusBySubMenuId.id IS NOT NULL AND osiMenusByMenuId.id IS NULL AND osiFunctions.id IS NULL) FROM OsiMenuEntries WHERE  osiMenusByMenuId.id IS NULL AND osiFunctions.id IS NULL AND businessGroupId=:businessGroupId")
//			 				  .setParameter("subMenuId", osiResponsibilities.getOsiMenus().getId())
			                  .setParameter("businessGroupId", osiResponsibilities.getBusinessGroupId())
			 				  .getSingleResult();
			if(object[0]!=null && Integer.parseInt(object[0].toString())==0){
				OsiMenuEntries osiMenuEntries = new OsiMenuEntries();
				osiMenuEntries.setActive(1);
				osiMenuEntries.setBusinessGroupId(osiResponsibilities.getBusinessGroupId());
				osiMenuEntries.setPrompt(osiResponsibilities.getRespName());
				osiMenuEntries.setSeq(Integer.parseInt(object[1].toString()));
//				osiMenuEntries.setOsiMenusBySubMenuId(osiResponsibilities.getOsiMenus());
				this.entityManager.merge(osiMenuEntries);
			}
		}catch (Exception e) {
			throw new DataAccessException("ERR_1005", e.getMessage()); 
			//e.printStackTrace();
		}

		return osiResponsibilities;
	}

	@Override
	public OsiResponsibilities updateResponsbility(OsiResponsibilities osiResponsibilities) throws DataAccessException {
		
		//OsiResponsibilities OsiResponsibilities = new OsiResponsibilities();
	// TODO Auto-generated method stub
	try {
	String userUpdateQuery = "update OsiResponsibilities set id=:id, respName=:respName, description=:description, startDate=:startDate, endDate=:endDate,active=:active, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and id =:id";
	String userOsiMenuRespDeleteQuery = "delete from OsiMenuResp where osiResponsibilities.id=:id";
	this.entityManager.createQuery(userOsiMenuRespDeleteQuery).setParameter("id", osiResponsibilities.getId()).executeUpdate();
	this.entityManager.createQuery(userUpdateQuery)
	 .setParameter("id",  osiResponsibilities.getId())
	 .setParameter("respName", osiResponsibilities.getRespName()) 
	 .setParameter("description", osiResponsibilities.getDescription()) 
	 .setParameter("startDate", osiResponsibilities.getStartDate()) 
	 .setParameter("endDate", osiResponsibilities.getEndDate())
	 .setParameter("active", osiResponsibilities.getActive()) 
	 .setParameter("updatedBy", osiResponsibilities.getUpdatedBy()) 
	 .setParameter("updatedDate", osiResponsibilities.getUpdatedDate()) 
	 .setParameter("businessGroupId", osiResponsibilities.getBusinessGroupId()) 
	 .executeUpdate();
	 
	for (OsiMenuResp osiMenuResp : osiResponsibilities.getOsiMenuResp()) {
	this.entityManager.merge(osiMenuResp);
	}
	}catch (Exception e) {
	throw new DataAccessException("ERR_1000", e.getMessage()); 
	}

	return osiResponsibilities;
	} 
	}