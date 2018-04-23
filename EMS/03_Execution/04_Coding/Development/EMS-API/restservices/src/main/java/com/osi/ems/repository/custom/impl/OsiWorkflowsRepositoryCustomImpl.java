package com.osi.ems.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.osi.ems.repository.custom.OsiWorkflowsRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Component
public class OsiWorkflowsRepositoryCustomImpl implements OsiWorkflowsRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	private final Logger LOGGER = Logger.getLogger(OsiWorkflowsRepositoryCustomImpl.class);
	
	@Override
	public Integer getWorkFlow(String activityName, Integer orgId) throws DataAccessException {
		Integer wfsId = null;
		try {
			Query query = entityManager.createNativeQuery("select wfs_id from osi_work_flows where activity_internal_name=? and org_id=?");
			query.setParameter(1, activityName);
			query.setParameter(2, orgId);
			Object object = (Object)query.getSingleResult();
			wfsId = Integer.parseInt(object.toString());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} 
		return wfsId;
	}
	
	@Override
	public Integer verifyExistingWorkflow(Integer wfsId, Integer objectId, Integer orgId) throws DataAccessException{
		Integer count = null;
		try {
			Query query = entityManager.createNativeQuery("select count(*) from osi_wf_activities where wfs_id=? and object_id=? and process_flag=? and org_id=?");
			query.setParameter(1, wfsId);
			query.setParameter(2, objectId);
			query.setParameter(3, "N");
			query.setParameter(4, orgId);
			Object object = (Object)query.getSingleResult();
			count = Integer.parseInt(object.toString());
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} 
		return count;
	}
	
	@Override
	public Integer deleteExistingTerminationRecords(List<Integer> wfsId, Integer objectId, Integer orgId) throws DataAccessException{
		Integer count = null;
		try {
			String str = wfsId.get(0).toString()+","+ wfsId.get(1).toString();
			Query query = entityManager.createNativeQuery("delete from osi_wf_activities where wfs_id in("+str+") and object_id=? and process_flag=? and org_id=?");
			//query.setParameter(1, wfsId);
			query.setParameter(1, objectId);
			query.setParameter(2, "N");
			query.setParameter(3, orgId);
			count = query.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Exception : " + e.getMessage());			
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		} 
		return count;
	}
}


