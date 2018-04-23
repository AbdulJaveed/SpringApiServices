package com.osi.urm.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.osi.urm.config.ErrorConfig;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiOperationsRepositoryCustom;

public class OsiOperationsRepositoryImpl implements OsiOperationsRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private ErrorConfig errorConfig;

	@Override
	public List getUserExlOperations(Integer userId, Integer functionId, Integer businessGroupId)  throws DataAccessException{
		List list= null;
		try {
			//String query = "select op.id, op.name, fo.opUrl from OsiOperations op, OsiFuncOperations fo where fo.osiOperations.id= op.id and fo.businessGroupId= op.businessGroupId and fo.osiFunctions.id=:functionId and fo.businessGroupId=:businessGroupId and fo.osiOperations.id not in (select ue.osiOperations.id from OsiUserOperationExcl ue where ue.osiFunctions.id=:functionId and ue.osiUser.id=:userId and ue.businessGroupId=:businessGroupId)";
			String query = "select op.id, op.name, fo.op_url from osi_operations op, osi_func_operations fo where fo.op_id= op.id and fo.func_id=:functionId and fo.op_id not in (select ue.op_id from osi_user_operation_excl ue where ue.func_id=:functionId and ue.user_id=:userId)";
			list= this.entityManager.createNativeQuery(query)
							  .setParameter("userId", userId)
							  .setParameter("functionId", functionId)
					          .getResultList();
			
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002" , e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1005", e.getMessage()); 
			//e.printStackTrace();
		}

		return list;
	}

	@Override
	public void deleteByUserId(Integer userId, Integer functionId, Integer businessGroupId) throws DataAccessException{
		String query = "delete from OsiUserOperationExcl ue where ue.osiFunctions.id=:functionId and ue.osiUser.id=:userId and ue.businessGroupId=:businessGroupId";
		int i = this.entityManager.createQuery(query)
		  .setParameter("userId", userId)
		  .setParameter("functionId", functionId)
		  .setParameter("businessGroupId", businessGroupId)
		  .executeUpdate();
	}
	
	@Override
	public Integer deleteByUserId(Integer userId) throws DataAccessException {
		String query = "delete from osi_user_operation_excl where func_id NOT IN "
				+ " (select func_id from osi_menu_entries e where menu_id in ( "
				+ " select menu_id from osi_menu_resp where resp_id in (select resp.ID from osi_responsibilities resp "
				+ " where resp.ID in (select ur.RESP_ID from osi_resp_user ur where ur.USER_ID = ?))) "
				+ " and not exists (select 1 from osi_user_func_excl where func_id = e.func_id "
				+ " and user_id=?) and func_id is not null) and user_id = ?";
		int i = this.entityManager.createNativeQuery(query)
				.setParameter(1, userId)
				.setParameter(2, userId)
				.setParameter(3, userId)
				.executeUpdate();
		return i;
	}
	
	@Override
	public Integer deleteFuncExclByUserId(Integer userId) throws DataAccessException {
		String query = "delete from osi_user_func_excl where func_id not IN ( "
				+ " select func_id from osi_menu_entries e where menu_id in ( "
				+ " select menu_id from osi_menu_resp where resp_id in (select resp.ID from osi_responsibilities resp "
				+ " where resp.ID in (select ur.RESP_ID from osi_resp_user ur where ur.USER_ID = ?))))"
				+ " and user_id = ?";
		int i = this.entityManager.createNativeQuery(query)
				.setParameter(1, userId)
				.setParameter(2, userId)
				.executeUpdate();
		return i;
	}
}
