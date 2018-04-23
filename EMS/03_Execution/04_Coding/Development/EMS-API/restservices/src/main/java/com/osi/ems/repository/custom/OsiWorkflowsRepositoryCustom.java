package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.urm.exception.DataAccessException;

public interface OsiWorkflowsRepositoryCustom {

	Integer getWorkFlow(String activityName, Integer orgId) throws DataAccessException;
	public Integer verifyExistingWorkflow(Integer wfsId, Integer objectId, Integer orgId) throws DataAccessException;
	public Integer deleteExistingTerminationRecords(List<Integer> wfsId, Integer objectId, Integer orgId) throws DataAccessException;
}
