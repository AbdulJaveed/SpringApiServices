package com.osi.urm.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.osi.urm.domain.OsiRptGroups;
import com.osi.urm.domain.OsiRptGrpRpts;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiRptGroupsRepositoryCustom;

public class OsiRptGroupsRepositoryImpl implements
		OsiRptGroupsRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Integer saveOrUpdateRptGroup(OsiRptGroups osiRptGroups) throws DataAccessException {
		List<Integer> rptGrpRptsIds = null;
		try {
				if(osiRptGroups.getRptGrpId()!=null){
					OsiRptGroups osiRptGroupsExist = this.entityManager.find(OsiRptGroups.class, osiRptGroups.getRptGrpId());
					osiRptGroups.setCreatedBy(osiRptGroupsExist.getCreatedBy());
					osiRptGroups.setCreationDate(osiRptGroupsExist.getCreationDate());
				}
				rptGrpRptsIds = new ArrayList<Integer>();
				for (OsiRptGrpRpts osiRptGrpRpts : osiRptGroups.getOsiRptGrpRpts()) {
					if(osiRptGrpRpts.getRptGrpRptsId()!=null){
						rptGrpRptsIds.add(osiRptGrpRpts.getRptGrpRptsId());
					}
				}
				if(rptGrpRptsIds!=null && rptGrpRptsIds.size()>0){
					String updateQuery = "DELETE FROM OsiRptGrpRpts WHERE rptGrpRptsId NOT IN (:rptGrpRptsId) AND osiRptGroups.rptGrpId=:rptGrpId";
					this.entityManager.createQuery(updateQuery)
					.setParameter("rptGrpRptsId", rptGrpRptsIds)
					.setParameter("rptGrpId", osiRptGroups.getRptGrpId())
					.executeUpdate();
				}
				osiRptGroups = this.entityManager.merge(osiRptGroups);
			} catch(Exception e) {
				e.printStackTrace();
				throw new DataAccessException("ERR_1000", e.getMessage());
			}
		return osiRptGroups.getRptGrpId();
	}

	@Override
	public Integer validateRptGroupById(Integer businessGroupId, String reptGrpName, Integer rptGrpId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "SELECT count(*) FROM OsiRptGroups t where t.businessGroupId = :businessGroupId and t.rptGrpName=:reptGrpName and t.rptGrpId!=:rptGrpId";
			Object obj = (Object)this.entityManager.createQuery(query)
					 		  .setParameter("rptGrpId", rptGrpId)
							  .setParameter("reptGrpName", reptGrpName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							  .getSingleResult();
			if(obj!=null)
				count = Integer.parseInt(obj.toString());
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public Integer validateRptGroup(Integer businessGroupId, String reptGrpName) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "SELECT count(*) FROM OsiRptGroups t where t.businessGroupId = :businessGroupId and t.rptGrpName=:reptGrpName";
			Object obj = (Object)this.entityManager.createQuery(query)
							  .setParameter("reptGrpName", reptGrpName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							  .getSingleResult();
			if(obj!=null)
				count = Integer.parseInt(obj.toString());
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}

	@Override
	public Integer isRptGrpInactivable(Integer rptGrpId, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) from OsiRptGroups rptg, OsiResponsibilities r, OsiRespUser ru where rptg.rptGrpId = r.reportGrpId and ru.osiResponsibilities.id=r.id and rptg.businessGroupId= :businessGroupId and rptg.rptGrpId=:rptGrpId";
			Object obj = (Object)this.entityManager.createQuery(query)
					 		  .setParameter("rptGrpId", rptGrpId)
							  .setParameter("businessGroupId", businessGroupId)
							  .getSingleResult();
			if(obj!=null)
				count = Integer.parseInt(obj.toString());
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiRptGroups> findAllRptGroups(Integer businessGroupId, String rptGrpName) throws DataAccessException {
		List<OsiRptGroups> osiRptGroupsList = null;
		try {
			if(rptGrpName==null){
				rptGrpName = "";
			}
			String query = "FROM OsiRptGroups WHERE businessGroupId = :businessGroupId and  upper(rptGrpName) like '%"+rptGrpName+"%' order by rptGrpName";
			osiRptGroupsList = (List<OsiRptGroups>)this.entityManager.createQuery(query)
							  .setParameter("businessGroupId", businessGroupId)
					          .getResultList();
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiRptGroupsList;
	}

	
}